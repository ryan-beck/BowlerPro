package firstapp.ryanbeck.bowler_pro.Database.Game;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import firstapp.ryanbeck.bowler_pro.Database.Game.Game_schema.gameTable;
import firstapp.ryanbeck.bowler_pro.Model.Game;

public class GameHelper extends SQLiteOpenHelper {
    public static final String TAG = "GYM_Log";

    public static final String DATA_BASE_NAME = "gymLog.db";
    public static final int VERSION = 1;

    private SQLiteDatabase db;

    public GameHelper(Context context) {
        super(context, DATA_BASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + gameTable.NAME + "(" +

                "_id integer primary key autoincrement,"  +
                gameTable.cols.UUID + "," +
                gameTable.cols.PLAYER_UUID + "," +
                gameTable.cols.SCORE + "," +
                gameTable.cols.STRIKES + "," +
                gameTable.cols.SPARES +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private ContentValues getContentValues(Game game) {
        ContentValues CV = new ContentValues();
        CV.put(gameTable.cols.UUID, game.getId().toString());
        CV.put(gameTable.cols.PLAYER_UUID, game.getPlayerId().toString());
        CV.put(gameTable.cols.SCORE, game.getScore());
        CV.put(gameTable.cols.STRIKES, game.getStrikes());
        CV.put(gameTable.cols.SPARES, game.getStrikes());

        return CV;
    }

    public long addLongItem(Game game) {
        ContentValues CV = getContentValues(game);

        db = this.getWritableDatabase();

        return db.insert(gameTable.NAME, null, CV);
    }

    private Cursor queryDataBase(String dbName, String whereClause, String[] whereArgs) {
        db = this.getWritableDatabase();

        try{
            return db.query(
                    gameTable.NAME,
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


//    public List<User> getUsers() {
//        UserCursorWrapper cursor = new UserCursorWrapper(queryDataBase(userTable.NAME, null, null));
//
//        List<User> logs = new ArrayList<>();
//
//        try {
//            cursor.moveToFirst();
//            while(!cursor.isAfterLast()) {
//                logs.add(cursor.getUser());
//                cursor.moveToNext();
//            }
//        }finally {
//            cursor.close();
//        }
//
//        return logs;
//    }
}