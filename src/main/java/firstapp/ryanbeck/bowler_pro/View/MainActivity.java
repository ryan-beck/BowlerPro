package firstapp.ryanbeck.bowler_pro.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Date;
import java.util.UUID;

import firstapp.ryanbeck.bowler_pro.Controller.GameControl;
import firstapp.ryanbeck.bowler_pro.Controller.LeagueControl;
import firstapp.ryanbeck.bowler_pro.Model.Game;
import firstapp.ryanbeck.bowler_pro.Model.League;
import firstapp.ryanbeck.bowler_pro.R;
import firstapp.ryanbeck.bowler_pro.Model.User;
import firstapp.ryanbeck.bowler_pro.Controller.UserControl;

public class MainActivity extends AppCompatActivity {

    private static boolean isSignedIn = false;

    private User user = null;
    UserControl userControl;
    LeagueControl leagueControl;
    GameControl gameControl;

    static public void sign_in() {
        isSignedIn = !isSignedIn;
    }

    Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userControl = UserControl.get(this.getApplicationContext());
        leagueControl = LeagueControl.get(this.getApplicationContext());
        gameControl = GameControl.get(this.getApplicationContext());

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


        if(userControl.getAllUsers().isEmpty()) {
            User admin = new User(UUID.randomUUID(), true, "admin2", "admin2", "none");
            userControl.addUser(admin);

            League group1 = new League("Gungans", UUID.randomUUID());
            leagueControl.addLeague(group1);
            User u1 = new User(UUID.randomUUID(), false, "Jar Jar Binks", "meeesa", "Gungans");
            userControl.addUser(u1);
            //Game(int score, int strikes, int spares, UUID id, UUID playerId, Date date)
            Game g1 = new Game(9, 0, 0, UUID.randomUUID(), u1.getId(), new Date());
            gameControl.addGame(g1);
            User u2 = new User(UUID.randomUUID(), false, "Boss Nass", "wesaFriends", "Gungans");
            userControl.addUser(u2);
            Game g2 = new Game(72, 0, 2, UUID.randomUUID(), u2.getId(), new Date());
            gameControl.addGame(g2);
            User u3 = new User(UUID.randomUUID(), false, "Captain Tarples", "OuchTime", "Gungans");
            userControl.addUser(u3);
            Game g3 = new Game(120, 1, 3, UUID.randomUUID(), u3.getId(), new Date());
            gameControl.addGame(g3);

            League group2 = new League("Peter's Friends", UUID.randomUUID());
            leagueControl.addLeague(group2);
            User u4 = new User(UUID.randomUUID(), false, "Spidey Boy", "IDontFeelSoGood", "Peter's Friends");
            userControl.addUser(u4);
            Game g4 = new Game(99, 1, 3, UUID.randomUUID(), u4.getId(), new Date());
            gameControl.addGame(g4);
            User u5 = new User(UUID.randomUUID(), false, "Mr. Stark", "goHomeSquidward", "Peter's Friends");
            userControl.addUser(u5);
            Game g5 = new Game(95, 0, 4, UUID.randomUUID(), u5.getId(), new Date());
            gameControl.addGame(g5);
            User u6 = new User(UUID.randomUUID(), false, "The Wizard", "hitherToUndreamptOf", "Peter's Friends");
            userControl.addUser(u6);
            Game g6 = new Game(145, 2, 4, UUID.randomUUID(), u6.getId(), new Date());
            gameControl.addGame(g6);

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
            Toast.makeText(this, R.string.sign_in_toast, Toast.LENGTH_LONG).show();
        }
    }

    public void playerStats(View v) {
        if(isSignedIn) {
            // generate activity for stats
            Intent intent = new Intent(MainActivity.this, playerStats_activity.class);
            intent.putExtra("name", user.getUsername());
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.sign_in_toast, Toast.LENGTH_LONG).show();
        }
    }

    public void group(View v) {
        if(isSignedIn) {
            if(!user.getGroupName().equals("none")) {
                Intent intent = new Intent(MainActivity.this, groupInfo_activity.class);
                intent.putExtra("name", user.getUsername());
                startActivity(intent);
            } else {
                Intent intent = new Intent(MainActivity.this, joinGroup_activity.class);
                intent.putExtra("name", user.getUsername());
                startActivity(intent);
            }
        }else {
            Toast.makeText(this, R.string.sign_in_toast, Toast.LENGTH_LONG).show();
        }
    }

    public void manage(View v) {
        if(isSignedIn && user.isAdmin()) {
            Intent intent = new Intent(MainActivity.this, admin_activity.class);
            intent.putExtra("name", user.getUsername());
            startActivity(intent);
        } else {
            Toast.makeText(this, "No admin access", Toast.LENGTH_LONG).show();
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
