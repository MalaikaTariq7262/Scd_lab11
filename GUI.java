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
public String[] TokeniseString(String s) {
		String[] arr = s.split(" ");
		return arr;


		public boolean addRoot(String Root) {
		try {
			
			String sql = "INSERT INTO roots (root) VALUES (?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, Root);

			int rowsInserted = preparedStatement.executeUpdate();

	}
public String[] verseDrop() {
		String Filename = "PoemFile.txt";
		ArrayList<String> arr = new ArrayList<String>();
		String s = " ";
		arr.add(s);
		try (BufferedReader f = new BufferedReader(new FileReader(Filename))) {
			String line;
			while ((line = f.readLine()) != null) {
				addVerse("poem1",line);
				arr.add(line);
			}
			String[] arrs = new String[arr.size()];
			for (int i = 0; i < arr.size(); i++) {
				arrs[i] = arr.get(i);

			}
			return arrs;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	public String getVersetext() {
		return verse.getText() + "";
	}
public void tokenresultset(String s)
{
	resultArea.setText("");
	String arr[]=TokeniseString(s);
	 if
	 (arr.length=='\0') {
	  resultArea.append("No Tokens Available"); } else 
	  {
		  for(int i=0;i<arr.length;i++)
		  {
			  resultArea.append("Tokens are:");
			  resultArea.append(arr[i]+"\n");
		  }
	 } 
}
	


			if (rowsInserted > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public String[] roots() {
		String Filename = "Roots.txt";
		ArrayList<String> arr = new ArrayList<String>();
		String s = " ";
		arr.add(s);
		try (BufferedReader f = new BufferedReader(new FileReader(Filename))) {
			String line;
			while ((line = f.readLine()) != null) {
				String[] arrline = line.split(" ");
				for (int i = 0; i < arrline.length; i++) {
					arr.add(arrline[i]);
				}
			}
			String[] arrs = new String[arr.size()];
			for (int i = 0; i < arr.size(); i++) {
				arrs[i] = arr.get(i);
//addRoot(arr.get(i)); roots added in data base 
			}
			return arrs;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}add

	public boolean InsertToken(String root, String token) {
	    try {
	       
	      String tokenInsertQuery = "INSERT INTO tokens (token,root) VALUES (?, ?)";
	        preparedStatement = connection.prepareStatement(tokenInsertQuery);
	        preparedStatement.setString(1, token);
	        preparedStatement.setString(2, root);

	        int rowsInserted = preparedStatement.executeUpdate();

	        if (rowsInserted > 0) {
	            return true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
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

	dropdownRoot.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String selectedOption = (String) dropdownRoot.getSelectedItem();
				root.setText(selectedOption);
			}
		});
		dropdownToken.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 
				String selectedOption = (String) dropdownToken.getSelectedItem();
				token.setText(selectedOption);
			}
		});
addRootBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = getVersetext();
		        String[] tokens = TokeniseString(s);
			 dropdownToken.addItem(" ");
		        for(int i=0;i<tokens.length;i++)
		        {
		        dropdownToken.addItem(tokens[i]);
		        }
				addRootBtn.setVisible(false);
				tokenlbl.setVisible(true);
				dropdownToken.setVisible(true);
				rootlbl.setVisible(true);
				dropdownRoot.setVisible(true);

				addRootBtn.setVisible(true);
				if(token.getText()!=" "&& root.getText()!="")
				{				if(InsertToken(token.getText(),root.getText()))
				{
					JOptionPane.showMessageDialog(frame, "Insert Successful ");
				}else
				{
					JOptionPane.showMessageDialog(frame, "Insert Unsuccessful ");
				}
				}
			}

dropdownVerse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String selectedOption = (String) dropdownVerse.getSelectedItem();
				verse.setText(selectedOption);
				
					}
			
		});
Tokenise.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String s = getVersetext();
		        String[] tokens = TokeniseString(s); // Call your tokenization method
		        if (tokens.length > 0) {
		            resultArea.setText("Tokens are:\n");
		            for (String token : tokens) {
		                resultArea.append(token + "\n");
		            }
		        } else {
		            resultArea.setText("No Tokens Available");
		        }
		        addRootBtn.setVisible(true);
}
		});
	
	}

	public static void main(String[] args) {
		GUI objGui = new GUI();
		
	}
}
