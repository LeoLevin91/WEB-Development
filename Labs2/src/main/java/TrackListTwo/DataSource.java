package TrackListTwo;

import Track_List.ClientSide.Client;
import java.sql.DriverManager;
import java.sql.Connection;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class DataSource {

    // url для доступа к базе данных
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    // логин для доступа к базе данных
    private final String login = "postgres";
    // пароль для доступа к базе данных
    private final String password = "777349";

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        // Выполнение соединения с базой данных
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(url, login, password);
    }

    public void createTableClient() throws SQLException, ClassNotFoundException {
        /*
        * Данный метод создает требуемые таблицы
        * */
        // Стандартный способ установления соединения
        Connection connection = getConnection();
        // Установка автоматического сохранения в базе данных (в данно случае автоматическое сохранение не выполняетяс)
        connection.setAutoCommit(false);
        // Создаем таблицу users при помощи prepareStatement который позволяет подготовить запрос и отформатировать его
        PreparedStatement statement = connection.prepareStatement("create table users(id integer primary key, username text, login text, password text)");
        statement.execute();
        // Создаем таблицу trackLists
        statement = connection.prepareStatement("create table trackLists(user_ID integer, trackList_ID integer)");
        statement.execute();
        // Создаем таблицу trackList
        statement = connection.prepareStatement("create table trackList(id integer, track_title text)");
        statement.execute();
        // Создаем таблицу track
        statement = connection.prepareStatement("create table track(title text primary key , size numeric, duration integer)");
        statement.execute();

        // Выполняем коммит изменений
        connection.commit();
        connection.close();
    }

    public boolean existTrackList(TrackList trackList) throws SQLException, ClassNotFoundException {
        /*
        * Данный метод провреряет наличие trackList по определенному id
        * */
        Connection connection = getConnection();
        // Готовим запрос и форматируем его
        PreparedStatement statement = connection.prepareStatement("select exists(select id from trackList where id = ?)");
        // Устанавливаем значение в id
        statement.setInt(1, trackList.getID());
        ResultSet rs = statement.executeQuery();
        rs.next();
        connection.close();
        return rs.getBoolean(1);
    }

    public boolean existTrack(Track track) throws SQLException, ClassNotFoundException {
        /*
        * Проверяется наличие трека по определенному title (названию трека)
        * */
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement("select exists(select title from track where title = ?)");
        statement.setString(1, track.getTitle());
        ResultSet rs = statement.executeQuery();
        rs.next();
        connection.close();
        return rs.getBoolean(1);
    }

    public void insertUser(User user) throws SQLException, ClassNotFoundException {
        /*
        * Даннные метод производит добавление Юзера
        *
        * */
        Connection connection = getConnection();
        connection.setAutoCommit(false);
        PreparedStatement statementUser = connection.prepareStatement("insert into users(id, username, login, password) values(?,?,?,?)");
        statementUser.setInt(1, user.getId());
        statementUser.setString(2, user.getName());
        statementUser.setString(3, user.getLogin());
        statementUser.setString(4, user.getPassword());
        statementUser.execute();


        // Для каждого Юзера добавляется trackLists, trackList, track в цикле (для недопущения нагромаждения кода)
        PreparedStatement statementTrackLists = connection.prepareStatement("insert into trackLists(user_ID, trackList_ID) values(?,?)");
        PreparedStatement statementTrackList = connection.prepareStatement("insert into trackList(id, track_title) values (?,?)");
        PreparedStatement statementTrack = connection.prepareStatement("insert into track(title, size, duration ) values (?,?,?)");

        for(int i = 0; i < user.getSizeTrackList(); i++){
            TrackList trackList = user.getTrackListOnIndex(i);
            statementTrackLists.setInt(2, trackList.getID());
            statementTrackLists.setInt(1, user.getId());
            statementTrackLists.execute();

            if(!existTrackList(trackList)){
                for(int j = 0; j < trackList.getSizeTracksArray(); j++){
                    statementTrackList.setInt(1, trackList.getID());
                    Track track = trackList.getTrackToIndex(j);
                    statementTrackList.setString(2, track.getTitle());
                    statementTrackList.execute();
                    if(!existTrack(track)){
                        statementTrack.setString(1, track.getTitle());
                        statementTrack.setDouble(2, track.getSize());
                        statementTrack.setLong(3, track.getDuration());
                        statementTrack.execute();
                    }
                }
            }
            connection.commit();
        }
        connection.close();
    }

    // Два метода для сохранения и загрузки Юзера из файла (используется метод, который был написан в первой лабораторной работе)
    public void loadingUser(String path) throws SQLException, IOException, ClassNotFoundException {
        insertUser(User.loading(path));
    }

    public static void saveingUser(String name, User user) throws IOException {
        User.saveing(name, user);
    }


    public boolean existUser(String login, String password) throws SQLException, ClassNotFoundException {
        // Данный метод проверяет наличие Юзера
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement("select exists(select id  from users where login = ? and password = ?)");
        statement.setString(1, login);
        statement.setString(2, password);
        ResultSet rs = statement.executeQuery();
        rs.next();
        connection.close();
        return rs.getBoolean(1);
    }

    public ArrayList<TrackList> findTrackListByUser(User user) throws SQLException, ClassNotFoundException {
        // Данный метод ищет trackList принадлежащий определенному Юзеру
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement("select trackList_ID from trackLists where user_ID = ? group by trackList_ID");
        statement.setInt(1, user.getId());
        ResultSet rs = statement.executeQuery();
        ArrayList result = new ArrayList();
        while(rs.next()){
            TrackList trackList = new TrackList();
            trackList.setID(rs.getInt("trackList_ID"));
            ArrayList<Track> tempTrack = findTrackByTrackList(trackList);
            trackList.setTracks(tempTrack);
            result.add(trackList);
        }
        connection.close();
        return result;
    }

    public ArrayList<Track> findTrackByTrackList(TrackList trackList) throws SQLException, ClassNotFoundException {
        // Данный метод ищет track в определенном trackList
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from track where title in (select track_title from trackList where id = ?)");
        statement.setInt(1, trackList.getID());
        ResultSet rs = statement.executeQuery();
        ArrayList result = new ArrayList();
        while(rs.next()){
            Track track = new Track(rs.getString("title"), rs.getDouble("size"), rs.getLong("duration"));
            result.add(track);
        }
        connection.close();
        return result;
    }

    public void deleteTrack(String title) throws SQLException, ClassNotFoundException {
        // Данный метод удаляет трек по его названию
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement("delete from track where title = ?");
        statement.setString(1, title);
        statement.execute();
        //connection.commit();
        connection.close();
    }

    public void updateUsernameById(int id, String newUserName) throws SQLException, ClassNotFoundException {
        // Данный метод обновляет Username в таблице users по определенному id
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement("update users set username = ? where id = ?");
        statement.setString(1, newUserName);
        statement.setInt(2, id);
        statement.execute();
        //connection.commit();
        connection.close();
    }

    public void deleteUserOnId(int id) throws SQLException, ClassNotFoundException {
        // Данный метод удаляет юзера по определенному id
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement("delete from users where id == ?");
        statement.setInt(1, id);
        statement.execute();
        //connection.commit();
        connection.close();
    }

    public void dropTables() throws SQLException, ClassNotFoundException {
        // Данный метод удаляет таблицы
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement("drop table users, track, trackList, trackLists");
        statement.execute();
        connection.close();
    }


}
