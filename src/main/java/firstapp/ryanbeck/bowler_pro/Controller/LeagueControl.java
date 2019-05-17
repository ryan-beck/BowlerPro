package firstapp.ryanbeck.bowler_pro.Controller;

import android.content.Context;

import firstapp.ryanbeck.bowler_pro.Database.League.LeagueHelper;
import firstapp.ryanbeck.bowler_pro.Model.League;

public class LeagueControl {
    public static LeagueControl mLeagueControl;
    public static Context mContext;
    private LeagueHelper mLeagueHelper;

    public static LeagueControl get(Context c) {
        if(mLeagueControl == null) {
            mLeagueControl = new LeagueControl(c);
        }
        return mLeagueControl;
    }

    private LeagueControl(Context c) {
        mContext = c.getApplicationContext();
        mLeagueHelper = new LeagueHelper(mContext);
    }

    public long addLeague(League league) {
        return mLeagueHelper.addLongItem(league);
    }
}
