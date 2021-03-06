package cloud.ptl.boardgamecollector.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import cloud.ptl.boardgamecollector.MainActivity;
import cloud.ptl.boardgamecollector.R;
import cloud.ptl.boardgamecollector.db.entity.Artist;
import cloud.ptl.boardgamecollector.db.entity.Author;
import cloud.ptl.boardgamecollector.db.entity.Game;
import cloud.ptl.boardgamecollector.db.entity.Location;
import cloud.ptl.boardgamecollector.io.db.ArtistAddAsyncTask;
import cloud.ptl.boardgamecollector.io.db.ArtistFetchAsyncTask;
import cloud.ptl.boardgamecollector.io.db.ArtistFetchByIdAsyncTask;
import cloud.ptl.boardgamecollector.io.db.AuthorAddAsyncTask;
import cloud.ptl.boardgamecollector.io.db.AuthorFetchAsyncTask;
import cloud.ptl.boardgamecollector.io.db.AuthorFetchByIdAsyncTask;
import cloud.ptl.boardgamecollector.io.db.GameAddAsyncTask;
import cloud.ptl.boardgamecollector.io.db.GameFetchAsyncTask;
import cloud.ptl.boardgamecollector.io.db.GameFetchByIdAsyncTask;
import cloud.ptl.boardgamecollector.io.db.GameUpdateAsyncTask;
import cloud.ptl.boardgamecollector.io.db.LocationFetchAsyncTask;
import cloud.ptl.boardgamecollector.io.db.LocationFetchByIdAsyncTask;
import cloud.ptl.boardgamecollector.io.dto.GameDetailsDTO;
import cloud.ptl.boardgamecollector.io.network.GameDetailsFetchAsyncTask;
import lombok.SneakyThrows;

public class GameEditActivity extends AppCompatActivity {

    private EditText gameTitle;
    private EditText orginalTitle;
    private EditText prodDate;
    private EditText orderDate;
    private EditText additionDate;
    private EditText description;
    private EditText buyPrice;
    private EditText msrp;
    private EditText ean;
    private EditText manufacturerCode;
    private EditText comment;
    private Switch addon;
    private Switch standalone;
    private Spinner addonTo;
    private Spinner artist;
    private Spinner author;
    private Spinner location;
    private Button confirm;

    private TextView addonToTextView;

    private List<Artist> artists;
    private List<String> artistsSring;
    private ArrayAdapter<String> artistsAdapter;
    private List<Author> authors;
    private List<String> authorsString;
    private ArrayAdapter<String> authorsAdapter;
    private List<Location> locations;
    private List<String> locationsString;
    private ArrayAdapter<String> locationsAdapter;

    private ArrayAdapter<String> addonAdapter;

    private List<Game> games;

    private Intent intent;
    private Long id;

    private GameDetailsDTO gameDetailsDTO;

    private String mode;

