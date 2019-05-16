package firstapp.ryanbeck.bowler_pro.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.UUID;

import firstapp.ryanbeck.bowler_pro.Controller.GameControl;
import firstapp.ryanbeck.bowler_pro.Controller.UserControl;
import firstapp.ryanbeck.bowler_pro.Model.Game;
import firstapp.ryanbeck.bowler_pro.Model.User;
import firstapp.ryanbeck.bowler_pro.R;

public class logGame_activity extends AppCompatActivity {

    TextView score;
    TextView strikes;
    TextView spares;

    User user;
    Game game;
    GameControl gameControl;
    UserControl userControl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_game_activity);

        userControl = UserControl.get(this.getApplicationContext());
        gameControl = GameControl.get(this.getApplicationContext());

        score = findViewById(R.id.score);
        strikes = findViewById(R.id.strikes);
        spares = findViewById(R.id.spares);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        user = userControl.getUserByName(name);


    }

    public void submit(View v) {

        game = new Game(Integer.parseInt(score.getText().toString()),
                             Integer.parseInt(strikes.getText().toString()),
                             Integer.parseInt(spares.getText().toString()),
                             UUID.randomUUID(),
                             user.getId());
        gameControl.addGame(game);


        Intent intent = new Intent(logGame_activity.this, MainActivity.class);
        intent.putExtra("name", user.getUsername());
        startActivity(intent);
    }
}
