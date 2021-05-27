package cloud.ptl.boardgamecollector.activities.copycollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import cloud.ptl.boardgamecollector.R;
import cloud.ptl.boardgamecollector.activities.GameEditActivity;
import cloud.ptl.boardgamecollector.io.dto.UserGamesDTO;
import cloud.ptl.boardgamecollector.io.network.GameDetailsFetchAsyncTask;
import cloud.ptl.boardgamecollector.io.network.UserGamesFetchAsyncTask;

public class CopyActivity extends AppCompatActivity {

    private EditText name;
    private Button confirm;
    private ListView listView;

    private ArrayAdapter<String> adapter;
    private List<Long> ids;
    private List<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copy);

        this.confirm = this.findViewById(R.id.copy_confirm);
        this.name = this.findViewById(R.id.copy_name);
        this.listView = this.findViewById(R.id.copy_list);

        this.ids = new ArrayList<>();
        this.ids = new ArrayList<>();
        this.names = new ArrayList<>();

        this.adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                this.names
        );
        this.adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.listView.setAdapter(this.adapter);

        this.confirm.setOnClickListener(v -> {
            String username = this.name.getText().toString();
            try {
                UserGamesDTO newGames = new UserGamesFetchAsyncTask().execute(username).get();
                this.ids = newGames.getGamesID();
                this.adapter.clear();
                this.adapter.addAll(newGames.getNames());
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        this.listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, GameEditActivity.class);
            intent.putExtra("id", this.ids.get(position));
            intent.putExtra("mode", "create");
            this.startActivity(intent);
        });
    }
}