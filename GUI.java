import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GUI {
	Frame frame;
	Panel RootPanel;
	Panel TokenPanel;
	Panel InputPanel;
	Button AddPoem;
	JComboBox<String> dropdownRoot;
	JComboBox<String> dropdownToken;
	JButton addBtn;
	JButton addRootBtn;
	JButton Tokenise;
	TextField verse;
	private static final String DB_URL = "jdbc:mysql://localhost:3306/scdlab11";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "";
	Button Token;
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	JTextArea resultArea;
	public GUI() {
		// TODO Auto-generated constructor stub
		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame = new Frame("Poem ");
		frame.setSize(350, 500);
		AddPoem = new Button("Add Poem");
		Token = new Button("Tokenise Verse");
		frame.setLayout(new FlowLayout());
		frame.add(AddPoem);
		frame.add(Token);
		frame.setVisible(true);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		AddPoem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				page2();
			}
		});

		Token.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				page3();
			}
		});

	}

	

	

	

	public void page3() {
		frame.removeAll();
		InputPanel = new Panel();

		TextField root = new TextField(30);
		TextField token = new TextField(30);

		TokenPanel = new Panel();
		RootPanel = new Panel();
		Label ver = new Label("Verse :");
		Label tokenlbl = new Label("Select Token : ");
		Label rootlbl = new Label("Select Root : ");
		TextField poem = new TextField(30);
		verse = new TextField(30);

		JButton addBtn = new JButton("Add");
		JButton addRootBtn = new JButton("Add Root");
		
		resultArea = new JTextArea(10, 30);
		resultArea.setEditable(false);
	
		JScrollPane scrollableTextArea = new JScrollPane(resultArea); ;
		JButton Tokenise = new JButton("Tokenize Verse");
		
		dropdownRoot = new JComboBox<>(roots());
		Dimension largerSize = new Dimension(200, 30);
		dropdownRoot.setPreferredSize(largerSize);
		dropdownToken = new JComboBox<>(TokeniseString(getVersetext()));
		dropdownToken.setPreferredSize(largerSize);
		JComboBox dropdownVerse = new JComboBox<>(verseDrop());
		dropdownVerse.setPreferredSize(largerSize);
		RootPanel.setSize(200, 200);
		InputPanel.add(ver);
		InputPanel.add(dropdownVerse);
		frame.add(InputPanel);
		frame.add(scrollableTextArea);
		frame.add(tokenlbl);
		frame.add(dropdownToken);
		frame.add(rootlbl);
		frame.add(dropdownRoot);
		frame.add(addRootBtn);
		tokenlbl.setVisible(false);
		dropdownToken.setVisible(false);
		rootlbl.setVisible(false);
		dropdownRoot.setVisible(false);
		addRootBtn.setVisible(false);
		frame.add(Tokenise);
		// frame.add(InputPanel);
		// frame.add(RootPanel);
		// frame.add(TokenPanel);
		dropdownRoot.setActionCommand("dropdownRoot");
		dropdownToken.setActionCommand("dropdownToken");
		addBtn.setActionCommand("AddPoem");
		addRootBtn.setActionCommand("AddRoot");
		Tokenise.setActionCommand("Tokenise");
dropdownVerse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String selectedOption = (String) dropdownVerse.getSelectedItem();
				verse.setText(selectedOption);
				
					}
			
		});
	
	}

	public static void main(String[] args) {
		GUI objGui = new GUI();
		
	}
}
