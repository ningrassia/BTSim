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
		
		//TODO
		//Hieu do some magic and dump out useful data from the peers!
		/** 
		 * Below I will explain my strategy to meet the requirement of the 
		 * assignment.
		 * 1. Resilience
		 * - Show how long a peer tooks to recover its download rate when one 
		 *   of the peers it was downloading from disconnect.
		 * - Show how the leaving of a peer affect the average download speed of
		 *   ALL peers over time (It should not affect for long).
		 * 2. Effectiveness under peer heterogeneity
		 * 
		 * 3. Scalability
		 * - Show how average download speed of all peers change in a simulation
		 *   where more and more peers are added over time.
		 * 
		 * 4. Effectiveness against free-rider
		 * - Compare average download speed of a peer with low upload speed (or 
		 *   even 0) with that of a peer with high upload speed.
		 * - https://www.cs.duke.edu/~msirivia/publications/large-view.pdf
		 *   To Nik, just read the abstract...
		 * 
		 * 5. Fairness
		 * - How the <avg down speed>/<avg up speed> changes with upload speed
		 * 
		 */
		
		//quit.
		System.exit(0);
	}

}
