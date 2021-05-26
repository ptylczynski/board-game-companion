package cloud.ptl.boardgamecollector.io.db;

import android.os.AsyncTask;

import cloud.ptl.boardgamecollector.db.DB;
import cloud.ptl.boardgamecollector.db.entity.Game;

public class GameUpdateAsyncTask extends AsyncTask<Game, Integer, Long> {
    @Override
    protected Long doInBackground(Game... games) {
        DB.db.gameDAO().update(games[0]);
        return null;
    }
}
