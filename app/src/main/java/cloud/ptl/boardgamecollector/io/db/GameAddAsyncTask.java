package cloud.ptl.boardgamecollector.io.db;

import android.os.AsyncTask;

import cloud.ptl.boardgamecollector.db.DB;
import cloud.ptl.boardgamecollector.db.entity.Game;

public class GameAddAsyncTask extends AsyncTask<Game, Integer, Long> {
    @Override
    protected Long doInBackground(Game... games) {
        return DB.db.gameDAO().add(games[0]);
    }
}
