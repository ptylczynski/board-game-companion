package cloud.ptl.boardgamecollector.io.db;

import android.os.AsyncTask;

import cloud.ptl.boardgamecollector.db.DB;
import cloud.ptl.boardgamecollector.db.entity.Location;

public class LocationDeleteAsyncTask extends AsyncTask<Location, Integer, String> {
    @Override
    protected String doInBackground(Location... strings) {
        DB.db.locationDAO().delete(strings[0]);
        return null;
    }
}
