package database;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class TableStart {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        TestUserDataBase gs = new TestUserDataBase();
        try {
            gs.startTest();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
