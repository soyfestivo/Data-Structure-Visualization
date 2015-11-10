import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class OptionsPane extends JPanel implements ActionListener
{
	private JTextField textBox; // textbox is a JTextField object
	private Button actionButton; // actionButton is a Button object
	private UIWindow parentClass; // parentclass will represent a UIWindow object

	public OptionsPane(UIWindow parentClass, String name, String action)
    // PRE : parentClass is valid, name and action not null
    // POST: Returns an OptionsPane object that initializes a GUI Windows with the corresponding textbox
    //       actionButton and labels displaying
    {
		super();
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BorderLayout());
		setBorder(new EmptyBorder(10, 15, 15, 10));
		this.parentClass = parentClass;
		setLayout(new GridLayout(3, 1));
		textBox = new JTextField(5);
		actionButton = new Button(action);

		actionButton.addActionListener(this);
		buttonPanel.add(actionButton, BorderLayout.EAST);

		add(new JLabel(name));
		add(textBox);
		add(buttonPanel);
	}

	private int parseInput()
    // POST: FCTVAL == returns an integer based on the number string that was inserted in the textbox
    {
		int data = 0;
		
        // If the attempt is a success, parse string into an int
        try
        {
			data = new Integer(textBox.getText());
		}
		// If the attempt is a failure, data variables becomes -1
        catch(Exception e)
        {
			data = -1;
		}
		return data;
	}

	public void actionPerformed(ActionEvent e)
    // POST: FCTVAL == executes the parseInput function, in the event that the button is pressed
    {
        // Will only execute if the button is pressed
		if(e.getSource() == actionButton)
        {
			parentClass.actionPerformed(this, parseInput());
			textBox.setText("");
		}
	}
}