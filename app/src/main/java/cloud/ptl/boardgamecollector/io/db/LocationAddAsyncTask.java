package cloud.ptl.boardgamecollector.io.db;

import android.os.AsyncTask;

import cloud.ptl.boardgamecollector.db.DB;
import cloud.ptl.boardgamecollector.db.entity.Location;

public class LocationAddAsyncTask extends AsyncTask<Location, Integer, Long> {
    @Override
    protected Long doInBackground(Location... locations) {
        return DB.db.locationDAO().add(locations[0]);
    }
}
