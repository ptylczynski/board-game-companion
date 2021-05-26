package cloud.ptl.boardgamecollector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.List;

import cloud.ptl.boardgamecollector.activities.GameEditActivity;
import cloud.ptl.boardgamecollector.activities.artist.ArtistActivity;
import cloud.ptl.boardgamecollector.activities.author.AuthorActivity;
import cloud.ptl.boardgamecollector.activities.gameadd.GameAddActivity;
import cloud.ptl.boardgamecollector.activities.location.LocationActivity;
import cloud.ptl.boardgamecollector.activities.main.GameAdapter;
import cloud.ptl.boardgamecollector.db.DB;
import cloud.ptl.boardgamecollector.db.dao.GameDAO;
import cloud.ptl.boardgamecollector.db.entity.Game;
import cloud.ptl.boardgamecollector.io.db.GameFetchAsyncTask;
import lombok.SneakyThrows;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButton;
    private FloatingActionButton locationAddFly;
    private FloatingActionButton authorFloat;
    private FloatingActionButton artistFloat;
    private ListView gameList;

    private List<Game> games;
    private GameAdapter adapter;

    @SneakyThrows
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DB.init(
                this.getApplicationContext()
        );

        this.gameList = this.findViewById(R.id.game_list);
        this.games = new GameFetchAsyncTask().execute().get();
        this.adapter = new GameAdapter(
                this,
                R.layout.activity_game_entry_view,
                this.games
        );
        this.gameList.setAdapter(this.adapter);

        this.floatingActionButton = this.findViewById(R.id.add_game_button);
        this.locationAddFly = this.findViewById(R.id.location_add_fly);
        this.authorFloat = this.findViewById(R.id.author_float);
        this.artistFloat = this.findViewById(R.id.artist_float);

        this.floatingActionButton.setOnClickListener(v -> {
            Intent runNew = new Intent(MainActivity.this, GameAddActivity.class);
            MainActivity.this.startActivity(runNew);
        });

        this.locationAddFly.setOnClickListener(v -> {
            Intent runNew = new Intent(MainActivity.this, LocationActivity.class);
            MainActivity.this.startActivity(runNew);
        });

        this.authorFloat.setOnClickListener(v -> {
            Intent runNew = new Intent(MainActivity.this, AuthorActivity.class);
            MainActivity.this.startActivity(runNew);
        });

        this.artistFloat.setOnClickListener(v -> {
            Intent runNew = new Intent(MainActivity.this, ArtistActivity.class);
            MainActivity.this.startActivity(runNew);
        });


    }

    @SneakyThrows
    @Override
    protected void onResume() {
        super.onResume();
        if (this.adapter != null){
            this.adapter.clear();
            this.adapter.addAll(
                    new GameFetchAsyncTask().execute().get()
            );
        }

    }
}