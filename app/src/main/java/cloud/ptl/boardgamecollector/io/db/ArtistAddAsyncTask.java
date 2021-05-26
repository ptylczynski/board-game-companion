package cloud.ptl.boardgamecollector.io.db;

import android.os.AsyncTask;

import cloud.ptl.boardgamecollector.db.DB;
import cloud.ptl.boardgamecollector.db.entity.Artist;

public class ArtistAddAsyncTask extends AsyncTask<Artist, Integer, Long> {
    @Override
    protected Long doInBackground(Artist... artists) {
        return DB.db.artistDAO().add(artists[0]);
    }
}
