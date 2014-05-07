import java.util.ArrayList;
import java.util.Iterator;

/**
 * Used to store/get all trackers being simulated by BTSim.
 * @author ningrassia
 *
 */
public class TrackerList {
	static ArrayList<Tracker> trackers;
	static int next_tracker_id;
	
	/**
	 * Creates a tracker, adds it to the list and runs it.
	 */
	public void addTracker() {
		//create the  and add it to the list
		Tracker newTracker = new Tracker();
		trackers.add(newTracker);
		
		//configure the tracker
		
	}
	
	/**
	 * Gets a Tracker object for a given tracker_id.
	 * @param tracker_id The tracker_id of the desired tracker.
	 * @return The actual Tracker.
	 */
	public Tracker getTracker(int tracker_id) {
		Iterator<Tracker> trackerIter = trackers.iterator();
		while(trackerIter.hasNext()) {
			Tracker tempTracker = trackerIter.next();
			if(tempTracker.getID() == tracker_id) {
				return tempTracker;
			}
		}
		
		return null; //if we didn't find the peer!
	}
	
	/**
	 * Returns an iterator over the tracker list
	 * @return An Iterator over the tracker list 
	 */
	public Iterator<Tracker> getTrackerIter(){
		return trackers.iterator();
	}
}
