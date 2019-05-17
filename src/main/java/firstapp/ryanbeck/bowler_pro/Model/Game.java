package firstapp.ryanbeck.bowler_pro.Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Game {
    private int score;
    private int strikes;
    private int spares;
    private UUID id;
    private UUID playerId;
    private Date date;

    private SimpleDateFormat sf= new SimpleDateFormat("dd-MM-yyyy");


    public Game(int score, int strikes, int spares, UUID id, UUID playerId, Date date) {
        this.score = score;
        this.strikes = strikes;
        this.spares = spares;
        this.id = id;
        this.playerId = playerId;
        this.date = date;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getStrikes() {
        return strikes;
    }

    public void setStrikes(int strikes) {
        this.strikes = strikes;
    }

    public int getSpares() {
        return spares;
    }

    public void setSpares(int spares) {
        this.spares = spares;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return sf.format(date) + "                             " + score + "              " + strikes + "              " + spares;
    }
}
