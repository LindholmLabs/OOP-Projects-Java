package assignment2;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import se.his.it401g.todo.Task;
import se.his.it401g.todo.TaskInputListener;
import se.his.it401g.todo.TaskListener;

/**
 * Implements a simple work task type, following the Task.java interface class.
 *  
 * This file licensed under the <a href="https://creativecommons.org/licenses/by/4.0/">Creative Commons (CC) BY 4.0 license</a>.
 * 
 * @author Dr. Erik Billing, University of Skovde
 *
 */

// Custom task
public class WorkTask extends JPanel implements Task, ActionListener {

	/**
	 * The editable text field. 
	 */
	private JTextField text;
	
	/**
	 * The non editable text label.
	 */
	private JLabel textLabel;
	
	/**
	 * Check box holding the completion status. 
	 */
	JCheckBox completed = new JCheckBox();
	
	//comment
	JPanel colourBlock = new JPanel();
	
	/**
	 * The task listener used for reporting changes to the main application. 
	 */
	private TaskListener listener;
	
	/**
	 * This is the constructor for the task, initiating the GUI component for the task. Several listeners are used to react to various events in the GUI.  
	 */
	public WorkTask() {
		super(new BorderLayout());
		this.text = new JTextField("New task",20);
		this.textLabel = new JLabel();
		this.textLabel.setVisible(false);
		
		TaskInputListener inputListener = new TaskInputListener(this, text, textLabel);
		this.text.addKeyListener(inputListener);
		this.textLabel.addMouseListener(inputListener);
		
		JPanel center = new JPanel();
		center.add(text);
		center.add(textLabel);
		add(center,BorderLayout.CENTER);
		
		GridLayout eastGrid = new GridLayout(1, 2, 5, 0);
		JPanel east = new JPanel();
		
		east.setLayout(eastGrid);
		add(east, BorderLayout.EAST);
		
		// how to make remove, colour center aligned vertically in the box.
		JButton remove = new JButton("Remove");
		remove.addActionListener(inputListener);
		east.add(remove);
		
		JButton colour = new JButton("Choose Colour!");
		colour.addActionListener(this);
		east.add(colour);
		
		GridLayout grid = new GridLayout(0,2);
		JPanel west = new JPanel();
		west.setLayout(grid);
		add(west, BorderLayout.WEST);
		
		colourBlock.setBackground(Color.white);
		colourBlock.setSize(new Dimension(20, 50));
		west.add(colourBlock);
		
		completed.addItemListener(inputListener);
		west.add(completed);
		
		setMaximumSize(new Dimension(1000,75));
		setBorder(new TitledBorder(getTaskType()));
	}

	@Override
	public String getText() {
		return text.getText();
	}

	@Override
	public String getTaskType() {
		return "Work";
	}

	@Override
	public void setTaskListener(TaskListener t) {
		listener = t;		
	}

	@Override
	public TaskListener getTaskListener() {
		return listener;
	}

	@Override
	public boolean isComplete() {
		// set strikethrough on text when iscomplete is true.
		
		return completed.isSelected();
	}

	@Override
	public Component getGuiComponent() {
		// Since this class extends JPanel, it is itself a GUI component, and thus we can return "this". 
		return this;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String choice = chooseColour();
		if (!choice.isEmpty()) {
			setColour(choice);
		}
		
	}
	
	private String chooseColour() {
		String[] options = {"None", "Red", "Blue", "Green"};

		Object choice = JOptionPane.showInputDialog(null, "Choose colour!", "Options", JOptionPane.DEFAULT_OPTION, null, options, "0");
		if (choice != null) {
		    return choice.toString(); //return selected sorting type 
		}
		return ""; //user cancelled, returning empty string, no sorting will ensue.
	}
	
	private void setColour(String colour) {
		switch(colour) {
		case "None":
			colourBlock.setBackground(Color.white);
			break;
		case "Red":
			colourBlock.setBackground(Color.red);
			break;
		case "Blue":
			colourBlock.setBackground(Color.blue);
			break;
		case "Green":
			colourBlock.setBackground(Color.green);
			break;
		default:
			break;
		}
	}

}
