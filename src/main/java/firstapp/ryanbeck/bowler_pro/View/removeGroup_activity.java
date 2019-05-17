package firstapp.ryanbeck.bowler_pro.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import firstapp.ryanbeck.bowler_pro.Controller.LeagueControl;
import firstapp.ryanbeck.bowler_pro.Controller.UserControl;
import firstapp.ryanbeck.bowler_pro.Model.League;
import firstapp.ryanbeck.bowler_pro.Model.User;
import firstapp.ryanbeck.bowler_pro.R;

public class removeGroup_activity extends AppCompatActivity {

    User user;
    UserControl userControl;
    LeagueControl leagueControl;

    TextView groupName;

    private ListView listView;
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_group_activity);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        userControl = UserControl.get(this.getApplicationContext());
        leagueControl = leagueControl.get(this.getApplicationContext());

        user = userControl.getUserByName(name);

        groupName = findViewById(R.id.removeGroupText);

        listView = (ListView) findViewById(R.id.groups_list);

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


    }

    public void remove(View v) {
        List<User> userList = userControl.getAllUsers();

        for(User u : userList) {
            if(u.getGroupName().equals(groupName.getText().toString())) {
                u.setGroupName("none");
            }
        }
        leagueControl.delete(groupName.getText().toString());
        Intent intent = new Intent(removeGroup_activity.this, admin_activity.class);
        intent.putExtra("name", user.getUsername());
        startActivity(intent);
    }

}
