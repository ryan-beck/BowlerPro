package firstapp.ryanbeck.bowler_pro.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import firstapp.ryanbeck.bowler_pro.Database.User_schema.userTable;
import firstapp.ryanbeck.bowler_pro.User;

public class UserHelper extends SQLiteOpenHelper {
    public static final String TAG = "GYM_Log";

    public static final String DATA_BASE_NAME = "gymLog.db";
    public static final int VERSION = 1;

    private SQLiteDatabase db;

    public UserHelper(Context context) {
        super(context, DATA_BASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + userTable.NAME + "(" +

                "_id integer primary key autoincrement,"  +
                userTable.cols.UUID + "," +
                userTable.cols.ADMIN + "," +
                userTable.cols.USERNAME + "," +
                userTable.cols.PASSWORD +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private ContentValues getContentValues(User user) {
        ContentValues CV = new ContentValues();
        CV.put(userTable.cols.UUID, user.getId().toString());
        CV.put(userTable.cols.ADMIN, Boolean.toString(user.isAdmin()));
        CV.put(userTable.cols.USERNAME, user.getUsername());
        CV.put(userTable.cols.PASSWORD, user.getPassword());

        return CV;
    }

    public long addLongItem(User user) {
        ContentValues CV = getContentValues(user);

        db = this.getWritableDatabase();

        return db.insert(userTable.NAME, null, CV);
    }

    private Cursor queryDataBase(String dbName, String whereClause, String[] whereArgs) {
        db = this.getWritableDatabase();

        try{
            return db.query(
                    userTable.NAME,
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

    public User getByUsername(String username) {
        Cursor c = queryDataBase(userTable.NAME, userTable.cols.USERNAME+"=?", new String[]{username});
        UserCursorWrapper userC = new UserCursorWrapper(c);
        try {
            if(c.getCount() == 0) {
                Log.d(TAG, "No results from getByUserName");
                return null;
            }
            c.moveToFirst();
            return userC.getUser();
        } finally {
            c.close();
        }
        //
    }

    public List<User> getUsers() {
        UserCursorWrapper cursor = new UserCursorWrapper(queryDataBase(userTable.NAME, null, null));

        List<User> logs = new ArrayList<>();

        try {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                logs.add(cursor.getUser());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }

        return logs;
    }


}
