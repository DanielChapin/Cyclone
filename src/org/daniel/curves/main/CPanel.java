package org.daniel.curves.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CPanel extends JPanel {
	
	private Point[][] points;
	private Point center;
	
	private static final double ARC_LENGTH = 1 * Math.PI / 1;
	private static final int SEGMENTS = 80, STRANDS = 50;
	private int radius;
	
	private double additionalRotation = 0d;

	public static void main(String[] args) {
		new CPanel();
		
	}
	
	private CPanel() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setContentPane(this);
		frame.setVisible(true);
		
		this.center = new Point(frame.getWidth() / 2, frame.getHeight() / 2);
		this.radius = this.center.y - 25;
		this.initPoints(STRANDS);
		this.setBackground(new Color(0.2f, 0.2f, 0.2f));
		
		long now = System.nanoTime(), lastLoop = now, loopNanos = 1000000000 / 60;
		
		while(true) 
			if((now = System.nanoTime()) - lastLoop >= loopNanos) {
				lastLoop = now;
				this.repaint();
			}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		this.initPoints(STRANDS);
		for(int j = 0; j < this.points.length; j++) for(int i = 0; i < SEGMENTS - 1; i++) {
			g.setColor(Color.getHSBColor((float) i / (SEGMENTS - 1) + (float) this.additionalRotation, 1, 1));
			g.drawLine(this.points[j][i].x + this.center.x, this.points[j][i].y + this.center.y, this.points[j][i + 1].x + this.center.x, this.points[j][i + 1].y + this.center.y);
		}
	}
	
	private void initPoints(int index) {
		this.points = new Point[index][SEGMENTS];
		this.additionalRotation -= 0.01;
		for(int j = 0; j < index; j++) for(int i = 0; i < SEGMENTS; i++) this.points[j][i] = new Point((int) ((float) radius / SEGMENTS * i * Math.cos(ARC_LENGTH / SEGMENTS * i + Math.PI * 2 / index * j + this.additionalRotation)), (int) ((float) radius / SEGMENTS * i * Math.sin(ARC_LENGTH / SEGMENTS * i + Math.PI * 2 / index * j + this.additionalRotation)));
	}

}
