package cloud.ptl.boardgamecollector.io.db;

import android.os.AsyncTask;

import cloud.ptl.boardgamecollector.db.DB;
import cloud.ptl.boardgamecollector.db.entity.Artist;

public class ArtistFetchByIdAsyncTask extends AsyncTask<Long, Integer, Artist> {
    @Override
    protected Artist doInBackground(Long... longs) {
        return DB.db.artistDAO().findById(longs[0]);
    }
}
