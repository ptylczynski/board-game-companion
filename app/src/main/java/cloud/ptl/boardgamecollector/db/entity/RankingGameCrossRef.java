package cloud.ptl.boardgamecollector.db.entity;

import androidx.room.Entity;

@Entity(primaryKeys = {"gameId", "rankingEntryId"})
public class RankingGameCrossRef {
    public int gameId;
    public int rankingEntryId;
}
