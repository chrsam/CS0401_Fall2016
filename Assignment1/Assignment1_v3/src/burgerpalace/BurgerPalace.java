package burgerpalace;

import java.util.Scanner;

public class BurgerPalace {

    // form of user input
    private final Scanner keyboard = new Scanner(System.in);

    // customer choices
    private int mainMenuCustomerSelection;
    private int sandwichSelection;
    private int beverageTypeSelection;
    private int beverageSizeSelection;

    // types and prices of the meals
    private final int STANDARD_MEAL_ORDER = 1;
    private final int SUPER_SIZE_MEAL_ORDER = 2;
    private final int KIDS_MEAL_ORDER = 3;
    private final int CUSTOM_ORDER = 4;
    private final double KIDS_MEAL_PRICE = 6.99;
    private final double STANDARD_MEAL_PRICE = 9.99;
    private final double SUPER_SIZE_MEAL_PRICE = 11.00;

    // types and prices of sandwiches
    private final int SINGLE_STACKED_HAMBURGER = 1;
    private final int DOUBLE_STACKED_HAMBURGER = 2;
    private final int TRIPLE_STACKED_HAMBURGER = 3;
    private final double SINGLE_STACKED_HAMBURGER_PRICE = 3.99;
    private final double DOUBLE_STACKED_HAMBURGER_PRICE = 5.99;
    private final double TRIPLE_STACKED_HAMBURGER_PRICE = 7.99;

    // types of beverages
    private final int COCA_COLA_BEVERAGE = 1;
    private final int SPRITE_BEVERAGE = 2;
    private final int ICED_TEA_BEVERAGE = 3;
    private final int NO_BEVERAGE = 0;

    // fries prices
    private final double SMALL_FRIES_PRICE  = 1.99;
    private final double MEDIUM_FRIES_PRICE = 2.49;
    private final double LARGE_FRIES_PRICE  = 2.99;
    
    // beverages prices
    private final double SMALL_BEVERAGE_PRICE  = 1.49;
    private final double MEDIUM_BEVERAGE_PRICE = 1.69;
    private final double LARGE_BEVERAGE_PRICE  = 1.89;

    // item types
    private final int SANDWICH = 1;
    private final int BEVERAGE = 2;
    private final int FRIES = 3;

    // item sizes (for Fries and Beverages)
    private final int SMALL_SIZE = 1;
    private final int MEDIUM_SIZE = 2;
    private final int LARGE_SIZE = 3;

    private boolean readyToCheckout = false;
    private final int NO_SELECTION = 0;
    private double invoiceTotal;

    // Order Changes Related Variables
    private int orderChangeRequest;
    private final int ORDER_CHANGE_REQUESTED = 1;

    // variables containing the actual types and values during run-time
    // based on customer selections
    private String mealType;
    private double mealPrice;
    private String sandwichType;
    private double sandwichPrice;
    private String friesSize;
    private double friesPrice;
    private String beverageSize;
    private String beverageType;
    private double beveragePrice;
    
    // flags for ordering the items
    private boolean sandwichHasBeenOrdered = false;
    private boolean friesHasBeenOrdered = false;
    private boolean beverageHasBeenOrdered = false;
    private boolean mealHasBeenOrdered = false;

    // promotion code related variables
    private final String PROMOTION_CODE = "FATHERS_DAY";
    private boolean validPromotionCode;
    private final double mealOrderDiscount = 0.15;
    
    // exiting menus
    private final int EXIT = 5;
    private final int RETURN = 4;
    
    public BurgerPalace() {
        runProcess();
    }

    private void runProcess() {
        while (!readyToCheckout) {
            displayBanner();
            displayMainMenu();

            // process customer choice
            if ((mainMenuCustomerSelection == STANDARD_MEAL_ORDER)
                    || (mainMenuCustomerSelection == SUPER_SIZE_MEAL_ORDER)
                    || (mainMenuCustomerSelection == KIDS_MEAL_ORDER)) {
                mealHasBeenOrdered = true;
                getBeverageType();
                setSelectionsBasedOnCustomerOrder();
            } else if (mainMenuCustomerSelection == CUSTOM_ORDER) {
                mealHasBeenOrdered = false;
                displayItemizedMenu();
                checkIfCustomOrderIsAnOfferedMeal();
            } else {
                System.out.println("Order has been canceled, Thanks for stopping by.");
                System.exit(0);
            }

            checkIfCustomerHasPromotionCode();
            displayOrderAndConfirmation();
            
            confirmOrderBeforeProceedToCheckout();
            // rediplay the main menu to change order
            if (orderChangeRequest == ORDER_CHANGE_REQUESTED) {
                continue;
            } else {
                readyToCheckout = true;
            }

            completeTransaction();
        }
    }

