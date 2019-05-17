package firstapp.ryanbeck.bowler_pro.Database.League;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

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

    public boolean deleteGroup(String name) {
        db = this.getWritableDatabase();
        boolean success =  db.delete(leagueTable.NAME, leagueTable.cols.NAME + "=?", new String[]{name}) > 0;
        if(success) {
            Log.d("deleting", "success");
        } else {
            Log.d("deleting", "fail");
        }
        return success;
    }

    private Cursor queryDataBase(String dbName, String whereClause, String[] whereArgs) {
        db = this.getWritableDatabase();

        try{
            return db.query(
                    leagueTable.NAME,
                    null,
                    whereClause,
                    whereArgs,
                    null,
                    null,
                    null
            );
        }catch (Exception e) {
            Log.d(TAG, "GymLogHelper: QueryDB didn't find anything..");
            return null;
        }

    }

    public List<League> getLeagues() {
        LeagueCursorWrapper cursor = new LeagueCursorWrapper(queryDataBase(leagueTable.NAME, null, null));

        List<League> logs = new ArrayList<>();

        try {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                logs.add(cursor.getLeague());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }

        return logs;
    }

}
