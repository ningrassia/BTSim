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
		
		//booleans for my(the peer who owns this PeerInfo) state.
		public boolean my_choke;
		public boolean my_interest;
		public String ID;
}
