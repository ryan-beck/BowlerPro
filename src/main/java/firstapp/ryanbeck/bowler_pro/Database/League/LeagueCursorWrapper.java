package firstapp.ryanbeck.bowler_pro.Database.League;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import firstapp.ryanbeck.bowler_pro.Database.League.League_schema;
import firstapp.ryanbeck.bowler_pro.Model.League;

public class LeagueCursorWrapper extends CursorWrapper {

    public LeagueCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public League getLeague() {
        String uuidString = getString(getColumnIndex(League_schema.leagueTable.cols.UUID));
        String name = getString(getColumnIndex(League_schema.leagueTable.cols.NAME));

        League league = new League(name, UUID.fromString(uuidString));
        return league;

    }
}
