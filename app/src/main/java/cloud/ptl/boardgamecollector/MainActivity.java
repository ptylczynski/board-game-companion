package cloud.ptl.boardgamecollector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import cloud.ptl.boardgamecollector.activities.GameEditActivity;
import cloud.ptl.boardgamecollector.activities.gameadd.GameAddActivity;
import cloud.ptl.boardgamecollector.activities.location.LocationActivity;
import cloud.ptl.boardgamecollector.db.DB;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButton;
    private FloatingActionButton locationAddFly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DB.init(
                this.getApplicationContext()
        );

        this.floatingActionButton = this.findViewById(R.id.add_game_button);
        this.locationAddFly = this.findViewById(R.id.location_add_fly);

        this.floatingActionButton.setOnClickListener(v -> {
            Intent runNew = new Intent(MainActivity.this, GameAddActivity.class);
            MainActivity.this.startActivity(runNew);
        });

        this.locationAddFly.setOnClickListener(v -> {
            Intent runNew = new Intent(MainActivity.this, LocationActivity.class);
            MainActivity.this.startActivity(runNew);
        });
    }
}