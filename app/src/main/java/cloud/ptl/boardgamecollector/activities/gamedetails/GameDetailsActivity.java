package cloud.ptl.boardgamecollector.activities.gamedetails;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import cloud.ptl.boardgamecollector.R;
import cloud.ptl.boardgamecollector.activities.GameEditActivity;
import cloud.ptl.boardgamecollector.db.entity.Artist;
import cloud.ptl.boardgamecollector.db.entity.Author;
import cloud.ptl.boardgamecollector.db.entity.Game;
import cloud.ptl.boardgamecollector.db.entity.Location;
import cloud.ptl.boardgamecollector.io.db.ArtistFetchByIdAsyncTask;
import cloud.ptl.boardgamecollector.io.db.AuthorFetchByIdAsyncTask;
import cloud.ptl.boardgamecollector.io.db.GameFetchByIdAsyncTask;
import cloud.ptl.boardgamecollector.io.db.LocationFetchByIdAsyncTask;
import lombok.SneakyThrows;

public class GameDetailsActivity extends AppCompatActivity {

    private TextView title;
    private TextView orginal;
    private TextView description;
    private TextView standalone;
    private TextView addon;
    private TextView buyPrice;
    private TextView comment;
    private TextView ean;
    private TextView msrp;
    private TextView toCollectionAdd;
    private TextView orderDate;
    private TextView author;
    private TextView artist;
    private TextView localization;
    private TextView productionCode;

    private FloatingActionButton edit;

    private Long id;
    private Game game;
    private Location location;
    private Artist artistEntity;
    private Author authorEntity;

    @SuppressLint("SetTextI18n")
    @SneakyThrows
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);

        this.title = this.findViewById(R.id.description_title);
        this.orginal = this.findViewById(R.id.description_orginal);
        this.description = this.findViewById(R.id.description_description);
        this.standalone = this.findViewById(R.id.description_standalone);
        this.addon = this.findViewById(R.id.description_addon);
        this.buyPrice = this.findViewById(R.id.description_buy_price);
        this.comment = this.findViewById(R.id.description_comment);
        this.ean = this.findViewById(R.id.description_ean);
        this.msrp = this.findViewById(R.id.description_msrp);
        this.toCollectionAdd = this.findViewById(R.id.description_to_collection_add);
        this.orderDate = this.findViewById(R.id.description_order_date);
        this.author = this.findViewById(R.id.description_author);
        this.artist = this.findViewById(R.id.description_artist);
        this.localization = this.findViewById(R.id.description_localization);
        this.productionCode = this.findViewById(R.id.description_productionCode);

        this.edit = this.findViewById(R.id.description_edit);

        Intent intent = this.getIntent();
        this.id = intent.getLongExtra("id", -1);

        this.game = new GameFetchByIdAsyncTask().execute(this.id).get();
        this.location = new LocationFetchByIdAsyncTask().execute(this.game.locationId).get();
        this.authorEntity = new AuthorFetchByIdAsyncTask().execute(this.game.authorId).get();
        this.artistEntity = new ArtistFetchByIdAsyncTask().execute(this.game.artistId).get();

        this.title.setText(this.game.title);
        this.orginal.setText(this.game.orginalTitle);
        this.description.setText(this.game.description);
        this.standalone.setText(
                this.game.isStandalone ? "Tak" : "Nie"
        );
        this.addon.setText(
                this.game.isAddon ? "Tak" : "Nie"
        );
        this.buyPrice.setText(this.game.buyPrice);
        this.comment.setText(this.game.comment);
        this.ean.setText(this.game.EAN);
        this.msrp.setText(this.game.MSRP);
        this.toCollectionAdd.setText(this.game.toCollectionAddDate);
        this.orderDate.setText(this.game.orderDate);
        this.author.setText(
                this.authorEntity.name + " " + this.authorEntity.surname
        );
        this.artist.setText(
                this.artistEntity.name + " " + this.authorEntity.surname
        );
        this.localization.setText(this.location.name);
        this.productionCode.setText(this.game.productionCode);

        this.edit.setOnClickListener(v -> {
            Intent intent1 = new Intent(GameDetailsActivity.this, GameEditActivity.class);
            intent1.putExtra("mode", "edit");
            intent1.putExtra("id", id);
            startActivity(intent1);
        });
    }
}