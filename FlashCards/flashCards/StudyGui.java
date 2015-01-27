package flashCards;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

/**
 * Created by Ted Fujimoto
 */
public class StudyGui extends JFrame implements ActionListener {
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem load, save, quit;
	JPanel answerPanel;
	JLabel stim;
	JTextArea resp;
	JButton enter, next;
	StudyList sList;
	ArrayList<String> stringFile;
	String respText, textEnter, nextText;
	int iteration, numCorrect;
	StudyList s1;

	public StudyGui() {
		try {
			sList  = new StudyList();
			sList.load();

			s1 = new StudyList();
			for (Item item : sList.getItemList()) {
				if (item.getTimesCorrect() < 4) {
					s1.add(item);
				}
			}
			stringFile = sList.stringFile;

		} catch (IOException e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());


			menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			menu = new JMenu("File");
			menu.setMnemonic('m');
			menuBar.add(menu);
			load = new JMenuItem("Load");
			save = new JMenuItem("Save");
			quit = new JMenuItem("Quit");
			menu.add(load);
			menu.add(save);
			menu.add(quit);
			
			load.addActionListener(this);
			save.addActionListener(this);
			quit.addActionListener(this);
			iteration = 0;
			numCorrect = 0;
			stim = new JLabel();
			stim.setText(s1.getItemList().get(iteration).getStimulus());
			getContentPane().add(stim, BorderLayout.NORTH);

			resp = new JTextArea();
			resp.setText("Type Here");
			getContentPane().add(resp, BorderLayout.CENTER);

			answerPanel = new JPanel();
			answerPanel.setBackground(Color.gray);
			getContentPane().add(answerPanel, BorderLayout.WEST);
		
			textEnter = "Enter";
			enter = new JButton(textEnter);
			enter.addActionListener(this);
			getContentPane().add(enter, BorderLayout.SOUTH);

			nextText = "Next Card";
			next = new JButton(nextText);
			next.addActionListener(this);
			getContentPane().add(next, BorderLayout.EAST);

			setSize(600, 600);
		}
	 
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == enter) {
			System.out.println(resp.getText());
			System.out.println(s1.find(stim.getText()).getResponse());
			// if correct
			if (resp.getText().equals(s1.find(stim.getText()).getResponse())) {
				answerPanel = new JPanel();
				answerPanel.setBackground(Color.green);
				getContentPane().add(answerPanel, BorderLayout.WEST);
				s1.getItemList().get(iteration).setTimesCorrect(s1.getItemList().get(iteration).getTimesCorrect() + 1);
				respText = "Correct:";
				resp.setText(respText.concat(" ".concat(Integer.toString(s1.getItemList().get(iteration).getTimesCorrect()))));
				if (s1.getItemList().get(iteration).getTimesCorrect() >= 4) {
					System.out.println("Learned!");
					s1.delete(s1.getItemList().get(iteration));
				}
				
			} else {
				s1.getItemList().get(iteration).setTimesCorrect(0);
				answerPanel = new JPanel();
				answerPanel.setBackground(Color.red);
				getContentPane().add(answerPanel, BorderLayout.WEST);
				respText = "\nWrong. The correct answer is: ";
				resp.setText(resp.getText().concat(respText.concat(s1.find(stim.getText()).getResponse())));
			}
		} else if (e.getSource() == next) {
			iteration += 1;
			if (iteration >= s1.getItemList().size()) {
				iteration = 0;
				s1 = shuffle(s1.getItemList());
			}

			stim.setText(s1.getItemList().get(iteration).getStimulus());
			System.out.println(s1.getItemList().get(iteration).getStimulus());
			getContentPane().add(stim, BorderLayout.NORTH);
			resp.setText("Type Here");
			getContentPane().add(resp, BorderLayout.CENTER);
		} else if (e.getSource() == load) {
			try {
				sList  = new StudyList();
				sList.load();
								
				s1 = shuffle(sList.getItemList());
				for (String s : stringFile) {
					System.out.println(s);
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} else if (e.getSource() == save) {
			try {
				sList.save();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} else if (e.getSource() == quit) {
			dispose();
		}
	}

	public static void main(String[] args) {
		new StudyGui().setVisible(true);
	}

	// A shuffler
	private StudyList shuffle(ArrayList<Item> a) {
		ArrayList<Item> b = new ArrayList<Item>();
		int len = a.size();
		for (int i = 0; i < len; i += 1) {
			b.add(a.get(i));
		}
		
		for (int i = 0; i < len - 2; i += 2) {
			if (new Random().nextInt() < 0.75) {
				b.set(i, a.get(i + 1));
				b.set(i + 1, a.get(i));
			}
		}
		
		StudyList slist = new StudyList();
		
		for (int i = 0; i < len; i += 1) {
			slist.add(b.get(i));
		}
		
		return slist;
	}
	
}