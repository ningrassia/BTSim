import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * @author ningrassia
 *
 */
public class Tracker implements Runnable {
	private Mailbox mailbox;
	private String tracker_id;
	private ArrayList<String> peer_id_list;
	@Override
	/**
	 * Runs the Tracker as a thread.
	 * <p>
	 * Listens for peer requests, sends out required info to the peer.
	 */
	public void run() {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Parses a received message.
	 * 
	 * @param message The message the tracker is parsing.
	 */
	private void parse(String message) {
		String key;
		StringTokenizer tokenizedMessage;
		tokenizedMessage = new StringTokenizer(message);
		key = tokenizedMessage.nextToken();
		switch(key) {
		
		case "info_hash":
		
		case "peer_id":
			
		case "uploaded":
			
		case "downloaded":
			
		case "left":
			
		default:
			
		}
	}
	/**
	 * Public method used to send a message to this tracker
	 * @param message The message being sent to/received by the tracker.
	 */
	public void recMessage(String message) {
		mailbox.putMessage(message);
	}
	
	/**
	 * Returns this tracker's tracker ID.
	 * @return This tracker's tracker ID.
	 */
	public String getID() {
		return this.tracker_id;
	}

}
