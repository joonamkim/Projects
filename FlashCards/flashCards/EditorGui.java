/**
 * 
 */
package flashCards;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Joo-nam Kim
 */
public class EditorGui extends JFrame {
	
	StudyList studyList = new StudyList(); 
	
	//Main declarations
	JFrame frame;
	JPanel panel;
	JPanel secondPanel;
	JLabel label; 
	JTextArea mainText; 
	
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem load;
	JMenuItem save;
	JMenuItem saveAs;
	JMenuItem quit;
	JMenu tools;
	JMenuItem add; 
	JMenuItem find;
	JMenuItem edit;
	JMenuItem delete;
	JTextArea textArea;
	
	//Boolean to check quit without saving
	Boolean saved = false; 
	
	//Load variables
	JPanel loadPanel;
	JTextArea loadText;
	String findString;
	
	//Find variables
	JButton findButton;
	JLabel findLabel;
	JTextField findItemTextfield;
	JTextArea findText;
	JTextArea findMsg; 
	Item foundItem; 
	
	//Add variables
	JLabel addStimLabel;
	JTextField addTextfield;
	JLabel addRespLabel;
	JTextField respTextfield;
	JButton addButton;
	Item itemToAdd;
	JLabel msg;
	JLabel blankAddMsg;
	JTextArea addMsg;
	String addStim; 
	String addResp;
	
	//Edit variables
	JButton editButton;
	JButton viewButton; 
	JLabel numberLabel;
	JTextField number;
	JLabel stimLabel;
	JTextField stim;
	JLabel respLabel;
	JTextField resp;
	JTextArea editText;
	String[] findTempArray = new String[studyList.itemList.size()];
	int index;
	String stimText;
	String respText;
	JLabel blankLabel;
	
