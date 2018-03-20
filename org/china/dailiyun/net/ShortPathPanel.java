package org.china.dailiyun.net;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

class ShortPathPanel extends JPanel {
	public ShortPathPanel() {

		squares = new ArrayList<PointEllipse2D>();

		current = null;
		helpView = true;

		addMouseListener(new MouseHandler());
		addMouseMotionListener(new MouseMotionHandler());
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		String message = "双击为新建接点,按着SHIFT从一个接点到另一个接点的鼠标拖动为连接两个接点";

		Font f = new Font("Serif", Font.BOLD, 20);
		g2.setFont(f);

		// measure the size of the message

		FontRenderContext context = g2.getFontRenderContext();
		if (helpView) {
			Rectangle2D bounds = f.getStringBounds(message, context);

			// set (x,y) = top left corner of text

			double x = (getWidth() - bounds.getWidth()) / 2;
			double y = (getHeight() - bounds.getHeight()) / 2;

			// add ascent to y to reach the baseline

			double ascent = -bounds.getY();
			double baseY = y + ascent;

			// draw the message
			g2.setPaint(Color.BLUE);

			g2.drawString(message, (int) x, (int) (baseY));
		}
		g2.setPaint(Color.black);

		g.setFont(f);
		// draw all squares
		PointEllipse2D temp = null;

		for (PointEllipse2D r : squares) {
			if (r.linkEllipse2D1.isEmpty())
				temp = r;

			g2.draw(r);
			g2.drawString(r.name, (int) r.x + SIDELENGTH / 2 - 6, (int) r.y
					+ SIDELENGTH / 2 + 3);
			int tempStartX = (int) r.x;
			int tempStartY = (int) r.y;

			if (r.linkEllipse2D1 != null) {

				for (PointLink p : r.linkEllipse2D1) {
					p.x1 = p.startPoint.x + SIDELENGTH / 2;
					p.y1 = p.startPoint.y + SIDELENGTH / 2;
					p.x2 = p.endPoint.x + SIDELENGTH / 2;
					p.y2 = p.endPoint.y + SIDELENGTH / 2;
					int tempX = (int) (p.startPoint.x + p.endPoint.x + SIDELENGTH) / 2;
					int tempY = (int) (p.startPoint.y + p.endPoint.y + SIDELENGTH) / 2;
					Line2D.Double tempLine = new Line2D.Double(tempX, tempY,
							p.x2, p.y2);

					g2.drawString(((Integer) (p.maxCapcity)).toString(), tempX
							- SIDELENGTH / 2, tempY - SIDELENGTH / 2);
					Point2D start = new Point2D.Double(p.x1, p.y1);
					Point2D end = new Point2D.Double(p.x2, p.y2);
					float[] dist = { 0.5f, 1.0f };
					Color[] colors = { Color.blue, Color.RED };

					LinearGradientPaint pp = new LinearGradientPaint(start,
							end, dist, colors);
					g2.setPaint(pp);
					g2.draw(p);

					g2.setColor(Color.black);

				}

			}
		}
		if (temp != null) {

			g2.setColor(Color.red);
			while (temp.getCurrentChoicePath() != null) {
				g2.draw(temp.getCurrentChoicePath());
				temp = temp.getCurrentChoicePath().startPoint;
			}
			g2.setColor(Color.black);
		}

	}

	/**
	 * Finds the first square containing a point.
	 * 
	 * @param p
	 *            a point
	 * @return the first square that contains p
	 */
	public Ellipse2D find(Point2D p) {
		for (Ellipse2D r : squares) {
			if (r.contains(p))
				return r;
		}
		return null;
	}

	/**
	 * Adds a square to the collection.
	 * 
	 * @param p
	 *            the center of the squarej
	 */
	public void add(Point2D p) {
		helpView=false;
		double x = p.getX();
		double y = p.getY();

		current = new PointEllipse2D(x - SIDELENGTH / 2, y - SIDELENGTH / 2,
				SIDELENGTH, SIDELENGTH);
		squares.add(current);
		repaint();
	}

	/**
	 * Removes a square from the collection.
	 * 
	 * @param s
	 *            the square to remove
	 */
	public void remove(PointEllipse2D s) {
		if (s == null)
			return;
		if (s == current)
			current = null;
		squares.remove(s);
		repaint();
	}

	public ArrayList<PointEllipse2D> getSquares() {
		return squares;
	}

	private static final int SIDELENGTH = 28;
	private ArrayList<PointEllipse2D> squares;
	private PointEllipse2D current;
	private boolean helpView;

	private ArrayList<PointLink> arrayLink;

	// the square containing the mouse cursor

	private class MouseHandler extends MouseAdapter {
		public void mousePressed(MouseEvent event) {
			// add a new square if the cursor isn't inside a square

			current = (PointEllipse2D) find(event.getPoint());

			if (current == null && event.getClickCount() == 2) {

				add(event.getPoint());

				JOptionPane jop = new JOptionPane();
				try {
					String temp = jop.showInputDialog("这条网络接点的名字（建议用英语字母 ）:");

					current.name = temp;
					repaint();

				} catch (Exception e) {
					e.printStackTrace();

				}
			}

		}
	}

	private class MouseMotionHandler implements MouseMotionListener {
		public void mouseMoved(MouseEvent event) {
			// set the mouse cursor to cross hairs if it is inside
			// a rectangle

			if (find(event.getPoint()) == null)
				setCursor(Cursor.getDefaultCursor());
			else
				setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		}

		public void mouseDragged(MouseEvent event) {
			if (current != null) {

				if (event.isShiftDown()) {
					PointEllipse2D tempCurrent = (PointEllipse2D) find(event
							.getPoint());
					if (tempCurrent != null && tempCurrent != current) {

						PointLink tempLink = new PointLink(current, tempCurrent);
						current.linkEllipse2D1.add(tempLink);
						tempCurrent.linkEllipse2D2.add(tempLink);
						repaint();
						JOptionPane jop = new JOptionPane();
						try {
							int temp = Integer.parseInt(jop
									.showInputDialog("这条网络的长度:"));

							tempLink.maxCapcity = temp;

						} catch (Exception e) {
							e.printStackTrace();

						}
					}

				} else {

					// drag the current rectangle to center it at (x, y)
					int x = event.getX();
					int y = event.getY();
					current.setFrame(x - SIDELENGTH / 2, y - SIDELENGTH / 2,
							SIDELENGTH, SIDELENGTH);

				}
				repaint();

			}
		}
	}

	public void setSquares(ArrayList<PointEllipse2D> squares) {
		this.squares = squares;
	}

}
