package com.wumitang.game24;

import java.awt.Frame;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;

import org.junit.Test;

public class Game24Player {
	
	//可能排列结果存储在数组列表中
	private static ArrayList<int[]> arrayList ;
	//操作符
	static char[] ops = { '+', '-', '*', '/' };
	//是否有解
	static boolean flag = false;
	//定义生成的四个随机数
	static int number[] =new int[4];
	
	//两两调换
	private static void dobleNumChange(int[] num, int i,int j) {
		int centre=0;
		centre = num[i];
		num[i] = num[j] ;
		num[j] = centre;
	}
	/*数组N个元素的排序种数
	private static void ArrayNNumChange(int [] nums,int c) {
	}*/
	
	//4个元素的排列组合
	@Test
	public static void pailiezuh(int[] nums) {
		
		int[] num= Arrays.copyOf(nums, nums.length);
		int[] change=Arrays.copyOf(num, num.length);
		int[] ch2;
		for(int i = 0 ; i<4 ; i++) {
			num=Arrays.copyOf(change, change.length);
			dobleNumChange(num, 0, i);
			ch2=Arrays.copyOf(num, num.length);
			for(int j = 1 ;j<4;j++) {
				num=Arrays.copyOf(ch2, ch2.length);
				dobleNumChange(num, 1,j);
				for(int k=2 ; k<4;k++) {
					dobleNumChange(num, 2,k);
					arrayList.add(num);
				}
			}
		}
	}

	//两个数字计算
	private static double getNum(double firstL, double b, char ch) {
	   	switch (ch) {
	   	case '+':
	   		return firstL + b;
	   	case '-':
	   		return firstL - b;
	   	case '*':
	   		return firstL * b;
	   	case '/':
	   		if( b==0 ) break;
	   		return firstL / b;
	   	default :
	   		System.out.println("运算符错误");
	   		return -999;
	   	}
	   	return -999;
   }
	public static void operation (ArrayList<int[]> num) {
		String awstion = null ;
		for(int i=0 ; i< arrayList.size() ; i++) {
			int n[] = arrayList.get(i);
			for(int j = 0 ; j < 4 ; j++) {
				//随机选两个数进行四个符号运算
				double firstL = getNum(n[0],n[1],ops[j]);
				double firstMid = getNum(n[1],n[2],ops[j]);
				double firstR = getNum(n[2],n[3],ops[j]);
				for(int k = 0 ; k< 4  ; k++) {
					if(k==j) {continue ;}
					double secondR = getNum(firstL,n[2],ops[k]);
					double secondMid = getNum(n[0],firstMid,ops[k]);
					double secondL = getNum(n[1],firstR,ops[k]);
					double secondF = getNum(n[2],n[3],ops[k]);
					//第三次计算
					for(int t = 0 ; t<4; t++) {
						if(t==j|| t == k) {
							continue;
						}
						//由以上算法输出表达式
						if((int)(getNum(secondR,n[3],ops[t])*100)==2400) {
							if(k<=1 && t>=2) {
								awstion="("+n[0]+ops[j]+n[1]+ops[k]+n[2]+")"+ops[t]+n[3]+"="+"24";
							}else if(k>=2 && j<=1 ) {
								awstion="("+n[0]+ops[j]+n[1]+")"+ops[k]+n[2]+ops[t]+n[3]+"="+"24";
							}else{
							awstion=""+ n[0]+ops[j]+n[1]+ops[k]+n[2]+ops[t]+n[3]+"="+"24";
								}
							System.out.println(awstion);
							return;
						}
						if((int)(getNum(secondMid,n[3],ops[t])*100)==2400) {
							if(k>=2&&t>=2) {
								awstion="("+n[0]+ops[k]+n[1]+ops[j]+n[2]+")"+ops[t]+n[3]+"="+"24";
							}
							else if(j>=2 && k<=1) {
								awstion=""+n[0]+ops[k]+"("+n[1]+ops[j]+n[2]+")"+ops[t]+n[3]+"="+"24";
							}else {
								awstion=""+ n[0]+ops[k]+n[1]+ops[j]+n[2]+ops[t]+n[3]+"="+"24";
							}
							System.out.println(awstion);
							return;
						}
						if((int)(getNum(n[0],secondL,ops[t])*100)==2400) {
							awstion=""+ n[0]+ops[t]+"("+n[1]+ops[k]+"("+n[2]+ops[j]+n[3]+")"+")"+"="+"24";
							System.out.println(awstion);
							return;
						}
						if((int)(getNum(firstL,secondF,ops[t])*100)==2400) {
							awstion=""+ "("+n[0]+ops[j]+n[1]+")"+ops[t]+"("+n[2]+ops[k]+n[3]+")"+"="+"24";
							System.out.println(awstion);
							return;
						}
					}
				}
			}
			
		}
		 System.out.println("无结果");
	}
	
	public static void main(String[] args) {
		//获取的数字
		int[] a = {3,8,8,10};
		number = Arrays.copyOf(a, a.length);
		//显示随机生成的数字
		System.out.println("生成数字："+Arrays.toString(number));
		arrayList = new ArrayList<int[]>();
		pailiezuh(number);
		System.out.println("24game 结果:");
		operation(arrayList);
		
	}

}
