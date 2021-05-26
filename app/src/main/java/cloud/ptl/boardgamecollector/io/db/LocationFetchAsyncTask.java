package cloud.ptl.boardgamecollector.io.db;

import android.os.AsyncTask;

import java.util.List;

import cloud.ptl.boardgamecollector.db.DB;
import cloud.ptl.boardgamecollector.db.entity.Location;

public class LocationFetchAsyncTask extends AsyncTask<String, Integer, List<Location>> {
    @Override
    protected List<Location> doInBackground(String... strings) {
        return DB.db.locationDAO().getAll();
    }
}
