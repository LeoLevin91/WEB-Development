package beans;

import musicLibrary.Track;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import java.io.IOException;
import java.sql.SQLException;


@ManagedBean
@SessionScoped
public class trackListBean {
    private trackListEJB trackListEJB = new trackListEJB();
    private int ID;
    private String trackTitle;
    private double size;
    private long duration;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTrackTitle() {
        return trackTitle;
    }

    public void setTrackTitle(String trackTitle) {
        this.trackTitle = trackTitle;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }


    public void insertTrack(int id) {
        this.ID = id;
        System.out.println(ID);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("insert.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void titleValidator(FacesContext facesContext, UIComponent uiComponent, Object o){
        String toValidate = "filler";
        try {
            toValidate = o.toString();
            if (toValidate.equals("")) {
                FacesMessage facesMessage = new FacesMessage("Имя должно быть непустым.");
                throw new ValidatorException(facesMessage);
            }
        } catch (ValidatorException e) {
            e.printStackTrace();
        }
    }

    public void durationValidator(FacesContext facesContext, UIComponent uiComponent, Object o) {
        long toValidate;
        toValidate = Integer.parseInt(o.toString());
        if (toValidate <= 0) {
            FacesMessage facesMessage = new FacesMessage("Число должно быть положительным.");
            throw new ValidatorException(facesMessage);
        }
    }

    public void sizeValidator(FacesContext facesContext, UIComponent uiComponent, Object o) {
        long toValidate;
        toValidate = (long) Double.parseDouble(o.toString());
        if (toValidate <= 0) {
            FacesMessage facesMessage = new FacesMessage("Число должно быть положительным.");
            throw new ValidatorException(facesMessage);
        }
    }

    public String addTrack(UserBean userBean){
        Track track = new Track(trackTitle, size, duration);
        try {
            trackListEJB.addTrack(track, ID);
            userBean.updateUser();
        } catch (SQLException e) {
            e.printStackTrace();
            return "error";
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "success";
    }



}
