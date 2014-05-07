import java.util.ArrayList;

/**
 * General-purpose mailbox between threads (hope this works!)
 * @author ningrassia
 *
 */
public class Mailbox {
	private ArrayList<String> messages;
	
	/**
	 * Removes a message from this box and returns it.
	 * @return The oldest message in this mailbox. 
	 */
	public String getMessage() {
		String message;
		try{
			message = messages.remove(0); //get the oldest message;
		}
		catch(Exception e){
			message = "MAILBOX_EMPTY";
		}
		return message;
	}
	
	/**
	 * Adds a message to this mailbox.
	 * @param message The message to add to the mailbox.
	 */
	public void putMessage(String message) {
		messages.add(message);
	}
	
	public boolean hasMessage(){
		return !messages.isEmpty();
	}
}
