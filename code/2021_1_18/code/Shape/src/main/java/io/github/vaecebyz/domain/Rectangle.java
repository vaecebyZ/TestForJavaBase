package main.java.io.github.vaecebyz.domain;

public class Rectangle extends main.java.io.github.vaecebyz.domain.Shape {

	private double length;
	
	private double width;
	
	
	
	
	
	@Override
	public double getPerimerter() {
		
	 double perimerter =(this.length + this.width)*2;
		
	 return perimerter;
		
	}

	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		
		double area = this.length * this.width;
		
		return area;
		
	}

	public void  initRectangle(double length,double width) {
		setLength(length);
		setWidth(width);
	}
	
	
	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public void name() {
		
	}
	
}
