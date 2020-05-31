package beans;


import database.DataSource;
import musicLibrary.User;
import org.w3c.dom.ls.LSOutput;

import javax.ejb.Stateless;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Stateless

public class UserEJB {

    private HttpSession session;

    private DataSource dataSource = new DataSource();

    public User validateUserLogin(String login, String password) {
        User user = new User();
        try {
            if (dataSource.existUser(login, password)) {
                // Проверка User-а если он есть возвращаем юзера
                System.out.println("User exsist?: " + dataSource.existUser(login, password));
                user.setLogin(login);
                user.setPassword(password);
            } else {
                user = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("User is: " + user);
        return user;
    }

}
