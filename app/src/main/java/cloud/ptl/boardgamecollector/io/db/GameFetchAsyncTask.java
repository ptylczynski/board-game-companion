package cloud.ptl.boardgamecollector.io.db;

import android.os.AsyncTask;

import java.util.List;

import cloud.ptl.boardgamecollector.db.DB;
import cloud.ptl.boardgamecollector.db.entity.Game;

public class GameFetchAsyncTask extends AsyncTask<Long, Integer, List<Game>> {
    @Override
    protected List<Game> doInBackground(Long... longs) {
        return DB.db.gameDAO().findAllGames();
    }
}
