package cloud.ptl.boardgamecollector.activities.artist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import cloud.ptl.boardgamecollector.R;
import cloud.ptl.boardgamecollector.db.dao.ArtistDAO;
import cloud.ptl.boardgamecollector.db.entity.Artist;
import cloud.ptl.boardgamecollector.io.db.ArtistAddAsyncTask;
import cloud.ptl.boardgamecollector.io.db.ArtistDeleteAsyncTask;
import cloud.ptl.boardgamecollector.io.db.ArtistFetchAsyncTask;
import lombok.SneakyThrows;

public class ArtistActivity extends AppCompatActivity {

    private EditText artistName;
    private Button add;
    private ListView listView;

    private List<Artist> artists;
    private List<String> artistsString;
    private ArrayAdapter<String> adapter;
    private ArtistDAO artistDAO;

    @SneakyThrows
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);
        this.listView = this.findViewById(R.id.artist_list);
        this.add = this.findViewById(R.id.artist_add);
        this.artistName = this.findViewById(R.id.artist_name);
        this.artists = new ArtistFetchAsyncTask().execute().get();
        this.artistsString = this.artists.stream().map(el -> el.name + " " + el.surname).collect(Collectors.toList());
        this.adapter = new ArrayAdapter<>(
                this,
                R.layout.listitem,
                artistsString
        );
        this.listView.setAdapter(this.adapter);
        this.listView.setOnItemClickListener((parent, view, position, id) -> {
            try {
                new ArtistDeleteAsyncTask().execute(
                        this.artists.get(position)
                ).get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.adapter.remove(
                    this.artistsString.get(position)
            );
            this.artists.remove(position);
        });
        this.add.setOnClickListener(v -> {
            Artist artist = new Artist();
            EditText editText = this.findViewById(R.id.artist_name);
            artist.name = editText.getText().toString().split(" ")[0];
            artist.surname = editText.getText().toString().split(" ")[1];
            try {
                artist.artistId = new ArtistAddAsyncTask().execute(artist).get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            artists.add(artist);
            artistsString.add(editText.getText().toString());
            editText.setText("");
        });
    }
}