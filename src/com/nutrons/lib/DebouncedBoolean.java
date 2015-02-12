package com.nutrons.lib;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class DebouncedBoolean {
	
	private int length;
	private int count;
	private boolean state = false;
	
	public DebouncedBoolean(int length) {
		this.length = length;
		count = 0;
	}
	
	public void feed(boolean in) {
		if(state) {
			state = in;
		}else if(in) {
			if(count < length) {
				state = true;
			}else {
				count++;
			}
		}
		
		if(!in) {
			reset();
		}
	}
	
	public boolean get() {
		return state;
	}
	
	public void reset() {
		count = 0;
	}
}
