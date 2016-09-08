package ch.neliuk.tools.timeevo.model;

import java.util.ArrayList;
import java.util.Calendar;

import ch.neliuk.tools.timeevo.observers.Observable;
import ch.neliuk.tools.timeevo.observers.Observer;

/**
 * <b> Clock is the class running the core of the clock</B>
 * <p>
 * Update every second and send the new time to all observers
 * </p>
 * 
 * @author NeliuK
 * @version 1.0
 */
public class Clock implements Observable{
	/**
	 * Instance of a calendar to retrieve the time
	 */
	private Calendar cal;
	
	/**
	 * String to format the time, returned by the class every update
	 * 
	 * @see Clock#updateObservers()
	 */
	private String hour = "";
	
	/**
	 * Observers collection. Contains the observers subscribing to this Observable.
	 * 
	 * @see Clock#addObserver(Observer)
	 * @see Clock#updateObservers()
	 * @see Clock#delObservers()
	 */
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	
	/**
	 * Main method of this class. Get the time every second, format the time string
	 * and update the observers
	 */
	public void run() {
		while(true){
			
			// Get the time
			this.cal = Calendar.getInstance();
			this.hour =
				// Hours
				this.cal.get(Calendar.HOUR_OF_DAY) + " : " + 
				(// Minutes
				this.cal.get(Calendar.MINUTE) < 10 ? "0" + this.cal.get(Calendar.MINUTE) : this.cal.get(Calendar.MINUTE)) + " : " + ( 
				// Seconds
				(this.cal.get(Calendar.SECOND)< 10) ? "0" + this.cal.get(Calendar.SECOND) : this.cal.get(Calendar.SECOND));
			
			// Tells observers the time has been updated
			this.updateObservers();
	      
			// Wait 1 second
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	  /**
	   *  Adds an observer to the list.
	   *  
	   *  @param obs
	   *  			The observer to add
	   *  
	   *  @see Observer
	   */
	  public void addObserver(Observer obs) {
	    this.listObserver.add(obs);
	  }
	  
	  /**
	   *  Removes all the observers from the list
	   *  
	   *  @see Observer
	   */
	  public void delObservers() {
	    this.listObserver = new ArrayList<Observer>();
	  }

	  /**
	   *  Tells all observers the observable object has been modified by
	   *  calling the update(hour) method with the new time for each of them.
	   *  
	   *  @see Observer
	   *  
	   *  @see Observer#update(String)
	   */
	  public void updateObservers() {
	    for(Observer obs : this.listObserver )
	      obs.update(this.hour);
	  }
	}