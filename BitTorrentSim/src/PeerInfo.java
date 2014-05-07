import java.util.ArrayList;

/**
 * 
 * @author ningrassia
 *
 */
public class PeerInfo {
		//booleans for the other peer's state
		//If a peer is choked, no information will be sent until unchoked.
		public boolean choke;
		public boolean interest;
		//the pieces the other peer has - updated by bitfield/have messages
		public ArrayList<Boolean> pieceList;
		//time since the last message was received from this peer
		public double time_since_last;
		//booleans for my(the peer who owns this PeerInfo) state.
		public boolean my_choke;
		public boolean my_interest;
		public int ID;
}
