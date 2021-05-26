package cloud.ptl.boardgamecollector.activities.gamedetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import cloud.ptl.boardgamecollector.R;

public class GameDetailsActivity extends AppCompatActivity {

    private TextView title;
    private TextView orginal;
    private TextView description;
    private TextView standalone;
    private TextView addon;
    private TextView buyPrice;
    private TextView comment;
    private TextView ean;
    private TextView msrp;
    private TextView toCollectionAdd;
    private TextView orderDate;
    private TextView author;
    private TextView artist;
    private TextView localization;

    private Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);

        this.title = this.findViewById(R.id.description_title);
        this.orginal = this.findViewById(R.id.description_orginal);
        this.description = this.findViewById(R.id.description_description);
        this.standalone = this.findViewById(R.id.description_standalone);
        this.addon = this.findViewById(R.id.description_addon);
        this.buyPrice = this.findViewById(R.id.description_buy_price);
        this.comment = this.findViewById(R.id.description_comment);
        this.ean = this.findViewById(R.id.description_ean);
        this.msrp = this.findViewById(R.id.description_msrp);
        this.toCollectionAdd = this.findViewById(R.id.description_to_collection_add);
        this.orderDate = this.findViewById(R.id.description_order_date);
        this.author = this.findViewById(R.id.description_author);
        this.artist = this.findViewById(R.id.description_artist);
        this.localization = this.findViewById(R.id.description_localization);

        Intent intent = this.getIntent();
        this.id = intent.getLongExtra("id", -1);


    }
}