package cloud.ptl.boardgamecollector.io.db;

import android.os.AsyncTask;

import cloud.ptl.boardgamecollector.db.DB;
import cloud.ptl.boardgamecollector.db.entity.Author;

public class AuthorFetchByIdAsyncTask extends AsyncTask<Long, Integer, Author> {
    @Override
    protected Author doInBackground(Long... longs) {
        return DB.db.authorDAO().findById(longs[0]);
    }
}
