import java.util.ArrayList;
import java.util.Iterator;


/**
 * 
 * @author ningrassia
 *
 */
public class PeerList {
	ArrayList<Peer> peers;
	
	/**
	 * Creates a new Peer, sets it up, adds it to the peer list, and runs it.
	 */
	public void addPeer() {
		//create the peer and add it to the list
		Peer newPeer = new Peer();
		peers.add(newPeer);
		
		//configure the peer
		String tracker = "test";
		newPeer.addTracker(tracker);
	
		//start the thread
		new Thread(newPeer).start();
	}
	
	/**
	 * Given a peer_id, returns a Peer object - useful for sending/messages to the peer.
	 * @param peer_id The peer_id of the desired peer.
	 * @return The Peer object.
	 */
	public Peer getPeer(String peer_id) {
		Iterator<Peer> peerIter = peers.iterator();
		while(peerIter.hasNext()) {
			Peer tempPeer = peerIter.next();
			if(tempPeer.getID() == peer_id) {
				return tempPeer;
			}
		}
		
		return null; //if we didn't find the peer!
	}
}
