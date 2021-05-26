package cloud.ptl.boardgamecollector.io.db;

import android.os.AsyncTask;

import cloud.ptl.boardgamecollector.db.DB;
import cloud.ptl.boardgamecollector.db.entity.Location;

public class LocationFetchByIdAsyncTask extends AsyncTask<Long, Integer, Location> {
    @Override
    protected Location doInBackground(Long... longs) {
        return DB.db.locationDAO().findById(longs[0]);
    }
}
