package ch.neliuk.tools.timeevo.observers;

/**
 * <b>Implements an observable which can update its observers</b>
 * <p>
 * Add the ability to handle many objects that implements "Observer"
 * </p>
 * 
 * @see Observer
 * 
 * @author Sardaukar
 * @version 1.0
 */
public interface Observable {
	
	/**
	 * Add an observer to the list of observers.
	 * 
	 * @param obs
	 * 			The observer to add
	 * 
	 * @see Observer
	 */
	public void addObserver(Observer obs);
	
	/**
	 * Inform of an update every observer that subscribed to this
	 * observable
	 * 
	 * @see Observer
	 */
	public void updateObservers();
	
	/**
	 * Empty the whole list of observers
	 * 
	 * @see Observer
	 */
	public void delObservers();
}
