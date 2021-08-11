/**************************************
 * COSC 30063: Principles of Programming Languages
 * COSC 30053: Automata and Language Theory
 * ABAKADA: A mini programming language
 * Lexical and Syntactical Analyzer 
 * 
 * Leader:
 * 	Villareal, Isaac C.
 * Members:
 * 	Balita, Maria Sophia G.
 * 	Cailing, Hannah A.
 * 	Elinon, Leonel S.A.
 * 	Santiago, Jeremy Karl C.
 * 	Sta. Maria, Rudolph Vincent B.
 * 	Talde, Dexter Karl F.
 */

import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import java.awt.*;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	
	//MAIN JFRAME
	private final JPanel contentPane = new JPanel(); 	
	private final JMenuBar menuBar   = new JMenuBar();
	private final JMenu mnFile  = new JMenu("File");
	private final JMenu mnRun   = new JMenu("Run");
	private final JMenu mnHelp  = new JMenu("Help");
	private final JMenuItem mntmNew   = new JMenuItem("New");
	private final JMenuItem mntmOpen  = new JMenuItem("Open");
	private final JMenuItem mntmSave  = new JMenuItem("Save");
	private final JMenuItem mntmClose = new JMenuItem("Close");
	private final JMenuItem mntmExit  = new JMenuItem("Exit");
	private final JMenuItem mntmLexical  = new JMenuItem("Lexical Analyzer");
	private final JMenuItem mntmSyntax   = new JMenuItem("Syntax Analyzer");
	private final JMenuItem mntmSemantic = new JMenuItem("Semantic Analyzer");
	private final JMenuItem mntmAbout    = new JMenuItem("About");
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} 
	String line;
	File file   = null;
	int isSaved = 0;
	
	JTextArea textArea = new JTextArea(); 
	JScrollPane scroll = new JScrollPane(textArea);
	LineNumberModelImpl lineNumberModel      = new LineNumberModelImpl();
	LineNumberComponent lineNumberComponent  = new LineNumberComponent(lineNumberModel);
	LineNumberComponent lineNumberComponent1 = new LineNumberComponent(lineNumberModel);
	
	public Main(){
		//INPUT WINDOW
		setTitle("ABAKADA");			setUndecorated(false);
		setSize(1024,768);				setLocationRelativeTo(null);
		setContentPane(contentPane);	contentPane.setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//MENU BAR
		setJMenuBar(menuBar);
 		menuBar.add(mnFile);	mnFile.setFont(new Font("Consolas", Font.BOLD, 20));
 		mnFile.add(mntmNew);	mntmNew.setFont(new Font("Segoe UI", Font.PLAIN, 20));
 		mnFile.add(mntmOpen);	mntmOpen.setFont(new Font("Segoe UI", Font.PLAIN, 20));
 		mnFile.add(mntmSave);	mntmSave.setFont(new Font("Segoe UI", Font.PLAIN, 20));
 		mnFile.add(mntmClose);	mntmClose.setFont(new Font("Segoe UI", Font.PLAIN, 20));
 		mnFile.add(mntmExit);	mntmExit.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(mnRun);			mnRun.setFont(new Font("Consolas", Font.BOLD, 20));
		mnRun.add(mntmLexical);		mntmLexical.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnRun.add(mntmSyntax);		mntmSyntax.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnRun.add(mntmSemantic);	mntmSemantic.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(mnHelp);	mnHelp.setFont(new Font("Consolas", Font.BOLD, 20));
		mnHelp.add(mntmAbout);	mntmAbout.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		mntmLexical.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(file == null) {
					JOptionPane.showMessageDialog(null,"No File "
						+ "Opened","RUN ERROR", 2);
					return;
				}
				
				JPanel tablePanel = new JPanel(new BorderLayout());
				DefaultTableModel model = new DefaultTableModel(null, 
						new String[] {"Lexeme", "Token"});
				JTable table = new JTable(model);
				table.setEnabled(false);
				table.setModel(model);
				tablePanel.add(table, BorderLayout.CENTER);
				tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);
				table.setFont(new Font("Consolas", Font.PLAIN, 15));
				
				
				/********************************TEST WITHOUT SEPARATOR*****************************
				JPanel tablePanel1 = new JPanel(new BorderLayout());
				DefaultTableModel model1 = new DefaultTableModel(null, 
						new String[] {"Lexeme", "Token"});
				JTable table1 = new JTable(model1);
				table1.setEnabled(false);
				table1.setModel(model1);
				tablePanel1.add(table1, BorderLayout.CENTER);
				tablePanel1.add(table1.getTableHeader(), BorderLayout.NORTH);
				table1.setFont(new Font("Consolas", Font.PLAIN, 15));
				String lexemeTemp = textArea.getText();
				String tokensTemp = textArea.getText();
				String [] splitLexemes = lexemeTemp.split("[ \n]");
				String [] splitTokens = tokensTemp.split("[ \n]");
				for(int i=0; i<splitLexemes.length; i++) {
					Object[] rows1 = new Object[]{splitLexemes[i], splitTokens[i]};
					model1.addRow(rows1);
				}
				JOptionPane.showMessageDialog(null, new JScrollPane(table1),"LEXICAL ANALYZER: OUTPUT", 1);
				********************************TEST WITHOUT SEPARATOR*****************************/
			    
				checkToken tokenize = new checkToken();	       
			    Separator lexemes = new Separator();
				String temporary = textArea.getText();
		        String [] separatedLexemes = lexemes.separate(temporary);
				String[] lexeme = lexemes.getLexemes(separatedLexemes);
				String[] tokens = tokenize.inStringtoCheck(lexeme);
				
				//FILE WRITE
				try {
					String name = file.getName().replaceFirst("[.][^.]+$", "");
					File outputFile = new File(".\\Output\\"+ name +"_Lexeme.abkdlex");
					outputFile.createNewFile();
					PrintWriter myWriter = new PrintWriter(outputFile);
					//myWriter.println("LEXEME\t\t\t\t\t TOKEN");
					String formatline = "%-40s %-40s %n";
					Object[] rows;
					for (int x = 0; x<lexeme.length; x++)
					{
					    if(!lexeme[x].equals("")) 
					    {	    	
					    	rows = new Object[]{lexeme[x], tokens[x]};
					    myWriter.format(formatline, lexeme[x], tokens[x]);
					    	model.addRow(rows);
					    }
					}
					myWriter.close();
					JOptionPane.showMessageDialog(null, new JScrollPane(table),"LEXICAL ANALYZER: OUTPUT", 1);
				} catch (IOException e) {
					e.printStackTrace();
				}
		}});
		
		textArea.setForeground(Color.WHITE);
		textArea.setBackground(Color.BLACK);
		textArea.setFont(new Font("Consolas", Font.PLAIN, 20));
		scroll.setRowHeaderView(lineNumberComponent);
		contentPane.add(scroll, BorderLayout.CENTER); 
		lineNumberComponent.setAlignment(LineNumberComponent.CENTER_ALIGNMENT);
		
		mntmSyntax.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(file == null) {
					JOptionPane.showMessageDialog(null,"No File "
						+ "Opened","RUN ERROR", 2);
					return;
				}
				JOptionPane.showMessageDialog(null,"No function "
						+ "defined yet","SYNTAX ANALYZER", 2);
				return;
			}
		});
		mntmSemantic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(file == null) {
					JOptionPane.showMessageDialog(null,"No File "
						+ "Opened","RUN ERROR", 2);
					return;
				}
				JOptionPane.showMessageDialog(null,"No function "
						+ "defined yet","SEMANTIC ANALYZER", 2);
				return;	
			}
		});
		
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"ABAKADA: "+
						"Filipino-based Mini Programming Language\n"+
						"Creators:"
						+ "\nBalita, Maria Sophia G."
						+ "\nVillareal, Isaac C."
						+ "\nMembers:"
						+ "\nBalita, Maria Sophia G."
						+ "\nCailing, Hannah A."
						+ "\nElinon, Leonel S.A."
						+ "\nSantiago, Jeremy Karl C."
						+ "\nSta. Maria, Rudolph Vincent B."
						+ "\nTalde, Dexter Karl F."
						, "About the Language", 1);
			}
		});
		
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					if(file == null) {
						JOptionPane.showMessageDialog(null,"No File "
							+ "Opened","SAVE ERROR", 2);
						if (!textArea.getText().isEmpty()){
								int y = JOptionPane.showConfirmDialog(null, 
							        	"Save the code to an ABKD File?", 
							           	"SAVE", JOptionPane.YES_NO_OPTION);
								if(y == JOptionPane.YES_OPTION) {
									try{
										do{
										JFileChooser fc = new JFileChooser();
										FileNameExtensionFilter filter = new FileNameExtensionFilter("ABAKADA FILES", "abkd", "abakada");
										fc.setFileFilter(filter); fc.setCurrentDirectory(new java.io.File("."));
										fc.setDialogTitle("Select a directory and enter the file name");  
									    int returnVal = fc.showSaveDialog(null);
								        if (returnVal == JFileChooser.APPROVE_OPTION) {
							        		file = fc.getSelectedFile();
							        		file = new File(file.getName()+".abkd");
											if (file.createNewFile()) {
												JOptionPane.showMessageDialog(null,"Source Code ABAKADA File "
														+ file.getName() + " Created","NEW", 1);
												FileWriter myWriter = new FileWriter(file);
												line = textArea.getText();
												myWriter.write(line);
												myWriter.close();
												setTitle("ABAKADA: " + file.getName());
												return;
											} else{
												JOptionPane.showMessageDialog(null,"Data File "
													+ file.getName() + " Already Exist","NEW", 1);
												int x = JOptionPane.showConfirmDialog(null, 
											        	"Do you want to overwrite the file?", 
											        	"Data File Already Exist",
											        	JOptionPane.YES_NO_OPTION);
												if(x == JOptionPane.YES_OPTION) {
													file.delete();
													file = new File(file.getName());
													file.createNewFile();
													setTitle("ABAKADA: " + file.getName());
													JOptionPane.showMessageDialog(null,"ABKD File "
														+ file.getName() + " Overwritten","OVERWRITE", 1);
													return;
												}else {
													int w = JOptionPane.showConfirmDialog(null, 
												        	"Create new file?", 
												        	"Data File Already Exist",
												        	JOptionPane.YES_NO_OPTION);
													if(w == JOptionPane.YES_OPTION) {
														continue;
													}else {
														break;
													}
												}
											}
								        }else return;
										}while(true);
									}catch (IOException ex) {
										ex.printStackTrace();
									}
								}else return;
						}return;
					}
					
					FileWriter myWriter = new FileWriter(file);
					line = textArea.getText();
					myWriter.write(line);
					myWriter.close();
					JOptionPane.showMessageDialog(null,"ABKD File "
						+ "Successfully Saved","SAVE", 1);
					isSaved = 1;
				}catch (Exception ex) {
					JOptionPane.showMessageDialog(null,"No File "
							+ "Opened","SAVE ERROR", 2);
				}
		}
		});
		
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try{
				do{
				JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("ABAKADA FILES", "abkd", "abakada");
				fc.setFileFilter(filter);
				fc.setCurrentDirectory(new java.io.File("."));
				fc.setDialogTitle("Specify a file to save");  
			    int returnVal = fc.showSaveDialog(null);
		        if (returnVal == JFileChooser.APPROVE_OPTION) {
	        		file = fc.getSelectedFile();
	        		file = new File(file.getName()+".abkd");
					if (file.createNewFile()) {
						JOptionPane.showMessageDialog(null,"Source Code ABAKADA File "
								+ file.getName() + " Created","NEW", 1);
						setTitle("ABAKADA: " + file.getName());
						return;
					}else{
						JOptionPane.showMessageDialog(null,"Data File "
							+ file.getName() + " Already Exist","NEW", 1);
						int x = JOptionPane.showConfirmDialog(null, 
					        	"Do you want to overwrite the file?", 
					        	"Data File Already Exist",
					        	JOptionPane.YES_NO_OPTION);
						if(x == JOptionPane.YES_OPTION) {
							file.delete();
							file = new File(file.getName());
							file.createNewFile();
							setTitle("ABAKADA: " + file.getName());
							JOptionPane.showMessageDialog(null,"ABKD File "
								+ file.getName() + " Overwritten","OVERWRITE", 1);
							return;
						}else {
							int y = JOptionPane.showConfirmDialog(null, 
						        	"Create new file?", 
						        	"Data File Already Exist",
						        	JOptionPane.YES_NO_OPTION);
							if(y == JOptionPane.YES_OPTION) {
								continue;
							}else {
								break;
							}
						}
					}
		        }else return;
				}while(true);
			}catch (IOException ex) {
				ex.printStackTrace();
			}
			}
		});
		
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("ABAKADA FILES", "abkd", "abakada");
				fc.setFileFilter(filter);
				fc.setDialogTitle("Specify a file to open"); 
				fc.setCurrentDirectory(new java.io.File("."));
		        int returnVal = fc.showOpenDialog(null);
		        if (returnVal == JFileChooser.APPROVE_OPTION) {
	        	try {
	        		file = fc.getSelectedFile();
	        		Scanner myReader = new Scanner(file);
	        		JOptionPane.showMessageDialog(null,"File "+
							file.getName() +" Opened.",
							"OPEN", 1);
	        		setTitle("ABAKADA: " + file.getName());
	        		textArea.setText("");
	        		while(myReader.hasNextLine()) {
						line  = myReader.nextLine();
						textArea.append(line+"\n");
					}
	        		textArea.setText(textArea.getText().trim());
					myReader.close();
				}catch (FileNotFoundException ex) {
					JOptionPane.showMessageDialog(null,"File "
							+ "not Found", "OPEN ERROR", 2);
				}
		        }else return;
			}	
		});
		
		mntmClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(file == null) {
					JOptionPane.showMessageDialog(null,"No File "
						+ "Opened","CLOSE ERROR", 2);
					return;
				}
				if(isSaved == 0) {
					int w = JOptionPane.showConfirmDialog(null,"File "
						+ "Unsaved. Close unsaved file?","FILE UNSAVED", JOptionPane.YES_NO_OPTION);
					if(w == JOptionPane.NO_OPTION) {
						return;
					}

				}
				textArea.setText("");
				setTitle("ABAKADA");
				JOptionPane.showMessageDialog(null,"File "+
						file.getName() +" Closed.",
						"ClOSE", 1);
				file = null;
			}
		});
		
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int x = JOptionPane.showConfirmDialog(null, 
			        	"Are you sure you want to exit?", 
			        	"Application Database Exit Confirm",
			        	JOptionPane.YES_NO_OPTION);
				if(x == JOptionPane.YES_OPTION) {
				    System.exit(0);
				}
			}
		});
			
		textArea.getDocument().addDocumentListener(new DocumentListener(){
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				isSaved = 0;
				lineNumberComponent.adjustWidth();
			}
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				isSaved = 0;
				lineNumberComponent.adjustWidth();
			}
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				isSaved = 0;
				lineNumberComponent.adjustWidth();
			}
		});
	}
	
	private class LineNumberModelImpl implements LineNumberModel{
		@Override
		public int getNumberLines() {
			return textArea.getLineCount();
		}
		@Override
		public Rectangle getLineRect(int line) {
			try{
				return textArea.modelToView(textArea.getLineStartOffset(line));
			}catch(BadLocationException e){
				e.printStackTrace();
				return new Rectangle();
			}
		}
	}
}