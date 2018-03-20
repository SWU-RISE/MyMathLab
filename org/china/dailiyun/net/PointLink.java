package org.china.dailiyun.net;
import java.awt.Color;
import java.awt.geom.Line2D;

class PointLink extends Line2D.Double {
	// private PointEllipse2D startPoint;

	PointEllipse2D endPoint;
	PointEllipse2D startPoint;
	int maxCapcity;
	int currentCapcity;
	int haiSheng;
	private Color mycolor;//我的颜色用与最短路和关键路径
	boolean flag;//true 表示向前的流;false 表示向后的流
	static int SIDELENGTH = 28;

	public PointLink(PointEllipse2D start, PointEllipse2D end) {
		super(start.x + SIDELENGTH / 2, start.y + SIDELENGTH / 2, end.x
				+ SIDELENGTH / 2, end.y + SIDELENGTH / 2);
		endPoint = end;
		startPoint = start;
		maxCapcity = 0;
		currentCapcity = 0;

	}

	public Color getMycolor() {
		return mycolor;
	}

	public void setMycolor(Color mycolor) {
		this.mycolor = mycolor;
	}

}
