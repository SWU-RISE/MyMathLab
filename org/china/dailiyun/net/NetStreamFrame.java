package org.china.dailiyun.net;

/*
 *  ����һ�������������������ͼ
 *  
 */
/**
 @version 1.0 2008.05.17
 @author dailiyun
 */
import org.china.dailiyun.util.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

/*public class NetStreamV {
	public static void main(String[] args) {
		NetStreamFrame frame = new NetStreamFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
*/
/**
 * A frame containing a panel for testing mouse operations
 */
public class NetStreamFrame extends JFrame {
	public NetStreamFrame() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		img = new ImageIcon("tu.jpg");

		panel = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(img.getImage(), 0, 0, null);
				super.paintComponent(g);
			}
		};

		panel.setOpaque(false);
		add(panel, BorderLayout.CENTER);

		setTitle("Operation problmes");

		this.setIconImage(new ImageIcon("tu1.jpg").getImage());
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		int W = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int H = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		setLocation((W - getWidth()) / 2, (H - getHeight()) / 2);

	
		// add panel to frame

		maxPane = new MaxBridePanel();
		pertPane = new PERTBridePanel();
		shortPane = new ShortBridePanel();
	
		panel.setVisible(true);

		JMenu NetSrteamMenu = new JMenu("Network");

		JMenuItem MaxItem = NetSrteamMenu.add("Maxflow");
		MaxItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				shortPane.setVisible(false);
				pertPane.setVisible(false);
				add(maxPane, BorderLayout.CENTER);
				maxPane.setVisible(true);

			}
		});

		JMenuItem MaxShortPathItem = NetSrteamMenu.add("Shortest path");
		MaxShortPathItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				maxPane.setVisible(false);
				pertPane.setVisible(false);
				add(shortPane, BorderLayout.CENTER);
				shortPane.setVisible(true);

			}
		});
		JMenuItem ImportPathItem = NetSrteamMenu.add("Critical path");
		ImportPathItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				maxPane.setVisible(false);
				shortPane.setVisible(false);
				add(pertPane, BorderLayout.CENTER);
				pertPane.setVisible(true);

			}
		});
		JMenuBar menuBar = new JMenuBar();

		setJMenuBar(menuBar);
		JMenu m = new JMenu();
		menuBar.add(m);
		menuBar.add(NetSrteamMenu);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				NetStreamFrame.this.setVisible(false);
			}
			
		});

	}

	public static final int DEFAULT_WIDTH = Info.DEFAULT_WIDTH;
	public static final int DEFAULT_HEIGHT = Info.DEFAULT_WIDTH;
	

	private JPanel panel;

	private MaxBridePanel maxPane;
	private PERTBridePanel pertPane;
	private ShortBridePanel shortPane;

	ImageIcon img;
}

/**
 * A panel with mouse operations for adding and removing squares.
 */
