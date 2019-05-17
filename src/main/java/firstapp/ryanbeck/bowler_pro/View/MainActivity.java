package firstapp.ryanbeck.bowler_pro.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import firstapp.ryanbeck.bowler_pro.R;
import firstapp.ryanbeck.bowler_pro.Model.User;
import firstapp.ryanbeck.bowler_pro.Controller.UserControl;

public class MainActivity extends AppCompatActivity {

    private static boolean isSignedIn = false;

    private User user = null;
    UserControl userControl;

    static public void sign_in() {
        isSignedIn = !isSignedIn;
    }

    Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userControl = UserControl.get(this.getApplicationContext());

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        if(name != null) {
            user = userControl.getUserByName(name);
        }


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
            AlertDialog alert = signOutAlert();
            alert.show();
            //Toast.makeText(this, "sign out here", Toast.LENGTH_SHORT).show();
        }
    }

    public void logGame(View v) {
        if(isSignedIn) {
            // generate log game activity
            Intent intent = new Intent(MainActivity.this, logGame_activity.class);
            intent.putExtra("name", user.getUsername());
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.sign_in_toast, Toast.LENGTH_SHORT).show();
        }
    }

    public void playerStats(View v) {
        if(isSignedIn) {
            // generate activity for stats
            Intent intent = new Intent(MainActivity.this, playerStats_activity.class);
            intent.putExtra("name", user.getUsername());
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.sign_in_toast, Toast.LENGTH_SHORT).show();
        }
    }

    public void manage(View v) {
        if(isSignedIn && user.isAdmin()) {
            Intent intent = new Intent(MainActivity.this, admin_activity.class);
            intent.putExtra("name", user.getUsername());
            startActivity(intent);
        } else {
            Toast.makeText(this, "No admin access", Toast.LENGTH_SHORT).show();
        }
    }



    public void setUser(User user) {
        this.user = user;
    }

    private AlertDialog signOutAlert() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
        final AlertDialog alertDialog = alertBuilder.create();

        alertBuilder.setMessage("Are you sure you want to sign out\n");

        alertBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                sign_in();
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
//                intent.putExtra("name", null);
                startActivity(intent);
            }
        });

        alertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                alertDialog.dismiss();
            }
        });

        AlertDialog goodAlert = alertBuilder.create();
        return goodAlert;
    }
}
