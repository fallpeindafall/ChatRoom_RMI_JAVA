import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class ChatServeur {

	public static void main(String[] args) {
		try{
			//lancement dynamique du registre de noms RMI
			LocateRegistry.createRegistry(1099);
			//instanciation d'un objet de la classe distante
			ChatRoomImpl chatRoomImpl = new ChatRoomImpl();
			//enregistrement dans le registre de noms RMI
			//String url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + "/TP0";
			//System.out.println("Enregistrement de l'objet avec l'url : " + url);
			Naming.rebind("TP0", chatRoomImpl);
			
		
			System.out.println("Serveur lance avec succes");
		}catch(RemoteException e){
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
