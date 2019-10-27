package model;

public class DynExpInfluence extends StaticInfluence{
	
	private double mean=0.01;
	
	
	public DynExpInfluence(double mean,double NODE_ACT_PROB) {
		// TODO Auto-generated constructor stub
		this.mean=mean;
		this.NODE_ACT_PROB=NODE_ACT_PROB;
	}
	
	@Override
	public double GetEdgeProb() {
		// TODO Auto-generated method stub
		double a=Math.random();
		double prob=-mean*Math.log(a);
		
		return (prob<1)?prob:1;
	}

}
