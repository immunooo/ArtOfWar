package GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUIMain extends JFrame{
	
	//height and width
	private static final int WIDTH = 600;
	private static final int HEIGHT = 800;
	
	public GUIMain()
	{
		//set a title
		super("Art of War");
		//basic stuff
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setLayout(new GridBagLayout());
		
		JPanel pane = new JPanel(new GridBagLayout());
		//GridBagConstraints c = new GridBagConstraints();
		
		//some rabdom example, I am trying to understand how this layout wroks
		JButton button;
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		button = new JButton("Button 1");
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(button, c);

		button = new JButton("Button 2");
		c.gridx = 1;
		c.gridy = 0;
		pane.add(button, c);

		button = new JButton("Button 3");
		c.gridx = 2;
		c.gridy = 0;
		pane.add(button, c);

		button = new JButton("Long-Named Button 4");
		c.ipady = 40;      //make this component tall
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 1;
		pane.add(button, c);

		button = new JButton("5");
		c.ipady = 0;       //reset to default
		c.weighty = 1.0;   //request any extra vertical space
		c.anchor = GridBagConstraints.PAGE_END; //bottom of space
		c.insets = new Insets(10,0,0,0);  //top padding
		c.gridx = 1;       //aligned with button 2
		c.gridwidth = 2;   //2 columns wide
		c.gridy = 2;       //third row
		pane.add(button, c);
		
		add(pane);
	    setVisible(true);
	}

}
