
package hw1;
import java.util.Scanner; 
/**
 *
 * @author chrisman
 */
public class HW1 {
    
    Scanner input = new Scanner(System.in);
    int choiceOpt;
    private final int NO_BEV = 0;
    private final int CO_CO = 1;
    private final int SPRITE = 2;
    private final int ICED_TEA = 3;
    int indexBasic;
    boolean pass = false;
    boolean checkOut = false;
    String[] sandwich = {
        "Double-Stacked Hamburger ", "Triple-Stacked Hamburger ", "Simple Hamburger "
    };
    String[] beverage = {
        "Medium Beverage ", "Large Beverage ", "Small Beverage "
    };
    String[] fries = {
        "Medium Fries ", "Large Fries ", "Small Fries "
    };
    String[] bevChoice = {
        "No Beverage ", "Coca-Cola ", "Sprite ", "Iced Tea " 
    };
    double[] pMeal = {
        
        8.64, 10.94, 6.35   //standard meal, super-sized meal, kids meal (meals)
    };
    double[] pSand = {
        
        5.99, 7.99,  3.99   //double-stacked burger, triple-stacked burger, simple burger (sandwiches)
    };
    
    double[] pFries = {
        
        2.49, 2.99, 1.99,   //medium, large, small (fries)
    };
    double[] pBev = {
        
        1.69, 1.89, 1.49    //medium, large, small (beverages)
    };
    public static void main(String[] args) {
        new HW1();
        
    }
    
    public HW1(){
        //while (checkOut = false) {
        System.out.println("****************************************");
        System.out.println("\tWelcome to Burger Palace!");
        System.out.println("****************************************");
        System.out.println("");
        System.out.println("Main Menu: ");
        System.out.println("1 - Standard Meal: " + sandwich[0] + ", "
                + beverage[0] + ", " + "and " + fries[0]);
        System.out.println("2 - Super Sized Meal: " + sandwich[1] + ", "
                + beverage[1] + ", " + "and " + fries[1]);
        System.out.println("3 - Kids Meal: " + sandwich[2] + ", "
                + beverage[2] + ", " + "and " + fries[2]);
        System.out.println("4 - Customer Other: Select each item individually");
        System.out.println("5 - Cancel Order");
        System.out.println(""); 
        System.out.print("Please select the type of meal you want: ");
        int choiceOpt;
        choiceOpt = input.nextInt();
        conFirm(choiceOpt);  
        bevOpt();
        promo();
        ordSum();
        //}
    }
    public void conFirm(int choiceOpt) {
        if (choiceOpt == 1) {
            System.out.print("You have selected: Standard Meal that comes with:\n");
            System.out.println("\t* Double-Stacked Hamburger");
            System.out.println("\t* Medium Beverage");
            System.out.println("\t* Medium Fries");
            indexBasic = 0;
            
        }
        if (choiceOpt == 2) {
            System.out.print("You have selected: Super Sized Meal that comes with:\n");
            System.out.println("\t* Triple-Stacked Hamburger");
            System.out.println("\t* Large Beverage");
            System.out.println("\t* Large Fries");
            indexBasic = 1;
        }
        if (choiceOpt == 3) {
            System.out.print("You have selected: Kids Meal that comes with:\n");
            System.out.println("\t* Simple Hamburger");
            System.out.println("\t* Small Beverage");
            System.out.println("\t* Small Fries");
            indexBasic = 2;
        }
        if (choiceOpt == 4) {
            System.out.print("You have selected: Customer Order:");
            System.out.print("Proceed with your order");
            customOpt();
            
        }
        if (choiceOpt == 5) {
            System.out.print("You have selected: Cancel Order");
            System.exit(0);
        }
        
    }
    public void customOpt() {
        
        System.out.println("");
        System.out.println("************************************");
        System.out.println("");
        System.out.println("Customized Menu:");
        System.out.println("");
        System.out.println("1 - Sandwhich");
        System.out.println("2 - Beverage");
        System.out.println("3 - Fries");
        System.out.println("4 - Beverage");
        System.out.println("");
        System.out.print("What would you like to add?");
        int plan = input.nextInt(); 
        
        if (plan == 1) {
            System.out.println("************************************");
            System.out.println("");
            System.out.println("Sandwich Menu: ");
            System.out.println("1 - Simple Hamburger");
            System.out.println("2 - Double-Stacked Hamburger");
            System.out.println("3 - Triple-Stacked Hamburger");
            System.out.println("0 - No sandwich");
            System.out.println("");
            System.out.println("What sandwich would you like?");
            int pick1 = input.nextInt();
        } else if (plan == 2){
            System.out.println("************************************");
            System.out.println("");
            System.out.println("Beverage Menu: ");
            System.out.println("");
            System.out.println("1 - Coca-cola ");
            System.out.println("2 - Sprite ");
            System.out.println("3 - Tea ");
            System.out.println("0 - No beverage");
            System.out.println("");
            System.out.println("What kind of beverage would you like?");
            int pick2 = input.nextInt();
            input.nextLine(); 
            System.out.println("");
            System.out.println("************************************");
            System.out.println("");
            System.out.println("Beverage Size Menu: ");
            System.out.println("");
            System.out.println("1 - Small");
            System.out.println("2 - Medium");
            System.out.println("3 - Large");
            System.out.println("");
            System.out.println("Please select the beverage size: ");
        } else if (plan == 3){
            System.out.println("************************************");
            System.out.println("");
            System.out.println("French Fry Size Menu: ");
            System.out.println("");
            System.out.println("1 - Small");
            System.out.println("2 - Medium");
            System.out.println("3 - Large");
            System.out.println("");
            System.out.println("Please select portion size: ");
            System.out.println("");
        }
        
    }
    
