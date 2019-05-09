package firstapp.ryanbeck.bowler_pro.Database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import firstapp.ryanbeck.bowler_pro.User;
import firstapp.ryanbeck.bowler_pro.Database.User_schema.userTable;

public class UserCursorWrapper extends CursorWrapper {

    public UserCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public User getUser(){
        String uuidString = getString(getColumnIndex(userTable.cols.UUID));
        Boolean admin = Boolean.parseBoolean(getString(getColumnIndex(userTable.cols.ADMIN)));
        String username = getString(getColumnIndex(userTable.cols.USERNAME));
        String password = getString(getColumnIndex(userTable.cols.PASSWORD));

        User user = new User(UUID.fromString(uuidString), admin, username, password);

        return user;
    }


}
