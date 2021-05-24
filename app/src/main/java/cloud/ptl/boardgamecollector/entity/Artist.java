package cloud.ptl.boardgamecollector.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Artist {
    @PrimaryKey
    public int artistId;

    public String name;
    public String surname;
}
