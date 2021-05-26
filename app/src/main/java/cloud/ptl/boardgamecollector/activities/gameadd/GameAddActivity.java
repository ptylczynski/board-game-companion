package cloud.ptl.boardgamecollector.activities.gameadd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import cloud.ptl.boardgamecollector.R;
import cloud.ptl.boardgamecollector.activities.GameEditActivity;
import cloud.ptl.boardgamecollector.io.dto.GameSearchResult;
import cloud.ptl.boardgamecollector.io.network.GameSearchAsyncTask;
import lombok.SneakyThrows;

public class GameAddActivity extends AppCompatActivity {

    private ListView listView;
    private List<String> titles;
    private List<Integer> ids;
    private Button searchButton;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_add);
        this.titles = new ArrayList<>();
        this.searchButton = this.findViewById(R.id.button_search);
        this.listView = this.findViewById(R.id.results_view);
        this.adapter = new ArrayAdapter<String>(
                this,
                R.layout.listitem,
                this.titles
        );
        this.listView.setAdapter(this.adapter);
        this.listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent run = new Intent(this, GameEditActivity.class);
            run.putExtra("id", this.ids.get(position));
            run.putExtra("mode", "create");
            this.startActivity(run);
        });
        this.searchButton.setOnClickListener(v -> {
            String query = ((TextView) findViewById(R.id.query_game_name)).getText().toString();
            try {
                GameSearchResult gsr = new GameSearchAsyncTask()
                        .execute(query).get();
                ids = gsr.getIds();
                adapter.clear();
                adapter.addAll(gsr.getGameNames());
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}