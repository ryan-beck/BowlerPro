package firstapp.ryanbeck.bowler_pro.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import firstapp.ryanbeck.bowler_pro.R;

public class logGame_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_game_activity);
    }

    public void submit(View v) {
        //TODO: submit to data base here

        Intent intent = new Intent(logGame_activity.this, MainActivity.class);
        startActivity(intent);
    }
}
