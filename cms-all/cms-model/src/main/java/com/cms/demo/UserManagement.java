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
	 
	public static void main(String [] args){
		
	int input = 89652;	
	int output = 	getReverseInt(input);
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
