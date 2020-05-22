package Track_List.BothSide;

import Track_List.TrackListTwo.TrackList;

import java.rmi.Remote;
import java.rmi.RemoteException;

/*
* Сначала нам понадобится интерфейс который будет удовлетворять нашим требованиям
* Remote - интерфейс маркер
*
* */


public interface RMITracks extends Remote {
    // В процессе выполнения может появится ошибка мы ее отлавливаем
    TrackList sortTracks(TrackList tL) throws RemoteException;
}
