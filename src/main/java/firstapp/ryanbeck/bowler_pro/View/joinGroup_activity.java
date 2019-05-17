package firstapp.ryanbeck.bowler_pro.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import firstapp.ryanbeck.bowler_pro.Controller.LeagueControl;
import firstapp.ryanbeck.bowler_pro.Controller.UserControl;
import firstapp.ryanbeck.bowler_pro.Model.League;
import firstapp.ryanbeck.bowler_pro.Model.User;
import firstapp.ryanbeck.bowler_pro.R;

public class joinGroup_activity extends AppCompatActivity {

    User user;
    UserControl userControl;
    LeagueControl leagueControl;

    private ListView listView;
    private ArrayAdapter arrayAdapter;

    TextView groupName;

    Button joinButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_group_activity);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        userControl = UserControl.get(this.getApplicationContext());
        leagueControl = leagueControl.get(this.getApplicationContext());

        user = userControl.getUserByName(name);

        listView = (ListView) findViewById(R.id.groupsJoin_list);

        joinButton = findViewById(R.id.joinButton);

        groupName = findViewById(R.id.joinGroupText);

        List<League> leagues = leagueControl.getAllLeagues();

        if(leagues.isEmpty()) {
            Log.d("empty", "db was empty");
            String[] arr = {"No leagues currently available"};
            arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arr);
            listView.setAdapter(arrayAdapter);
        }else {
            Log.d("empty", "db was not empty");

            List<String> arr = new ArrayList<>();
            for(League l : leagues) {
                arr.add(l.getName());
                arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arr);
                listView.setAdapter(arrayAdapter);
            }


        }

        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = groupName.getText().toString();
                for(League l : leagueControl.getAllLeagues()) {
                    if(text.equals(l.getName())) {
                        user.setGroupName(l.getName());
                        userControl.delete(user.getUsername());
                        User replacement = new User(user.getId(), user.isAdmin(), user.getUsername(), user.getPassword(), l.getName());
                        userControl.addUser(replacement);
                        Intent intent = new Intent(joinGroup_activity.this, MainActivity.class);
                        intent.putExtra("name", user.getUsername());
                        startActivity(intent);
                    }
                }



            }
        });
    }

}