    private void displayBanner() {
        System.out.println("************************************");
        System.out.println("     Welcome to Burger Palace!      ");
        System.out.println("************************************\n");
    }

    private void displayMainMenu() {
        // display meal menu
        System.out.println("Main Menu:");
        System.out.println("1 - Standard Meal:    Double-Stacked Hamburger, "
                + "Medium Beverage, and Medium Fries");
        System.out.println("2 - Super Sized Meal: Triple-Stacked Hamburger, "
                + "Large Beverage, and Large Fries");
        System.out.println("3 - Kids Meal:        Simple Hamburger, Small Beverage, "
                + "and Small Fries");
        System.out.println("4 - Customer Other:   Select each item individually");
        System.out.println("5 - Cancel Order");

        // request customer selection
        System.out.print("\nPlease select the type of meal you want: ");
        mainMenuCustomerSelection = keyboard.nextInt();

        // shown customer selection
        System.out.print("You have selected: ");
        switch (mainMenuCustomerSelection) {
            case STANDARD_MEAL_ORDER:
                System.out.println("Standard Meal that comes with: \n"
                        + "\t* Double-Stacked Hamburger\n"
                        + "\t* Medium Beverage\n"
                        + "\t* Medium Fries");
                break;

            case SUPER_SIZE_MEAL_ORDER:
                System.out.println("Super Sized Meal that comes with: \n"
                        + "\t* Triple-Stacked Hamburger\n"
                        + "\t* Large Beverage\n"
                        + "\t* Large Fries");
                break;

            case KIDS_MEAL_ORDER:
                System.out.println("Kids Meal that comes with: \n"
                        + "\t* Simple Hamburger\n"
                        + "\t* Small Beverage\n"
                        + "\t* Small Fries");
                break;

            case CUSTOM_ORDER:;
                System.out.println("Custom Order. \nProceed with your order");
                break;

            case EXIT:
                System.out.println("Cancel Order");
                break;
        }
    }

    private void setSelectionsBasedOnCustomerOrder() {
        switch (mainMenuCustomerSelection) {
            case STANDARD_MEAL_ORDER:
                mealType = "Standar Meal";
                mealPrice = STANDARD_MEAL_PRICE;
                sandwichType = "Double Stacked Hamburger";
                sandwichPrice = DOUBLE_STACKED_HAMBURGER_PRICE;
                friesSize = "Medium";
                friesPrice = MEDIUM_FRIES_PRICE;
                beverageSize = "Medium";
                beveragePrice = MEDIUM_BEVERAGE_PRICE;
                sandwichHasBeenOrdered = true;
                friesHasBeenOrdered = true;
                beverageHasBeenOrdered = true;
                break;

            case SUPER_SIZE_MEAL_ORDER:
                mealType = "Super Size Meal";
                mealPrice = SUPER_SIZE_MEAL_PRICE;
                sandwichType = "Triple Stacked Hamburger";
                sandwichPrice = TRIPLE_STACKED_HAMBURGER_PRICE;
                friesSize = "Large";
                friesPrice = LARGE_FRIES_PRICE;
                beverageSize = "Large";
                beveragePrice = LARGE_BEVERAGE_PRICE;
                sandwichHasBeenOrdered = true;
                friesHasBeenOrdered = true;
                beverageHasBeenOrdered = true;
                break;

            case KIDS_MEAL_ORDER:
                mealType = "Kids Meal";
                mealPrice = KIDS_MEAL_PRICE;
                sandwichType = "Single Stacked Hamburger";
                sandwichPrice = SINGLE_STACKED_HAMBURGER_PRICE;
                friesSize = "Small";
                friesPrice = SMALL_FRIES_PRICE;
                beverageSize = "Small";
                beveragePrice = SMALL_BEVERAGE_PRICE;
                sandwichHasBeenOrdered = true;
                friesHasBeenOrdered = true;
                beverageHasBeenOrdered = true;
                break;
        }
    }

