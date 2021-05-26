package cloud.ptl.boardgamecollector.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Author {
    @PrimaryKey(autoGenerate = true)
    public Long authorId;

    public String name;
    public String surname;
}
