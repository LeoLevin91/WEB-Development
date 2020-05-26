package musicLibrary;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.*;
import java.util.ArrayList;


@XmlRootElement
public class User implements Serializable{
    // Экземпляры класса User. Класс User сериализуемый
    private int id;
    private String name;
    private String login;
    private String password;
    private ArrayList<TrackList> trackLists;

    public User(int id, String name, String login, String password, ArrayList<TrackList> trackLists) {
        // Конструктор класса
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.trackLists = trackLists;
    }

    public User() {}

    // Получение идентификатора
    public int getId() {
        return id;
    }

    // Изменения идентификатора
    public void setId(int id) {
        this.id = id;
    }

    // Получение имени
    public String getName() {
        return name;
    }

    // Изменение имени
    public void setName(String name) {
        this.name = name;
    }

    // Получение логина
    public String getLogin() {
        return login;
    }

    // Изменение логина
    public void setLogin(String login) {
        this.login = login;
    }


    public String getPassword() {
        return password;
    }

    @XmlTransient
    public void setPassword(String password) {
        this.password = password;
    }

    // Получение массива
    public ArrayList<TrackList> getTrackLists() {
        return trackLists;
    }

    @XmlElementWrapper(name = "trackLists")
    @XmlElement(name = "trackList")
    public void setTrackLists(ArrayList<TrackList> trackLists){
        this.trackLists = trackLists;
    }

    // Получение длинны массива по индексу
    public TrackList getTrackListOnIndex(int index){return this.trackLists.get(index);}
    // Получение размера массива
    public int getSizeTrackList(){return this.trackLists.size();}

    // Добавление элемента в массив
    public void addInTrackList(TrackList trackList){this.trackLists.add(trackList);}

    // Удаление элемента из списка по иедексу
    public void deleteTrackList(int index){this.trackLists.remove(index);}

    // Сериализация
    public static User loading(String name) throws IOException {

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(name))) {
            User temp = (User)in.readObject();
            return temp;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void saveing(String name, User user) throws IOException {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(name))){
            out.writeObject(user);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