    private void getBeverageType() {
        // request beverage selection
        System.out.println("\n************************************\n");
        System.out.println("Beverage Menu:");
        System.out.println("1 - Coca-cola");
        System.out.println("2 - Sprite");
        System.out.println("3 - Iced Tea");
        System.out.println("0 - No Beverage");
        System.out.print("\nWhat kind of beverage would you like? ");
        beverageTypeSelection = keyboard.nextInt();

        // display selected beverage
        System.out.print("Your beverage selection: ");
        switch (beverageTypeSelection) {
            case COCA_COLA_BEVERAGE:
                beverageType = "Coca-cola  ";
                System.out.println(beverageType);
                beverageHasBeenOrdered = true;
                break;

            case SPRITE_BEVERAGE:
                beverageType = "Sprite     ";
                System.out.println(beverageType);
                beverageHasBeenOrdered = true;
                break;

            case ICED_TEA_BEVERAGE:
                beverageType = "Iced Tea   ";
                System.out.println(beverageType);
                beverageHasBeenOrdered = true;
                break;

            case NO_BEVERAGE:
                beverageType = "No Beverage";
                System.out.println(beverageType);
                beverageHasBeenOrdered = false;
                break;
        }
    }

    private void displayItemizedMenu() {
        mealType = "Customized Meal";
        mealPrice = 0.00;

        int itemizedCustomerChoice = 0;
        while (itemizedCustomerChoice != RETURN) {
            System.out.println("\n************************************\n");
            System.out.println("Customized Menu:");
            System.out.println("1 - Sandwich");
            System.out.println("2 - Beverage");
            System.out.println("3 - Fries");
            System.out.println("4 - Return");
            System.out.print("\nWhat would you like to add? ");

            itemizedCustomerChoice = keyboard.nextInt();

            if (itemizedCustomerChoice == SANDWICH) {
                displaySandwichOptions();
            } else if (itemizedCustomerChoice == BEVERAGE) {
                displayBeverageOptions();
            } else if (itemizedCustomerChoice == FRIES) {
                displayFrieOptions();
            }
        }
    }

    private void displaySandwichOptions() {
        System.out.println("\n************************************\n");
        System.out.println("Sandwich Menu: ");
        System.out.println("1 - Simple Hamburger");
        System.out.println("2 - Double-Stacked Hamburger");
        System.out.println("3 - Triple-Stacked Hamburger");
        System.out.println("0 - No sandwich");
        System.out.print("\nWhat sandwich would you like? ");
        sandwichSelection = keyboard.nextInt();

        switch (sandwichSelection) {
            case SINGLE_STACKED_HAMBURGER:
                sandwichType = "Single Stacked Hamburger";
                sandwichPrice = SINGLE_STACKED_HAMBURGER_PRICE;
                sandwichHasBeenOrdered = true;
                break;

            case DOUBLE_STACKED_HAMBURGER:
                sandwichType = "Double Stacked Hamburger";
                sandwichPrice = DOUBLE_STACKED_HAMBURGER_PRICE;
                sandwichHasBeenOrdered = true;
                break;

            case TRIPLE_STACKED_HAMBURGER:
                sandwichType = "Triple Stacked Hamburger";
                sandwichPrice = TRIPLE_STACKED_HAMBURGER_PRICE;
                sandwichHasBeenOrdered = true;
                break;

            default:
                sandwichType = "No sandwich";
                sandwichPrice = 0.00;
                sandwichHasBeenOrdered = false;
                break;
        }
    }

