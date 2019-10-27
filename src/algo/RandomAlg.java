package algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import model.StaticInfluence;

public class RandomAlg {
	
	private int graph_size;
	//private double node_active_prob;
	private int[][] graph;
	private StaticInfluence model;
	private double[][] weight;
	private int[] state;
	private int budget;
	private int test_num=1;
	
	public RandomAlg(int graph_size,int budget,int test_num, int[][] graph,
			StaticInfluence model) {
		// TODO Auto-generated constructor stub
		this.graph=graph;
		this.graph_size=graph_size;
		//this.node_active_prob=node_active_prob;
		this.model=model;
		this.budget=budget;
		this.test_num=test_num;
		
//		weight=new double[graph_size][];
//		for(int i=0;i<graph_size;++i) {
//			weight[i]=new double[graph[i].length];
//			Arrays.fill(weight[i], 0);
//		}
		
		state=new int[graph_size];
		Arrays.fill(state, -1);
	}
	
	public double RandomStrategy() {
		double count=0;
		Arrays.fill(state, -1);
		
		HashSet<Integer> hash=new HashSet<Integer>();
		while(hash.size()<budget) {
			Random r=new Random();
			hash.add(r.nextInt(graph_size));
		}
		
		List<Integer> list=new ArrayList<Integer>();
		Iterator<Integer> it=hash.iterator();
		while(it.hasNext()) {
			list.add(it.next());
		}
		
		for(int i=0;i<test_num;++i) {
			count=count+model.Spread(graph, state, list);
			Arrays.fill(state, -1);
			it=hash.iterator();
			while(it.hasNext()) {
				list.add(it.next());
			}
		}
		
		return (count*1.0)/test_num;
	}

}
