package firstapp.ryanbeck.bowler_pro.Model;

import java.util.ArrayList;

public class League {

    private ArrayList<User> members;

    public ArrayList<User> getMembers() {
        return members;
    }

    public void addMember(User user) {
        members.add(user);
    }
}
