package cloud.ptl.boardgamecollector.io.db;

import android.os.AsyncTask;

import cloud.ptl.boardgamecollector.db.DB;
import cloud.ptl.boardgamecollector.db.entity.Game;

public class GameFetchByIdAsyncTask extends AsyncTask<Long, Integer, Game> {
    @Override
    protected Game doInBackground(Long... longs) {
        return DB.db.gameDAO().findById(longs[0]);
    }
}
