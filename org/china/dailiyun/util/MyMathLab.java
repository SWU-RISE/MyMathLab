package org.china.dailiyun.util;

import org.china.dailiyun.net.*;
import org.china.dailiyun.lpsolve.*;
import org.china.dailiyun.chaogao.*;

import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;

public class MyMathLab extends JFrame {
	public static void main(String[] args) {
		MyMathLab lab = new MyMathLab();

		lab.init();
		lab.intiAction();

	}

	public MyMathLab() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setTitle("MyMathLab");

		JMenu simpleMenu = new JMenu("Problem");
		caogaoItem = new JMenuItem("Draft");
		netItem = new JMenuItem("Network");
		lpItem = new JMenuItem("Optimzation");
		simpleMenu.add(caogaoItem);
		simpleMenu.add(netItem);
		simpleMenu.add(lpItem);

		JMenuBar menuBar = new JMenuBar();

		setJMenuBar(menuBar);
		JMenu m = new JMenu("   ");
		menuBar.add(m);
		menuBar.add(simpleMenu);
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		int W = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int H = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		setLocation((W - getWidth()) / 2, (H - getHeight()) / 2);
		setVisible(true);

	}

	private void init() {
		chaoGao = new ChaoFrame();
		chaoGao.init();

		lpSolve = new LPSolve();
		lpSolve.init();
		lpSolve.initSolve();

		net = new NetStreamFrame();

	}

	private void intiAction() {
		caogaoItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chaoGao.setVisible(true);

			}
		});
		netItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				net.setVisible(true);

			}
		});
		lpItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lpSolve.setVisible(true);

			}
		});

	}

	private ChaoFrame chaoGao;
	private LPSolve lpSolve;
	private NetStreamFrame net;
	JMenuItem caogaoItem;
	JMenuItem netItem;
	JMenuItem lpItem;

	public static final int DEFAULT_WIDTH = Info.DEFAULT_WIDTH;
	public static final int DEFAULT_HEIGHT = Info.DEFAULT_WIDTH;

}
