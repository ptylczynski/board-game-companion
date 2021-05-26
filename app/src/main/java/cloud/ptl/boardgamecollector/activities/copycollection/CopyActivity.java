package cloud.ptl.boardgamecollector.activities.copycollection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import cloud.ptl.boardgamecollector.R;
import cloud.ptl.boardgamecollector.io.dto.UserGamesDTO;
import cloud.ptl.boardgamecollector.io.network.UserGamesFetchAsyncTask;

public class CopyActivity extends AppCompatActivity {

    private EditText name;
    private Button confirm;
    private ListView listView;

    private ArrayAdapter<String> adapter;
    private UserGamesDTO userGamesDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copy);

        this.confirm = this.findViewById(R.id.copy_confirm);
        this.name = this.findViewById(R.id.copy_name);
        this.listView = this.findViewById(R.id.copy_list);

        this.userGamesDTO = new UserGamesDTO();
        this.userGamesDTO.setGames(new ArrayList<>());

        this.adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                this.userGamesDTO.getGames().stream().map(el -> el.getGame().title).collect(Collectors.toList())
        );
        this.adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.listView.setAdapter(this.adapter);

        this.confirm.setOnClickListener(v -> {
            String username = this.name.getText().toString();
            try {
                UserGamesDTO newGames = new UserGamesFetchAsyncTask().execute(username).get();
                this.adapter.clear();
                this.adapter.addAll(
                        newGames.getGames().stream().map(el -> el.getGame().title).collect(Collectors.toList())
                );
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}