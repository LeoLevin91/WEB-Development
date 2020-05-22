package Track;

// Импортируем необходимые библиотеки

import java.io.*;


public class Track implements Serializable, Comparable<Track> {
    /* + инкапсулировать приватные поля согласно варианту;
       + иметь конструктор с параметрами, проверяющий корректность значени
    *  + реализовывать методы получения и изменения полей объекта
       + быть подготовлен к сериализации
       + иметь статический метод, позволяющий записать в текстовый файл объект
       + иметь статический метод, позволяющий считать из текстового файла объект
    */

    // Название трека
    private String title;
    // Размер трека
    private double size;
    // Длительность трека
    private long duration;

    public Track(String title, double size, long duration){
        // Конструктор с проверкой значений
        this.title = title;
        // Проверка на размер трека
        if (size > 0.0D && duration > 0){
            this.size = size;
            this.duration = duration;

        } else {
            this.size = 0;
            // Если размер трека 0, но и его длительность тоже 0 - это значит, что трек поврежден
            this.duration = 0;
            this.title = title + " (поврежденный трек)";
        }
    }

    // МЕТОДЫ ДЛЯ ПОЛУЧЕНИЯ И ИЗМЕНЕНИЯ ПОЛЕЙ ОБЪЕКТА
    //---Методы для получения полей объекта
    public String getTitle() {
        return title;
    }

    public Double getSize(){
        return size;
    }

    public long getDuration() {
        return duration;
    }
    // Методы для изменения полей объекта

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSize(double size) {
        // Проверка на размер
        if (size > 0.0D) {
            this.size = size;
        } else {
            System.out.println("Invalid size!");
            System.exit(0);
        }
    }

    public void setDuration(long duration) {
        if (duration > 0){
            this.duration = duration;
        } else {
            System.out.println("Invalid duration!");
            System.exit(0);
        }
    }

    // Метод записи объекта в файл
    /*

     Сериализация - позволяет сохранить состояния объекта (аналогия с сохранением игры)
     * ObjectOutputStream -> Позволяет записывать объекты
     * FileOutputStream -> Этот объект знает кк подключаться к файлу
     * */
    public static void saving(Track trackOBJ, String name) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(name))){
            out.writeObject(trackOBJ);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Метод считывающий объект из файла
    public static Track loading(String name) throws IOException {

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(name))) {
            Track temp = (Track)in.readObject();
            return temp;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int compareTo(Track track) {
        return this.title.compareTo(track.getTitle());
    }

    public String toString() {
        return "(Title: " + title + "; Size: " + size + "; Duration: " + duration + ')';
    }


}
