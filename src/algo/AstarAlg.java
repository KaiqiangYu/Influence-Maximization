package algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.StaticInfluence;

public class AstarAlg {
	
	private int graph_size;
	//private double node_active_prob;
	private int[][] graph;
	private StaticInfluence model;
	private double[][] weight;
	private int[] state;
	private int budget;
	private int test_num=1;
	private int estimate_num=1;
	
	public AstarAlg(int graph_size,int budget, int[][] graph,int test_num,
			int estimate_num, StaticInfluence model) {
		// TODO Auto-generated constructor stub
		this.graph=graph;
		this.graph_size=graph_size;
		//this.node_active_prob=node_active_prob;
		this.test_num=test_num;
		this.model=model;
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
	
	public double AstarStrategy() {		
		int count=0;
		Arrays.fill(state, -1);
		int[] choice=new int[budget];
		//int[] status=new int[graph_size];
		//Arrays.fill(status, 0);
		List<Integer> list=new ArrayList<Integer>();
		
		int index_max=0;
		double record_max=0;
		double temp_num=0;
		
		int[] copy_state=new int[graph_size];
		Arrays.fill(copy_state, -1);
		
		for(int i=0;i<budget;++i) {
			for(int j=0;j<graph_size;++j) {
				if(state[j]<=0) {
					choice[i]=j;
					
					for(int em=0;em<estimate_num;++em) {
						for(int k=0;k<graph_size;++k) {
							copy_state[k]=state[k];
						}
						for(int k=0;k<=i;++k) {
							list.add(choice[k]);
						}
						temp_num=temp_num+model.Spread(graph, copy_state, list);
						//Arrays.fill(state, -1);
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
			
			for(int k=0;k<=i;++k) {
				list.add(choice[k]);
			}
			model.Spread(graph, state, list);
			//status[index_max]=1;
		}
		
		for(int i=0;i<test_num;++i) {
			for(int k=0;k<budget;++k) {
				list.add(choice[k]);
			}
			Arrays.fill(state, -1);
			count=count+model.Spread(graph, state, list);			
		}
		return (count*1.0)/test_num;
	}

}
