package firstapp.ryanbeck.bowler_pro.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

import firstapp.ryanbeck.bowler_pro.R;
import firstapp.ryanbeck.bowler_pro.Model.User;
import firstapp.ryanbeck.bowler_pro.Controller.UserControl;

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

    private void backToMain(User u) {
        MainActivity.sign_in();
        Intent intent = new Intent(sign_in_activity.this, MainActivity.class);
        intent.putExtra("name", u.getUsername());
        startActivity(intent);
    }

    public void signIn(View v) {

        String name = username.getText().toString();
        String pass = password.getText().toString();

        if(!name.equals("") && !pass.equals("")) {
            user = userControl.getUserByName(name);
            //if user data is correct
            if(user != null) {
                if(user.getPassword().equals(pass)) {
                    backToMain(user);
                } else {
                    Toast.makeText(this, "Incorrect password", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Username does not exist", Toast.LENGTH_LONG).show();
            }
        }


    }


    public void create(View v) {
        String name = username.getText().toString();
        String pass = password.getText().toString();

        if(!name.equals("") && pass.length() >= 6) {
            // logic to check if username already exists in DB

            if (userControl.usernameExists(name)) {
                Toast.makeText(this, "Username already exists", Toast.LENGTH_LONG).show();
            } else {
                user = new User(UUID.randomUUID(), false, name, pass, "none");
                userControl.addUser(user);
                backToMain(user);
            }
        } else {
            Toast.makeText(this, "Invalid username or password\nPasswords must be at least 6 characters", Toast.LENGTH_LONG).show();
        }
    }
}
