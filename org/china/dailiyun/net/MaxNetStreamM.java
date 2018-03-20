package org.china.dailiyun.net;

public class MaxNetStreamM {
	private MaxNetStreamPanel maxV;
	private PointEllipse2D firstPoint;
	private int MAX = 100000;// �������������Ǻܴ�,���������ܺͲ�����MAX

	public MaxNetStreamM(MaxNetStreamPanel maxV) {
		this.maxV = maxV;

	}

	public int getMaxLiU() {
		// �����һ��Ϊ���,���ҽ���ֻ��һ�����,һ���յ�

		firstPoint = maxV.getSquares().get(0);

		int temp = piouMaxLiu(firstPoint, MAX);

		return temp;
	}

	public void qiou() {
		if (maxV.getSquares() != null) {
			while (getMaxLiU() > 0)
				;
			maxV.repaint();
		}

	}

	// ���ش�p�����������
	public int piouMaxLiu(PointEllipse2D p, int preMax) {

		if (p.linkEllipse2D1.isEmpty())
			return preMax;
		int tempR = piouMaxLiuR(p, preMax);

		return tempR;

	}

	// ��ǰ����
	public int piouMaxLiuR(PointEllipse2D p, int preMax) {
		int sumRulst = 0;
		for (PointLink link : p.linkEllipse2D1) {

			if (link.endPoint.flag)
				;
			else {
				int temp = link.maxCapcity - link.currentCapcity;
				if (temp > 0) {

					link.endPoint.flag = true;
					int tempMax = preMax > temp ? temp : preMax;

					int tempR = piouMaxLiu(link.endPoint, tempMax);
					link.endPoint.flag = false;

					link.currentCapcity += tempR;
					preMax -= tempR;

					sumRulst += tempR;
					if (preMax == 0)
						return sumRulst;// ���������Ѿ�û�п��Է��ɵ�����.

				}
			}

		}
		for (PointLink link : p.linkEllipse2D2) {
			if (link.startPoint.flag)
				;
			else {
				int temp = link.currentCapcity;
				if (temp > 0) {
					link.startPoint.flag = true;
					int tempMax = preMax > temp ? temp : preMax;
					int tempR = piouMaxLiu(link.startPoint, tempMax);
					link.startPoint.flag = false;
					preMax -= tempR;

					link.currentCapcity -= tempR;

					sumRulst += tempR;
					if (preMax == 0)
						return sumRulst;

				}

			}
		}
		return sumRulst;

	}

}
