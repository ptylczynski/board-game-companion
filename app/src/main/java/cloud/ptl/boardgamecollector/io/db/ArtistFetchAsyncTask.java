package cloud.ptl.boardgamecollector.io.db;

import android.os.AsyncTask;

import java.util.List;

import cloud.ptl.boardgamecollector.db.DB;
import cloud.ptl.boardgamecollector.db.entity.Artist;

public class ArtistFetchAsyncTask extends AsyncTask<String, Integer, List<Artist>> {
    @Override
    protected List<Artist> doInBackground(String... strings) {
        return DB.db.artistDAO().getAll();
    }
}
