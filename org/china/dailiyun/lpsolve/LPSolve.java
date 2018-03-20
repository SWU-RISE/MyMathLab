package org.china.dailiyun.lpsolve;

/*
 * ��������һ���ܱȽ��ٵ�JAVA ���±�
 * 
 * ���ߣ�������
 * ������������
 */
import org.china.dailiyun.net.NetStreamFrame;
import org.china.dailiyun.util.*;
import java.io.*;

import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.Toolkit;
import javax.swing.filechooser.*;

public class LPSolve extends JFrame {

	public static void main(String[] argv) {
		LPSolve frame = new LPSolve();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.init();
		frame.initSolve();

	}

	public LPSolve() {
		this.setVisible(false);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		jPanel = new JPanel();
		jlabelVersion = new JLabel("lp editor V1.0");
		JMENU = new JMenuBar();
		jTextArea = new JTextArea();

		jScrollPane = new JScrollPane(jTextArea);

		jFileMenu = new JMenu("File");
		jEditMenu = new JMenu("Edit");
		jSolveMenu = new JMenu("Run");

		openAction = new OpenAction("Open");
		openItem = new JMenuItem(openAction);
		openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				InputEvent.CTRL_MASK));

		saveAction = new SaveAction("Save");

		saveItem = new JMenuItem(saveAction);
		saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_MASK));

		saveAsAction = new SaveAsAction("Save as");
		saveAsItem = new JMenuItem(saveAsAction);

		closeAction = new CloseAction("Close");
		closeItem = new JMenuItem(closeAction);

		createAction = new CreateAction("New");
		createItem = new JMenuItem(createAction);
		createItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				InputEvent.CTRL_MASK));

		// icons
		cutAction = new MyTestAction("Cut");
		cutItem = new JMenuItem(cutAction);
		cutAction.putValue(Action.SMALL_ICON, new ImageIcon("cut.gif"));

		copyAction = new MyTestAction("Copy");
		copyItem = new JMenuItem(copyAction);
		copyAction.putValue(Action.SMALL_ICON, new ImageIcon("copy.gif"));
		pasteAction = new MyTestAction("Paste");
		pasteItem = new JMenuItem(pasteAction);
		pasteAction.putValue(Action.SMALL_ICON, new ImageIcon("paste.gif"));

		runAction = new RunAction("Run");
		runItem = new JMenuItem(runAction);
		
		// pop-ups

		popup = new JPopupMenu();

		popup.add(runAction);
		popup.add(cutAction);
		popup.add(copyAction);
		popup.add(pasteAction);
		jTextArea.setComponentPopupMenu(popup);
		auothorItem = new JMenuItem("Author");

		chooser = new JFileChooser();

		// accept all image files ending with .lp
		final ExtensionFileFilter filter = new ExtensionFileFilter();
		filter.addExtension("lp");

		filter.setDescription(" lp  file");
		chooser.setFileFilter(filter);
		chooser.setCurrentDirectory(new File("work"));

		setJMenuBar(JMENU);
		setTitle("Linear programming");

		jFileMenu.add(createItem);
		jFileMenu.addSeparator();

		jFileMenu.add(openItem);
		jFileMenu.addSeparator();

		jFileMenu.add(saveItem);
		jFileMenu.addSeparator();

		jFileMenu.add(saveAsItem);
		jFileMenu.addSeparator();

		jFileMenu.add(closeItem);
		jEditMenu.add(cutItem);

		jEditMenu.addSeparator();
		jEditMenu.add(copyItem);

		jEditMenu.addSeparator();
		jEditMenu.add(pasteItem);

		jEditMenu.addSeparator();
		jEditMenu.add(auothorItem);

		jSolveMenu.add(runItem);

		JMENU.add(jFileMenu);
		JMENU.add(jEditMenu);
		JMENU.add(jSolveMenu);

		jSaveButton = new JButton("Save");
		jSaveButton.addActionListener(saveAction);

		jCloseButton = new JButton("Close");
		jCloseButton.addActionListener(closeAction);

		jPanel.add(jSaveButton);
		jPanel.add(jCloseButton);
		jPanel.add(jlabelVersion);
		add(jPanel, "South");
		jTextArea.setWrapStyleWord(true);
		add(jScrollPane);
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		int W = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int H = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		setLocation((W - getWidth()) / 2, (H - getHeight()) / 2);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				LPSolve.this.setVisible(false);
			}

		});

	}

	public void init() {
		try {
			FileReader fr = new FileReader("Sample.myinit");
			Scanner scanner = new Scanner(fr);
			while (scanner.hasNext())
				jTextArea.append(scanner.nextLine() + "\n");
			scanner.close();

		} catch (FileNotFoundException fe) {
			fe.printStackTrace();

		} catch (IOException ie) {
			ie.printStackTrace();
		}

	}

	public void initSolve() {
		solve = new Solve();

	}

	public void initCompont() {

	}

	public void windowClosing(WindowEvent e) {
		this.setVisible(false);
	}

	class SaveAction extends AbstractAction {
		public SaveAction(String name) {
			super(name);
		}

		public void actionPerformed(ActionEvent event) {
			try {
		
				if (filename == null) {

					// show file chooser dialog
					int result = chooser.showSaveDialog(LPSolve.this);
					if (chooser.getSelectedFile() != null)
						filename = chooser.getSelectedFile().getAbsolutePath();
					else
						filename = "tempfile";
					boolean flag = true;
					while (flag
							&& !(new File(filename + ".lp").createNewFile())
							&& result == JFileChooser.APPROVE_OPTION) {

						Object[] options = { "��", "��" };
						int rs = JOptionPane.showOptionDialog(null, "������ǰ���ļ�",
								"Warning", JOptionPane.DEFAULT_OPTION,
								JOptionPane.WARNING_MESSAGE, null, options,
								options[1]);
						if (rs == JOptionPane.OK_OPTION)
							flag = false;
						else

							result = chooser.showSaveDialog(LPSolve.this);
					}
					if (result == JFileChooser.APPROVE_OPTION) {

						FileOutputStream fout = new FileOutputStream(filename
								+ ".lp");
						byte bb[] = jTextArea.getText().getBytes();
						/* System.out.println(filename); */
						fout.write(bb);
						fout.close();
					}

				} 
				else {
					FileOutputStream fout = new FileOutputStream(filename
							+ ".lp");
					byte bb[] = jTextArea.getText().getBytes();
					fout.write(bb);
					fout.close();

				}

			} catch (IOException ioe) {
				System.err.println(ioe);

			}

		}

	}

	
	class CreateAction extends AbstractAction {
		public CreateAction(String name) {
			super(name);
		}

		public void actionPerformed(ActionEvent event) {
			if (!(jTextArea.getText().equals(""))) {
				Object[] options = { "ȷ��", "ȡ��" };
				int response = JOptionPane
						.showOptionDialog(null, "���Ƿ񱣴�", "��ʾ",
								JOptionPane.YES_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[0]);
				if (response == 0) {
					saveAction.actionPerformed(event);

				}
				if (response == 1) {
					JOptionPane.showMessageDialog(null, "��ѡ����ȡ��");

				}
				jTextArea.setText("");
				filename = null;
			}
		}
	}

	/*
	 * open ����
	 */

	class OpenAction extends AbstractAction {
		public OpenAction(String name) {
			super(name);
		}

		public void actionPerformed(ActionEvent e) {
			int result = chooser.showOpenDialog(LPSolve.this);

			// if lp file accepted, set it as contents of the JTextArea
			if (result == JFileChooser.APPROVE_OPTION) {

				File f = chooser.getSelectedFile();
				for (int i = 0; i <= f.length(); i++) {
					char[] ch = new char[i];
					try {
						FileReader fr = new FileReader(f);
						fr.read(ch);
						String str = new String(ch);
						jTextArea.setText(str);

					} catch (FileNotFoundException fe) {
						fe.printStackTrace();

					} catch (IOException ie) {
						ie.printStackTrace();
					}
				}

			}

		}
	}

	/*
	 * ������
	 */

	class SaveAsAction extends AbstractAction {
		public SaveAsAction(String name) {
			super(name);
		}

		public void actionPerformed(ActionEvent e) {
			filename = null;
			saveAction.actionPerformed(e);

		}
	}

	class CloseAction extends AbstractAction {
		public CloseAction(String name) {
			super(name);

		}

		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	class MyTestAction extends AbstractAction {
		public MyTestAction(String name) {
			super(name);
		}

		public void actionPerformed(ActionEvent event) {
			System.out.println(getValue(Action.NAME) + " selected.");
		}
	}

	class RunAction extends AbstractAction {
		public RunAction(String name) {
			super(name);
		}

		public void actionPerformed(ActionEvent e) {
			saveAction.actionPerformed(e);
			solve.resultFrame.setVisible(true);
			solve.run(filename);

		}
	}

	private JButton jSaveButton, jCloseButton;
	private JTextArea jTextArea;
	private String filename;

	private JPanel jPanel;
	/**
	 * 
	 */
	private JMenu jFileMenu, jEditMenu, jSolveMenu;
	private JMenuItem saveAsItem, saveItem, createItem, openItem, closeItem,
			auothorItem, cutItem, copyItem, pasteItem, runItem;
	private Action saveAsAction, saveAction, createAction, openAction,
			closeAction, cutAction, copyAction, pasteAction, runAction;
	private JMenuBar JMENU;

	private JPopupMenu popup;
	private JScrollPane jScrollPane;
	private JLabel jlabelVersion;
	private JFileChooser chooser;
	private Solve solve;
	public static final int DEFAULT_WIDTH = Info.DEFAULT_WIDTH;
	public static final int DEFAULT_HEIGHT = Info.DEFAULT_HEIGHT;

	public JButton getJSaveButton() {
		return jSaveButton;
	}

	public void setJSaveButton(JButton saveButton) {
		jSaveButton = saveButton;
	}

	public JTextArea getJTextArea() {
		return jTextArea;
	}

	public void setJTextArea(JTextArea textArea) {
		jTextArea = textArea;
	}

	public JMenu getJSolveMenu() {
		return jSolveMenu;
	}

	public void setJSolveMenu(JMenu solveMenu) {
		jSolveMenu = solveMenu;
	}

	public JMenuItem getSaveAsItem() {
		return saveAsItem;
	}

	public void setSaveAsItem(JMenuItem saveAsItem) {
		this.saveAsItem = saveAsItem;
	}

	public JMenuItem getSaveItem() {
		return saveItem;
	}

	public void setSaveItem(JMenuItem saveItem) {
		this.saveItem = saveItem;
	}

	public JMenuItem getOpenItem() {
		return openItem;
	}

	public void setOpenItem(JMenuItem openItem) {
		this.openItem = openItem;
	}

	public JMenuItem getCutItem() {
		return cutItem;
	}

	public void setCutItem(JMenuItem cutItem) {
		this.cutItem = cutItem;
	}

	public JMenuItem getPasteItem() {
		return pasteItem;
	}

	public void setPasteItem(JMenuItem pasteItem) {
		this.pasteItem = pasteItem;
	}

	public JMenuItem getRunItem() {
		return runItem;
	}

	public void setRunItem(JMenuItem runItem) {
		this.runItem = runItem;
	}

	public Action getSaveAsAction() {
		return saveAsAction;
	}

	public void setSaveAsAction(Action saveAsAction) {
		this.saveAsAction = saveAsAction;
	}

	public Action getSaveAction() {
		return saveAction;
	}

	public void setSaveAction(Action saveAction) {
		this.saveAction = saveAction;
	}

	public Action getOpenAction() {
		return openAction;
	}

	public void setOpenAction(Action openAction) {
		this.openAction = openAction;
	}

	public Action getPasteAction() {
		return pasteAction;
	}

	public void setPasteAction(Action pasteAction) {
		this.pasteAction = pasteAction;
	}

	public JMenuBar getJMENU() {
		return JMENU;
	}

	public void setJMENU(JMenuBar jmenu) {
		JMENU = jmenu;
	}

	public JPopupMenu getPopup() {
		return popup;
	}

	public void setPopup(JPopupMenu popup) {
		this.popup = popup;
	}

}

class MyFileFliter implements FilenameFilter {
	public boolean accept(File dir, String name) {
		if (name.endsWith("lp"))
			return true;
		else
			return false;
	}

}
