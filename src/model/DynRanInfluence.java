package model;

public class DynRanInfluence extends StaticInfluence{
	
	private double discrete_pro[]= {};
	
	public DynRanInfluence(double NODE_ACT_PROB, double EDGE_ACT_PROB) {
		super(NODE_ACT_PROB, EDGE_ACT_PROB);
	}
	
	public DynRanInfluence(double NODE_ACT_PROB, double discrete_pro[]) {
		// TODO Auto-generated constructor stub
		
		this.NODE_ACT_PROB=NODE_ACT_PROB;
		this.discrete_pro=discrete_pro;
		
	}
	
	@Override
	public double GetEdgeProb() {
		// TODO Auto-generated method stub
		int a=(int) (Math.random()*discrete_pro.length);
		return discrete_pro[a];
	}

}
