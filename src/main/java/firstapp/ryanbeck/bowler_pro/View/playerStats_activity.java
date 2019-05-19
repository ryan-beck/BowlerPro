package firstapp.ryanbeck.bowler_pro.View;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import firstapp.ryanbeck.bowler_pro.Controller.GameControl;
import firstapp.ryanbeck.bowler_pro.Controller.UserControl;
import firstapp.ryanbeck.bowler_pro.Model.Game;
import firstapp.ryanbeck.bowler_pro.Model.User;
import firstapp.ryanbeck.bowler_pro.R;


public class playerStats_activity extends AppCompatActivity {


    User user;
    UserControl userControl;
    GameControl gameControl;

    private ListView listView;
    private ArrayAdapter arrayAdapter;

    TextView avg;

    private static DecimalFormat df2 = new DecimalFormat("#.##");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_stats_activity);

        userControl = UserControl.get(this.getApplicationContext());
        gameControl = GameControl.get(this.getApplicationContext());

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        user = userControl.getUserByName(name);

        listView = (ListView) findViewById(R.id.games_list);
        avg = findViewById(R.id.avg_text);


        List<Game> games = gameControl.getUserGames(user.getId());

        if(games.isEmpty()) {
            Log.d("empty", "db was empty");
            String[] arr = {"No game data available"};
            avg.setText("0.0");
            arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arr);
            listView.setAdapter(arrayAdapter);
        }else {
            Log.d("empty", "db was not empty");

            List<String> arr = new ArrayList<>();
            Collections.reverse(games);
            for(Game game : games) {
                arr.add(game.toString());
                avg.setText(Double.toString(avgPlayerScore(games)));
                arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arr);
                listView.setAdapter(arrayAdapter);
            }


        }

    }

    // TODO: Create delete game functionality

    public static double avgPlayerScore(List<Game> games) {
        double total = 0.0;
        for(Game game : games) {
            total += game.getScore();
        }
        return total / games.size();
    }
}
