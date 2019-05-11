package firstapp.ryanbeck.bowler_pro.Model;

import java.util.UUID;

public class Game {
    private int score;
    private int strikes;
    private int spares;
    private UUID id;
    private UUID playerId;

    public Game(int score, int strikes, int spares, UUID id, UUID playerId) {
        this.score = score;
        this.strikes = strikes;
        this.spares = spares;
        this.id = id;
        this.playerId = playerId;
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
}
