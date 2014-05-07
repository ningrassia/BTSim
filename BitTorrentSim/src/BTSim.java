import java.util.Iterator;

/**
 * The actual BTSim itself!
 * Pass in a file with peer behavior to simulate
 * Should dump a ton of peer data!
 * @author ningrassia
 *
 */
public class BTSim {

//Each time we run a peer, we go this amount of time!
static double timeDelta = 0.1; 
static int pieceSize;
static int torrentSize;
static int numPieces;
static double currTime = 0;
static double endTime;
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
	public static BTSimImporter importer;
	public static void main(String[] args) {
		
		//Initialize BTSim things
		peerList = new PeerList();
		trackerList = new TrackerList();
		importer = new BTSimImporter(args[0]);
		//check if the import worked!
		if(BTSimImporter.badFile) {
			for(int i = 0; i < BTSimImporter.errorList.size(); i++) {
				System.out.println(BTSimImporter.errorList.get(i));
			}
			System.exit(-1);
		}
		numPieces = 1000; //this is a good approx. per torrent!

		
		//create the tracker
		trackerList.addTracker();
		
		//create the peers
		for(int i = 0; i < BTSimImporter.peerIDList.size(); i++) {
			//peerList.addPeer(tracker, origin, filesize)
		}
	
		
		pieceSize = torrentSize/numPieces;
		
		//Actually simulate the peers/tracker here
		while(currTime <= endTime){
			Iterator<Tracker> trackers;
			Iterator<Peer> peers;
			
			trackers = trackerList.getTrackerIter();
			peers = peerList.getPeerIter();
			
			while(trackers.hasNext()){
				Tracker curr = trackers.next();
				curr.run();
			}
			
			while(peers.hasNext()){
				Peer curr = peers.next();
				curr.run();
			}
			currTime += timeDelta;
		}
		
		//quit.
		System.exit(0);
	}

}
