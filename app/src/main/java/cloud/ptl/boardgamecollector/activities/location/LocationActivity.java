package cloud.ptl.boardgamecollector.activities.location;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import cloud.ptl.boardgamecollector.R;
import cloud.ptl.boardgamecollector.db.entity.Location;

public class LocationActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        this.listView = this.findViewById(R.id.loaction_list);
        this.adapter = new ArrayAdapter<String>(this, R.layout.listitem, locations);
        this.listView.setAdapter(this.adapter);
    }
}