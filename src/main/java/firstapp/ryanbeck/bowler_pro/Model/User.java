package firstapp.ryanbeck.bowler_pro.Model;

import java.util.ArrayList;
import java.util.UUID;

public class User implements Comparable< User >{

    private UUID id;
    private boolean admin;
    private String username;
    private String password;
    private String groupName;
    private Double average;

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

    public void setAverage(double average) {
        this.average = average;
    }

    public Double getAverage() {
        return average;
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

    @Override
    public int compareTo(User o) {
        return this.getAverage().compareTo(o.getAverage());
    }

}
