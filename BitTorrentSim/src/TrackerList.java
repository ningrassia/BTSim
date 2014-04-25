import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 * @author ningrassia
 *
 */
public class TrackerList {
	ArrayList<Tracker> trackers;
	
	/**
	 * Creates a tracker, adds it to the list and runs it.
	 */
	public void addTracker() {
		//create the  and add it to the list
		Tracker newTracker = new Tracker();
		trackers.add(newTracker);
		
		//configure the tracker
		
		//start the thread
		new Thread(newTracker).start();
	}
	
	/**
	 * Gets a Tracker object for a given tracker_id.
	 * @param tracker_id The tracker_id of the desired tracker.
	 * @return The actual Tracker.
	 */
	public Tracker getTracker(String tracker_id) {
		Iterator<Tracker> trackerIter = trackers.iterator();
		while(trackerIter.hasNext()) {
			Tracker tempTracker = trackerIter.next();
			if(tempTracker.getID() == tracker_id) {
				return tempTracker;
			}
		}
		
		return null; //if we didn't find the peer!
	}
}
