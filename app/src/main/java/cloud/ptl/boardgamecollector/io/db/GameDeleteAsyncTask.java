package cloud.ptl.boardgamecollector.io.db;

import android.os.AsyncTask;

import cloud.ptl.boardgamecollector.db.DB;
import cloud.ptl.boardgamecollector.db.entity.Game;

public class GameDeleteAsyncTask extends AsyncTask<Game, Integer, String> {
    @Override
    protected String doInBackground(Game... games) {
        DB.db.gameDAO().delete(games[0]);
        return null;
    }
}
