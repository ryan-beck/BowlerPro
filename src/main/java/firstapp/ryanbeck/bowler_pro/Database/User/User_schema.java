package firstapp.ryanbeck.bowler_pro.Database.User;

public class User_schema {
    public static final class userTable {
        public static final String NAME = "user";

        public static final class cols {
            public static final String UUID = "uuid";
            public static final String ADMIN = "admin";
            public static final String USERNAME = "username";
            public static final String PASSWORD = "password";
            public static final String LEAGUE_NAME = "leagueuuid";
        }
    }
}
