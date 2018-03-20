package org.china.dailiyun.net;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

class PERTBridePanel extends JPanel {
	private PERTPanel PERTP;
	private PERTM PERM;
	private JPanel buttonPane;

	public PERTBridePanel() {
		PERTP = new PERTPanel();
		PERM = new PERTM(PERTP);
		buttonPane = new JPanel();
		this.setLayout(new BorderLayout());

		add(PERTP, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		JButton startButton = new JButton("¿ªÊ¼ËÑË÷ ");
		startButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				PERM .qiou();
			}
		});
		JButton cleanButton = new JButton("Çå³ý");
		cleanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PERTP.setSquares(new ArrayList<PointEllipse2D>());
				PERTP.repaint();
			}

		});
		JLabel state=new JLabel("ÍøÂçPERT");
		buttonPane.add(state);
		buttonPane.add(startButton);
		buttonPane.add(cleanButton);
		add(buttonPane, BorderLayout.SOUTH);

	}

}
