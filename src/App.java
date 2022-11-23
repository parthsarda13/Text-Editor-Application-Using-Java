import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;


public class App extends JFrame implements ActionListener {
	JFrame p;
	JTextArea t;

	App() {
		p = new JFrame("PYTextEditor");
		t = new JTextArea();
		Font font = new Font("Verdana", Font.BOLD, 16);
		t.setFont(font);
		// t.setForeground(Color.WHITE);
		// t.setBackground(Color.BLACK);
		t.setTabSize(4);

		JMenuBar mb = new JMenuBar();

		JMenu m1 = new JMenu("File");
		JMenuItem mi1 = new JMenuItem("Open");
		mi1.setBackground(Color.CYAN);
		JMenuItem mi2 = new JMenuItem("New");
		mi2.setBackground(Color.RED);
		JMenuItem mi3 = new JMenuItem("Save");
		mi3.setBackground(Color.GREEN);
		JMenuItem mi9 = new JMenuItem("Print");
		mi9.setBackground(Color.YELLOW);
		mi1.addActionListener(this);
		mi2.addActionListener(this);
		mi3.addActionListener(this);
		mi9.addActionListener(this);
		m1.add(mi1);
		m1.add(mi2);
		m1.add(mi3);
		m1.add(mi9);

		JMenu m2 = new JMenu("Edit");
		JMenuItem mi4 = new JMenuItem("Cut");
		mi4.setBackground(Color.BLUE);
		mi4.addActionListener(this);
		JMenuItem mi5 = new JMenuItem("Copy");
		mi5.setBackground(Color.GREEN);
		mi5.addActionListener(this);
		JMenuItem mi6 = new JMenuItem("Paste");
		mi6.setBackground(Color.RED);
		mi6.addActionListener(this);
		m2.add(mi4);
		m2.add(mi5);
		m2.add(mi6);
		JMenu themes = new JMenu("Themes");
		JMenuItem darkTheme = new JMenuItem("Dark Theme");
		darkTheme.addActionListener(this); // dark theme
		JMenuItem moonLightTheme = new JMenuItem("Moonlight Theme");
		moonLightTheme.addActionListener(this); // moonlight theme
		JMenuItem defaultTheme = new JMenuItem("Default Theme");
		defaultTheme.addActionListener(this); // default theme
		themes.add(darkTheme);
		themes.add(moonLightTheme);
		themes.add(defaultTheme);

		JMenuItem close = new JMenuItem("Close");
		close.setBackground(Color.YELLOW);
		close.addActionListener(this);

		mb.add(m1);
		mb.add(m2);
		mb.add(themes);
		mb.add(close);
		mb.setBackground(Color.YELLOW);
		p.setJMenuBar(mb);
		JScrollPane scroll = new JScrollPane(t, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		p.add(scroll);
		p.setSize(700, 900);
		p.setDefaultCloseOperation(EXIT_ON_CLOSE);
		p.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		if (s.equals("New")) {
			t.setText("");
		} else if (s.equals("Open")) {
			JFileChooser j = new JFileChooser("f:");
			int r = j.showOpenDialog(null);
			if (r == JFileChooser.APPROVE_OPTION) {
				try {
					File fi = new File(j.getSelectedFile().getAbsolutePath());
					String s1 = "", sl = "";
					FileReader fr = new FileReader(fi);
					BufferedReader br = new BufferedReader(fr);
					while ((s1 = br.readLine()) != null) {
						sl = sl + s1 + "\n";
					}
					t.setText(sl);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			} else {
				JOptionPane.showMessageDialog(null, "user cancelled");
			}
		} else if (s.equals("Save")) {
			JFileChooser j = new JFileChooser("f:");
			int r = j.showSaveDialog(null);
			if (r == JFileChooser.APPROVE_OPTION) {
				File fi = new File(j.getSelectedFile().getAbsolutePath());
				try {
					FileWriter fw = new FileWriter(fi);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(t.getText());
					bw.flush();
					bw.close();
				} catch (Exception e3) {
					JOptionPane.showMessageDialog(null, e3.getMessage());
				}
			} else {
				JOptionPane.showMessageDialog(null, "user cancelled");
			}
		} else if (s.equals("Print")) {
			try {
				t.print();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, e2.getMessage());
			}
		} else if (s.equals("Cut")) {
			t.cut();
		} else if (s.equals("Copy")) {
			t.copy();
		} else if (s.equals("Paste")) {
			t.paste();
		} else if (s.equals("Close")) {
			p.setVisible(false);
			System.exit(0);
		} else if (s.equals("Dark Theme")) {

			t.setBackground(Color.DARK_GRAY); // dark Theme
			t.setForeground(Color.WHITE);
		}

		else if (s.equals("Moonlight Theme")) {
			t.setBackground(new Color(107, 169, 255));
			t.setForeground(Color.black);
		}

		else if (s.equals("Default Theme")) {
			t.setBackground(new Color(255, 255, 255));
			t.setForeground(Color.black);
		}

	}

	public static void main(String args[]) {
		App pad = new App();
	}
}