    @SneakyThrows
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_edit);

        this.gameTitle = this.findViewById(R.id.edit_game_title);
        this.orginalTitle = this.findViewById(R.id.edit_orginal_title);
        this.prodDate = this.findViewById(R.id.edit_prod_date);
        this.orderDate = this.findViewById(R.id.edit_order_date);
        this.additionDate = this.findViewById(R.id.edit_addition_date);
        this.buyPrice = this.findViewById(R.id.edit_buy_price);
        this.msrp = this.findViewById(R.id.edit_msrp);
        this.ean = this.findViewById(R.id.edit_ean);
        this.manufacturerCode = this.findViewById(R.id.edit_manufacturer_code);
        this.comment = this.findViewById(R.id.edit_comment);
        this.addon = this.findViewById(R.id.edit_addon);
        this.standalone = this.findViewById(R.id.edit_standalone);
        this.addonTo = this.findViewById(R.id.edit_addon_to);
        this.author = this.findViewById(R.id.edit_author);
        this.artist = this.findViewById(R.id.edit_artist);
        this.description = this.findViewById(R.id.edit_description);
        this.confirm = this.findViewById(R.id.edit_confirm);
        this.location = this.findViewById(R.id.edit_locaalization);

        this.addonToTextView = this.findViewById(R.id.addonTo_textView);

        this.locations = new LocationFetchAsyncTask().execute().get();
        this.artists = new ArtistFetchAsyncTask().execute().get();
        this.authors = new AuthorFetchAsyncTask().execute().get();
        this.games = new GameFetchAsyncTask().execute().get();

        if (this.games == null) this.games = new ArrayList<>();

        this.locationsString = this.locations.stream().map(el -> el.name).collect(Collectors.toList());
        this.artistsSring = this.artists.stream().map(el -> el.name + " " + el.surname).collect(Collectors.toList());
        this.authorsString = this.authors.stream().map(el -> el.name + " " + el.surname).collect(Collectors.toList());

        this.locationsAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                this.locationsString
        );
        this.locationsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.artistsAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                this.artistsSring
        );
        this.artistsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.authorsAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                this.authorsString
        );
        this.authorsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.addonAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                this.games.stream().map(el -> el.title).collect(Collectors.toList())
        );
        this.addonAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.addonTo.setAdapter(this.addonAdapter);

        this.intent = this.getIntent();
        this.id = this.intent.getLongExtra("id", UUID.randomUUID().hashCode());
        this.mode = this.intent.getStringExtra("mode");
        if (this.id > 0 && this.mode.equals("create")) {
            this.gameDetailsDTO =
                    new GameDetailsFetchAsyncTask().execute(this.id.toString()).get();
        }
        else if(this.id > 0) {
            this.gameDetailsDTO = new GameDetailsDTO();
            Game game = new GameFetchByIdAsyncTask().execute(this.id).get();
            Author author = new AuthorFetchByIdAsyncTask().execute(game.authorId).get();
            Artist artist = new ArtistFetchByIdAsyncTask().execute(game.artistId).get();
            Location location = new LocationFetchByIdAsyncTask().execute(game.locationId).get();
            this.gameDetailsDTO.setGame(game);
            this.gameDetailsDTO.setAuthors(Collections.singletonList(author));
            this.gameDetailsDTO.setArtists(Collections.singletonList(artist));
            this.gameDetailsDTO.setLocation(location);
            this.confirm.setText("Aktualizuj");
        }

        this.gameTitle.setText(this.gameDetailsDTO.getGame().title);
        this.orginalTitle.setText(this.gameDetailsDTO.getGame().orginalTitle);
        this.prodDate.setText(this.gameDetailsDTO.getGame().productionDate);
        this.addon.setChecked(this.gameDetailsDTO.getGame().isAddon);
        this.standalone.setChecked(this.gameDetailsDTO.getGame().isStandalone);
        this.description.setText(this.gameDetailsDTO.getGame().description);

        this.author.setAdapter(this.authorsAdapter);
        this.artist.setAdapter(this.artistsAdapter);
        this.location.setAdapter(this.locationsAdapter);

        if (this.gameDetailsDTO.getGame().buyPrice != null)
            this.buyPrice.setText(
                    this.gameDetailsDTO.getGame().buyPrice
            );

        if (this.gameDetailsDTO.getGame().MSRP != null)
            this.msrp.setText(
                    this.gameDetailsDTO.getGame().MSRP
            );

        if (this.gameDetailsDTO.getGame().productionCode != null)
            this.manufacturerCode.setText(
                    this.gameDetailsDTO.getGame().productionCode
            );

        if (this.gameDetailsDTO.getGame().comment != null)
            this.comment.setText(
                    this.gameDetailsDTO.getGame().comment
            );

        if (this.gameDetailsDTO.getGame().orderDate != null)
            this.orderDate.setText(
                    this.gameDetailsDTO.getGame().orderDate
            );

        if (this.gameDetailsDTO.getGame().toCollectionAddDate != null)
            this.additionDate.setText(
                    this.gameDetailsDTO.getGame().toCollectionAddDate
            );

        if (this.gameDetailsDTO.getGame().EAN != null)
            this.ean.setText(
                    this.gameDetailsDTO.getGame().EAN
            );

        if (this.authors.stream().noneMatch(el ->
                el.name.equals(this.gameDetailsDTO.getAuthors().get(0).name) &&
                        el.surname.equals(this.gameDetailsDTO.getAuthors().get(0).surname))
        ) {
            this.authorsAdapter.add(
                    this.gameDetailsDTO.getAuthors().get(0).name + " " +
                    this.gameDetailsDTO.getAuthors().get(0).surname
            );
            this.author.setSelection(this.authorsString.size() - 1);
        }
        else {
            this.author.setSelection(
                    this.authors.indexOf(this.gameDetailsDTO.getAuthors().get(0))
            );
        }

        if (this.artists.stream().noneMatch(el ->
                el.name.equals(this.gameDetailsDTO.getArtists().get(0).name) &&
                el.surname.equals(this.gameDetailsDTO.getArtists().get(0).surname))
        ) {
            this.artistsAdapter.add(
                    this.gameDetailsDTO.getArtists().get(0).name + " " +
                            this.gameDetailsDTO.getArtists().get(0).surname
            );
            this.artist.setSelection(this.artistsSring.size() - 1);
        }
        else {
            this.artist.setSelection(
                    this.artists.indexOf(this.gameDetailsDTO.getArtists().get(0))
            );
        }

        if (this.addon.isChecked()) {
            this.addonTo.setVisibility(View.VISIBLE);
            this.addonToTextView.setVisibility(View.VISIBLE);
        }
        else {
            this.addonTo.setVisibility(View.INVISIBLE);
            this.addonToTextView.setVisibility(View.INVISIBLE);
        }

        this.addon.setOnClickListener(v -> {
            if (this.addon.isChecked()) {
                this.addonTo.setVisibility(View.VISIBLE);
                this.addonToTextView.setVisibility(View.VISIBLE);
            }
            else {
                this.addonTo.setVisibility(View.INVISIBLE);
                this.addonToTextView.setVisibility(View.INVISIBLE);
            }
        });

        this.confirm.setOnClickListener(v -> {
            if (this.artists.stream()
                    .noneMatch(el -> (el.name + " " + el.surname)
                            .equals(this.artistsSring.get(this.artist.getSelectedItemPosition()))
                    )
            ) {
                try {
                    Artist nArtist = new Artist();
                    nArtist.name = this.artistsSring.get(this.artist.getSelectedItemPosition()).split(" ")[0];
                    nArtist.surname = this.artistsSring.get(this.artist.getSelectedItemPosition()).split(" ")[1];
                    nArtist.artistId =
                            new ArtistAddAsyncTask().execute(
                                    nArtist
                            ).get();
                    this.artists.add(nArtist);
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (this.authors.stream()
                    .noneMatch(el -> (el.name + " " + el.surname)
                            .equals(this.authorsString.get(this.author.getSelectedItemPosition()))
                    )
            ) {
                try {
                    Author nAuthor = new Author();
                    nAuthor.name = this.authorsString.get(this.author.getSelectedItemPosition()).split(" ")[0];
                    nAuthor.surname = this.authorsString.get(this.author.getSelectedItemPosition()).split(" ")[1];
                    nAuthor.authorId =
                            new AuthorAddAsyncTask().execute(
                                    nAuthor
                            ).get();
                    this.authors.add(nAuthor);
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Game game = new Game();
            game.title = this.gameTitle.getText().toString();
            game.orginalTitle = this.orginalTitle.getText().toString();
            game.description = this.description.getText().toString();
            game.isStandalone = this.standalone.isChecked();
            game.isAddon = this.addon.isChecked();
            game.productionDate = this.prodDate.getText().toString();
            game.BGGIdentifier = Math.toIntExact(id);
            game.buyPrice = this.buyPrice.getText().toString();
            game.comment = this.comment.getText().toString();
            game.EAN = this.ean.getText().toString();
            game.MSRP = this.msrp.getText().toString();
            game.toCollectionAddDate = this.additionDate.getText().toString();
            game.orderDate = this.orderDate.getText().toString();
            game.description = this.description.getText().toString();
            game.productionCode = this.manufacturerCode.getText().toString();
            game.imageURL = this.gameDetailsDTO.getGame().imageURL;
            game.authorId = this.authors.get(
                    this.author.getSelectedItemPosition()
            ).authorId;
            game.artistId = this.artists.get(
                    this.artist.getSelectedItemPosition()
            ).artistId;
            game.locationId = this.locations.get(
                    this.location.getSelectedItemPosition()
            ).locationId;
            if (this.addonTo.getSelectedItemPosition() == -1)
                game.addonToId = -1L;
            if (!this.addon.isChecked())
                game.addonToId = -1L;
            else
                game.addonToId = this.games.get(
                        this.addonTo.getSelectedItemPosition()
                ).gameId;

            if (this.mode.equals("create")){
                if (this.games.stream().anyMatch(el -> el.title.equals(game.title))){
                    Snackbar.make(
                            this.findViewById(R.id.edit_confirm),
                            "Gra ju?? istnieje, nie dodano",
                            Snackbar.LENGTH_SHORT
                    ).show();
                }
                else {
                    try {
                        new GameAddAsyncTask().execute(game).get();
                    } catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            else {
                try {
                    game.gameId = this.gameDetailsDTO.getGame().gameId;
                    new GameUpdateAsyncTask().execute(game).get();
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
        });
    }
}