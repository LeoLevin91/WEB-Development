package Track_List.ClientSide;

//import Track;
import Track_List.BothSide.RMITracks;
import TrackListTwo.TrackList;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.rmi.ConnectException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.ParseException;

public class Client {
    // Придуманное имя нашего удаленного объекта
    public static final String UNIC_MY_NAME = "serverTitleSorting";

    public static void main(String[] args) throws Exception {
        System.setProperty("java.security.policy", "src\\Track_List\\BothSide\\Security.policy");

        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        // Созданее реестра расшареных объектов
        // Порт должен быть таким же как и у сервера
        final Registry registry = LocateRegistry.getRegistry("127.0.0.1",2099);

        // Считывание файла
        PrintStream wr = new PrintStream(new FileOutputStream(args[1]));
        try {
            //получаем объект
            /*
            * Получаем объект у реестра. Полученный объект является proxy-объектом и приводится к типу интерфейса.
            * */
            RMITracks service = (RMITracks) registry.lookup(UNIC_MY_NAME);
            // Создаем объект и вызываем удаленные методы
            TrackList tt = new TrackList();
            tt.loading(args[0]);
            System.out.println(tt);
            TrackList result = service.sortTracks(tt);
            System.out.println(result);
            result.saveing(args[1]);
        }
        catch (ConnectException e){
            e.printStackTrace(wr);
        }
        catch (NullPointerException e) {
            e.printStackTrace(wr);
        }
        catch (NumberFormatException e) {
            e.printStackTrace(wr);
        }
        finally {
            wr.close();
        }

    }
}
