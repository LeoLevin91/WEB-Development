package Track_List.TrackListTwo;
import Track.Track;
import Track_List.BothSide.RMITracks;

import java.io.*;
import java.rmi.RemoteException;
import java.util.*;

public class TrackList implements Serializable {
    //  + инкапсулировать приватный уникальный идентификатор согласно варианту
    //  + инкапсулировать приватный массив элементов согласно варианту;
    //  + реализовывать методы получения идентификатора списка и длины массива;
    //  + реализовывать методы добавления и удаления объекта по индексу;
    //  + реализовывать метод чтения массива объектов из файла;
    //  + реализовывать метод записи массива объектов в файл
    //  + реализовывать метод сортировки объектов в массиве по полю указанному в задании.

    private int ID;
    private ArrayList<Track> tracks;

    public TrackList(){
    }

    public TrackList(int ID, ArrayList<Track> arr){
        this.ID = ID;
        this.tracks = arr;
    }

    // Метод получения идентификатора списка
    public int getID(){
        return this.ID;
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    // Метод получения длинны массива
    public int getSizeTracksArray(){
        return this.tracks.size();
    }


    // Метод добавление объекта по индексу
    public void addTrack(int index, Track track){
        this.tracks.add(index, track);
    }

    // Метод удаление объекта по индексу
    public void deleteTrack(int index){
        this.tracks.remove(index);
    }

    // Метод записывающий объект в файл (сериализация)
    /*Сериализация - позволяет сохранить состояния объекта (аналогия с сохранением игры)
    * ObjectOutputStream -> Позволяет записывать объекты
    * FileOutputStream -> Этот объект знает кк подключаться к файлу
    * */
    public void saveing(String name) throws IOException {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(name))){
            out.writeObject(this.tracks);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Метод десиреализации
    public ArrayList<Track> loading(String name) throws IOException{
        ArrayList<Track> arrayList = new ArrayList<Track>();
        try {
            FileInputStream fis = new FileInputStream(name);
            ObjectInputStream ois = new ObjectInputStream(fis);
            arrayList = (ArrayList) ois.readObject();
            System.out.println(arrayList);
            ois.close();
            fis.close();
            this.tracks = arrayList;
            return null;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        } catch (ClassNotFoundException c){
            System.out.println("Class not found");
            c.printStackTrace();
            return null;
        }
    }


// Сортировка
    public void sortTracks(){
        /*
        * Применяется TreeSet так как он автоматически удаляет дубликаты и сортирует
        *
        * */
        TreeSet<Track> trackss = new TreeSet<>(tracks);
        tracks = new ArrayList<>(trackss);

    }

    // toString -> Преобразование объекта в String для вывода в System.out.print()
    // StringBuilder -> Конкатенация строки (метод работает быстрее чем обычная конкатенация)
    public String toString() {
        StringBuilder temp = new StringBuilder("ID: " + this.ID + "\nTracks: ");
        for (Track track : this.tracks) {
            temp.append(track.toString()).append(' ');
        }
        return temp.toString();
    }

}
