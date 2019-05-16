package firstapp.ryanbeck.bowler_pro.Controller;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public List<Game> getUserGames(UUID playerId) {

        List<Game> res = new ArrayList<>();

        for(Game game : mGameHelper.getGames()) {
            if(game.getPlayerId().equals(playerId)) {
                res.add(game);
            }
        }
        return res;
    }
}
