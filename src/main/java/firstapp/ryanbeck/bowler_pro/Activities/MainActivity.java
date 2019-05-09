package firstapp.ryanbeck.bowler_pro.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import firstapp.ryanbeck.bowler_pro.R;
import firstapp.ryanbeck.bowler_pro.User;

public class MainActivity extends AppCompatActivity {

    private static boolean isSignedIn = false;

    private static User user = null;

    static public void sign_in() {
        isSignedIn = !isSignedIn;
    }

    Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signIn = findViewById(R.id.sign_in_button);
        if(isSignedIn) {
            signIn.setText(R.string.signOUT_button);
        } else {
            signIn.setText(R.string.signIN_button);
        }
    }

    public void createAccount(View v) {
        if(!isSignedIn) {
            Intent intent = new Intent(MainActivity.this, sign_in_activity.class);
            startActivity(intent);
        } else {
            // alert to confirm sign out
            Toast.makeText(this, "sign out here", Toast.LENGTH_SHORT).show();
        }
    }

    public void logGame(View v) {
        if(isSignedIn) {
            // generate log game activity
            Intent intent = new Intent(MainActivity.this, logGame_activity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.toast, Toast.LENGTH_SHORT).show();
        }
    }

    public void playerStats(View v) {
        if(isSignedIn) {
            // generate activity for stats
            Toast.makeText(this, "good", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.toast, Toast.LENGTH_SHORT).show();
        }
    }

    public void setUser(User user) {
        this.user = user;
    }
}
