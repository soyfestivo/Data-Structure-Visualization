import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class OptionsPane extends JPanel implements ActionListener {
	private JTextField textBox;
	private Button actionButton;
	private UIWindow parentClass;

	public OptionsPane(UIWindow parentClass, String name, String action) {
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

	private int parseInput() {
		int data = 0;
		try {
			data = new Integer(textBox.getText());
		}
		catch(Exception e) {
			data = -1;
		}
		return data;
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == actionButton) {
			parentClass.actionPerformed(this, parseInput());
		}
	}
}