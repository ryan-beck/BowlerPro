package firstapp.ryanbeck.bowler_pro.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

import firstapp.ryanbeck.bowler_pro.R;
import firstapp.ryanbeck.bowler_pro.User;
import firstapp.ryanbeck.bowler_pro.UserControl;

public class sign_in_activity extends AppCompatActivity {

    TextView username;
    TextView password;

    User user;
    UserControl userControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        userControl = UserControl.get(this.getApplicationContext());

        username = findViewById(R.id.usernameText);
        password = findViewById((R.id.passwordText));


    }

    private void backToMain() {
        MainActivity.sign_in();
        Intent intent = new Intent(sign_in_activity.this, MainActivity.class);
        startActivity(intent);
    }

    public void signIn(View v) {
        //if user data is correct

        //else
            // prompt to retry
    }


    public void create(View v) {
        String name = username.getText().toString();
        String pass = password.getText().toString();
        // logic to check if username already exists in DB

        if(userControl.usernameExists(name)) {
            Toast.makeText(this, "User name already exists", Toast.LENGTH_SHORT).show();
        } else {
            if(name.equals("admin2") && pass.equals("admin2")) {
                user = new User(UUID.randomUUID(), true, name, pass);
            }else {
                user = new User(UUID.randomUUID(),false, name, pass);
            }
            userControl.addUser(user);
            backToMain();

        }
    }
}
