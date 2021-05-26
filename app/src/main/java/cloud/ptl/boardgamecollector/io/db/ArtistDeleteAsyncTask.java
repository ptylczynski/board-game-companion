package cloud.ptl.boardgamecollector.io.db;

import android.os.AsyncTask;

import cloud.ptl.boardgamecollector.db.DB;
import cloud.ptl.boardgamecollector.db.entity.Artist;

public class ArtistDeleteAsyncTask extends AsyncTask<Artist, Integer, String> {
    @Override
    protected String doInBackground(Artist... artists) {
        DB.db.artistDAO().delete(artists[0]);
        return null;
    }
}
