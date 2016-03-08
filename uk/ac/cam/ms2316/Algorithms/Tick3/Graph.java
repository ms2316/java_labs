package uk.ac.cam.ms2316.Algorithms.Tick3;

import uk.ac.cam.rkh23.Algorithms.Tick3.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

public abstract class Graph {
	
	protected int f[][]; // Current flow
	
	public Graph(URL url) throws IOException {
		f = new int[mN][mN];
		super(url);
	}
	
	public Graph(String file) throws IOException {
		f = new int[mN][mN];
		super(file);
	}
	
	public Graph(int adj[][]) {
		f = new int[mN][mN];
		super(adj);
	}
	
	/**
	 * Computes a path between two nodes that has the smallest number
	 * of edges using BFS. Ignores the edge weights.
	 * @param src  The source node ID
	 * @param target The sink node ID
	 * @return A list of node IDs for a path from src to target
	 * @throws TargetUnreachable Thrown if s and t are disconnected
	 */
	public List<Integer> getFewestEdgesPath(int src, int target) throws TargetUnreachable {
		Queue<Integer> q = new Queue<Integer>();
		int[] parent = new int[mN];
		int[] d = new int[mN];
		for (int i = 0; i < mN; i++) {
			parent[i] = -1;
			d[i] = Integer.MAX_VALUE;
		}
		
		parent[src] = src;
		d[src] = 0;
		q.add(src);
		
		while (!q.empty) {
			int u = q.poll();
			for (int i = 0; i < mN; i++) {
				if (parent[i] == -1 && (f[u][i] < mAdj[u][i] || f[i][u] > 0) ) {
					q.add(i);
					parent[i] = u;
				}
			}
		}
		
		if (parent[i] == -1) 
			throw TargetUnreachable();
		
		List<Integer> list = new List<Integer>();
		int p = target;
		while (p != parent[p]) {
			list.add(p);
			p = parent[p];
		}
		list.add(p);
		Collections.reverse(list);
		
		return list;
	}
	
	/**
	 * Compute the maximum flow between two nodes
	 * @param s Source node ID
	 * @param t Sink node ID
	 * @return Flow value and network
	 */
	public MaxFlowNetwork getMaxFlow(int s, int t) {
		while (true) {
			try {
				List<Integer> path = getFewestEdgesPath(s,t);
			
			
			} catch (TargetUnreachable e) {
			
			
			}
		
		
		
		}
	
	}

}