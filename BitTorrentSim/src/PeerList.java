import java.util.ArrayList;
import java.util.Iterator;


/**
 * Used to store/get all peers being simulated by BTSim.
 * @author ningrassia
 *
 */
public class PeerList {
	static ArrayList<Peer> peers;
	static int next_peer_id = 1;
	/**
	 * Creates a new Peer, sets it up, adds it to the peer list, and runs it.
	 */
	public void addPeer(int tracker, Boolean origin, int filesize, int ul_speed, int dl_speed, double start_time, double end_time) {
		//create the peer and add it to the list
		Peer newPeer = new Peer(start_time, end_time, ul_speed, dl_speed);
		peers.add(newPeer);
		
		//configure the peer
		//set the peer id
		newPeer.setID(next_peer_id);
		next_peer_id++; //make sure we have a fresh peer ID
		
		//add the tracker to the peer
		//newPeer.addTracker(tracker);
		
		//set if peer has already finished (is the origin)
		if(origin) newPeer.setComplete();
		
	}
	
	/**
	 * Given a peer_id, returns a Peer object - useful for sending/messages to the peer.
	 * @param peer_id The peer_id of the desired peer.
	 * @return The Peer object.
	 */
	public Peer getPeer(int peer_id) {
		Iterator<Peer> peerIter = peers.iterator();
		while(peerIter.hasNext()) {
			Peer tempPeer = peerIter.next();
			if(tempPeer.getID() == peer_id) {
				return tempPeer;
			}
		}
		
		return null; //if we didn't find the peer!
	}
	
	/**
	 * Returns an iterator over the peer list
	 * @return An Iterator over the peer list 
	 */
	public Iterator<Peer> getPeerIter(){
		return peers.iterator();
	}
}
