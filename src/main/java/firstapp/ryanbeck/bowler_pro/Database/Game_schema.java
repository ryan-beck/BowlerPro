package firstapp.ryanbeck.bowler_pro.Database;

public class Game_schema {
    public static final class gameTable {
        public static final String name = "game";

        public static final class cols {
            public static final String UUID = "uuid";
            public static final String playerUUID = "player_uuid";
            public static final String score = "score";
            public static final String strikes = "strikes";
            public static final String spares = "spares";
        }
    }
}