    private void displayBeverageOptions() {
        System.out.println("\n************************************\n");
        System.out.println("Beverage Menu: ");
        System.out.println("1 - Coca-cola");
        System.out.println("2 - Sprite");
        System.out.println("3 - Tea");
        System.out.println("0 - No beverage");

        System.out.print("\nWhat kind of beverage would you like? ");
        beverageTypeSelection = keyboard.nextInt();

        switch (beverageTypeSelection) {
            case COCA_COLA_BEVERAGE:
                beverageType = "Coca-Cola  ";
                beverageHasBeenOrdered = true;
                break;

            case SPRITE_BEVERAGE:
                beverageType = "Sprite     ";
                beverageHasBeenOrdered = true;
                break;

            case ICED_TEA_BEVERAGE:
                beverageType = "Iced Tea   ";
                beverageHasBeenOrdered = true;
                break;

            default:
                beverageType = "No beverage";
                beverageHasBeenOrdered = true;
                break;
        }

        if (beverageTypeSelection != NO_SELECTION) {
            requestBeverageSize();

            
            switch (beverageSizeSelection) {
                case SMALL_SIZE:
                    beverageSize = "Small";
                    beveragePrice = SMALL_BEVERAGE_PRICE;
                    break;

                case MEDIUM_SIZE:
                    beverageSize = "Medium";
                    beveragePrice = MEDIUM_BEVERAGE_PRICE;
                    break;

                case LARGE_SIZE:
                    beverageSize = "Large";
                    beveragePrice = LARGE_BEVERAGE_PRICE;
                    break;
            }
        }
    }

    private void requestBeverageSize() {
        System.out.println("\n************************************\n");
        System.out.println("Beverage Size Menu: ");
        System.out.println("1 - Small");
        System.out.println("2 - Medium");
        System.out.println("3 - Large");

        System.out.print("\nPlease select the beverage size: ");
        beverageSizeSelection = keyboard.nextInt();
    }

    private void displayFrieOptions() {
        System.out.println("\n************************************\n");
        System.out.println("French Fry Size Menu: ");
        System.out.println("1 - Small");
        System.out.println("2 - Medium");
        System.out.println("3 - Large");

        System.out.print("\nPlease select portion size: ");
        int choice = keyboard.nextInt();
        
        switch (choice) {
            case 1:
                friesSize = "Small";
                friesPrice = SMALL_FRIES_PRICE;
                friesHasBeenOrdered = true;
                break;
            case 2:
                friesSize = "Medium";
                friesPrice = MEDIUM_FRIES_PRICE;
                friesHasBeenOrdered = true;
                break;
            default:
                friesSize = "Large";
                friesPrice = LARGE_FRIES_PRICE;
                friesHasBeenOrdered = true;
                break;
        }
    }
    
    private void checkIfCustomOrderIsAnOfferedMeal() {
        if (beverageHasBeenOrdered && friesHasBeenOrdered && sandwichHasBeenOrdered) {
            if ( sandwichType.equalsIgnoreCase("Single Stacked Hamburger") && 
                 beverageSize.equalsIgnoreCase("Small") && 
                 friesSize.equalsIgnoreCase("Small")) {
                System.out.println("************************************");
                System.out.println("Your order is the same as our Kids meal.");
                System.out.println("The meal discount will be applied to your order");
                mealHasBeenOrdered = true;
            } else if ( sandwichType.equalsIgnoreCase("Double Stacked Hamburger") && 
                         beverageSize.equalsIgnoreCase("Medium") && 
                        friesSize.equalsIgnoreCase("Medium")) {
                System.out.println("************************************");
                System.out.println("Your order is the same as our Standard meal.");
                System.out.println("The meal discount will be applied to your order");
                mealHasBeenOrdered = true;
            } else if ( sandwichType.equalsIgnoreCase("Triple Stacked Hamburger") && 
                 beverageSize.equalsIgnoreCase("Large") && 
                 friesSize.equalsIgnoreCase("Large")) {
                System.out.println("************************************");
                System.out.println("Your order is the same as our Super Sized meal.");
                System.out.println("The meal discount will be applied to your order");
                mealHasBeenOrdered = true;
            }
        }
    }

    private void checkIfCustomerHasPromotionCode() {
        validPromotionCode = false;
        System.out.println("\n************************************\n");
        System.out.println("Does customer have the current promotion code?");
        System.out.println("1 - Yes");
        System.out.println("2 - No");

        System.out.print("\nPlease select one option: ");
        int answer = keyboard.nextInt();

        if (answer == 1) {
            for (int i = 0; i < 2; i++) {
                System.out.print("Enter promotion code: ");
                String userProvidedPromotionCode = keyboard.next();
                if (userProvidedPromotionCode.equalsIgnoreCase(PROMOTION_CODE)) {
                    validPromotionCode = true;
                    break;
                }
            }
        }
    }

