package org.china.dailiyun.net;

public class PERTM {
	private PERTPanel pathPanel;
	private PointEllipse2D firstPoint;

	public PERTM(PERTPanel panel) {
		pathPanel = panel;

	}

	public void qiou() {
		if (pathPanel.getSquares() != null) {
			firstPoint = pathPanel.getSquares().get(0);
			firstPoint.setMostLong(1);
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
			if (link.startPoint.getMostLong() == 0) {
				qiouLang(link.startPoint);
			}
			int temp = link.maxCapcity + link.startPoint.getMostLong();
			if (tempR == 0) {
				tempR = temp;
				p.setCurrentChoicePath(link);

			} else if (tempR < temp) {
				tempR = temp;
				p.setCurrentChoicePath(link);
			}

		}
		p.setMostLong(tempR);

		return tempR;
	}

}
