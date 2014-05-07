import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Simulates a BitTorrent Peer in BTSim
 * @author ningrassia
 *
 */

public class Peer {

	private Mailbox mailbox;
	private int peer_id;
	private ArrayList<PeerInfo> peer_id_list; //this peer's current peers
	private ArrayList<Integer> tracker_id_list; //this peer's current trackers
	private ArrayList<Boolean> piece_list; //the pieces this peer has!

	private int ul_speed; //maximum upload speed per peer
	private int dl_speed; //maximum download speed per peer
	private int curr_dl_speed; //smaller of either this peer's dl speed or the other peer's ul speed
	private int curr_ul_speed; //smaller of either this peer's ul speed or the other peer's dl speed
	private int curr_dl_peer; //current peer we're downloading from
	private int curr_ul_peer; //current peer we're uploading to;
	private int curr_dl_size; //amount left to download
	private int curr_ul_size; //amount left to upload
	private int curr_dl_piece; //current piece we're downloading
	private int curr_ul_piece; //current piece we're uploading
	private double start_time; //time we start simulating
	private double end_time; //time we end simulating
	private double finish_time; //the time when this torrent finished downloading!
	private Boolean downloading; //true if we are downloading a piece - can only d/l 1 at a time (lazy implementation)
	private Boolean uploading; //true if we are uploading a piece - only 1 at a time (lazy)
	private Boolean complete;
	private Random rng; //used to choose next piece to download
	private PeerStat stats;
	

	/**
	 * Constructor
	 */
	
	public Peer(double start_time, double end_time, int ul_speed, int dl_speed) {
		this.start_time = start_time;
		this.end_time = end_time;
		this.ul_speed = ul_speed;
		this.dl_speed = dl_speed;
		
	}
	
	/**
	 * Adds a tracker to this peer's tracker list
	 * @param tracker_id The tracker ID of the tracker to add.
	 */
	public void addTracker(Integer tracker_id) {
		tracker_id_list.add(tracker_id);
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
	public int getID() {
		return this.peer_id;
	}
	
	/**
	 * Public method to set this peer's peer ID.
	 * @param id This peer's new peer ID.
	 */
	public void setID(int id) {
		this.peer_id = id;
	}
	/**
	 * Public method to set the size of a torrent (from info in .torrent file in theory)
	 * @param size The size of the torrent this peer is downloading (actually stored in BTSim
	 */
//	public void setSize(int size) {
//		this = size;
//	}
	
	
	/**
	 * Public method to mark a peer as complete - used to set a peer as the origin
	 */
	public void setComplete() {
		this.complete = true;
		//also mark the piece list as complete
		for(int i = 0; i < BTSim.torrentSize; i++){
			 piece_list.set(i, true);
		}
	}
	
	/**
	 * Public method to set the upload/download speeds of a peer
	 * @param up Upload speed per peer
	 * @param down Download speed per peer
	 */
	public void setSpeed(int up, int down) {
		this.ul_speed = up;
		this.dl_speed = down;
	}
	
	/**
	 * Public method to get peer statistics!
	 * @return the PeerStat of this peer.
	 */
	//obviously this stat thing will need to be updated regularly!
	public PeerStat getStats() {
		return stats;
	}
	
	/**
	 * Runs the peer as a thread.
	 */
	public void run() {
		//make sure we should actually run!
		//return if we haven't started or have already ended!
		if((BTSim.currTime < start_time) || (BTSim.currTime > end_time)) return;
		
		//update time since last message and remove 'dead' peers
		
		
		//parse any messages received
		while(mailbox.hasMessage()){
			String message = mailbox.getMessage();
			parse(message);
		}
		//update the current download
		if(downloading) download();
		//otherwise we want to be downloading so try and download!
		else find_download();
		//update the current upload
		if(uploading) upload();
		//check each connection's state, and send messages if they need to be sent
		for(int i = 0; i < peer_id_list.size(); i++) {
			PeerInfo currentPeer = peer_id_list.get(i);
			//then check state and handle connection!
		}
	}
	
	/**
	 * Parses a received message from a peer/tracker.
	 * @param message The message to be parsed.
	 */
	//MESSAGE FORMAT
	//TYPE SOURCE_PEER_ID PARAMETERS
	private void parse(String message) {
		String key;
		String peer_id;
		StringTokenizer tokenizedMessage;
		tokenizedMessage = new StringTokenizer(message);
		//First two tokens are always the type/key and the peer id
		key = tokenizedMessage.nextToken();
		peer_id = tokenizedMessage.nextToken();
		//handle keepalive and possibly create peerInfo thingy
		
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
			
		case "keepalive": break; //does nothing but update peerinfo above
		
		default: break;	
		}
	}
	
	//Here are private methods that are used to respond to requests
	private void choke(String peer_id){
		PeerInfo curr;
		curr = getPeer(Integer.parseInt(peer_id));
		curr.choke = true;
	}
	
	private void unchoke(String peer_id){
		PeerInfo curr;
		curr = getPeer(Integer.parseInt(peer_id));
		curr.choke = false;
	}
	
