package beans;


import database.DataSource;
import musicLibrary.User;
import org.w3c.dom.ls.LSOutput;

import javax.ejb.Stateless;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Stateless
public class UserEJB {

    public User validateUserLogin(String login, String password) throws SQLException, ClassNotFoundException {
        DataSource dataSource = new DataSource();
        if(dataSource.existUser(login, password)){
            return dataSource.getUser(login, password);
        } else {
            return null;
        }
    }

    public void deleteTrack(String trackTitle) throws SQLException, ClassNotFoundException {
        DataSource dataSource = new DataSource();
        dataSource.deleteTrack(trackTitle);
    }

}
