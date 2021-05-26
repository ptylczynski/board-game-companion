package cloud.ptl.boardgamecollector.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Location {
    @PrimaryKey(autoGenerate = true)
    public long locationId;
    public int gameLocatedId;

    public String name;
    public String description;
}
