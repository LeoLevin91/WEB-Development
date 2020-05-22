package Track_List.ServerSide;

import Track_List.BothSide.RMITracks;
import Track_List.TrackListTwo.TrackList;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/*
* RMI - remote method invocation - удаленный вызов метода. Позволяет вызвать метод с другой Java машины
* В Java удаленно можно вызывать только методы интерфейсов, но не классов.
*
*
* */

// Имплементим интерфейс
public class Server implements RMITracks {

    // Придуманное имя нашего удаленного объекта
    public static final String UNIC_MY_NAME = "serverTitleSorting";

    // Переопределяем методы интерфейса
    @Override
    public TrackList sortTracks(TrackList tL) throws RemoteException {
        tL.sortTracks();
        return tL;
    }

    public static void main(String[] args) throws Exception
    {
        System.setProperty("java.security.policy", "src\\Track_List\\BothSide\\Security.policy");

        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        //создание объекта для удаленного доступа, чьи методы будут вызываться
        final Server service = new Server();

        //создание реестра расшареных объетов.
        // Регистрация объектов которые мы шарим
        // 2099 - уникальный порт по которому другая программа обращается к реестру объектов
        // Чтобы обратиться к объекту, надо знать уникальный номер реестра объектов (порт),
        // знать уникальное имя объекта и иметь такой же интерфейс, как и тот, который реализовывает удаленный объект.
        final Registry registry = LocateRegistry.createRegistry(2099);
        //создание "заглушки" – приемника удаленных вызовов
        /*
        * Заглушка – это специальный объект, который принимает информацию об удаленном
        * вызове, распаковывает ее, десериализует переданные параметры методов и вызывает нужный метод. Затем сериализует
        * результат или исключение, если оно было, и отсылает все это назад вызывающему.
        * */
        Remote stub = UnicastRemoteObject.exportObject(service, 0);
        //регистрация "заглушки" в реесте
        registry.bind(UNIC_MY_NAME, stub);
        //усыпляем главный поток, иначе программа завершится
        Thread.sleep(Integer.MAX_VALUE);
    }
}
