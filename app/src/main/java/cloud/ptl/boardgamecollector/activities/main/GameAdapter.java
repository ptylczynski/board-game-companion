package cloud.ptl.boardgamecollector.activities.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Arrays;
import java.util.List;

import cloud.ptl.boardgamecollector.R;
import cloud.ptl.boardgamecollector.db.entity.Game;

public class GameAdapter extends ArrayAdapter<Game> {
    private final Context context;
    private final List<Game> games;

    public GameAdapter(@NonNull Context context, int resource, @NonNull List<Game> objects) {
        super(context, resource, objects);
        this.context = context;
        this.games = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Game game = this.games.get(position);

        if (convertView == null)
            convertView = LayoutInflater.from(this.context)
                    .inflate(
                            R.layout.activity_game_entry_view,
                            parent,
                            false
                    );
        TextView title = convertView.findViewById(R.id.tetxView_list_elem_title);
        TextView description = convertView.findViewById(R.id.textView_list_elem_description);

        title.setText(game.title);
        description.setText(game.description);
        return convertView;
    }
}
