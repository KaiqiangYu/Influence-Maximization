package util;
/**
 * @author: Yui
 * @date: 19/10/2019
 * @thanks: DMAL NTU & C401 SIST & Inori Minase
 * @description: Generate graph based on the different datasets
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenerateGraph {
	
	/** URL of the source file */
	private String sourceFile=null;
	/** URL of the target File*/
	private String targetFile=null;
	/** data structure used to save the adjacent list*/
	private List<Integer>[] list=null;
	
	private String split="	";
	
	int arr[]=new int[40000000];
	/**
	 * 
	 * @param sourceFile URL of the source file
	 * @param targetFile URL of the target file
	 */
	public GenerateGraph(String sourceFile, String targetFile) {
		this.sourceFile=sourceFile;
		this.targetFile=targetFile;
	}
	
	/**
	 * Generate the adjacent list of the given graph
	 * @return whether this operation is successful.
	 */
	@SuppressWarnings("unchecked")
	
	public void index() {
		Arrays.fill(arr, -1);
		try {
			BufferedReader stdin=new BufferedReader(new FileReader(sourceFile));
			String line=stdin.readLine();
			String s[]=line.split(split);
			
			while((line=stdin.readLine())!=null) {
				s=line.split(split);
				int temp1=Integer.parseInt(s[0]);
				int temp2=Integer.parseInt(s[1]);
				arr[temp1]=0;
				arr[temp2]=0;
				
			}
			int count=0;
			for(int i=0;i<arr.length;++i) {
				if(arr[i]==0) {
					arr[i]=count;
					count++;
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean Generate() {
		try {
			BufferedReader stdin=new BufferedReader(new FileReader(sourceFile));
			String line=stdin.readLine();
			String s[]=line.split(split);
			int graph_size=Integer.parseInt(s[0]);
			System.out.println(graph_size);
			list=new List[graph_size];
			
			
			
			for(int i=0;i<graph_size;++i)
				list[i]=new ArrayList<Integer>();
			int count=0;
			while((line=stdin.readLine())!=null) {
				s=line.split(split);
				int temp1=Integer.parseInt(s[0]);
				int temp2=Integer.parseInt(s[1]);
				
				temp1=arr[temp1];temp2=arr[temp2];
				
				
				if(!list[temp1].contains(temp2)&&temp1!=temp2) {
					count++;
					list[temp1].add(temp2);
					//list[temp2].add(temp1);
				}
	
				
			}
			System.out.println(count);
			stdin.close();
			
			BufferedWriter stdout =new BufferedWriter(new FileWriter(targetFile,false));
			stdout.write(graph_size+"\n");
			stdout.flush();
			for(int i=0;i<graph_size;++i) {
				stdout.write(i+"");
				for(int j=0;j<list[i].size();++j) {
					stdout.write(" "+list[i].get(j));
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
		return true;
	}
	
	public static void main(String[] args) {
		//GenerateAdj adj=new GenerateAdj("./p2p.txt","./datasets/p2p.txt");
		GenerateGraph adj=new GenerateGraph("./datasets/p2p-Gnutella08.txt","./datasets/p2p08.txt");
		adj.index();
		adj.Generate();
	}
}
