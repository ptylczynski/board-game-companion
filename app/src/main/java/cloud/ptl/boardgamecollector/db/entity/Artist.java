package cloud.ptl.boardgamecollector.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Artist {
    @PrimaryKey(autoGenerate = true)
    public Long artistId;

    public String name;
    public String surname;
}
