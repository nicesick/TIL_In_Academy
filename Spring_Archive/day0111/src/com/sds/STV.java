package com.sds;

public class STV implements TV {
	private int size;
	private Speaker speaker;
	
	public STV(int size, Speaker speaker) {
		super();
		this.size = size;
		this.speaker = speaker;
	}
	
	public STV(Speaker speaker) {
		System.out.println("STV Construct");
		this.speaker = speaker;
	}
	
	public STV(int size) {
		System.out.println("STV Construct");
		this.size = size;
	}
	
	public STV() {
		System.out.println("STV Construct");
	}
	
	public void up() {
		speaker.up();
	}
	
	public void turnOn() {
		System.out.println("STV On");
	}
	
	public void turnOff() {
		System.out.println("STV Off");
	}

	@Override
	public String toString() {
		return "STV [size=" + size + ", speaker=" + speaker + "]";
	}

	
}
