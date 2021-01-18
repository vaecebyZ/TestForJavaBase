package main.io.github.vaecebyz.test;


import java.util.ArrayList;

import java.util.Collections;
import java.util.Iterator;

public class test {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String string = "34，22,45,78,101，21，17，78,55,92,41,70，63，190,13";
		
		String[] arrsStrings;
		ArrayList<Integer> arrayList = new ArrayList<Integer>() ;
		
		
		 System.out.println("原始-----------------------------------------");
		 
		String[] arr = string.split(",");
		for (int i = 0; i < arr.length; i++) {
				//System.out.println(arr[i]);
			
				 arrsStrings = arr[i].split("，");
				
					 
				 for (int j = 0; j < arrsStrings.length; j++) {
					 	System.out.println(arrsStrings[j]);
						arrayList.add(Integer.valueOf(arrsStrings[j]));
					}
				 	 
			
		}
		
		
		
		System.out.println("冒泡排序实现-------------------------------------------");
		//冒泡
		ArrayList<Integer> bubIntegers = new ArrayList<Integer>();
		bubIntegers = arrayList;
		
		   for (int i = 0; i < bubIntegers.size() - 1; i++) {  
	            for (int j = 1; j < bubIntegers.size() - i; j++) {  
	                Integer a = bubIntegers.get(j - 1);  
	                Integer b = bubIntegers.get(j);  
	  
	                if (a.compareTo(b) > 0) { // 比较两个整数的大小  
	  
	                    Integer temp = bubIntegers.get(j - 1);  
	                    bubIntegers.set((j - 1), bubIntegers.get(j));  
	                    bubIntegers.set(j, temp);  
	                }  
	            }  
	        }  
		

		
		for (Integer s : bubIntegers) {
			System.out.println(s);
		}
		
		
		
		
		System.out.println("升序函数实现-------------------------------------------");
		
		//升序
		ArrayList<Integer> ascIntegers = new ArrayList<Integer>();
		ascIntegers = arrayList;
		Collections.sort(ascIntegers);
		for (Integer integer : ascIntegers) {
			System.out.println(integer);
		}
		
//		System.out.println("降序-------------------------------------------");
//		
//		//降序
//		ArrayList<Integer> descIntegers = new ArrayList<Integer>();
//		descIntegers = arrayList;
//		Collections.sort(descIntegers);
//		Collections.reverse(descIntegers);
//		for (Integer integer : descIntegers) {
//			System.out.println(integer);
//		}
//		
		
	}

}
