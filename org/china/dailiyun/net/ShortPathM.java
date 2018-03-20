package org.china.dailiyun.net;

import java.awt.Color;

public class ShortPathM {
	private ShortPathPanel pathPanel;
	private PointEllipse2D firstPoint;

	public ShortPathM(ShortPathPanel panel) {
		pathPanel = panel;

	}

	public void qiou() {
		if (pathPanel.getSquares() != null) {
			firstPoint = pathPanel.getSquares().get(0);
			firstPoint.setMostShort(1);
			PointEllipse2D tempP = null;
			for (PointEllipse2D P : pathPanel.getSquares()) {
				if (P.linkEllipse2D1.isEmpty())
					tempP = P;
			}

			qiouLang(tempP);
			pathPanel.repaint();
		}
	}

	public int qiouLang(PointEllipse2D p) {
		int tempR = 0;
		for (PointLink link : p.linkEllipse2D2) {
			if (link.startPoint.getMostShort() == 0) {
				qiouLang(link.startPoint);
			}
			int temp = link.maxCapcity + link.startPoint.getMostShort();
			if (tempR == 0) {
				tempR = temp;
				p.setCurrentChoicePath(link);

			} else if (tempR > temp) {
				tempR = temp;
				p.setCurrentChoicePath(link);
			}

		}
		p.setMostShort(tempR);

		return tempR;
	}
}
