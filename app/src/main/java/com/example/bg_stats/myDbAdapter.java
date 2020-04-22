package com.example.bg_stats;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class myDbAdapter {

    myDbHelper myhelper;
    ArrayList<String> gameList = new ArrayList<>(Arrays.asList("Carcassonne", "Catan", "Final_Boss", "Utopia", "Small_World", "Yucatan", "Horus"));

    public myDbAdapter(Context context) {
        myhelper = new myDbHelper(context);
    }

    public Boolean usernameCheck(String username) {

        SQLiteDatabase db = myhelper.getWritableDatabase();
        String selectQuery = "SELECT Name from MainTable where Name='" + username + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        boolean res = (cursor != null) && (cursor.getCount() > 0);
        cursor.close();
        return res;

    }

    public Boolean passwordCheck(String username, String password) {

        SQLiteDatabase db = myhelper.getWritableDatabase();
        String selectQuery = "SELECT Name from MainTable where Name='" + username + "' and Password='" + password + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        boolean res = (cursor != null) && (cursor.getCount() > 0);
        cursor.close();
        return res;

    }

    public Boolean insertNewUser(String username, String password) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String selectQuery = "SELECT * from MainTable where Name='" + username + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        boolean res = (cursor != null) && (cursor.getCount() > 0);
        if (res) {
            cursor.close();
            return false;
        } else {
            db.execSQL("INSERT INTO MainTable (Name, Password, Carcassonne, Catan, Final_Boss, Utopia, Small_World, Yucatan, Horus) " +
                    "VALUES ('" + username + "','" + password + "', 'NO', 'NO', 'NO', 'NO', 'NO', 'NO', 'NO')");
            cursor.close();
            return true;
        }

    }

    public void changeUserName(String username, String newUserName) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String selectQuery = "SELECT Name from MainTable where Name='" + username + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        db.execSQL("UPDATE MainTable " +
                "SET Name = '" + newUserName +
                "' WHERE Name = '" + username + "'");

        cursor.close();
    }

    public void changePassword(String password, String newPassWord) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String selectQuery = "SELECT Name from MainTable where PassWord='" + password + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        db.execSQL("UPDATE MainTable " +
                "SET PassWord = '" + newPassWord +
                "' WHERE PassWord = '" + password + "'");

        cursor.close();
    }

    public ArrayList<String> getGamesByUser(String username) {
        ArrayList<String> gamesUsername = new ArrayList<>();
        SQLiteDatabase db = myhelper.getReadableDatabase();
        String selectQuery = "SELECT * from MainTable where Name='" + username + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        for (int index = 3; index < gameList.size() + 3; index++) {
            String res = cursor.getString(index);
            if (res.equals("YES")) {
                gamesUsername.add(cursor.getColumnName(index).replace("_", " "));
            }
        }
        return gamesUsername;
    }

    public ArrayList<String> getAllGames() {
        return gameList;
    }

    public void setUserGames(String username, ArrayList<String> valueUser) {
        //UPDATE 'MainTable' SET `gamename from gamelist` = 'YES/NO from valueUser' WHERE `username` = username;
        SQLiteDatabase db = myhelper.getWritableDatabase();
        for (int index = 0; index < gameList.size(); index++) {
            db.execSQL("UPDATE 'MainTable' SET '" + gameList.get(index) + "' = '" + valueUser.get(index) + "' WHERE Name='" + username + "'");
        }
    }

    public void setNewGame(ArrayList<String> parameters) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        db.execSQL("INSERT INTO GameTable (GameName, Player_1, Player_2, Player_3, Player_4, Player_5, Player_6, Player_7, Player_8, " +
                "Score_1, Score_2, Score_3, Score_4, Score_5, Score_6, Score_7, Score_8, Commentary, Date) " +
                "VALUES ('" + parameters.get(0) + "','" + parameters.get(1) + "','" + parameters.get(2) + "','" + parameters.get(3) + "','" + parameters.get(4) + "','"
                + parameters.get(5) + "','" + parameters.get(6) + "','" + parameters.get(7) + "','" + parameters.get(8) + "','" + parameters.get(9) + "','"
                + parameters.get(10) + "','" + parameters.get(11) + "','" + parameters.get(12) + "','" + parameters.get(13) + "','" + parameters.get(14) + "','"
                + parameters.get(15) + "','" + parameters.get(16) + "','" + parameters.get(17) + "','" + parameters.get(18) + "')");
    }

    public Integer getNumberOfGamesPlayedByUserAndGame(String username, String game) {
        SQLiteDatabase db = myhelper.getReadableDatabase();
        String selectQuery = "SELECT * from GameTable where GameName = '" + game + "' and (Player_1 = '" + username + "' or Player_2 = '" + username + "' or Player_3 = '" + username
                + "' or Player_4 = '" + username + "' or Player_5 = '" + username + "' or Player_6 = '" + username
                + "' or Player_7 = '" + username + "' or Player_8 = '" + username + "')";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() <= 0) {
            return 0;
        } else {
            return cursor.getCount();
        }
    }

    public Integer getNumberOfWinsByUserAndGame(String username, String game) {
        SQLiteDatabase db = myhelper.getReadableDatabase();
        String selectQuery = "SELECT * from GameTable where Player_1 = '" + username + "' and GameName = '" + game + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() <= 0) {
            return 0;
        } else {
            return cursor.getCount();
        }
    }

    public ArrayList<String> getAllUsers() {
        SQLiteDatabase db = myhelper.getReadableDatabase();
        String selectQuery = "SELECT Name from MainTable";
        ArrayList<String> userList = new ArrayList<>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        while (cursor.moveToNext()) {
            userList.add(cursor.getString(0));
        }
        return userList;
    }

    public ArrayList<GamePreview> getGamesPlayedByUserAndGame(String username, String game) {
        SQLiteDatabase db = myhelper.getReadableDatabase();
        String selectQuery = "SELECT * from GameTable where GameName = '" + game + "' and (Player_1 = '" + username + "' or Player_2 = '" + username + "' or Player_3 = '" + username
                + "' or Player_4 = '" + username + "' or Player_5 = '" + username + "' or Player_6 = '" + username
                + "' or Player_7 = '" + username + "' or Player_8 = '" + username + "')";
        ArrayList<GamePreview> listedGames = new ArrayList<>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        while (cursor.moveToNext()) {
            ArrayList<String> row = new ArrayList<>();
            for (int index = 1; index < cursor.getColumnCount(); index ++) {
                row.add(cursor.getString(index));
            }
            GamePreview gameRow = new GamePreview(row);
            listedGames.add(gameRow);
        }
        return listedGames;
    }

    public int[] getFilteredGamesByMonthsAndUserAndGame(String username, String game) {
        int[] filteredGames = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        SQLiteDatabase db = myhelper.getReadableDatabase();
        String selectQuery = "SELECT * from GameTable where GameName = '" + game + "' and (Player_1 = '" + username + "' or Player_2 = '" + username + "' or Player_3 = '" + username
                + "' or Player_4 = '" + username + "' or Player_5 = '" + username + "' or Player_6 = '" + username
                + "' or Player_7 = '" + username + "' or Player_8 = '" + username + "')";

        Cursor cursor = db.rawQuery(selectQuery, null);
        while (cursor.moveToNext()) {
            String dateParameter = cursor.getString(19);
            int month = Integer.parseInt(dateParameter.substring(3, 5));
            filteredGames[month-1] += 1;
        }

        return filteredGames;
    }

    static class myDbHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "myDatabase";    // Database Name
        private static final String MAIN_TABLE = "MainTable";   // Table Name
        private static final int DATABASE_Version = 1;   // Database Version
        private static final String UID = "_id";     // Column I (Primary Key)
        private static final String NAME = "Name";    //Column II
        private static final String PASSWORD = "Password";    // Column III
        private static final String CARCASSONE = "Carcassonne";
        private static final String CATAN = "Catan";
        private static final String FINALBOSS = "Final_Boss";
        private static final String UTOPIA = "Utopia";
        private static final String SMALLWORLD = "Small_World";
        private static final String YUCATAN = "Yucatan";
        private static final String HORUS = "Horus";
        private static final String CREATE_MAIN_TABLE = "CREATE TABLE " + MAIN_TABLE +
                " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " VARCHAR(255) ,"
                + PASSWORD + " VARCHAR(225) ,"
                + CARCASSONE + " VARCHAR(3) ,"
                + CATAN + " VARCHAR(3) ,"
                + FINALBOSS + " VARCHAR(3) ,"
                + UTOPIA + " VARCHAR(3) ,"
                + SMALLWORLD + " VARCHAR(3) ,"
                + YUCATAN + " VARCHAR(3) ,"
                + HORUS + " VARCHAR(3));";
        private static final String GAME_TABLE = "GameTable";   // Table Name
        private static final String GAME = "GameName";    //Column II
        private static final String ID_1 = "Player_1";    //Column III
        private static final String ID_2 = "Player_2";    //Column IV
        private static final String ID_3 = "Player_3";    //Column V
        private static final String ID_4 = "Player_4";    //Column VI
        private static final String ID_5 = "Player_5";    //Column VII
        private static final String ID_6 = "Player_6";    //Column VIII
        private static final String ID_7 = "Player_7";    //Column IX
        private static final String ID_8 = "Player_8";    //Column X
        private static final String SCORE_1 = "Score_1";    //Column XI
        private static final String SCORE_2 = "Score_2";    //Column XII
        private static final String SCORE_3 = "Score_3";    //Column XIII
        private static final String SCORE_4 = "Score_4";    //Column XIV
        private static final String SCORE_5 = "Score_5";    //Column XV
        private static final String SCORE_6 = "Score_6";    //Column XVI
        private static final String SCORE_7 = "Score_7";    //Column XVII
        private static final String SCORE_8 = "Score_8";    //Column XVIII
        private static final String COMMENTARY = "Commentary";        //Column XIX
        private static final String DATE = "Date";        //Column XX
        private static final String CREATE_GAME_TABLE = "CREATE TABLE " + GAME_TABLE +
                " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + GAME + " VARCHAR(50) ,"
                + ID_1 + " VARCHAR(8) ,"
                + ID_2 + " VARCHAR(8) ,"
                + ID_3 + " VARCHAR(8) ,"
                + ID_4 + " VARCHAR(8) ,"
                + ID_5 + " VARCHAR(8) ,"
                + ID_6 + " VARCHAR(8) ,"
                + ID_7 + " VARCHAR(8) ,"
                + ID_8 + " VARCHAR(8) ,"
                + SCORE_1 + " VARCHAR(10) ,"
                + SCORE_2 + " VARCHAR(10) ,"
                + SCORE_3 + " VARCHAR(10) ,"
                + SCORE_4 + " VARCHAR(10) ,"
                + SCORE_5 + " VARCHAR(10) ,"
                + SCORE_6 + " VARCHAR(10) ,"
                + SCORE_7 + " VARCHAR(10) ,"
                + SCORE_8 + " VARCHAR(10) ,"
                + COMMENTARY + " VARCHAR(50) ,"
                + DATE + " VARCHAR(50));";
        private static final String DROP_MAIN_TABLE = "DROP TABLE IF EXISTS " + MAIN_TABLE;
        private static final String DROP_GAME_TABLE = "DROP TABLE IF EXISTS " + GAME_TABLE;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context = context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_MAIN_TABLE);
                db.execSQL(CREATE_GAME_TABLE);
            } catch (Exception e) {
                Toast.makeText(context,
                        "Error - " + e, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Toast.makeText(context,
                        "On Upgrade", Toast.LENGTH_SHORT).show();
                db.execSQL(DROP_MAIN_TABLE);
                db.execSQL(DROP_GAME_TABLE);
                onCreate(db);
            } catch (Exception e) {
                Toast.makeText(context,
                        "Error - " + e, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
