package cloud.ptl.boardgamecollector.db.entity;

import androidx.room.Entity;

@Entity(primaryKeys = {"gameId", "artistId"})
public class ArtistGameCrossRef {
    public int gameId;
    public int artistId;
}
