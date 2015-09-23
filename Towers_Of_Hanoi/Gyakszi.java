/*
 * Mariann Szabo-Freund
 * 
 * ITCS-2215
 * Assignment #1
 * 
 * 7/17/2014
 * 
 */


import java.util.Scanner;
import java.util.Stack;

public class Gyakszi {

	//Creating 3 stacks to represent the three pegs
	static Stack<Integer> A = new Stack<Integer>();
	static Stack<Integer> B = new Stack<Integer>();
	static Stack<Integer> C = new Stack<Integer>();

	public static void main(String[] args) {

		int n; //number of disks specified by the user
		@SuppressWarnings("resource")
		//Creating  a Scanner object for user input
		Scanner keyboard = new Scanner(System.in);

		do{
			
			System.out.println("Enter the number of disks (between 2-7): ");
			n= keyboard.nextInt();

			if ((n<2)||(n>7)){
				System.out.println("Please enter a value between 2 and 7!");
			}

		}while((n<2)||(n>7));

		//Adding elements to the first stack
		for(int i = n; i> 0; i--){

			A.push((i));
		}

		System.out.println("Peg A " + A.toString());
		System.out.println("Peg B " + B.toString());
		System.out.println("Peg C " + C.toString());
		System.out.println("\n");

		//Calling the recursion method and passing args.
		Recursion(n, A, C, B);
	}


	//Recursive method
	public static void Recursion(int n, Stack<Integer> a, Stack<Integer> c, Stack<Integer> b){

		if(n==1){

			c.push(a.pop());
			System.out.println("Peg A " + A.toString());
			System.out.println("Peg B " + B.toString());
			System.out.println("Peg C " + C.toString());
			System.out.println("\n");
		}

		else {

			Recursion(n-1,a,b,c);

			c.push(a.pop());
			System.out.println("Peg A " + A.toString());
			System.out.println("Peg B " + B.toString());
			System.out.println("Peg C " + C.toString());
			System.out.println("\n");

			Recursion(n-1, b, c, a);
		}

	}

}





