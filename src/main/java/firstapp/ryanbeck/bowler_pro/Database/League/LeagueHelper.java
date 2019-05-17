package firstapp.ryanbeck.bowler_pro.Database.League;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import firstapp.ryanbeck.bowler_pro.Database.League.League_schema.leagueTable;
import firstapp.ryanbeck.bowler_pro.Model.League;

public class LeagueHelper extends SQLiteOpenHelper {
    public static final String TAG = "league";

    public static final String DATA_BASE_NAME = "league.db";
    public static final int VERSION = 1;

    private SQLiteDatabase db;

    public LeagueHelper(Context context) {
        super(context, DATA_BASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + leagueTable.NAME + "(" +

                "_id integer primary key autoincrement,"  +
                leagueTable.cols.UUID + "," +
                leagueTable.cols.NAME +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private ContentValues getContentValues(League league) {
        ContentValues CV = new ContentValues();
        CV.put(leagueTable.cols.UUID, league.getId().toString());
        CV.put(leagueTable.cols.NAME, league.getName());
        return CV;
    }

    public long addLongItem(League league) {
        ContentValues CV = getContentValues(league);

        db = this.getWritableDatabase();

        return db.insert(leagueTable.NAME, null, CV);
    }

}
