import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Enumeration;
import java.util.Hashtable;

public class ChatRoomImpl extends UnicastRemoteObject implements ChatRoom {

	Hashtable<String, ChatUser> utilisateurs;
	protected ChatRoomImpl() throws RemoteException {
		super();
		utilisateurs = new Hashtable<String, ChatUser>();
	}

	private static final long serialVersionUID = -4174498417430362926L;

	@Override
	public void subscribe(ChatUser user, String pseudo) throws RemoteException {
		if(!utilisateurs.containsKey(pseudo)){
			utilisateurs.put(pseudo, user);
			this.postMessage(pseudo, "is in the ChatRoom !");
		} 
	}

	@Override
	public void unsubscribe(String pseudo) throws RemoteException {
		String message = pseudo+" quit the chatRoom !";
		if(utilisateurs.containsKey(pseudo)) {
			utilisateurs.remove(pseudo);
			this.postMessage(pseudo, message);
		}
	}

	@Override
	public void postMessage(String pseudo, String message) throws RemoteException {
		if(message.equals("")) System.out.println("\n");
		else{
		String messageEntier = pseudo+" : "+message;
		System.out.println(messageEntier);
		Enumeration<ChatUser> e = utilisateurs.elements();
		while(e.hasMoreElements()){
			ChatUser user = (ChatUser)e.nextElement();
			user.displayMessage(messageEntier);
		}}
	}

}
