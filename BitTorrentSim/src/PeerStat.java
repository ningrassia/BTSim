
public class PeerStat {
	public double avg_dl_speed;
	public double avg_ul_speed;
	public double time_to_dl;
	
	
	// Nik, let me know if these 3 fields will create huge memory load. If it 
	// does, please see if you can arrange to only keep track of this for maybe 
	// 3-5 peers.
	/**
	 * ArrayList containing the ID of all peers while the subject is downloading
	 * from them. The time each peer disconnected should be stored in 
	 * {@link #lostPeerTime}, with exact same index as that of the peer in this
	 * ArrayList.
	 */
	public ArrayList<Integer> lostPeerIDList;
	
	/**
	 * Please refer to {@link #lostPeerIDList}
	 */
	public ArrayList<Integer> lostPeerTimeList;
	
	/**
	 * Array of the download speed of the peer at all sample time point.
	 */
	public ArrayList<Integer> downloadSpd ;
}
