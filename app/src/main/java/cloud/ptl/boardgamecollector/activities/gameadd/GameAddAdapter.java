package cloud.ptl.boardgamecollector.activities.gameadd;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import cloud.ptl.boardgamecollector.R;
import cloud.ptl.boardgamecollector.io.dto.GameSearchResult;

public class GameAddAdapter extends ArrayAdapter<GameSearchResult> {
    private Context context;
    private GameSearchResult[] gameSearchResult;
    public GameAddAdapter(@NonNull Context context, int resource, @NonNull GameSearchResult[] objects) {
        super(context, resource, objects);
        this.context = context;
        this.gameSearchResult = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        GameSearchResult gsr = this.gameSearchResult[position];
        assert convertView != null;
        TextView title = convertView.findViewById(R.id.textView_title);
        //TextView description = convertView.findViewById(R.id.textView_description);
        // title.setText(gsr.getGameName());
        return null;
    }
}
