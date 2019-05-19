package firstapp.ryanbeck.bowler_pro.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
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

    Date date;


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

        date = new Date();

    }

    public void submit(View v) {

        String scoreText = score.getText().toString();
        String strikesText = strikes.getText().toString();
        String sparesText = spares.getText().toString();

        if(scoreText.length() != 0 && strikesText.length() != 0 && sparesText.length() != 0) {
            game = new Game(Integer.parseInt(scoreText),
                    Integer.parseInt(strikesText),
                    Integer.parseInt(sparesText),
                    UUID.randomUUID(),
                    user.getId(),
                    date
            );
            gameControl.addGame(game);


            Intent intent = new Intent(logGame_activity.this, MainActivity.class);
            intent.putExtra("name", user.getUsername());
            startActivity(intent);
        } else {
            Toast.makeText(this, "Enter all fields", Toast.LENGTH_LONG).show();
        }


    }
}
