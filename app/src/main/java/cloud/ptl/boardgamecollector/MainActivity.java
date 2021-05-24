package cloud.ptl.boardgamecollector;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;

import cloud.ptl.boardgamecollector.db.AppDB;
import cloud.ptl.boardgamecollector.db.DB;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DB.init(
                this.getApplicationContext()
        );
    }
}