	private void interested(String peer_id){
		PeerInfo curr;
		curr = getPeer(Integer.parseInt(peer_id));
		curr.interest = true;
	}
	
	private void not_interested(String peer_id){
		PeerInfo curr;
		curr = getPeer(Integer.parseInt(peer_id));
		curr.interest = false;
	}
	
	private void have(String peer_id, String piece_index){
		PeerInfo curr;
		curr = getPeer(Integer.parseInt(peer_id));

	}
	
	private void bitfield(String peer_id, String bitfield){
		PeerInfo curr;
		ArrayList<Boolean> bfield;
		curr = getPeer(Integer.parseInt(peer_id));
		
		bfield = new ArrayList<Boolean>();
		//TODO: parse the string bitfield into bfield
		//IDEA! string is 0/1 for don't have have.
		for(int i = 0; i < bitfield.length(); i++)
			bfield.set(i, (bitfield.charAt(i) == 1));
		
		
		curr.pieceList = bfield;
		
	}
	
	//For the request/cancel/piece stuff, I might just assume each peer gets a full piece from a single peer.
	private void request(String peer_id, String begin, String length){
	
	}
	
	private void cancel(String peer_id, String begin, String length){
		
	}
	
	private void piece(String peer_id, String index, String begin, String piece){
		
	}
	
	//private methods to update the current dl/ul state!
	//even if a peer stops, we'll just pretend that they're still there
	//for whatever they were uploading/downloading.
	
	private void download(){
		//decrement the dl_size
		curr_dl_size -= BTSim.timeDelta * curr_dl_speed;
		//update downloading state if complete!
		if(curr_dl_size <= 0) {
			downloading = false;
			piece_list.set(curr_dl_piece, true);
		}
	}
	
	private void upload(){
		//decrement the ul_size
		curr_ul_size -= BTSim.timeDelta * curr_ul_speed;
		//update uploading state if complete!
		if(curr_ul_size <= 0) {
			uploading = false;
			PeerInfo ourpeer = getPeer(curr_ul_peer);
			ourpeer.pieceList.set(curr_ul_piece, true);
		}
	}
	
	//private method to set up a download!
	private void find_download() {
		int piece = choosePiece();
		int peer_id = choosePeer(piece); //find a piece and the peer to dl from.
		
		if(peer_id < 0) return;
		
		//send a request to that peer for the given piece
	}
	//private method to get the correct PeerInfo
	//if it doesn't exist, it creates it!
	private PeerInfo getPeer(int peer_id) {
		Iterator<PeerInfo> iter;
		PeerInfo currPeer;
		
		//check to see if we already have this peer!
		iter = peer_id_list.iterator();
		while(iter.hasNext()) {
			currPeer = iter.next();
			if(currPeer.ID == peer_id) return currPeer;
		}
		
		//make a new PeerInfo if we don't
		currPeer = new PeerInfo();
		currPeer.ID = peer_id;
		return currPeer;
		
		
	}
	
	//private method to select which piece to dl next
	private int choosePiece() {
		ArrayList<Integer> rarity;
		ArrayList<Integer> choices;
		Iterator<PeerInfo> peers;
		int best = 0;
		rarity = new ArrayList<Integer>(BTSim.numPieces);
		choices = new ArrayList<Integer>();
		
		//calculate the rarity of each piece (lower numbers are more rare)
		peers = peer_id_list.iterator();
		while(peers.hasNext()){
			PeerInfo curr = peers.next();
			//if a peer has a piece, add 1 to the rarity 
			for(int i = 0; i < curr.pieceList.size(); i++){
				if(curr.pieceList.get(i) == true){
					rarity.set(i, rarity.get(i) + 1);
				}
			}
		}
			
		//find the rarest pieces
		for(int lowest_rarity = 1; lowest_rarity < peer_id_list.size(); lowest_rarity++){
			//check if there any pieces with a given rarity
			for(int i = 0; i < rarity.size(); i++){
				if(rarity.get(i) == lowest_rarity) choices.add(i);
			}
			//break if we have choices.
			if(!choices.isEmpty()) break;
		}
		
		//choose a random rarest piece
		
		
		return best;
	}
	
	//private method to find a peer with a given piece
	//returns -1 if a peer can't be found
	private int choosePeer(int piece){
		int peer_id = 0;
		Iterator<PeerInfo> peers;
		ArrayList<PeerInfo> haspiece;
		PeerInfo temp;
		
		peers = peer_id_list.iterator();
		haspiece = new ArrayList<PeerInfo>();
		
		while(peers.hasNext()) {
			temp = peers.next();
			if(temp.pieceList.get(piece) == true) haspiece.add(temp);
		}
		peers = haspiece.iterator();
		
		while(peers.hasNext()) {
			temp = peers.next();
			//THIS NEEDS TO BE THE PROPER CHOKE/INTEREST STATE CHECK. not sure on that one yet.
			//if(temp.pieceList.get(piece) == true) haspiece.add(temp);
		}
		
		return -1;
	}
	
}
