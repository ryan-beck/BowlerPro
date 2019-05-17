package firstapp.ryanbeck.bowler_pro.Model;

import java.util.ArrayList;
import java.util.UUID;

public class League {

    private String name;
    private UUID id;

    public League(String name, UUID id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    private ArrayList<User> members;

    public ArrayList<User> getMembers() {
        return members;
    }

    public void addMember(User user) {
        members.add(user);
    }


}
