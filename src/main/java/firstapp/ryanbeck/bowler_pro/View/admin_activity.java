package firstapp.ryanbeck.bowler_pro.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import firstapp.ryanbeck.bowler_pro.Controller.UserControl;
import firstapp.ryanbeck.bowler_pro.Model.User;
import firstapp.ryanbeck.bowler_pro.R;

public class admin_activity extends AppCompatActivity {

    User user;
    UserControl userControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_activity);

        userControl = UserControl.get(this.getApplicationContext());

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        user = userControl.getUserByName(name);


    }

    public void addGroup(View v) {
        Intent intent = new Intent(admin_activity.this, addGroup_activity.class);
        intent.putExtra("name", user.getUsername());
        startActivity(intent);
    }

    //TODO: remove group

    public void gameLogs(View v) {
        Intent intent = new Intent(admin_activity.this, view_game_log_activity.class);
        intent.putExtra("name", user.getUsername());
        startActivity(intent);
    }
}
