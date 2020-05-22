package Track_List.TrackListTwo;
import Track.Track;
import Track_List.TrackListTwo.TrackList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Main {

    public static void main(String[] args) throws IOException, InputMismatchException{
	    // Создадим объект и потестируем методы
        Track trackOne = new Track("ZMiaCulpa", 2, 240);
        Track trackTwo = new Track("Lol", 4, 500);

        // Создаем ArrayList
        ArrayList<Track> arr = new ArrayList<Track>();
        // Добавляем в него сохданные ранее треки
        arr.add(trackOne);
        arr.add(trackTwo);

        // Создаем еще один объект
        Track aerosmith = new Track("Aerosmith", 400, 6000);

        // Создаем объект агрегатор TrackList
        TrackList tL = new TrackList(1,arr);
        // Показываем возможность добавления треков (возникают дубликаты)
        tL.addTrack(0, trackOne);
        tL.addTrack(1, trackTwo);
        System.out.println("До сортировки: " + tL);
        // Добавим трек
        tL.addTrack(1, aerosmith);
        System.out.println("После добавления трека: " + tL);
        // Запишем в файл
        tL.saveing("test.txt");

        // Отсортируем
        tL.sortTracks();
        System.out.println("После сортировки: " + tL);



    }
}
