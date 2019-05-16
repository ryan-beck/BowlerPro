package firstapp.ryanbeck.bowler_pro.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import firstapp.ryanbeck.bowler_pro.Controller.GameControl;
import firstapp.ryanbeck.bowler_pro.Controller.UserControl;
import firstapp.ryanbeck.bowler_pro.Model.Game;
import firstapp.ryanbeck.bowler_pro.Model.User;
import firstapp.ryanbeck.bowler_pro.R;

public class playerStats_activity extends AppCompatActivity {

    LinearLayout layout;

    User user;
    UserControl userControl;
    GameControl gameControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_stats_activity);

        userControl = UserControl.get(this.getApplicationContext());
        gameControl = GameControl.get(this.getApplicationContext());

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        layout = findViewById(R.id.gamesView);

        user = userControl.getUserByName(name);

        List<Game> games = gameControl.getUserGames(user.getId());
        if(games.isEmpty()) {
            TextView t = new TextView(this.getApplicationContext());
            t.setText("No game data available");
            layout.addView(t);
        }else {
            for(Game game : games) {
                TextView t = new TextView(this.getApplicationContext());
                t.setText(game.toString());
                layout.addView(t);
            }
        }

    }
}
