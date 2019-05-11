package firstapp.ryanbeck.bowler_pro.Controller;

import android.content.Context;

import firstapp.ryanbeck.bowler_pro.Database.Game.GameHelper;
import firstapp.ryanbeck.bowler_pro.Database.User.UserHelper;
import firstapp.ryanbeck.bowler_pro.Model.Game;

public class GameControl {

    public static GameControl mGameControl;
    public static Context mContext;
    private GameHelper mGameHelper;

    public static GameControl get(Context c) {
        if(mGameControl == null) {
            mGameControl = new GameControl(c);
        }
        return mGameControl;
    }

    private GameControl(Context c) {
        mContext = c.getApplicationContext();
        mGameHelper = new GameHelper(mContext);
    }

    public long addGame(Game game) {
        return mGameHelper.addLongItem(game);
    }
}
