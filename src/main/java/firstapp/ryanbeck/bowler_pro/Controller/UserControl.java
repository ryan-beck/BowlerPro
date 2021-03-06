package firstapp.ryanbeck.bowler_pro.Controller;

import android.content.Context;

import java.util.List;

import firstapp.ryanbeck.bowler_pro.Database.User.UserHelper;
import firstapp.ryanbeck.bowler_pro.Model.User;

public class UserControl {

    public static UserControl mUserControl;
    private static Context mContext;
    private UserHelper mUserHelper;

    public static UserControl get(Context c) {
        if(mUserControl == null) {
            mUserControl = new UserControl(c);
        }

        return mUserControl;
    }

    private UserControl(Context c) {
        mContext = c.getApplicationContext();
        mUserHelper = new UserHelper(mContext);
    }

    public long addUser(User user) {
        return mUserHelper.addLongItem(user);
    }

    public boolean usernameExists(String username) {
        User user = mUserHelper.getByUsername(username);
        if(user == null){
            return false;
        }
        return true;
    }

    public User getUserByName(String name) {
        return mUserHelper.getByUsername(name);
    }

    public List<User> getAllUsers() {
        return mUserHelper.getUsers();
    }

    public void delete(String name) {
        mUserHelper.deleteUser(name);
    }


}
