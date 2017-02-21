package com.cms.demo;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import com.cms.demo.UserModel;

public class UserManagement {
	
	static Set<UserModel> userList = new HashSet<UserModel>();	
	static Scanner scan = new Scanner(System.in);
	public static void createUser(){ 
		
		try{
		   UserModel user = new UserModel();
		   
	       System.out.println("Enter User Name");
	       String userName = scan.next();
	       user.setName(userName);
	       
	       System.out.println("Enter Salary");
	       Double salary = scan.nextDouble();
	       user.setSalary(salary);
	       
	       System.out.println("Enter Address");
	       String address = scan.next();
	       user.setAddress(address);
	       
	       System.out.println("Enter Company Name");
	       String company = scan.next();
	       user.setCompany(company);
	       
	       userList.add(user);    
	       
	       System.out.println("User "+user.getName()+" Added Successfully !!!! ");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
    public static void deleteUser(){
    	
    	System.out.println("Enter User Name to Remove : ");
	    String userName = scan.next();
    	Iterator<UserModel> itr = userList.iterator();  
    	while (itr.hasNext())
    	{
    	    UserModel um = itr.next();
    	    if (um.getName().equals(userName)) {
    	        itr.remove();
    	        System.out.println("User "+um.getName()+" Removed Successfully !!!! ");
    	    }
    	}
    	
	}
  public static void showalluser(){
    	
	  System.out.println("==========  USER UDETAIL ====================== ");
	  System.out.println("Name                  Salary               Adress             Company ");
	  
	  
	  Iterator<UserModel> itr=userList.iterator();  
      while(itr.hasNext()){ 
   	   UserModel um = itr.next();
   	   System.out.println(um.getName()+"          "+um.getSalary() +"           "+um.getAddress()+"         " +um.getCompany());
   	System.out.println("================================================== ");
      }  

	}
    
    public void updateUser(){
		System.out.println("update");
	}
	
	public static void operation(String option){
		switch(option) {
	    case "1" :
	    	createUser();  
	    	break;
	    case "2" :
	    	deleteUser();
	        break;
	    case "3" :
	    	showalluser();
	    	break;
	    case "search" :
	       System.out.println("Search");
	    default :
	       System.out.println("Invalid ");
	  }
		System.out.println("Please Enter option as below : ");	
		System.out.println("1 >> Create User");
		System.out.println("2 >> Delete User");
		System.out.println("3 >> Show All User");
	    option = scan.next();
		operation(option);
	}
    
	
	 /***************** SIMPLE LOGICS ****************************/
	
	
	 public static  int getReverseInt(int value) {
		    int resultNumber = 0;
		    for(int i = value; i !=0; i /= 10) {
		        resultNumber = resultNumber * 10 + i % 10;
		    }
		    return resultNumber;        
	}
	
	 public static void piramid() {
		        for (int i = 0; i < 5; i++) {
		            for (int j = 0; j < 5; j++) {
		                if (j <= i) {
		                    System.out.print("  *");
		                } else {
		                    System.out.print("   ");
		                }
		            }
		            System.out.println();
		        }
	 }
	 
	 public static void paskaltriangle()
	 {
		    int r, i, k, number=1, j;
			Scanner scan = new Scanner(System.in);
			
			System.out.print("Enter Number of Rows : ");
			r = scan.nextInt();
			
			for(i=0;i<r;i++)
			{
				for(k=r; k>i; k--)
				{
					System.out.print(" ");
				}
	            number = 1;
				for(j=0;j<=i;j++)
				{
					 System.out.print(number+ " ");
	                 number = number * (i - j) / (j + 1);
				}
				System.out.println();
			}
		}
	 
	 public static void checkvovelornot(){
		    char ch;
	        Scanner scan = new Scanner(System.in);
			
	        System.out.print("Enter an Alphabet : ");
	        ch = scan.next().charAt(0);
			
	        if(ch=='a' || ch=='A' || ch=='e' || ch=='E' ||
	        ch=='i' || ch=='I' || ch=='o' || ch=='O' ||
	        ch=='u' || ch=='U')
	        {
	            System.out.print("This is a Vowel");
	        }
	        else
	        {
	            System.out.print("This is not a Vowel");
	        }
	 }
	 
	 
	 public static void reverceequaloriginal(){
		  int num, orig, rev=0, rem;
	       Scanner scan = new Scanner(System.in);
		   
	       System.out.print("Enter a Number : ");
	       num = scan.nextInt();
		   
	       orig=num;
		   
	       while(num>0)
	       {
	           rem = num%10;
	           rev = rev*10 + rem;
	           num = num/10;
	       }
	       if(orig == rev)
	       {
	           System.out.print("Reverse is Equal to Original");
	       }
	       else
	       {
	           System.out.print("Reverse is not Equal to Original");
	       }
	 }
	 
	 
	 
	 public static void adddigitofnumber(){
		  int num, rem=0, sum=0, temp;
	        Scanner scan = new Scanner(System.in);
	        System.out.print("Enter a Number : ");
	        num = scan.nextInt();
			
	        temp = num;
			
	        while(num>0)
	        {
	            rem = num%10;
	            sum = sum+rem;
	            num = num/10;
	        }
			
	        System.out.print("Sum of Digits of " +temp+ " is " +sum);        
	    }
	 
	 public static void printtableofnumber(){
		   int num, i, tab;
	        Scanner scan = new Scanner(System.in);
			
	        System.out.print("Enter a Number : ");
	        num = scan.nextInt();
			
	        System.out.print("Table of " + num + " is\n");
	        for(i=1; i<=10; i++)
	        {
	            tab = num*i;
	            System.out.print(num + " * " + i + " = " + tab + "\n");
	        }
	 }
	 
	 
public static void addnnumbers(){
	  int i, n, sum=0, num;
      Scanner scan = new Scanner(System.in);
		
      System.out.print("How many Number You want to Enter to Add them ? ");
      n = scan.nextInt();
		
      System.out.print("Enter " +n+ " numbers : ");
      for(i=0; i<n; i++)
      {
          num = scan.nextInt();
          sum = sum + num;
      }
		
      System.out.print("Sum of all " +n+ " numbers is " +sum);
  }


public static void COUNTPOSITIVENEGATIVEOS(){
	 int countp=0, countn=0, countz=0, i;
     int arr[] = new int[10];
     Scanner scan = new Scanner(System.in);
		
     System.out.print("Enter 10 Numbers : ");
     for(i=0; i<10; i++)
     {
         arr[i] = scan.nextInt();
     }
     for(i=0; i<10; i++)
     {
         if(arr[i] < 0)
         {
             countn++;
         }
         else if(arr[i] == 0)
         {
             countz++;
         }
         else
         {
             countp++;
         }
     }
		
     System.out.print(countp + " Positive Numbers");
     System.out.print("\n" + countn + " Negative Numbers");
     System.out.print("\n" + countz + " Zero");
}


public static void printASCIIvalues(){
	   String ch;
       int i;
       for(i=1; i<=255; i++)
       {
           ch = new Character((char)i).toString();
           System.out.print(i+ " -> " + ch + "\t");
       }
}


public static void printarmstrongnumbers(){
	 int num1, num2, i, n, rem, temp, count=0;
     Scanner scan = new Scanner(System.in);
     System.out.print("Enter the Interval :\n");
     System.out.print("Enter Starting Number : ");
     num1 = scan.nextInt();
     System.out.print("Enter Ending Number : ");
     num2 = scan.nextInt();
		
     for(i=num1+1; i<num2; i++)
     {
         temp = i;
         n = 0;
         while(temp != 0)
         {
             rem = temp%10;
             n = n + rem*rem*rem;
             temp = temp/10;
         }
         if(i == n)
         {
             if(count == 0)
             {
                 System.out.print("Armstrong Numbers Between the Given Interval are :\n");
             }
             System.out.print(i + "  ");
             count++;
         }
     }
     if(count == 0)
     {
         System.out.print("Armstrong Number not Found between the Given Interval.");
     }
 }


	 
	public static void main(String [] args){
	paskaltriangle();	

	int input = 89652;	
	int output = getReverseInt(input);
	System.out.println("Reverse Int :" +output);
	
	System.out.println("Pramid printing start :===============");
	piramid();
	System.out.println("Pramid printing end :===============");
	
	System.out.println("Please Enter option as below : ");	
	System.out.println("1 >> Create User");
	System.out.println("2 >> Delete User");
	System.out.println("3 >> Show All User");
	String option = scan.next();
	
	switch(option) {
    case "1" :
    	createUser();  
    	break;
    case "2" :
    	deleteUser();
    	break;
    case "3" :
    	showalluser();
       break;
    case "search" :
       System.out.println("Search");
    default :
       System.out.println("Invalid ");
  }
	
	System.out.println("Please Enter option as below : ");
	System.out.println("1 >> Create User");
	System.out.println("2 >> Delete User");
	System.out.println("2 >> Show All User");
    option = scan.next();
	operation(option);
	}
}
