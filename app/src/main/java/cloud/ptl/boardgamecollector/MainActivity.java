package cloud.ptl.boardgamecollector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import cloud.ptl.boardgamecollector.activities.GameEditActivity;
import cloud.ptl.boardgamecollector.db.DB;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DB.init(
                this.getApplicationContext()
        );

        this.floatingActionButton = this.findViewById(R.id.add_game_button);

        this.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent runNew = new Intent(MainActivity.this, GameEditActivity.class);
                MainActivity.this.startActivity(runNew);
            }
        });
    }
}