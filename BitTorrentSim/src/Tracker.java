import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Simulates a BitTorrent tracker in BTSim
 * @author ningrassia
 *
 */
public class Tracker {
	private Mailbox mailbox;
	private int tracker_id;
	private ArrayList<Integer> peer_id_list;

	/**
	 * Runs the Tracker as a thread.
	 * <p>
	 * Listens for peer requests, sends out required info to the peer.
	 */
	public void run() {
		//parse any messages received
		while(mailbox.hasMessage()){
			String message = mailbox.getMessage();
			parse(message);
		}
	}
	
	/**
	 * Parses a received message.
	 * 
	 * @param message The message the tracker is parsing.
	 */
	/*
	 * Trackers are going to behave very differently than in "real" BT.
	 * Because we don't have to deal with actual networking, all a peer is going to
	 * send is "hello peerid" or "goodbye peerid", and the tracker will respond with "timetowait peerlist"
	 * where hello/goodbye is just the string hello or goodbye, peerid is the peer's ID number,
	 * timetowait is the amount of time to wait until talking to the tracker again,
	 * and peerlist is a list of all of the peer id's this tracker knows about.
	 */
	private void parse(String message) {
		String key;
		String peer_id;
		StringTokenizer tokenizedMessage;
		tokenizedMessage = new StringTokenizer(message);
		key = tokenizedMessage.nextToken();
		peer_id = tokenizedMessage.nextToken();
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
	public int getID() {
		return this.tracker_id;
	}
	
	private void info_hash(String peer_id){
		
	}
	
	private void peer_id(String peer_id){
		
	}
	
	private void uploaded(String peer_id){
		
	}
	
	private void downloaded(String peer_id){
		
	}
	
	private void left(String peer_id){
		
	}

}
