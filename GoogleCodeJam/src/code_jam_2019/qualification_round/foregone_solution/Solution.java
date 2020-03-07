package code_jam_2019.qualification_round.foregone_solution;

import java.util.*;
import java.io.*;

class Number {
	
	int value;
	int digit;
	boolean[] isFour;
	int firstFourPosition;
	
	public Number(int n) {
		this.value = n;
		this.digit = this.findDigit();
		this.isFour = identifyFours();
		this.firstFourPosition = findFirstFourPosition();
	}
	
    public int findDigit() {
    	
        int n = this.value;
        int digit = 1;
        while (n / 10 >= 1) {
            n = n / 10;
            digit++;
        }
        return digit;
        
    }
    
    public boolean[] identifyFours() {
        int n = this.value;
        boolean[] isFour = new boolean[this.digit];
        
        for (int i = 0; i < this.digit; i++) {
            if (n % 10 == 4) {
                isFour[i] = true;
            } else {
                isFour[i] = false;
            }
            n = n / 10;
        }
        return isFour;
    }
    
    public int findFirstFourPosition() {
        int length = this.isFour.length;
        int position = -1;
        
        for (int i = 0; i < length; i++) {
            if (this.isFour[i] == true) {
                position = i;
                break;
            }
        }
        return position;
    }
	
}



public class Solution {
    
	static int power(int a, int b) {
		int answer = 1;
		for (int i = 0; i < b; i++) {
			answer = answer * a;
		}
		
		return answer;
	}
	
	
    static int balance(Number n) {
        int sum = 0;
        for (int i = 0; i < n.digit; i++) {
        	if (n.isFour[i] == true) {
        		sum += power(10, i);
        	}
        }
        return sum;
    }
    
    static void update(Number n, int dif) {
    	n.value = n.value + dif;
    	n.digit = n.findDigit();
    	n.isFour = n.identifyFours();
    	n.firstFourPosition = n.findFirstFourPosition();
    }
    
    
    static int[] findPair(Number n) {
        
        int digit = n.digit;
        int leftValue, rightValue;
        
        if (n.value % 2 == 0) {
            leftValue = n.value / 2;
            rightValue = n.value / 2;
        
            
        } else {
            leftValue = n.value / 2 + 1;
            rightValue = n.value / 2;
        }
        
        Number left = new Number(leftValue);
        Number right = new Number(rightValue);
        
        
        int update_dif;
        
        
        while (left.firstFourPosition != -1 || right.firstFourPosition != -1) {
            if (left.firstFourPosition != -1) {
                
                update_dif = balance(left);
                update(left, update_dif);
                update(right, - update_dif);
                
            } else {
                
            	update_dif = balance(right);
                update(left, update_dif);
                update(right, - update_dif);
                
            }
        }
        
        int[] answerArray = new int[2];
        answerArray[0] = left.value;
        answerArray[1] = right.value;
        return answerArray;
        
    }
    
    
    public static void main(String[] args) {
        
    	Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int testCase = Integer.parseInt(sc.nextLine());
        int[] targetNumber = new int[testCase];
        
        for (int i = 0; i < testCase; i++) {
            targetNumber[i] = Integer.parseInt(sc.nextLine());
        }
        
        
        int[] answerPair;
        
        for (int i = 0; i < testCase; i++){
        	Number n = new Number(targetNumber[i]);
            answerPair = findPair(n);
            System.out.println("Case #" + (i+1) + ": " + answerPair[0] + " " + answerPair[1]);
        }
        
    }
    
    
}