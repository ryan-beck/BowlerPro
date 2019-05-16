package firstapp.ryanbeck.bowler_pro.Database.Game;

public class Game_schema {
    public static final class gameTable {
        public static final String NAME = "game";

        public static final class cols {
            public static final String UUID = "uuid";
            public static final String PLAYER_UUID = "player_uuid";
            public static final String SCORE = "score";
            public static final String STRIKES = "strikes";
            public static final String SPARES = "spares";
            public static final String DATE = "date";
        }
    }
}
