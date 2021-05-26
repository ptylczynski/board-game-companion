package cloud.ptl.boardgamecollector.io.db;

import android.os.AsyncTask;

import cloud.ptl.boardgamecollector.db.DB;
import cloud.ptl.boardgamecollector.db.entity.Author;

public class AuthorAddAsyncTask extends AsyncTask<Author, Integer, Long> {
    @Override
    protected Long doInBackground(Author... authors) {
        return DB.db.authorDAO().add(authors[0]);
    }
}
