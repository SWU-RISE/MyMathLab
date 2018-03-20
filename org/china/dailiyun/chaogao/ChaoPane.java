package org.china.dailiyun.chaogao;

/*
 * 本程序是一功能比较少计算器
 * 
 * 作者：代立云
 * ０８．６．６
 */
import org.china.dailiyun.lpsolve.LPSolve;
import org.china.dailiyun.util.*;
import java.io.*;

import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.Toolkit;
import javax.swing.filechooser.*;

public class ChaoPane extends JPanel implements KeyListener, ActionListener {

	public ChaoPane() {
		this.setVisible(false);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		jPanel = new JPanel();

		jTextArea = new JTextArea();

		jScrollPane = new JScrollPane(jTextArea);

		openAction = new OpenAction("打开");

		saveAction = new SaveAction("存为");

		saveAsAction = new SaveAsAction("另存为");

		closeAction = new CloseAction("关闭");

		createAction = new CreateAction("新建");

		// icons
		cutAction = new MyTestAction("Cut");

		cutAction.putValue(Action.SMALL_ICON, new ImageIcon("cut.gif"));

		copyAction = new MyTestAction("Copy");

		copyAction.putValue(Action.SMALL_ICON, new ImageIcon("copy.gif"));
		pasteAction = new MyTestAction("Paste");

		pasteAction.putValue(Action.SMALL_ICON, new ImageIcon("paste.gif"));

		// pop-ups

		popup = new JPopupMenu();

		popup.add(runAction);
		popup.add(cutAction);
		popup.add(copyAction);
		popup.add(pasteAction);
		jTextArea.setComponentPopupMenu(popup);

		chooser = new JFileChooser();

		// accept all image files ending with .lp
		final ExtensionFileFilter filter = new ExtensionFileFilter();
		filter.addExtension("result");

		filter.setDescription(" result 文件");
		chooser.setFileFilter(filter);
		chooser.setCurrentDirectory(new File("work"));

		jSaveButton = new JButton("保存");
		jSaveButton.addActionListener(saveAction);

		jCloseButton = new JButton("关闭");
		jCloseButton.addActionListener(closeAction);

		jPanel.add(jSaveButton);
		jPanel.add(jCloseButton);

		add(jPanel, "South");
		jTextArea.setWrapStyleWord(true);
		add(jScrollPane);
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		int W = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int H = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		setLocation((W - getWidth()) / 2, (H - getHeight()) / 2);

	}

	public void init() {

		jTextArea.addKeyListener(this);

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
				// 还没有给文件取名
				if (filename == null) {

					// show file chooser dialog
					int result = chooser.showSaveDialog(ChaoPane.this);
					if (chooser.getSelectedFile() != null)

						filename = chooser.getSelectedFile().getPath();
					else
						filename = "tempfile";
					boolean flag = true;
					while (flag
							&& !(new File(filename + ".lp").createNewFile())
							&& result == JFileChooser.APPROVE_OPTION) {

						Object[] options = { "是", "不" };
						int rs = JOptionPane.showOptionDialog(null, "覆盖以前的文件",
								"Warning", JOptionPane.DEFAULT_OPTION,
								JOptionPane.WARNING_MESSAGE, null, options,
								options[1]);
						if (rs == JOptionPane.OK_OPTION)
							flag = false;
						else

							result = chooser.showSaveDialog(ChaoPane.this);
					}
					if (result == JFileChooser.APPROVE_OPTION) {

						FileOutputStream fout = new FileOutputStream(filename
								+ ".lp");
						byte bb[] = jTextArea.getText().getBytes();
						System.out.println(filename);
						fout.write(bb);
						fout.close();
					}

				} // 文件已经取名
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

	/*
	 * 新建的类 有些地方有不足
	 */
	class CreateAction extends AbstractAction {
		public CreateAction(String name) {
			super(name);
		}

		public void actionPerformed(ActionEvent event) {
			if (!(jTextArea.getText().equals(""))) {
				Object[] options = { "确定", "取消" };
				int response = JOptionPane
						.showOptionDialog(null, "你是否保存", "提示",
								JOptionPane.YES_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[0]);
				if (response == 0) {
					saveAction.actionPerformed(event);

				}
				if (response == 1) {
					JOptionPane.showMessageDialog(null, "你选择了取消");

				}
				jTextArea.setText("");
				filename = null;
			}
		}
	}

	/*
	 * open 的类
	 */

	class OpenAction extends AbstractAction {
		public OpenAction(String name) {
			super(name);
		}

		public void actionPerformed(ActionEvent e) {
			int result = chooser.showOpenDialog(ChaoPane.this);

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
	 * 另存的类
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

		}
	}

	private JButton jSaveButton, jCloseButton;
	private JTextArea jTextArea;
	private String filename;

	private JPanel jPanel;
	/**
	 * 
	 */

	private Action saveAsAction, saveAction, createAction, openAction,
			closeAction, cutAction, copyAction, pasteAction, runAction;

	private JPopupMenu popup;
	private JScrollPane jScrollPane;
	private JLabel jlabelVersion;
	private JFileChooser chooser;

	public static final int DEFAULT_WIDTH = Info.DEFAULT_WIDTH;
	public static final int DEFAULT_HEIGHT = Info.DEFAULT_HEIGHT;
	static final String newline = System.getProperty("line.separator");
	private int end = 0;

	Parser p = new Parser();

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

	public JPopupMenu getPopup() {
		return popup;
	}

	public void setPopup(JPopupMenu popup) {
		this.popup = popup;
	}

	public void keyTyped(KeyEvent e) {

	}

	/** Handle the key pressed event from the text field. */
	public void keyPressed(KeyEvent e) {
		displayInfo(e, "KEY TYPED: ");
	}

	/** Handle the key released event from the text field. */
	public void keyReleased(KeyEvent e) {

	}

	/** Handle the button click. */
	public void actionPerformed(ActionEvent e) {

	}

	/*
	 * We have to jump through some hoops to avoid trying to print non-printing
	 * characters such as Shift. (Not only do they not print, but if you put
	 * them in a String, the characters afterward won't show up in the text
	 * area.)
	 */
	private void displayInfo(KeyEvent e, String keyStatus) {

		// You should only rely on the key char if the event
		// is a key typed event.
		int id = e.getID();
		/* String keyString; */
		if (id == KeyEvent.KEY_TYPED) {
			char c = e.getKeyChar();
			/* keyString = "key character = '" + c + "'"; */
		} else {
			int keyCode = e.getKeyCode();

			if (KeyEvent.getKeyText(keyCode).equals("Enter")) {

				int leng = jTextArea.getText().length();
				if (leng > end) {

					String cmd = jTextArea.getText().substring(end, leng);

					try {
						if (!cmd.trim().equals("")) {
							jTextArea.append("\n" + p.evaluate(cmd));

						}
					} catch (Exception e2) {
						jTextArea.append("\n" + e2);
					}
					end = jTextArea.getText().length();

				}
			}

		}

	}

}
