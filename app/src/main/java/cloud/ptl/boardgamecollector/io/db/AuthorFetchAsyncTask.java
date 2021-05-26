package cloud.ptl.boardgamecollector.io.db;

import android.os.AsyncTask;

import java.util.List;

import cloud.ptl.boardgamecollector.db.DB;
import cloud.ptl.boardgamecollector.db.entity.Author;

public class AuthorFetchAsyncTask extends AsyncTask<String, Integer, List<Author>> {
    @Override
    protected List<Author> doInBackground(String... strings) {
        return DB.db.authorDAO().getAll();
    }
}
