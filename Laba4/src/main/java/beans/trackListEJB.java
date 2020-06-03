package beans;

import database.DataSource;
import musicLibrary.Track;

import javax.faces.application.FacesMessage;
import javax.faces.validator.ValidatorException;
import java.sql.SQLException;

public class trackListEJB {
    public void addTrack(Track track, int ID) throws SQLException, ClassNotFoundException {
        DataSource dataSource = new DataSource();
        if(dataSource.existTrack(track)){
            FacesMessage facesMessage = new FacesMessage("Такой трек уже существует.");
            throw new ValidatorException(facesMessage);
        }
        dataSource.insertTrack(track, ID);
    }
}
