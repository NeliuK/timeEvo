package ch.neliuk.tools.timeevo.observers;

import ch.neliuk.tools.timeevo.model.Clock;

/**
 * Implements an observer which can subscribe to an observable and get
 * informed of its updates
 * 
 * @see Observable
 * 
 * @author Sardaukar
 * @version 1.0
 *
 */
public interface Observer {
	
	/**
	 * Update the  the observer
	 * 
	 * @param hour
	 * 			A formatted string of the time up-to-date
	 * 
	 * @see Observable#updateObservers()
	 * @see Clock#updateObservers()
	 */
	public void update(String hour);

}
