import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

public class JMemo extends JFrame implements ActionListener{
	private JMenuBar menuBar;
	private JMenuItem openFile;
	private JMenuItem saveFile;
	private JMenuItem exitFile;
	private JMenuItem aboutFile;
	private JFileChooser fileChooser;
	private JTextArea noteFile;
	private JScrollBar scrollFile;
	private JScrollPane scroll;
	private BufferedReader br;
	private FileWriter fw;
	private BufferedWriter bw;
	
	
	public JMemo(){
		init();
		this.setLayout(new BorderLayout());
		this.add(menuBar,"North");
		this.add(scroll);
		this.setTitle("JMemo");
		this.setSize(960,500);
		this.setFocusable(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		addToMenu();
		
		
	}
	
	public void init(){
		menuBar = new JMenuBar();
		openFile = new JMenuItem("Open");
		saveFile = new JMenuItem("Save");
		exitFile = new JMenuItem("Exit");
		aboutFile = new JMenuItem("About");
		fileChooser = new JFileChooser();
		noteFile = new JTextArea();
		scrollFile = new JScrollBar();
		scroll = new JScrollPane(noteFile);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			
	}
	
	public void addToMenu(){
		menuBar.add(openFile);
			openFile.addActionListener(this);
		menuBar.add(saveFile);
			saveFile.addActionListener(this);
		menuBar.add(aboutFile);
			aboutFile.addActionListener(this);
		menuBar.add(exitFile);
			exitFile.addActionListener(this);
		noteFile.setLineWrap(true);
	}
	
	public static void main(String[] args) {
		new Main();
	}

	@Override
	public void actionPerformed(ActionEvent note) {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Document (*.txt)", "txt");
		 fileChooser.setFileFilter(filter);

		if(note.getSource()==openFile){
			int open= fileChooser.showOpenDialog(this);
			if(open==JFileChooser.APPROVE_OPTION){
				try {
					//read = new FileReader(fileChooser.getSelectedFile());
					String sentences;
					String total="";
					noteFile.setText("");
					br = new BufferedReader(new FileReader(fileChooser.getSelectedFile()));
					try {
						while((sentences = br.readLine())!=null){
							noteFile.setText(total+=sentences+"\n");
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		if(note.getSource()==saveFile){
			int save= fileChooser.showSaveDialog(this);
			if(save==JFileChooser.APPROVE_OPTION){
				try {
					if(fileChooser.getSelectedFile().getName().endsWith(".txt")){
					fw = new FileWriter(fileChooser.getSelectedFile());
					}
					else{
						fw = new FileWriter(fileChooser.getSelectedFile()+".txt");
					}
					bw = new BufferedWriter(fw);
					bw.write(noteFile.getText());
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null,fileChooser.getSelectedFile().getName()+" successfully saved");
			}
		}
		if(note.getSource()==exitFile){
			this.dispose();
		}
		if(note.getSource()==aboutFile){
				JOptionPane.showMessageDialog(this,"Created by ClavinJune\nThanks to NAR17-1");
		}
	}
}
