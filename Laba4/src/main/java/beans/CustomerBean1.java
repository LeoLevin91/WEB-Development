package beans;


import database.DataSource;
import musicLibrary.User;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@ManagedBean(name = "user", eager = true)
@SessionScoped
public class CustomerBean1 {
    private DataSource dataSource = new DataSource();
    private UserEJB userEJB = new UserEJB();
    private User user = new User();

    public String getName(){
        return user.getName();
    }

    public void setName(String name){
        this.user.setName(name);
    }

    public String getLogin(){
        return user.getLogin();
    }

    public String getPassword(){
        return user.getPassword();
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public String validateUserLogin() throws SQLException, ClassNotFoundException {
        if (userEJB.validateUserLogin(user.getLogin(), user.getPassword()) != null) {
            user = userEJB.validateUserLogin(user.getLogin(), user.getPassword());
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.setAttribute("user", this);
            return "result";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Неверные данные"));
            return "login";
        }
    }

    public String logout(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        return "login";
    }





}
