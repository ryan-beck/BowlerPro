package firstapp.ryanbeck.bowler_pro.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.UUID;

import firstapp.ryanbeck.bowler_pro.Controller.LeagueControl;
import firstapp.ryanbeck.bowler_pro.Controller.UserControl;
import firstapp.ryanbeck.bowler_pro.Model.League;
import firstapp.ryanbeck.bowler_pro.Model.User;
import firstapp.ryanbeck.bowler_pro.R;

public class addGroup_activity extends AppCompatActivity {

    TextView groupName;
    User user;
    UserControl userControl;
    LeagueControl leagueControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group_activity);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        userControl = UserControl.get(this.getApplicationContext());
        leagueControl = leagueControl.get(this.getApplicationContext());

        user = userControl.getUserByName(name);

        groupName = findViewById(R.id.addgroup);
    }

    public void createGroup(View v) {
        String text = groupName.getText().toString();
        League newLeague = new League(text, UUID.randomUUID());
        leagueControl.addLeague(newLeague);
        Intent intent = new Intent(addGroup_activity.this, admin_activity.class);
        intent.putExtra("name", user.getUsername());
        startActivity(intent);
    }
}
