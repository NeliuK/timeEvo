package ch.neliuk.tools.timeevo.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import ch.neliuk.tools.timeevo.model.Clock;
import ch.neliuk.tools.timeevo.observers.Observer;

/**
 * <b>Displays the current time</b>
 * <p>
 * Displays the current time in a JFrame. The frame is always on top
 * to keep visible at any time, but a left click hide the form for a
 * defined delay to access things behind the window.
 * </p><p>
 * A right click shows a context menu with several options as:
 * <ul>
 * <li>Move
 * <li>Resize
 * <li>Quit
 * </ul>
 * </p>
 * 
 * 
 * @author Sardaukar
 * @version 0.1
 */
public class Window extends JFrame {
	/**
	 * The JLabel that displays the time.
	 * This label is updated at each time change.
	 */
	private JLabel label = new JLabel();
	
	/**
	 * The Clock that get and format the time.
	 */
	private Clock clock;
	  
	// Window position
	//private double posX;
	//private double posY;
	  
	// Items for menu
	private JPopupMenu contextMenu = new JPopupMenu();
	private JMenuItem move = new JMenuItem("Move");
	private JMenuItem resize = new JMenuItem("Resize");
	private JMenuItem quit = new JMenuItem("Quit");
	
	/**
	 * Window Constructor
	 * <p>
	 * Initialize the Frame and its components.
	 * Create a new Clock and set it to update the label with the
	 * new time at each update
	 * </p>
	 * 
	 * @see Clock
	 */
	public Window(){
		//Initialize JFrame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setSize(200, 80);
		this.getContentPane().setBackground(Color.BLACK);
		this.setAlwaysOnTop(true);
		//this.posX = this.getLocation().getX();
		//this.posY = this.getLocation().getY();
		//this.setUndecorated(true);
		    
		// Initialize context menu
		this.initContextMenu();
		    			
		// Initialize the clock
		this.clock = new Clock();
		// Add a listener to the clock
	    this.clock.addObserver(new Observer(){
	    	// Set the new time on update
	    	public void update(String hour) {
	    	label.setText(hour);
	    	}
	    });
			
	    //Initialize JLabel
	    Font police = new Font("DS-digital", Font.TYPE1_FONT, 30);
	    this.label.setFont(police);
	    this.label.setForeground(Color.GREEN);
	    this.label.setHorizontalAlignment(JLabel.CENTER);
	    this.label.addMouseListener(new ClickWindowListener());
	    //On ajoute le JLabel à la JFrame
	    this.getContentPane().add(this.label, BorderLayout.CENTER);		
	    this.setVisible(true);
	    this.clock.run();
	}

	/**
	 * Main method. Loads a new instance of Window.
	 * 
	 * @param args
	 * 			Array string for main entry args.
	 * 
	 * @see Window#Window()
	 */
	public static void main(String[] args){
		Window fen = new Window();
	}

	/**
	 * Format the context menu that pops on right-click.
	 * <p>
	 * Adds the actionListener to close the application when "Quit" is
	 * selected.
	 * </p>
	 */
	public void initContextMenu(){
		  
		// Create context menu
		contextMenu.add(move);
		contextMenu.add(resize);
		contextMenu.addSeparator();
		contextMenu.add(quit);
		  
		// Add quitting listener
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae){
			System.exit(0);
			}
		  });	  
	}
	  
	/** 
	 * <b>Listener for a click on the window.</b>
	 * <p>
	 * Hide the window for a defined amount of time on left-click,
	 * shows a menu on right-click.
	 * </p>
	 * @author Sardaukar
	 */
	public class ClickWindowListener extends MouseAdapter{
		public void mouseReleased(MouseEvent me){
			  
			// Shows the context menu on right-click
			if(me.isPopupTrigger()){
				contextMenu.show(label, me.getX(), me.getY());
			}
			// Clock disappear for 5 seconds on left click
			else {
				setVisible(false);
				try{
					Thread.sleep(5000);
				} catch(InterruptedException iEx){
					iEx.printStackTrace();
				}
				setVisible(true);
			}
		}
	}
}
