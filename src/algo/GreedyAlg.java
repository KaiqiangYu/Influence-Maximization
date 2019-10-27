package algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.StaticInfluence;

public class GreedyAlg {
	
	private int graph_size;
	//private double node_active_prob;
	private int[][] graph;
	private StaticInfluence Decision_model;
	private StaticInfluence Test_model;
	private double[][] weight;
	private int[] state;
	private int budget;
	private int test_num=1;
	private int estimate_num=1;
	
	public GreedyAlg(int graph_size,int budget, int[][] graph,int test_num,
			int estimate_num, StaticInfluence Decision_model, StaticInfluence Test_model) {
		// TODO Auto-generated constructor stub
		this.graph=graph;
		this.graph_size=graph_size;
		//this.node_active_prob=node_active_prob;
		this.test_num=test_num;
		this.Decision_model=Decision_model;
		this.Test_model=Test_model;
		this.budget=budget;
		this.estimate_num=estimate_num;
		
//		weight=new double[graph_size][];
//		for(int i=0;i<graph_size;++i) {
//			weight[i]=new double[graph[i].length];
//			Arrays.fill(weight[i], 0);
//		}
		
		state=new int[graph_size];
		Arrays.fill(state, -1);
	}
	
	public double GreedyStrategy() {		
		int count=0;
		Arrays.fill(state, -1);
		
		int[] choice=new int[budget];
		int[] status=new int[graph_size];
		Arrays.fill(status, 0);
		List<Integer> list=new ArrayList<Integer>();
		
		int index_max=0;
		double record_max=0;
		double temp_num=0;
		
		for(int i=0;i<budget;++i) {
			//System.out.println(i);
			for(int j=0;j<graph_size;++j) {
				//System.out.println(j);
				if(status[j]==0) {
					choice[i]=j;
					
					for(int em=0;em<estimate_num;++em) {
						for(int k=0;k<=i;++k) {
							list.add(choice[k]);
						}
						Arrays.fill(state, -1);
						temp_num=temp_num+Decision_model.Spread(graph, state, list);
						
					}
					
					temp_num=temp_num/estimate_num;
					if(temp_num>record_max) {
						index_max=j;
						record_max=temp_num;
					}
					//Arrays.fill(state, -1);
					
				}
			}
			choice[i]=index_max;
			record_max=0;
			status[index_max]=1;
		}
		
		for(int i=0;i<test_num;++i) {
			for(int k=0;k<budget;++k) {
				list.add(choice[k]);
			}
			Arrays.fill(state, -1);
			count=count+Test_model.Spread(graph, state, list);			
		}
		return (count*1.0)/test_num;
	}

}
