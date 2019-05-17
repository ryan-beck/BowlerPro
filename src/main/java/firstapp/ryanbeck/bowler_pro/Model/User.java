package firstapp.ryanbeck.bowler_pro.Model;

import java.util.ArrayList;
import java.util.UUID;

public class User {

    private UUID id;
    private boolean admin;
    private String username;
    private String password;
    private String groupName;

//    public User(boolean admin, String username, String password) {
//        id = UUID.randomUUID();
//        this.admin = admin;
//        this.username = username;
//        this.password = password;
//    }

    public User(UUID id, boolean admin, String username, String password, String groupName) {
        this.id = id;
        this.admin = admin;
        this.username = username;
        this.password = password;
        this.groupName = groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
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

}
