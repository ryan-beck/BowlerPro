package firstapp.ryanbeck.bowler_pro.Database.Game;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import firstapp.ryanbeck.bowler_pro.Model.Game;
import firstapp.ryanbeck.bowler_pro.Database.Game.Game_schema.gameTable;

public class GameCursorWrapper extends CursorWrapper {
    public GameCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Game getUser(){
        String uuidString = getString(getColumnIndex(gameTable.cols.UUID));
        String playerUUID = getString(getColumnIndex(gameTable.cols.PLAYER_UUID));
        int score = getInt(getColumnIndex(gameTable.cols.SCORE));
        int strikes = getInt(getColumnIndex(gameTable.cols.STRIKES));
        int spares = getInt(getColumnIndex(gameTable.cols.SPARES));

        Game game = new Game(score, strikes, spares, UUID.fromString(uuidString), UUID.fromString(playerUUID));

        return game;
    }
}
