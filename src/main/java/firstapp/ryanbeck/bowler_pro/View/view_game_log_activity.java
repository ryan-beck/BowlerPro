package firstapp.ryanbeck.bowler_pro.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.util.List;

import firstapp.ryanbeck.bowler_pro.Controller.GameControl;
import firstapp.ryanbeck.bowler_pro.Model.Game;
import firstapp.ryanbeck.bowler_pro.R;

public class view_game_log_activity extends AppCompatActivity {

    TextView logTextView;
    GameControl gameControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_game_log_activity);

        gameControl = GameControl.get(this.getApplicationContext());

        logTextView = findViewById(R.id.gameLogs);
        logTextView.setMovementMethod(new ScrollingMovementMethod());

        List<Game> games = gameControl.getAllGames();
        String logs = "";
        for(Game game : games) {
            logs += "User: " + game.getPlayerId() + "\n" + "Game: @" + game.toString().substring(0,42) + "\n";
        }
        logTextView.setText(logs);
    }
}
