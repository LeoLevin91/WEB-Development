package Track_List.TrackListTwo;

import java.util.ArrayList;

public class User {
    private long id;
    private String name;
    private String login;
    private String password;
    private ArrayList<TrackList> trackLists;

    public User(long id, String name, String login, String password, ArrayList<TrackList> trackLists) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.trackLists = trackLists;
    }

    // Получение идентификатора
    public long getId() {
        return id;
    }

    // Изменения идентификатора
    public void setId(long id) {
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

    public void setPassword(String password) {
        this.password = password;
    }

    // Получение массива
    public ArrayList<TrackList> getTrackLists() {
        return trackLists;
    }

    // Получение длинны массива по индексу
    public TrackList getTrackListOnIndex(int index){return this.trackLists.get(index);}
    // Получение размера массива
    public int getSizeTrackList(){return this.trackLists.size();}

    // Добавление элемента в массив
    public void addInTrackList(TrackList trackList){this.trackLists.add(trackList);}

    // Удаление элемента из списка по иедексу
    public void deleteTrackList(int index){this.trackLists.remove(index);}
}
