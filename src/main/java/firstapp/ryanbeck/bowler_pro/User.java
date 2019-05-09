package firstapp.ryanbeck.bowler_pro;

import java.util.ArrayList;
import java.util.UUID;

public class User {

    private UUID id;
    private boolean admin;
    private String username;
    private String password;
    private ArrayList<Game> games;

//    public User(boolean admin, String username, String password) {
//        id = UUID.randomUUID();
//        this.admin = admin;
//        this.username = username;
//        this.password = password;
//    }

    public User(UUID id, boolean admin, String username, String password) {
        this.id = id;
        this.admin = admin;
        this.username = username;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public boolean isAdmin() {
        return admin;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void addGame(Game game) {
        games.add(game);
    }
}
