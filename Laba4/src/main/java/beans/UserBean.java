package beans;


import database.DataSource;
import musicLibrary.Track;
import musicLibrary.TrackList;
import musicLibrary.User;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;

@ManagedBean
@SessionScoped
public class UserBean {
    private final String MESSAGE_USER_NOT_FOUND = "notfound";
    private final String MESSAGE_USER_IS_FOUND = "found";
    private final String MESSAGE_SQL_ERROR = "sqlerror";

    @EJB
    private final UserEJB userEJB;
    private User user;
    private String login;
    private String password;
    private String message = "User";

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    public UserBean(){
        this.userEJB = new UserEJB();
    }

    public String validateUserLogin() {
        try {
            user = userEJB.validateUserLogin(login, password);

            if (user == null) {
                return message = MESSAGE_USER_NOT_FOUND;
            }
            else {
                return message = MESSAGE_USER_IS_FOUND;
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            return message = MESSAGE_SQL_ERROR;
        }
    }

}

