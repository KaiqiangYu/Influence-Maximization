package model;

import java.util.Iterator;
import java.util.List;

/**
 * @author: Yui
 * @date: 19/10/2019
 * @thanks: DMAL NTU & C401 SIST & Inori Minase & Takagi
 * @description: Influence diffusion for DIC model
 */
public class StaticInfluence {
	
	protected double NODE_ACT_PROB=1;
	protected double EDGE_ACT_PROB=1;
	
	public StaticInfluence(double NODE_ACT_PROB,double EDGE_ACT_PROB) {
		this.EDGE_ACT_PROB=EDGE_ACT_PROB;
		this.NODE_ACT_PROB=NODE_ACT_PROB;
	}
	
	public StaticInfluence() {
		// TODO Auto-generated constructor stub
	}
	
	public double GetEdgeProb() {
		return EDGE_ACT_PROB;
	}
	
	public double GetNodeProb() {
		return NODE_ACT_PROB;
	}
	
	public int Spread(int[][] graph,int[] state,
			List<Integer> list) {
		int temp_node;

		for(int i=0;i<list.size();) {
			if(Math.random()>GetNodeProb()) {
				list.remove(i);
			}else {
				state[i]=1;
				i++;
			}
		}
		
		
		
		while(!list.isEmpty()) {
			//System.out.println(list.size());
			temp_node=list.remove(0);
			
			for(int i=0;i<graph[temp_node].length;++i) {
				if(state[graph[temp_node][i]]<=0) {
					double edge_prob=GetEdgeProb();
					//weight[temp_node][i]=edge_prob;
					if(Math.random()<=edge_prob) {
						state[graph[temp_node][i]]=0;
						if(Math.random()<=GetNodeProb()) {
							state[graph[temp_node][i]]=1;
							list.add(graph[temp_node][i]);
						}
					}
				}
			}
			
		}
		int res=0;
		for(int i=0;i<graph.length;++i) {
			if(state[i]==1) {
				++res;
			}
		}
		return res;
	}

}
