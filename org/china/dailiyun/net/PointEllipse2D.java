package org.china.dailiyun.net;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

class PointEllipse2D extends Ellipse2D.Double {
	public PointEllipse2D(double x, double y, double h, double w) {
		super(x, y, h, w);
		name = "";
		linkEllipse2D1 = new ArrayList<PointLink>();
		linkEllipse2D2 = new ArrayList<PointLink>();
		flag = false;
		mostShort=0;
		mostLong=0;
		selfUsage=0;
		currentChoicePath=null;

	}

	ArrayList<PointLink> linkEllipse2D1;// 从这个点出发的线段。
	ArrayList<PointLink> linkEllipse2D2;// 制向这个点的线段。
	PointLink myCurrent;
	String name;
	boolean flag;
	private int mostShort;// 和最短路径有关
	private int mostLong;//和关键路径有关
	private int selfUsage;
	private PointLink currentChoicePath;//和最短路径,关键路径有关 

	public void addEllipse2D1(PointLink pointE) {
		linkEllipse2D1.add(pointE);

	}

	public void removelinkEllipse2D1(PointLink pointE) {
		linkEllipse2D1.remove(pointE);

	}

	public void addEllipse2D2(PointLink pointE) {
		linkEllipse2D2.add(pointE);

	}

	public void removelinkEllipse2D2(PointLink pointE) {
		linkEllipse2D1.remove(pointE);

	}

	public int getMostShort() {
		return mostShort;
	}

	public void setMostShort(int mostShort) {
		this.mostShort = mostShort;
	}

	public PointLink getCurrentChoicePath() {
		return currentChoicePath;
	}

	public void setCurrentChoicePath(PointLink currentChoicePath) {
		this.currentChoicePath = currentChoicePath;
	}

	public int getSelfUsage() {
		return selfUsage;
	}

	public void setSelfUsage(int selfUsage) {
		this.selfUsage = selfUsage;
	}

	public int getMostLong() {
		return mostLong;
	}

	public void setMostLong(int mostLong) {
		this.mostLong = mostLong;
	}

}