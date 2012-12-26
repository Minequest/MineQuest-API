package com.theminequest.minequest.api.platform;

public class MultiVector {
	
	public static final MultiVector FORWARD = new MultiVector(0, 0, 1);
	public static final MultiVector ONE = new MultiVector(1, 1, 1);
	public static final MultiVector RIGHT = new MultiVector(1, 0, 0);
	public static final MultiVector UP = new MultiVector(0, 1, 0);
	
	private double x, y, z;
	
	public MultiVector(double x, double y, double z) {
		this.x = x;
		this.y = y; 
		this.z = z;
	}
	
	public MultiVector(MultiVector other) {
		this.x = other.x;
		this.y = other.y;
		this.z = other.z;
	}
	
	public MultiVector add(double x, double y, double z) {
		return new MultiVector(this.x + x, this.y + y, this.z + z);
	}
	
	public MultiVector add(MultiVector other) {
		return new MultiVector(x + other.x, y + other.y, z + other.z);
	}
	
	public MultiVector multiply(double x, double y, double z) {
		return new MultiVector(this.x * x, this.y * y, this.z * z);
	}
	
	public MultiVector multiply(MultiVector other) {
		return new MultiVector(x * other.x, y * other.y, z * other.z);
	}
	
	public MultiVector floor() {
		return new MultiVector(Math.floor(x), Math.floor(y), Math.floor(z));
	}
	
	public MultiVector round() {
		return new MultiVector(Math.round(x), Math.round(y), Math.round(z));
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getZ() {
		return z;
	}

	
}
