package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author: Yui
 * @date: 19/10/2019
 * @thanks: DMAL NTU & C401 SIST & Inori Minase
 * @description: ReadGraph from file
 */
public class ReadGraph {
	
	/** URL of the given graph */
	private String Graph_File=null;
	/** the number of vertex in the given graph*/
	public int graph_size=0;
	public int Graph[][]=null;
	//public double Graph_weight[][]=null;
	
	
	/**
	 * @param Graph_File URL of the given graph
	 */
	public ReadGraph(String Graph_File) {
		this.Graph_File=Graph_File;
	}
	
	/**
	 * Read the information of given graph from the file.
	 * 
	 * File Format:
	 * 3
	 * 0 1 2
	 * 1 0
	 * 2 1
	 * The first line of the file is the number of vertices (e.g. 3)
	 * The next few lines give the information about adjacency List of
	 * given graph (e.g. The second line '0 1 2' represents two different edges 
	 * such as 0-1,0-2)
	 * 
	 * @return adjacency List of the given graph 
	 */
	public int[][] readGraph(){
		int Graph[][]=null;
		try {
			BufferedReader stdin=new BufferedReader(new FileReader(Graph_File));
			String line=null;
			int vertex=0;
			
			line=stdin.readLine();
			String s[]=line.split(" ");
			int graph_size=Integer.parseInt(s[0]);
			Graph=new int[graph_size][];
			//Graph_weight=new double[graph_size][];
			this.graph_size=graph_size;
			while((line = stdin.readLine()) != null){
				s = line.split(" ");
				//System.out.println(s[0]);
				vertex = Integer.parseInt(s[0]);
				Graph[vertex] = new int[s.length - 1];
				//Graph_weight[vertex] = new double[s.length - 1];
				for(int i = 1;i < s.length;i ++){
					Graph[vertex][i - 1] = Integer.parseInt(s[i]);
				}
			}
			stdin.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.Graph=Graph;
		return Graph;
	}
	
	public int[][] RereadGraph(String aaa){
		int Graph[][]=null;
		try {
			BufferedReader stdin=new BufferedReader(new FileReader(Graph_File));
			String line=null;
			int vertex=0;
			
			line=stdin.readLine();
			String s[]=line.split(" ");
			int graph_size=Integer.parseInt(s[0]);
			Graph=new int[graph_size][];
			this.graph_size=graph_size;
			while((line = stdin.readLine()) != null){
				s = line.split(" ");
				//System.out.println(s[0]);
				vertex = Integer.parseInt(s[0]);
				Graph[vertex] = new int[s.length - 1];
				for(int i = 1;i < s.length;i ++){
					Graph[vertex][i - 1] = Integer.parseInt(s[i]);
				}
			}
			stdin.close();
			
			BufferedWriter stdout =new BufferedWriter(new FileWriter(aaa,false));
			stdout.write(graph_size+"\n");
			stdout.flush();
			for(int i=0;i<graph_size;++i) {
				stdout.write(i+"");
				int temp_array[]=Graph[i];
				Arrays.sort(temp_array);
				for(int j=0;j<Graph[i].length;++j) {
					stdout.write(" "+Graph[i][j]);
				}
				stdout.write("\n");
				stdout.flush();
			}
			stdout.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.Graph=Graph;
		return Graph;
	}
	


}
