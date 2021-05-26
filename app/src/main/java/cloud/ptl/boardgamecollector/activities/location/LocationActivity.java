package cloud.ptl.boardgamecollector.activities.location;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.stream.Collectors;

import cloud.ptl.boardgamecollector.R;
import cloud.ptl.boardgamecollector.db.DB;
import cloud.ptl.boardgamecollector.db.dao.LocationDAO;
import cloud.ptl.boardgamecollector.db.entity.Location;

public class LocationActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> locationsString;
    private List<Location> locations;
    private final LocationDAO locationDAO = DB.db.locationDAO();
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        this.locations = this.locationDAO.getAll();
        this.locationsString = this.locations.stream().map(el -> el.name).collect(Collectors.toList());
        this.listView = this.findViewById(R.id.loaction_list);
        this.adapter = new ArrayAdapter<String>(this, R.layout.listitem, locationsString);
        this.listView.setAdapter(this.adapter);

        this.add = this.findViewById(R.id.location_add);

        this.listView.setOnItemClickListener((parent, view, position, id) -> {
            locationDAO.delete(
                    locations.get(position)
            );
            adapter.remove(locationsString.get(position));
            locations.remove(position);
        });

        this.add.setOnClickListener(v -> {
            Location location = new Location();
            location.name = ((TextView) findViewById(R.id.location_new_name)).getText().toString();
            location.locationId = locationDAO.add(location);
            adapter.add(location.name);
            locations.add(location);
        });
    }
}