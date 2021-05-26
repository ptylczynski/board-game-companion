package cloud.ptl.boardgamecollector.activities.location;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import cloud.ptl.boardgamecollector.R;
import cloud.ptl.boardgamecollector.db.DB;
import cloud.ptl.boardgamecollector.db.dao.LocationDAO;
import cloud.ptl.boardgamecollector.db.entity.Location;
import cloud.ptl.boardgamecollector.io.db.LocationAddAsyncTask;
import cloud.ptl.boardgamecollector.io.db.LocationDeleteAsyncTask;
import cloud.ptl.boardgamecollector.io.db.LocationFetchAsyncTask;
import lombok.SneakyThrows;

public class LocationActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> locationsString;
    private List<Location> locations;
    private final LocationDAO locationDAO = DB.db.locationDAO();
    private Button add;

    @SneakyThrows
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        this.locations = new LocationFetchAsyncTask().execute().get();
        this.locationsString = this.locations.stream().map(el -> el.name).collect(Collectors.toList());
        this.listView = this.findViewById(R.id.author_list);
        this.adapter = new ArrayAdapter<String>(this, R.layout.listitem, locationsString);
        this.listView.setAdapter(this.adapter);

        this.add = this.findViewById(R.id.location_add);

        this.listView.setOnItemClickListener((parent, view, position, id) -> {
            try {
                new LocationDeleteAsyncTask().execute(
                        locations.get(position)
                ).get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            adapter.remove(locationsString.get(position));
            locations.remove(position);
        });

        this.add.setOnClickListener(v -> {
            Location location = new Location();
            location.name = ((TextView) findViewById(R.id.location_new_name)).getText().toString();
            try {
                location.locationId = new LocationAddAsyncTask().execute(location).get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            adapter.add(location.name);
            locations.add(location);
            ((TextView) findViewById(R.id.location_new_name)).setText("");
        });
    }
}