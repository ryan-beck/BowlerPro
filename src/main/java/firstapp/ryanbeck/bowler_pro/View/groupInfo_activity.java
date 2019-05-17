package firstapp.ryanbeck.bowler_pro.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import firstapp.ryanbeck.bowler_pro.Controller.GameControl;
import firstapp.ryanbeck.bowler_pro.Controller.LeagueControl;
import firstapp.ryanbeck.bowler_pro.Controller.UserControl;
import firstapp.ryanbeck.bowler_pro.Model.Game;
import firstapp.ryanbeck.bowler_pro.Model.User;
import firstapp.ryanbeck.bowler_pro.R;

public class groupInfo_activity extends AppCompatActivity {

    User user;
    UserControl userControl;
    GameControl gameControl;
    LeagueControl leagueControl;

    private ListView listView;
    private ArrayAdapter arrayAdapter;

    TextView groupNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_info_activity);

        userControl = UserControl.get(this.getApplicationContext());
        gameControl = GameControl.get(this.getApplicationContext());

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        user = userControl.getUserByName(name);

        listView = (ListView) findViewById(R.id.player_list);
        groupNameText = findViewById(R.id.groupName);

        String groupName = user.getGroupName();
        groupNameText.setText(groupName);

        List<User> playerList = userControl.getAllUsers();
        List<User> groupPlayers = new ArrayList<>();

        for(User user : playerList) {
            if(user.getGroupName().equals(groupName)) {
                List<Game> playerGames = gameControl.getUserGames(user.getId());
                double avg = playerStats_activity.avgPlayerScore(playerGames);
                user.setAverage(avg);
                groupPlayers.add(user);
            }
        }

        Collections.sort(groupPlayers);

        List<String> arr = new ArrayList<>();

        for(User u : groupPlayers) {
            arr.add(u.getUsername() + "  (Avg. Score: " + u.getAverage() + ")");
        }

        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, arr);
        listView.setAdapter(arrayAdapter);
    }

    public void changeGroup(View v) {
        Intent intent = new Intent(groupInfo_activity.this, joinGroup_activity.class);
        intent.putExtra("name", user.getUsername());
        startActivity(intent);
    }
}
