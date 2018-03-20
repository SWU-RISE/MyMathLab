package org.china.dailiyun.net;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

class MaxBridePanel extends JPanel {
	private MaxNetStreamPanel maxPane;
	private MaxNetStreamM maxM;
	private JPanel buttonPane;

	public MaxBridePanel() {
		maxPane = new MaxNetStreamPanel();
		maxM = new MaxNetStreamM(maxPane);
		buttonPane = new JPanel();
		this.setLayout(new BorderLayout());

		add(maxPane, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		JButton startButton = new JButton("Start search");
		startButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				maxM.qiou();
			}
		});
		JButton cleanButton = new JButton("Clean");
		cleanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maxPane.setSquares(new ArrayList<PointEllipse2D>());
				maxPane.repaint();
			}

		});
		JLabel state=new JLabel("Maxflow");
		buttonPane.add(state);
		buttonPane.add(startButton);
		buttonPane.add(cleanButton);
		add(buttonPane,BorderLayout.SOUTH);
		
		

	}

}
