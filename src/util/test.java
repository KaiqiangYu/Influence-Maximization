package util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class test {
	
	public static void main(String[] args) {
		ArrayList<Integer> a=new ArrayList<Integer>();
		a.add(2);
		a.add(3);
		a.add(2);
		a.add(3);
		a.add(2);
		int count=0;
		for(int i=0;i<a.size();) {
			if(a.get(i)==2) {
				a.remove(i);
			}else {
				i++;
			}
		}
		
		for(int i=0;i<a.size();++i) {
			System.out.println(a.get(i));
		}
		
	}

}
