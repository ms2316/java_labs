package uk.ac.cam.ms2316.Algorithms.Tick1;

import uk.ac.cam.rkh23.Algorithms.Tick1.*;

public class MaxCharHeap implements MaxCharHeapInterface {

	private char[] h;
	private int length;
	
	public MaxCharHeap(String s) {
		s = s.toLowerCase();
		h = s.toCharArray();
		length = h.length;
		for (int i = length/2; i >= 0; i--) 
			siftDown(i);
	}
	
	public char getMax() throws EmptyHeapException {
		if (length == 0)
			throw new EmptyHeapException();
		
		char output = h[0];
		h[0] = h[length - 1];
		length--;
		siftDown(0);
		
		return output;
	}
	
	public void insert(char i) {
		if (length == h.length) {
			char[] tmp = new char[2 * h.length];
			for (int it = 0; it < length; it++)
				tmp[it] = h[it];
			h = tmp;
		}
		h[length] = Character.toLowerCase(i);
		siftUp(length);
		length++;
	}
	
	
	public int getLength() {
		return length;
	}
       
    private void siftUp(int v) {   
		while (v > 0 && h[v] > h[(v-1)/2]) {
			char tmp = h[v];
			h[v] = h[(v-1)/2];
			h[(v-1)/2] = tmp;
			v = (v-1)/2;
		}
    }
     
    private void siftDown(int v) {
		int imax = v;
		while (true) {
			if ( v*2+1 < length && h[imax] < h[v*2+1] ) imax = v*2+1;
			if ( v*2+2 < length && h[imax] < h[v*2+2] ) imax = v*2+2;
			if (imax == v) return;
			
			char tmp = h[v];
			h[v] = h[imax];
			h[imax] = tmp;
			
			v = imax;
		}
    }
       
       
       
       
	
}