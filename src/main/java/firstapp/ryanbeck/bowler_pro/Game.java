package firstapp.ryanbeck.bowler_pro;

public class Game {
    private int score;
    private int strikes;
    private int spares;

    public Game(int score, int strikes, int spares) {
        this.score = score;
        this.strikes = strikes;
        this.spares = spares;
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
}
