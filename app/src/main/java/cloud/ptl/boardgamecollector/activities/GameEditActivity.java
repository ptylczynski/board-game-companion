package cloud.ptl.boardgamecollector.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import cloud.ptl.boardgamecollector.R;

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

    private Intent intent;
    private Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_edit);

        this.gameTitle = this.findViewById(R.id.edit_game_title);
        this.orginalTitle = this.findViewById(R.id.edit_orginal_title);
        this.prodDate = this.findViewById(R.id.edit_prod_date);
        this.orderDate = this.findViewById(R.id.edit_order_date);
        this.additionDate = this.findViewById(R.id.edit_description);
        this.buyPrice = this.findViewById(R.id.edit_buy_price);
        this.msrp = this.findViewById(R.id.edit_msrp);
        this.ean = this.findViewById(R.id.edit_ean);
        this.manufacturerCode = this.findViewById(R.id.edit_manufacturer_code);
        this.comment = this.findViewById(R.id.edit_comment);
        this.addon = this.findViewById(R.id.edit_addon);
        this.standalone = this.findViewById(R.id.edit_standalone);
        this.addonTo = this.findViewById(R.id.edit_addon_to);

        this.intent = this.getIntent();
        this.id = this.intent.getIntExtra("id", -1);
        if (this.id > 0) {
            // fetch data
        }

        this.gameTitle.setText(this.id.toString());
    }
}