import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * @author ningrassia
 *
 */
public class Peer implements Runnable {
	private Mailbox mailbox;
	private String peer_id;
	private ArrayList<String> peer_id_list; //this peer's current peers
	private ArrayList<String> tracker_id_list; //this peer's current trackers

	/**
	 * Adds a tracker to this peer's tracker list
	 * @param tracker_id The tracker ID of the tracker to add.
	 */
	public void addTracker(String tracker_id) {
		tracker_id_list.add(tracker_id);
	}
	
	/**
	 * Runs the peer as a thread.
	 */
	public void run() {
		//Get the first tracker and send it the initial messages
		
		//Start handling the actual download
		while(true) {
			String message = mailbox.getMessage();
			parse(message);
		}
	}
	
	/**
	 * Parses a received message from a peer/tracker.
	 * @param message The message to be parsed.
	 */
	private void parse(String message) {
		String key;
		StringTokenizer tokenizedMessage;
		tokenizedMessage = new StringTokenizer(message);
		key = tokenizedMessage.nextToken();
		switch(key) {	
		case "choke":
			
		case "unchoke":
			
		case "interested":
			
		case "not_interested":
			
		case "have":
			
		case "bitfield":
			
		case "request":
			
		case "piece":
			
		case "cancel":
			
		case "keepalive":
		
		default: break;	
		}
	}
	
	/**
	 * Public method used to send a message to this peer.
	 * @param message The message being sent to/received by the peer.
	 */
	public void recMessage(String message) {
		mailbox.putMessage(message);
	}
	
	/**
	 * Public method to get this peer's peer ID.
	 * @return This peer's peer ID.
	 */
	public String getID() {
		return this.peer_id;
	}

}
