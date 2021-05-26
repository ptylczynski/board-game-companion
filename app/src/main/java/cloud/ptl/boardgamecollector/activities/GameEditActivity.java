package cloud.ptl.boardgamecollector.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import cloud.ptl.boardgamecollector.R;
import cloud.ptl.boardgamecollector.io.dto.GameDetailsDTO;
import cloud.ptl.boardgamecollector.io.network.GameDetailsFetchAsyncTask;
import lombok.SneakyThrows;

public class GameEditActivity extends AppCompatActivity {

    private EditText gameTitle;
    private EditText orginalTitle;
    private EditText prodDate;
    private EditText orderDate;
    private EditText additionDate;
    private EditText description;
    private EditText buyPrice;
    private EditText msrp;
    private EditText ean;
    private EditText manufacturerCode;
    private EditText comment;
    private Switch addon;
    private Switch standalone;
    private Spinner addonTo;
    private Spinner artist;
    private Spinner author;
    private Button confirm;

    private Intent intent;
    private Integer id;

    private GameDetailsDTO gameDetailsDTO;

    @SneakyThrows
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_edit);

        this.gameTitle = this.findViewById(R.id.edit_game_title);
        this.orginalTitle = this.findViewById(R.id.edit_orginal_title);
        this.prodDate = this.findViewById(R.id.edit_prod_date);
        this.orderDate = this.findViewById(R.id.edit_order_date);
        this.additionDate = this.findViewById(R.id.edit_addition_date);
        this.buyPrice = this.findViewById(R.id.edit_buy_price);
        this.msrp = this.findViewById(R.id.edit_msrp);
        this.ean = this.findViewById(R.id.edit_ean);
        this.manufacturerCode = this.findViewById(R.id.edit_manufacturer_code);
        this.comment = this.findViewById(R.id.edit_comment);
        this.addon = this.findViewById(R.id.edit_addon);
        this.standalone = this.findViewById(R.id.edit_standalone);
        this.addonTo = this.findViewById(R.id.edit_addon_to);
        this.author = this.findViewById(R.id.edit_author);
        this.artist = this.findViewById(R.id.edit_artist);
        this.description = this.findViewById(R.id.edit_description);
        this.confirm = this.findViewById(R.id.edit_confirm);

        this.intent = this.getIntent();
        this.id = this.intent.getIntExtra("id", -1);
        if (this.id > 0) {
            this.gameDetailsDTO =
                    new GameDetailsFetchAsyncTask().execute(this.id.toString()).get();
            this.gameTitle.setText(this.gameDetailsDTO.getGame().title);
            this.orginalTitle.setText(this.gameDetailsDTO.getGame().orginalTitle);
            this.prodDate.setText(this.gameDetailsDTO.getGame().productionDate);
            this.addon.setChecked(this.gameDetailsDTO.getGame().isAddon);
            this.standalone.setChecked(this.gameDetailsDTO.getGame().isStandalone);
            this.description.setText(this.gameDetailsDTO.getGame().description);
        }

        this.confirm.setOnClickListener(v -> {

        });

    }
}