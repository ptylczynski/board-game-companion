package cloud.ptl.boardgamecollector.io.db;

import android.os.AsyncTask;

import java.nio.channels.AsynchronousChannelGroup;

import cloud.ptl.boardgamecollector.db.DB;
import cloud.ptl.boardgamecollector.db.entity.Author;

public class AuthorDeleteAsyncTask extends AsyncTask<Author, Integer, String> {
    @Override
    protected String doInBackground(Author... authors) {
        DB.db.authorDAO().delete(authors[0]);
        return null;
    }
}