    public void bevOpt() {
        
        System.out.println("");
        System.out.println("************************************");
        System.out.println("");
        System.out.println("Beverage Menu:");
        System.out.println("1 = " + bevChoice[1]);
        System.out.println("2 = " + bevChoice[2]);
        System.out.println("3 = " + bevChoice[3]);
        System.out.println("0 = " + bevChoice[0]);
        System.out.println("");
        System.out.print("What kind of beverage would you like? ");
        
        boolean valid;
        int choice = input.nextInt();
        
        switch(choice) {
            case NO_BEV: System.out.println("Your beverage selection: " + bevChoice[0]);
                    break;
                    
            case CO_CO: System.out.println("Your beverage selection: " + bevChoice[1]);
                    break;
                    
            case SPRITE: System.out.println("Your beverage selection: " + bevChoice[2]);
                    break;
                    
            case ICED_TEA: System.out.println("Your beverage selection: " + bevChoice[3]);
                    break;
            
            default: System.out.println("Your input was invalid!");
                    valid = false;
        }
        
    }
    
    public void promo() {
        int tries = 0;
        String code;
        System.out.println("");
        System.out.println("************************************");
        System.out.println("");
        System.out.print("Does customer have the current promotion code? ");
        System.out.println("");
        System.out.println("1 - Yes");
        System.out.println("2 - No"); 
        System.out.println("");
        System.out.print("Please select one option: ");
        int answer = input.nextInt();
        input.nextLine();
        if (answer == 1) {
            while(tries < 2){
                System.out.print("Enter promotion code: ");
                code = input.nextLine();
                if (code.equals("FATHERS_DAY")) {
                    pass = true;
                    tries = 2; 
                }
                else {
                   tries++; 
                }
            }
        }
    }
    
    public void ordSum() {
        
        double subtotal = pSand[indexBasic] + pFries[indexBasic] + pBev[indexBasic];
        double cash = 20.00;
        double mealDis;
        if(choiceOpt <= 3) {
           mealDis = subtotal * .15; 
        } else {
           mealDis = 0.00;
        }
        double proDis;
        if(pass != true) {
            proDis = 0.00;   
        } else {
            proDis = subtotal * .08;
        }
        double tax = subtotal * .06;
        double total = subtotal + mealDis + proDis + tax;
        double spare = cash - total;
        System.out.println("");
        System.out.println("************************************");
        System.out.println("");
        System.out.println("Order Summary:");
        
        System.out.printf("Sandwich: %30s %4s", sandwich[indexBasic], "$"); 
        System.out.format("%5.2f%n", pSand[indexBasic]);
        
        System.out.printf("Fries: %20s %17s", fries[indexBasic], "$"); 
        System.out.format("%5.2f%n", pFries[indexBasic]);
        
        System.out.printf("Beverage: %20s %14s", beverage[indexBasic], "$"); 
        System.out.format("%5.2f%n", pBev[indexBasic]);
         
        System.out.println("---------------------------------------------------");
        System.out.printf("Subtotal: %34s %1.2f%n", "$", subtotal);
        System.out.println("---------------------------------------------------");
        System.out.printf("Meal Discount: %30s %1.2f%n", "$", mealDis);
        System.out.printf("Promotion Discount: %25s %1.2f%n", "$", proDis);
        System.out.printf("Tax: %40s %4.2f%n", "$", tax);
        System.out.println("---------------------------------------------------");
        System.out.printf("Invoice Total: %29s %2.2f%n", "$", total);
        
        System.out.println("");
        System.out.println("************************************");
        System.out.println("Any changes to this order?");
        System.out.println("1 - Yes");
        System.out.println("2 - No, proceed to checkout");
        System.out.println("");
        System.out.print("Please select your choice: ");
        int change = input.nextInt(); 
        input.nextLine();
        //if(change == 2) {
            //checkOut = true; 
        //}
        System.out.println("************************************");
        System.out.println("Cash Value provided by the Customer: " + cash);
        System.out.printf("Total: %10s %1.2f%n", "$", total);
        System.out.printf("Paid: %11s %1.2f%n", "$", cash);
        System.out.printf("Change: %9s %5.2f%n", "$", spare);
        System.out.println("");
        System.out.println("Transaction Completed - THANK YOU!");
    } 
} 
    
    
