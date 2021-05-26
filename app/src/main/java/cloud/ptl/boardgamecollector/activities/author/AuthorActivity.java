package cloud.ptl.boardgamecollector.activities.author;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import cloud.ptl.boardgamecollector.R;
import cloud.ptl.boardgamecollector.db.entity.Author;
import cloud.ptl.boardgamecollector.io.db.AuthorAddAsyncTask;
import cloud.ptl.boardgamecollector.io.db.AuthorDeleteAsyncTask;
import cloud.ptl.boardgamecollector.io.db.AuthorFetchAsyncTask;
import lombok.SneakyThrows;

public class AuthorActivity extends AppCompatActivity {

    private EditText authorName;
    private Button add;
    private ListView listView;

    private ArrayAdapter<String> adapter;
    private List<String> authorString;
    private List<Author> authors;

    @SneakyThrows
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);
        this.add = this.findViewById(R.id.author_add);
        this.authorName = this.findViewById(R.id.author_name);
        this.authors = new AuthorFetchAsyncTask().execute().get();
        this.authorString = this.authors.stream().map(el -> el.name + " " + el.surname).collect(Collectors.toList());
        this.adapter = new ArrayAdapter<>(this, R.layout.listitem, this.authorString);
        this.listView.setAdapter(this.adapter);
        this.listView.setOnItemClickListener((parent, view, position, id) -> {
            try {
                new AuthorDeleteAsyncTask().execute(
                        this.authors.get(position)
                ).get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            adapter.remove(
                    this.authorString.get(position)
            );
            authors.remove(position);
        });

        this.add.setOnClickListener(v -> {
            Author author = new Author();
            EditText editText = findViewById(R.id.author_name);
            author.name = editText.getText().toString().split(" ")[0];
            author.surname = editText.getText().toString().split(" ")[1];
            try {
                author.authorId = new AuthorAddAsyncTask().execute(author).get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            authors.add(author);
            authorString.add(editText.getText().toString());
        });
    }
}