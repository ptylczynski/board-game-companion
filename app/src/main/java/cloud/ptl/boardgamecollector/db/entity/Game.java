package cloud.ptl.boardgamecollector.db.entity;

import androidx.room.Entity;
import androidx.room.Junction;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Game {
    @PrimaryKey(autoGenerate = true)
    public int gameId;

    public String title;
    public String orginalTitle;
    public String productionDate;
    public String description;
    public String orderDate;
    public String toCollectionAddDate;
    public Double buyPrice;
    public Double MSRP;
    public String EAN;
    public String BGGIdentifier;
    public String productionCode;
    public Boolean isStandalone;
    public Boolean isAddon;
    public String comment;
}