    private void displayOrderAndConfirmation() {
        String alignmentSpace;
        System.out.println("\n************************************\n");
        System.out.println("Order Summary: ");
        if (sandwichHasBeenOrdered) {
            alignmentSpace = getAlignmentSpace(sandwichPrice);
            System.out.println("Sandwich: " + sandwichType + "\t$" 
                    + alignmentSpace + sandwichPrice);
        }
        if (friesHasBeenOrdered) {
            alignmentSpace = getAlignmentSpace(friesPrice);
            System.out.println("Fries:    " + friesSize + "\t\t\t$" 
                    + alignmentSpace + friesPrice);
        }
        if (beverageHasBeenOrdered) {
            alignmentSpace = getAlignmentSpace(beveragePrice);
            System.out.println("Beverage: " + beverageSize + " " + beverageType
                + "\t\t$" + alignmentSpace + beveragePrice);
        }
        
        double invoiceSubTotal = sandwichPrice + friesPrice + beveragePrice;
        alignmentSpace = getAlignmentSpace(invoiceSubTotal);
        System.out.println("----------------------------------------------");
        System.out.printf("Subtotal: \t\t\t\t$%s%.2f\n", alignmentSpace, 
                invoiceSubTotal);
        
        // applying discounts
        System.out.println("----------------------------------------------");
        if (mealHasBeenOrdered) {
            double mealDiscount = (sandwichPrice + friesPrice + beveragePrice) 
                    * mealOrderDiscount;
            alignmentSpace = getAlignmentSpace(mealDiscount);
            System.out.printf("Meal Discount: \t\t\t\t$%s%.2f\n", alignmentSpace, 
                mealDiscount);
            invoiceSubTotal -= mealDiscount;
        }

        double promotionalDiscount = 0.0;
        if (validPromotionCode) {
            promotionalDiscount = 0.10 * invoiceSubTotal; // 10 percent
            alignmentSpace = getAlignmentSpace(promotionalDiscount);
            System.out.printf("Promotion Discount: \t\t\t$%s%.2f\n", alignmentSpace,
                    promotionalDiscount);
        }

        double invoiceTaxes = (invoiceSubTotal - promotionalDiscount) * 0.06;
        invoiceTotal = invoiceSubTotal - promotionalDiscount + invoiceTaxes;
        alignmentSpace = getAlignmentSpace(invoiceTaxes);
        System.out.printf("Tax: \t\t\t\t\t$%s%.2f\n", alignmentSpace, 
                invoiceTaxes);
        System.out.println("----------------------------------------------");
        alignmentSpace = getAlignmentSpace(invoiceTotal);
        System.out.printf("Invoice Total: \t\t\t\t$%s%.2f\n", 
                alignmentSpace, invoiceTotal);
    }
    
    private void confirmOrderBeforeProceedToCheckout() {
        System.out.println("\n\n************************************");
        System.out.println("Any changes in this order? ");
        System.out.println("1 - Yes ");
        System.out.println("2 - No, proceed to checkout");
        System.out.print("\nPlease select your choice: ");
        orderChangeRequest = keyboard.nextInt();
    }

    private void completeTransaction() {
        boolean correctAmountOfMoney = false;
        double amountGiven = 0;

        while (!correctAmountOfMoney) {
            System.out.println("\n************************************\n");
            System.out.print("Cash Value provided by the Customer: ");
            amountGiven = keyboard.nextDouble();
            if (amountGiven < invoiceTotal) {
                System.out.println("Not enough money, please review total price");
            } else {
                correctAmountOfMoney = true;
            }
        }
        double change = amountGiven - invoiceTotal;

        System.out.printf("Total:  \t\t$%.2f\n", invoiceTotal);
        System.out.printf("Paid:   \t\t$%.2f\n", amountGiven);
        System.out.printf("Change: \t\t$%.2f\n", change);

        System.out.println("\nTransaction Completed - THANK YOU!");
    }

    public static void main(String[] args) {
        new BurgerPalace();
    }

    private String getAlignmentSpace(double price) {
        if (price < 10.00) {
            return " ";
        } else {
            return "";
        }
    }
}
