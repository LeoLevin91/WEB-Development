package beans;


import database.DataSource;
import musicLibrary.Track;
import musicLibrary.TrackList;
import musicLibrary.User;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;

@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean {
    private User user;
    private UserEJB userEJB;
    private Track track;
    private TrackList trackList;
    private DataSource dataSource;

    public User getUser() {
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public UserEJB getUserEJB() {
        return userEJB;
    }

    public void setUserEJB(UserEJB userEJB) {
        this.userEJB = userEJB;
    }

    public UserBean(){
        user = new User();
        userEJB = new UserEJB();
        track = new Track();
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    // Делегация управления
    public String goToResultPage(){
        User userFromDataSource = userEJB.validateUserLogin(user.getLogin(), user.getPassword());
        if(userFromDataSource != null){
            this.user = userFromDataSource;
            return "result";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Такого пользователя не существует!"));
            return "index";
        }
    }

    public String seeXmlView(){
        return this.goToResultPage().equals("index") ? "index" : "resultXML";
    }

    public ArrayList<TrackList> getUserTrackLists() throws SQLException, ClassNotFoundException {
        return dataSource.findTrackListByUser(user);
    }

}

