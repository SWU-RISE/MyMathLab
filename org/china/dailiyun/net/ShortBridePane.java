package org.china.dailiyun.net;

import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

class ShortBridePanel extends JPanel {
	private  ShortPathPanel shortPane;
	private ShortPathM shortM;
	private JPanel buttonPane;

	public ShortBridePanel() {
		shortPane = new  ShortPathPanel();
		shortM = new ShortPathM(shortPane);
		buttonPane = new JPanel();
		this.setLayout(new BorderLayout());

		add(shortPane, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		JButton startButton = new JButton("¿ªÊ¼ËÑË÷ ");
		startButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				shortM.qiou();
			}
		});
		JButton cleanButton = new JButton("Çå³ý");
		cleanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shortPane.setSquares(new ArrayList<PointEllipse2D>());
				shortPane.repaint();
			}

		});
		JLabel state=new JLabel("ÍøÂç×î¶Ì¾à");
		buttonPane.add(state);
		buttonPane.add(startButton);
		buttonPane.add(cleanButton);
		add(buttonPane,BorderLayout.SOUTH);
		
		

	}

}
