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

import ch.neliuk.tools.timeevo.model.Horloge;
import ch.neliuk.tools.timeevo.observers.Observer;

public class Fenetre extends JFrame {
	private JLabel label = new JLabel();
	private Horloge horloge;
	  
	// Window position
	//private double posX;
	//private double posY;
	  
	// Items for menu
	private JPopupMenu contextMenu = new JPopupMenu();
	private JMenuItem deplacer = new JMenuItem("Déplacer");
	private JMenuItem redimensionner = new JMenuItem("Redimensionner");
	private JMenuItem quitter = new JMenuItem("Quitter");
		
	public Fenetre(){
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
		this.horloge = new Horloge();
		// Add a listener to the clock
	    this.horloge.addObservateur(new Observer(){
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
	    this.label.addMouseListener(new ClickFenetreListener());
	    //On ajoute le JLabel à la JFrame
	    this.getContentPane().add(this.label, BorderLayout.CENTER);		
	    this.setVisible(true);
	    this.horloge.run();
	}

	// Main method
	public static void main(String[] args){
		Fenetre fen = new Fenetre();
	}
	  
	public void initContextMenu(){
		  
		// Create context menu
		contextMenu.add(deplacer);
		contextMenu.add(redimensionner);
		contextMenu.addSeparator();
		contextMenu.add(quitter);
		  
		// Add quitting listener
		quitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae){
			System.exit(0);
			}
		  });	  
	}
	  
	// Listener for click on window
	public class ClickFenetreListener extends MouseAdapter{
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
