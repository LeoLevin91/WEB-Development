package TrackListTwo;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.net.ConnectException;
import java.util.Random;


public class TestUserDataBase {
    private ArrayList<Track> testTrack;
    private ArrayList<User> testUser;
    private  ArrayList<TrackList> testTrackList;

    private DataSource dataSource = new DataSource();

    public void startTest() throws SQLException, ParseException, IOException, ClassNotFoundException {
        testTrack = new ArrayList<Track>();
        for(int i = 1; i <= 14; ++i){
            String title = "title"+Integer.toString(i);
            Double size = i*11.5;
            int duration = i*100;
            Track track = new Track(title, size, duration);
            testTrack.add(track);
        }

        testTrackList = new ArrayList<TrackList>();
        for (int i = 1; i <= 10; ++i){
            ArrayList<Track> trList = new ArrayList<Track>();
            for (int j = 0; j < 5; ++j){
                trList.add(testTrack.get(i+j-1));
            }
            testTrackList.add(new TrackList(i, trList));
        }

        testUser = new ArrayList<User>();
        for(int i = 1; i <= 5; ++i){
            String name = "name"+Integer.toString(i);
            String login = "login"+Integer.toString(i);
            String password = "password"+Integer.toString(i);
            ArrayList<TrackList> trs = new ArrayList<TrackList>();
            trs.add(testTrackList.get(i-1));
            trs.add(testTrackList.get(4+i));
            testUser.add(new User(i, name, login, password, trs));
        }

        // Тестирование метода удаления таблиц
        dropTables();
        // Тестирования матода создания таблиц
        createTables();
        // Тестирования метода добавления Юзеров
        addUsers();
        // Удаление трека по его названию
        deleteTrack("title3");
        // Обновление Username по его id
        updateUsernameOnId(2);
        // Создаем нового юзера
        User newUser = testUser.get(0);
        // Запишем его
        newUser.setId(8);
        // Сериализация и десериализация
        insertInFile("test2.txt", newUser);
        insertFromFile("test2.txt");
        // Тестирование метода на наличие юзера
        isUser("login1", "password1");
        // Тестирование метода по поиску треклиста
        findTrackListByUser(testUser.get(2));
        // Тестирование метода по поиску трека в треклисте
        findTrackByTrackList(testTrackList.get(3));
    }

    public void createTables() throws SQLException, ClassNotFoundException {
        // Создание таблиц с помощью метода createTableClient из класса DataSource
        dataSource.createTableClient();
    }

    public void addUsers() throws SQLException, ClassNotFoundException {
        // Добавление user-а при помощи цикла в метода insertUser из класса DataSource
        for(int i = 0; i < testUser.size(); ++i){
            dataSource.insertUser(testUser.get(i));
        }
    }

    public void deleteTrack(String title) throws SQLException, ClassNotFoundException {
        // Удаление трека при помощи метода deleteTrack из класса DataSource
        dataSource.deleteTrack(title);
    }


    public void updateUsernameOnId(int id) throws SQLException, ClassNotFoundException {
        // Апдейт Username по id с помощью метода updateUsernameById из класса DataSource
        dataSource.updateUsernameById(id, "New name");
    }


    public void insertFromFile(String path) throws SQLException, ParseException, IOException, ClassNotFoundException{
        // Вставка из файла при помощи метода loadingUser из класса DataSource
        dataSource.loadingUser(path);
    }

    public void insertInFile(String path, User user) throws SQLException, ParseException, IOException, ClassNotFoundException{
        // Вставка в файл при помощи метода insertInFile из класса DataSource
        dataSource.saveingUser(path, user);
    }


    public void isUser(String login, String password) throws SQLException, ClassNotFoundException {
        // Метод проверяет наличие юзера в нашей таблице с помщью метода existUser из класса DataSource
        boolean isUser = dataSource.existUser(login, password);
        System.out.println("Does user exist?  " + isUser);
    }


    public void findTrackListByUser(User user) throws SQLException, ClassNotFoundException {
        ArrayList<TrackList> trs =  dataSource.findTrackListByUser(user);
        for(int i = 0; i < trs.size(); i++){
            System.out.println("TrackLis number: " + Integer.toString(i) + ": " + trs.get(i));
        }
    }


    public void findTrackByTrackList(TrackList trackList) throws SQLException, ClassNotFoundException {
        ArrayList<Track> ts =  dataSource.findTrackByTrackList(trackList);
        for(int i = 0; i < ts.size(); i++){
            System.out.println("Track number" + Integer.toString(i) + " : " + ts.get(i));
        }
    }


    public void dropTables() throws SQLException, ClassNotFoundException {
        // Удаление таблиц
        dataSource.dropTables();
    }




}
