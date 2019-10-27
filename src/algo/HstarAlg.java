package algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.StaticInfluence;

public class HstarAlg {
	
	private int graph_size;
	//private double node_active_prob;
	private int[][] graph;
	private StaticInfluence model;
	private double[][] weight;
	private int[] state;
	private int budget;
	private int test_num=1;
	private int estimate_num=1;
	private int[] omit;
	private int omit_num=100;
	
	public HstarAlg(int graph_size,int budget, int[][] graph,int test_num,
			int estimate_num,int omit_num, StaticInfluence model) {
		// TODO Auto-generated constructor stub
		this.graph=graph;
		this.graph_size=graph_size;
		//this.node_active_prob=node_active_prob;
		this.test_num=test_num;
		this.model=model;
		this.budget=budget;
		this.estimate_num=estimate_num;
		this.omit_num=omit_num;
		
		omit=new int[graph_size];
		Arrays.fill(omit, 0);
		
//		weight=new double[graph_size][];
//		for(int i=0;i<graph_size;++i) {
//			weight[i]=new double[graph[i].length];
//			Arrays.fill(weight[i], 0);
//		}
		
		state=new int[graph_size];
		
	}
	
	private int Preprocess() {
		double node_deg[]=new double[graph_size];
		double avg_deg[]=new double[omit_num];
		double temp=0;
		List<Integer> list=new ArrayList<Integer>();
		for(int k=0;k<omit_num;++k) {
			for(int i=0;i<graph_size;++i) {
				list.add(i);
				Arrays.fill(state, -1);
				temp=model.Spread(graph, state, list);
				node_deg[i]+=temp;
				avg_deg[k]+=temp;
			}
		}
		
		for(int i=0;i<graph_size;++i)
			node_deg[i]/=omit_num;
		temp=0;
		for(int i=0;i<omit_num;++i) {
			avg_deg[i]/=graph_size;
			temp+=avg_deg[i];
		}
		temp/=omit_num;
		double std=0;
		for(int i=0;i<omit_num;++i) {
			std=std+(avg_deg[i]-temp)*(avg_deg[i]-temp);
		}
		std/=omit_num;
		std=Math.sqrt(std);
		
		int count=0;
		for(int i=0;i<graph_size;++i) {
			if(node_deg[i]<temp-std) {
				omit[i]=-1;
			}else {
				omit[i]=count;
				count++;
			}
		}
		return count;
	}
	
	public double HstarStrategy() {		
		int count=0;
		Arrays.fill(state, -1);
		Arrays.fill(omit, 0);
		
		int new_graph_size=Preprocess();
		int[][] new_graph=new int[new_graph_size][];
		for(int i=0;i<graph_size;++i) {
			int temp_count=0;
			//int temp_array[];
			if(omit[i]>=0) {
				temp_count=0;
				for(int j=0;j<graph[i].length;++j) {
					if(omit[i]>=0)
						temp_count++;
				}
				new_graph[omit[i]]=new int[temp_count];
				for(int j=0;j<graph[i].length;++j) {
					if(omit[graph[i][j]]>=0)
						new_graph[omit[i]][j]=omit[graph[i][j]];
				}
			}
		}
		
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
			for(int j=0;j<new_graph_size;++j) {
				if(state[j]<=0) {
					
					choice[i]=j;					
					for(int em=0;em<estimate_num;++em) {
						
						for(int k=0;k<new_graph_size;++k) {
							copy_state[k]=state[k];
						}
						for(int k=0;k<=i;++k) {
							list.add(choice[k]);
						}
						temp_num=temp_num+model.Spread(new_graph, copy_state, list);
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
			model.Spread(new_graph, state, list);
			//status[index_max]=1;
		}
		for(int k=0;k<budget;++k) {
			for(int kk=0;kk<graph_size;++kk) {
				if(choice[k]==omit[kk]) {
					choice[k]=kk;
				}
			}
		}
		for(int i=0;i<test_num;++i) {
			for(int k=0;k<budget;++k) {
				list.add(choice[k]);
			}
			count=count+model.Spread(graph, state, list);
			Arrays.fill(state, -1);
		}
		return (count*1.0)/test_num;
	}


}
