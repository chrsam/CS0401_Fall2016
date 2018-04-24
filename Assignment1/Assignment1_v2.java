/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package hw1;
import java.util.Scanner; 
/**
 *
 * @author chrisman
 */
public class HW1 {
    
	public static Scanner input = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         
        //Scanner input = new Scanner(System.in);
        
        System.out.println("Welcome to Burger Palace!"); 
        System.out.println("");
        System.out.println("Main Menu: ");
        System.out.println("1 - Standard Meal: Double-Stacked Hamburger, "
                + "Medium Beverage, and Medium Fries");
        System.out.println("2 - Super Sized Meal: Triple-Stacked Hamburger, Large "
                + "Beverage, and Large Fries");
        System.out.println("3 - Kids Meal: Simple Hamburger, Small Beverage, "
                + "and Small Fries");
        System.out.println("4 - Customer Other: Select each item individually");
        System.out.println("5 - Cancel Order");
        System.out.println(""); 
        System.out.print("Please make a selection from the above menu: ");
        
		// Always use try catch when dealing with I/O
		try {
			int choiceOpt = input.nextInt();
			conFirm(choiceOpt);
		}
		catch (Exception ex) {
			System.out.println(ex);
		}
		 
		
		//always close scanner when you're done
		input.close();
    }
    public static void conFirm(int choiceOpt) {
        if (choiceOpt == 1) {
            System.out.print("You have selected: Standard Meal that comes with:\n");
            System.out.println("\tDouble-Stacked Hamburger");
            System.out.println("\tMedium Beverage");
            System.out.println("\tMedium Fries");
        }
        if (choiceOpt == 2) {
            System.out.print("You have selected: Super Sized Meal that comes with:\n");
            System.out.println("\tTriple-Stacked Hamburger");
            System.out.println("\tLarge Beverage");
            System.out.println("\tLarge Fries");
        }
        if (choiceOpt == 3) {
            System.out.print("You have selected: Kids Meal that comes with:\n");
            System.out.println("\tSimple Hamburger");
            System.out.println("\tSmall Beverage");
            System.out.println("\tSmall Fries");
        }
        if (choiceOpt == 4) {
            System.out.print("You have selected: Customer Other:");
            
        }
        if (choiceOpt == 5) {
            System.out.print("You have selected: Cancel Order:");
        }
        bevOpt();     
    }
    
    public static void bevOpt() {
        System.out.println("Beverage Menu:");
        System.out.println("1 = Coca-cola");
        System.out.println("2 = Sprite");
        System.out.println("3 = Iced Tea");
        System.out.println("0 = No Beverage");
        System.out.println("");
        System.out.println("What kind of beverage would you like?");
		
		boolean valid;
        int choice = input.nextInt();
		
        switch(choice) {
            case 0: System.out.println("Your beverage selection: No Beverage");
                    break;
                    
            case 1: System.out.println("Your beverage selection: Coca-cola");
                    break;
                    
            case 2: System.out.println("Your beverage selection: Sprite");
                    break;
                    
            case 3: System.out.println("Your beverage selection: Iced Tea");
                    break;
            
            default: System.out.println("Your input was invalid!");
                    valid = false;
        }
    }
    promo();
    
    public static void promo() {
        int tries = 0;
        String code; 
        System.out.println("Do you have a promotion code? ");
        System.out.println("");
        System.out.println("1 - Yes");
        System.out.println("2 - No"); 
        int answer = input.nextInt();
        if (answer == 1) {
            while(tries < 2);
                System.out.println("Please enter code:");
                code = input.nextLine();
                if (code == "FATHERS_DAY") {
                    System.out.println("Valid Code: 15% Discount Applied");
                    tries = 2; 
                }
                else {
                   System.out.println("Incorrect code. Try again.");
                   tries++; 
                }
        }
            
    }
    
}