	//Delete variables
	JButton deleteButton;
	JButton deletedListButton;
	JLabel deleteNumberLabel;
	JTextField deleteNumber;
	JTextArea deleteText;
	int deleteIndex;
	String toBeDeleted; 
	
	
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
    	new EditorGui().editor();
    	
    }
    /**
     * Main method to create a main frame, panel, menu, etc. 
     */
    public void editor() { 
    	
    	frame = new JFrame();
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setLocationRelativeTo(null);
    	panel = new JPanel(new FlowLayout());
    	label = new JLabel("FLASHCARD EDITOR", JLabel.CENTER);
    	mainText = new JTextArea(20,40);
    	mainText.setText("Hi! Welcome to FlashCard Editor." + "\n" + "In this editor, you can ADD, FIND, EDIT, and DELETE any of the flashcards!" + "\n" + "\n" +
    					"INSTRUCTIONS : " + "\n" + "\n" + "1) LOAD a file you want to edit by clicking on FILE on the top left." + "\n" + 
    					"2) To EDIT your file, click on TOOLS next to FILE and" + "\n" + "    select one you want to do." + "\n" + 
    					"3) After editing, make sure you SAVE your editing work!" + "\n" + "    But don't worry, we will ask you to save anytime you try to" + "\n" + 
    					"    quit without saving!" + "\n" + "\n" + 
    					"Now, let's get started! Click on LOAD to load a file" + "\n" 
    					+ "and then TOOLS to select what you want to do!" 
    			);
    	
    	
    	mainText.setEditable(false);
    	
    	panel.add(label);
    	panel.add(mainText);
    	
    	//MenuBar and Menu
    	menuBar = new JMenuBar(); 
    	setJMenuBar(menuBar);
    	menu = new JMenu("File");
    	menu.setMnemonic('m');
    	tools = new JMenu("Tools");
    	tools.setMnemonic('t');
    	menuBar.add(menu);   
    	menuBar.add(tools);
    	frame.setJMenuBar(menuBar);
    	
    	//JMenuItems
    	load = new JMenuItem("Load");
    	save = new JMenuItem("Save");
    	saveAs = new JMenuItem("Save As");
    	quit = new JMenuItem("Quit");
    	
    	//TOOLS
    	add = new JMenuItem("Add");
    	find = new JMenuItem("Find");
    	edit = new JMenuItem("Edit");
    	delete = new JMenuItem("Delete");
    	
    	//Call necessary methods for FILE menu 
    	load.addActionListener(new LoadActionListener());
    	save.addActionListener(new SaveActionListener());
    	saveAs.addActionListener(new SaveAsActionListener());
    	quit.addActionListener(new QuitActionListener());
    	
    	//Call necessary methods for TOOLS menu
    	add.addActionListener(new AddActionListener());
    	find.addActionListener(new FindActionListener());
    	edit.addActionListener(new EditActionListener());
    	delete.addActionListener(new DeleteActionListener());
    	
    	menu.add(load);
    	menu.add(save);
    	menu.add(saveAs);
    	menu.add(quit);
    	tools.add(add);
    	tools.add(find);
    	tools.add(edit);
    	tools.add(delete);
    	panel.setBackground(Color.CYAN);
    	
    	frame.getContentPane().add(panel);
    	frame.setSize(500, 500);
    	frame.setVisible(true);
    	
    	/**
    	 * Window listener to handle quit by clicking X without saving. 
    	 */
    	frame.addWindowListener(new WindowAdapter() {
    		/**
    		 * Event handler. 
    		 */
	    	public void windowClosing(WindowEvent e) {
    			if (saved == true){ // if user saved the edit, allow quit
    				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    				
    			} else { // if not saved, ask!
    				int confirmed = JOptionPane.showConfirmDialog(null, "Do you want to quit without saving?", "BYE...",JOptionPane.YES_NO_OPTION);
    				if (confirmed == JOptionPane.YES_OPTION) frame.dispose();
    				else{
    					frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    				}
    			}
    		}
    	});
    	
    }
    /**
     * Setting a panel for ADD 
     */
    public void setPanelForAdd() {
    	
    	addButton = new JButton("ADD");
    	addStimLabel = new JLabel("Enter a stimulus to add:");
    	addTextfield = new JTextField(30);
    	addRespLabel = new JLabel("Enter a response to add:");
    	respTextfield = new JTextField(30);
    	addMsg = new JTextArea(18, 40);
    	addMsg.setText("Press ADD to show status" + "\n");
    	blankAddMsg = new JLabel("\n");
    	addMsg.setEditable(false);
    	
    	panel.add(addStimLabel);
    	panel.add(addTextfield);
    	panel.add(addRespLabel);
    	panel.add(respTextfield);
    	panel.add(blankAddMsg);
    	
    	panel.add(addMsg);
    	panel.add(addButton, BorderLayout.SOUTH);
    	
    	panel.setVisible(true);
    	frame.setVisible(true);
    }
    /**
     * Helper method
     * @param an Item variable 
     * @return true if a itemList object has the particular item variable, else false. 
     */
    public boolean hasStim(Item itemToAdd) {
    	for (Item item : studyList.itemList) {
    		if (item.getStimulus().equals(itemToAdd.getStimulus())) {
    			return true;
    		} 	
    	}
    	return false;
    }
    /**
     * Method to actually add a new item to the class itemList. 
     */
    public void addNewItem() {
    	
    	addStim = addTextfield.getText();
    	addResp = respTextfield.getText();
    	itemToAdd = new Item(addStim, addResp);
    	
    	if (hasStim(itemToAdd)) { //check if itemList already has this item (no same stimulus allowed). 
    		addMsg.setText("This item already exists! Try with a different STIMULUS!");

    	} else { 
        	if (!addStim.isEmpty() && !addResp.isEmpty()) { // requires user to have BOTH stimulus & response in order to add. 
        		
        		studyList.itemList.add(itemToAdd);
        		addMsg.setText("Item Successfully Added!" + "\n" + "Here is the updated list : " + "\n" + "\n");
        		printNumberedList(addMsg);
        		
        	} else {  // Give a message, if user is missing either a stimulus or a response. 
        		addMsg.setText("Missing a STIMULUS or a RESPONSE!");
        		}
        	}
    	}
    /**
     * 
     * @author Joo-nam Kim
     * Add ActionListener to implement adding action. 
     */
    class AddActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			// Make sure a previous panel is not overwritten by removing all the components used before.
			panel.setBackground(Color.white);
			panel.setBackground(null);
			panel.removeAll();
    		
			// Call necessary methods. 
			setPanelForAdd();
			addMsg.setText(addTextfield.getText());
	    	addButton.addActionListener(new ActionListener()
	        {
	    	      public void actionPerformed(ActionEvent e)
	    	      {
	    	    	  addNewItem(); 
	    	    	  saved = false; 
	    	      }
	    	    });
		}
    }
    /**
     * a method to set panel for Find. 
     */
    public void setPanelForFind() { 
    	
    	panel.setBackground(Color.white);
		panel.setBackground(null);
    	findButton = new JButton("FIND");
    	findButton.setBounds(30, 30, 30, 30);
    	findLabel = new JLabel("Enter an item to find :");
    	findItemTextfield = new JTextField(20);
    	findMsg = new JTextArea(20, 40);
    	
    	panel.add(findItemTextfield);
    	panel.add(findLabel);
    	panel.add(findButton);
    	panel.add(findMsg);
    	findMsg.setEditable(false);
    	
    	panel.setVisible(true);
    	frame.setVisible(true);
    }
    /**
     * Method that actually does the finding functionality. 
     */
    public void findItem() {

    	try { 
    		foundItem = studyList.find(findItemTextfield.getText());
			findMsg.setText("Found Item! " + "\n" + "Stimulus : " + foundItem.getStimulus() + "\n" + "Response : " + foundItem.getResponse() + "\n");
		}
		catch (IllegalArgumentException e1) { // catch any errors!
			findMsg.setText("Could not find it!");
		}
    }
    
    /**
     * 
     * @author Joo-nam Kim 
     * Find ActionListener class. 
     */
    class FindActionListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			
			panel.removeAll();
			
			setPanelForFind();
			findButton.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			findItem();
	    			}
	    		});
			}	
    }
    /**
     * Method to set panel for Edit
     */
    public void setPanelForEdit() {
    	
    	editButton = new JButton("EDIT");
    	viewButton = new JButton("VIEW LIST");
    	
    	numberLabel = new JLabel("Choose a number to edit : " + "\n");
    	number = new JTextField(20);
    	
    	stimLabel = new JLabel("Enter a new stimulus : ");
    	stim = new JTextField(20);
    	
    	respLabel = new JLabel("Enter a new response : " + "\n");
    	resp = new JTextField(20);
    	
    	editText = new JTextArea(15, 40);
    	blankLabel = new JLabel("    ");
    	editText.setEditable(false);
    	
    	panel.add(editText);
    	panel.add(numberLabel);
    	panel.add(number);
    	panel.add(stimLabel);
    	panel.add(stim);
    	panel.add(respLabel);
    	panel.add(resp);
    	panel.add(blankLabel);
    	panel.add(editButton);
    	panel.add(viewButton);
    	
    	frame.setVisible(true);
    }
    /**
     * Helper method
     * @param a JTextArea variable
     * will draw a numbered list of items in the text area given. 
     */
    public void printNumberedList(JTextArea text) {
    	
    	int counter = 1;
    	for (Item item : studyList.itemList) {
    		text.append(counter + ".  (" + item.getStimulus() + ", " + item.getResponse() + ")" + "\n");
    		counter += 1;
    	}
    }
    /**
     * Method that edits items.
     */
    public void editList() { 
    	
    	String st = number.getText();
    	stimText = stim.getText();
    	respText = resp.getText();
    	index = Integer.parseInt(st);
    	
    	if (!stimText.isEmpty() && !respText.isEmpty()) { // allow to edit only if both Stimulus and Response are provided by user. 
    		
    		studyList.modify(studyList.itemList.get(index-1), stimText, respText);
    		
    	}
    }
    /**
     * Edit ActionListener class
     * @author Joo-nam Kim
     *
     */
    class EditActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			panel.removeAll();
			
			//Set panel with a numbered list. 
	    	setPanelForEdit();
	    	printNumberedList(editText);
	    	
	    	editButton.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			editList();
	    			editText.setText("Editted Successfully!" + "\n" + "\n" + "Here are your new STIMULUS & RESPONSE : " + "\n"
	    			+ "\n" + "STIMULUS : " + studyList.itemList.get(index - 1).getStimulus() + "\n" + "RESPONSE : " + studyList.itemList.get(index - 1).getResponse());
	    			saved = false; 
	    			
	    			}
	    		});
	    	viewButton.addActionListener(new ActionListener() { // View button handler. 
				public void actionPerformed(ActionEvent arg0) {
					editText.setText("");
					printNumberedList(editText);
				}
	    	});
		}
    }
    /**
     * method to set panel for Delete.
     */
    public void setPanelForDelete() { 
    	deleteButton = new JButton("DELETE");
    	deletedListButton = new JButton("VIEW");
    	
    	deleteNumberLabel = new JLabel("Choose a number to DELETE : " + "\n");
    	deleteNumber = new JTextField(20);
    	deleteText = new JTextArea(15, 40); 
    	deleteText.setEditable(false);
    	
    	panel.add(deleteText);
    	panel.add(deleteNumberLabel);
    	panel.add(deleteNumber);
    	panel.add(deleteButton);
    	panel.add(deletedListButton);
    	
    	frame.setVisible(true);
    }
    /**
     * Method that does the deleting functionality. 
     */
    public void deleteItem() { 
    
    	String st = deleteNumber.getText();
       	deleteIndex = Integer.parseInt(st);
       	
       	if (deleteIndex > studyList.itemList.size()) { // allow user to only delete items within range. 
       		deleteText.setText("No item exists. Click on VIEW to see the list again.");
       	}
       	toBeDeleted = "("+studyList.itemList.get(deleteIndex - 1).getStimulus() + ", " +studyList.itemList.get(deleteIndex - 1).getResponse() + ")";
    	studyList.delete(studyList.itemList.get(deleteIndex - 1));
    		
    
    }
    /**
     * Delete ActionListener class
     * @author Joo-nam Kim
     *
     */
    class DeleteActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			
			panel.setBackground(Color.white);
			panel.setBackground(null);
			
			panel.removeAll();
	    	
			//set panel with a numbered list
			setPanelForDelete();
	    	printNumberedList(deleteText);
	    	
	    	deleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					deleteItem();
					deleteText.setText(toBeDeleted + " is successfully deleted!" + "\n" + "If you want to see the updated list press VIEW"); 
					saved = false; 
				}
	    	});
	    	
	    	deletedListButton.addActionListener(new ActionListener() { // Show Updated List
				public void actionPerformed(ActionEvent arg0) {
					deleteText.setText("");
					printNumberedList(deleteText);
				}
	    	});
	
		}
    	
    }
    /**
     * Load ActionListener class
     * @author Joo-nam Kim
     *
     */
    class LoadActionListener implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		panel.removeAll();  
    		
			loadPanel = new JPanel(new FlowLayout());
	    	loadText = new JTextArea(20, 40);
	    	loadText.setEditable(false);
	    	panel.add(loadText);
	    	
	    	try {
	    		
				studyList.load();
				printNumberedList(loadText);
				
			} catch (IOException e1) { // Catch IOException here. 
				JOptionPane.showMessageDialog(frame,
		                "Wrong file.");
			}
	    	frame.setContentPane(panel);
	    	frame.setVisible(true);
    	}
    }
    /**
     * Save ActionListener class
     * @author Joo-nam Kim
     *
     */
    class SaveActionListener implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		try {
				studyList.save();
				saved = true; 
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(frame,
		                "Save error");
			}
    	}
    }
    /**
     * SaveAs ActionListener class
     * @author Joo-nam Kim
     *
     */
    class SaveAsActionListener implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		try {
    			studyList.saveAs();
    		
    		} catch (IOException e1) {
    			JOptionPane.showMessageDialog(frame,
		                "Save error");
    			}
    		}
    	}
    
    /**
     * Quit ActionListener class
     * @author Joo-nam Kim
     *
     */
    class QuitActionListener implements ActionListener {
    	  public void actionPerformed(ActionEvent e) {
    	    
    		  if (saved == false) { // Ask user to save before quit!
    			  if (JOptionPane.showConfirmDialog(null, "Do you want to quit without saving?", "WARNING",
    				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
    
    				  frame.dispose();
    			  
    			  } else {
    				  if (JOptionPane.showConfirmDialog(null, "Do you want to save?", "SMILE :) ",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) { // Ask nicely to save.
    					  try {
    						  studyList.save();
    						  } catch (IOException e1) {
    							  e1.printStackTrace();
    						  }
    					  } else {
    						  frame.dispose();
    					  }
    				  }
    			  }
    		  frame.dispose();
    		  }
    	  }
    	
    
	

}
