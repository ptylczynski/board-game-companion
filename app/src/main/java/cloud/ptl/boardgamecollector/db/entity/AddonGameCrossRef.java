package cloud.ptl.boardgamecollector.db.entity;

import androidx.room.Entity;

@Entity(primaryKeys = {"gameId", "addonId"})
public class AddonGameCrossRef {
    public int gameId;
    public int addonId;
}
