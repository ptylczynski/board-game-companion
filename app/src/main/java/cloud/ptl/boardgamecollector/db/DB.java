package cloud.ptl.boardgamecollector.db;

import android.content.Context;

import androidx.room.Room;

public class DB {
    public static AppDB db;

    public static void init(Context context){
        DB.db = Room.databaseBuilder(
               context,
               AppDB.class,
               "BoardGameCollectorDB"
        ).build();
    }
}
