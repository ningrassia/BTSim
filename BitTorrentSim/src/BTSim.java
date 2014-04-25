/**
 * 
 * @author ningrassia
 *
 */
public class BTSim {

	/**
	 * The main method of the BTSim!
	 * @param args Command line arguments to the simulator
	 */
	/**
	 * Regarding peerList and trackerList:
	 * Probably not the best design, but can't think
	 * of any better way to do it at the moment.
	 */
	public static PeerList peerList;
	public static TrackerList trackerList;
	
	public static void main(String[] args) {
		peerList = new PeerList();
		trackerList = new TrackerList();
		
		//do stuff to make peers here.
		
		//quit.
		System.exit(0);
	}

}
