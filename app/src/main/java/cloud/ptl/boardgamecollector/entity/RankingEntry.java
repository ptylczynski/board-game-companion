package cloud.ptl.boardgamecollector.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity
public class RankingEntry {
    @PrimaryKey
    public int rankingId;

    public LocalDate date;
    public int position;
    public int gameId;
}
