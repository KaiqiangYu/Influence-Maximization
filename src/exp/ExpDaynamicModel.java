package exp;

import java.util.Arrays;
import java.util.Date;

import algo.AstarAlg;
import algo.GreedyAlg;
import algo.HstarAlg;
import algo.RandomAlg;
import model.DynExpInfluence;
import model.DynRanInfluence;
import model.StaticInfluence;
import util.Log;
import util.ReadGraph;

public class ExpDaynamicModel {
	
	static String dataset_doc="./datasets/";
	static String[] datasets_url= {"wiki"};
	static String result="./result/StaticModel14.txt";
	static ReadGraph[] data=new ReadGraph[datasets_url.length];
	static Log log=new Log(result);
	
	static int[] budget= {5,10,15,20,25,30};
	static int test_num=1;
	static double count[]= {0,0,0,0};
	static int averge_num=500;
	static int estimate_num=20;
	static int omit_num=800;
	
	public static void main(String[] args) {		
		
		for(int i=0;i<datasets_url.length;++i) {
			data[i]=new ReadGraph(dataset_doc+datasets_url[i]+".txt");
			data[i].readGraph();
		}		
		StaticInfluence model=new StaticInfluence(0.5,0.01);
		DynExpInfluence dynmodel=new DynExpInfluence(0.01,0.5);
		//double[] pro_list= {0.1,0.01,0.001};
		//DynRanInfluence dynmodel=new DynRanInfluence(0.5,pro_list);
		
		double temp;
		Date date = new Date();
		String time = date.toLocaleString();
		String temp_string="";
		
		for(int i=0;i<data.length;++i) {
			
			date = new Date();
			time = date.toLocaleString();
			temp_string=datasets_url[i];
			System.out.println(time+" "+temp_string);
			log.write(temp_string+"\n");
			
			for(int j=0;j<budget.length;++j) {
				Arrays.fill(count, 0);
				
				RandomAlg randomalg=new RandomAlg(data[i].graph_size,budget[j],averge_num,
						data[i].Graph, dynmodel);
				for(int k=0;k<100;++k) {
					temp=randomalg.RandomStrategy();
					count[0]=count[0]+temp;
					
					date = new Date();
					time = date.toLocaleString();
					temp_string=time+" "+datasets_url[i]+"-"+"RandomAlg"
					+" budget:"+budget[j]+" test_num:"+k+" count:"+temp
							+" total:"+count[0];
					//System.out.println(temp_string);
					//log.write(temp_string+"\n");
					
				}
				count[0]=count[0]/100;
				
				date = new Date();
				time = date.toLocaleString();
				temp_string=datasets_url[i]+"-"+"RandomAlg"
				+" budget:"+budget[j]+" "+count[0];
				System.out.println(time+" "+temp_string);
				log.write(temp_string+"\n");
				
				GreedyAlg greedy=new GreedyAlg(data[i].graph_size, budget[j], data[i].Graph
						, averge_num, estimate_num, model, dynmodel);
				for(int k=0;k<test_num;++k) {
					temp=greedy.GreedyStrategy();
					count[1]=count[1]+temp;
					
					date = new Date();
					time = date.toLocaleString();
					temp_string=time+" "+datasets_url[i]+"-"+"GreedyAlg"
					+" budget:"+budget[j]+" test_num:"+k+" count:"+temp
							+" total:"+count[1];
					System.out.println(temp_string);					
				}
				
				count[1]=count[1]/test_num;
				
				date = new Date();
				time = date.toLocaleString();
				temp_string=datasets_url[i]+"-"+"GreedyAlg"
				+" budget:"+budget[j]+" "+count[1];
				System.out.println(time+" "+temp_string);
				log.write(temp_string+"\n");
				
				
				AstarAlg astar=new AstarAlg(data[i].graph_size, budget[j], data[i].Graph
						, averge_num, estimate_num, dynmodel);
				for(int k=0;k<test_num;++k) {					
					temp=astar.AstarStrategy();
					count[2]=count[2]+temp;
					
					date = new Date();
					time = date.toLocaleString();
					temp_string=time+" "+datasets_url[i]+"-"+"AstarAlg"
					+" budget:"+budget[j]+" test_num:"+k+" count:"+temp
							+" total:"+count[2];
					System.out.println(temp_string);					
				}
				
				count[2]=count[2]/test_num;
				
				date = new Date();
				time = date.toLocaleString();
				temp_string=datasets_url[i]+"-"+"AstarAlg"
				+" budget:"+budget[j]+" "+count[2];
				System.out.println(time+" "+temp_string);
				log.write(temp_string+"\n");
				
				
				
				HstarAlg hstar=new HstarAlg(data[i].graph_size, budget[j], data[i].Graph
						, averge_num, estimate_num, omit_num,dynmodel);
				for(int k=0;k<test_num;++k) {					
					temp=hstar.HstarStrategy();
					count[3]=count[3]+temp;
					
					date = new Date();
					time = date.toLocaleString();
					temp_string=time+" "+datasets_url[i]+"-"+"HstarAlg"
					+" budget:"+budget[j]+" test_num:"+k+" count:"+temp
							+" total:"+count[3];
					System.out.println(temp_string);					
				}
				
				count[3]=count[3]/test_num;
				
				date = new Date();
				time = date.toLocaleString();
				temp_string=datasets_url[i]+"-"+"HstarAlg"
				+" budget:"+budget[j]+" "+count[3];
				System.out.println(time+" "+temp_string);
				log.write(temp_string+"\n");
				
				
				
				
				
			}
		}
		
		
	}
	

}
