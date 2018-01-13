package ui;

import java.util.ArrayList;

import business.StatusDB;
import business.Status;
import util.Console;

public class StatusApp {

	public static void main(String[] args) {
		System.out.println("Welcome to the Status App");
		String choice = "y";
		while (!choice.equalsIgnoreCase("exit")) {
			System.out.println("Options");
			System.out.println("all - Get all status");
			System.out.println("get - Get a single status by id");
			System.out.println("exit - exit app");
			choice = Console.getString("Option?:  ");
			if (choice.equalsIgnoreCase("all"))
				getAllStatus();
			else if (choice.equalsIgnoreCase("get"))
				getStatus();
		}
		
		System.out.println("Bye!");
	}
	
	private static void getStatus() {
		//get input from user
		int statusID = Console.getInt("Enter status id:  ");
		//do logic - call the DB to get an instance of Status
		StatusDB sdb = new StatusDB();
		Status s = sdb.getStatus(statusID);
		//print results
		System.out.println(s);
		System.out.println("\n");
	}
	
	private static void getAllStatus() {
		 
		StatusDB sdb = new StatusDB();
		ArrayList<Status> list = sdb.getAll();
		if (list!=null && list.size()>0) {
			for (Status s: list) {
				System.out.println(s);
				System.out.println("\n");
			}
		}else {
			System.out.println("No data returned.");
			System.out.println("\n");
		}
	}

}
