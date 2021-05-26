package cloud.ptl.boardgamecollector.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Artist {
    @PrimaryKey(autoGenerate = true)
    public int artistId;

    public String name;
    public String surname;
}
