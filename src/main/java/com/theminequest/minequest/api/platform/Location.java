package com.theminequest.minequest.api.platform;

public class Location extends MultiVector {

	private String world;
	
	public Location(String world, double x, double y, double z) {
		super(x,y,z);
		this.world = world;
	}
	
	public Location(String world, MultiVector vector) {
		super(vector);
		this.world = world;
	}
	
	@Override
	public Location add(double x, double y, double z) {
		return new Location(world, super.add(x,y,z));
	}

	@Override
	public Location add(MultiVector other) {
		return new Location(world, super.add(other));
	}

	@Override
	public Location multiply(double x, double y, double z) {
		return new Location(world, super.multiply(x, y, z));
	}

	@Override
	public Location multiply(MultiVector other) {
		return new Location(world, super.multiply(other));
	}

	@Override
	public Location floor() {
		return new Location(world, super.floor());
	}

	@Override
	public Location round() {
		return new Location(world, super.round());
	}
	
	public MultiVector getVector() {
		return this;
	}

	public String getWorld() {
		return world;
	}
	
	public Location setWorld(String world) {
		return new Location(world, this);
	}
	
	
}
