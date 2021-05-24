package cloud.ptl.boardgamecollector.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity
public class RankingEntry {
    @PrimaryKey
    public int rankingEntryId;

    public String date;
    public int position;
    public int gameId;
}
