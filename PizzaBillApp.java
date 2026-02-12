import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class PizzaBillApp {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";

    public static String getPizzaArt(String pizzaName){
        switch(pizzaName){
            case "Veg Pizza":
                return GREEN +
                "    _\n  _|=|_________\n _|_____________|_\n| Veg Pizza 🍅🧀🌶️ |\n'-----------------'\n" + RESET;
            case "Non-Veg Pizza":
                return RED +
                "    _\n  _|=|_________\n _|_____________|_\n| Non-Veg 🍗🧀🌶️ |\n'-----------------'\n" + RESET;
            case "Deluxe Veg Pizza":
                return PURPLE +
                "    _\n  _|=|_________\n _|_____________|_\n| Deluxe Veg 🍄🧀🌶️ |\n'-----------------'\n" + RESET;
            case "Deluxe Non-Veg Pizza":
                return BLUE +
                "    _\n  _|=|_________\n _|_____________|_\n| Deluxe N-V 🍖🧀🌶️ |\n'-----------------'\n" + RESET;
            default:
                return "";
        }
    }

    public static void pizzaCountdown(){
        System.out.print(YELLOW + "\nPreparing your pizza" + RESET);
        for(int i=0;i<3;i++){ try{ Thread.sleep(500); }catch(Exception e){} System.out.print("."); }
        System.out.println(" Done! 🍕\n");
    }

    public static void toppingsAnimation(){
        String[] toppings = {"🍅","🧀","🌶️","🍗"};
        System.out.print("Adding toppings: ");
        for(int i=0;i<8;i++){ try{ Thread.sleep(200); }catch(Exception e){} System.out.print(toppings[i%4]); }
        System.out.println(" ✅");
    }

    public static void fireworks(){
        String[] fire = {"✨","🎆","🌟"};
        System.out.println("\nCelebrating your order!");
        for(int i=0;i<5;i++){
            try{ Thread.sleep(300); }catch(Exception e){} for(String f: fire){ System.out.print(f); } System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int totalBill = 0, loyaltyPoints = 0;
        String billText = "";

        System.out.println(YELLOW + "🍕 Welcome to Ultimate Pizzamania 🍕" + RESET);

        boolean moreOrders = true;
        while(moreOrders){
            System.out.println("\nSelect Pizza Type:");
            System.out.println("1. Veg Pizza (₹300)");
            System.out.println("2. Non-Veg Pizza (₹400)");
            System.out.println("3. Deluxe Veg Pizza (₹550)");
            System.out.println("4. Deluxe Non-Veg Pizza (₹650)");
            System.out.print("Enter your choice (1-4): ");
            int choice = sc.nextInt();

            int price = 0; String pizzaName=""; boolean extraCheese=false, extraToppings=false, takeaway=false;

            switch(choice){
                case 1: price=300; pizzaName="Veg Pizza"; break;
                case 2: price=400; pizzaName="Non-Veg Pizza"; break;
                case 3: price=550; pizzaName="Deluxe Veg Pizza"; extraCheese=true; extraToppings=true; break;
                case 4: price=650; pizzaName="Deluxe Non-Veg Pizza"; extraCheese=true; extraToppings=true; break;
                default: System.out.println(RED+"Invalid choice"+RESET); continue;
            }

            System.out.print("Enter quantity: "); int qty=sc.nextInt();

            if(choice==1||choice==2){
                System.out.print("Add Extra Cheese (y/n)? "); if(sc.next().equalsIgnoreCase("y")) extraCheese=true;
                System.out.print("Add Extra Toppings (y/n)? "); if(sc.next().equalsIgnoreCase("y")) extraToppings=true;
            }
            System.out.print("Take Away (y/n)? "); if(sc.next().equalsIgnoreCase("y")) takeaway=true;

            int cheesePrice=100, toppingsPrice=150, takeawayPrice=20;
            int orderTotal = price*qty;
            if(extraCheese) orderTotal += cheesePrice*qty;
            if(extraToppings) orderTotal += toppingsPrice*qty;
            if(takeaway) orderTotal += takeawayPrice;

            if(qty>=5){ int discount = orderTotal/10; orderTotal-=discount; System.out.println(GREEN+"Bulk order discount applied: -"+discount+RESET); }

            // Combo deal: If 2 or more pizzas, 50₹ off
            if(qty>=2){ orderTotal-=50; System.out.println(GREEN+"Combo deal applied: -50"+RESET); }

            pizzaCountdown(); toppingsAnimation();

            System.out.println(GREEN+"=== Order Bill ==="+RESET);
            System.out.println(getPizzaArt(pizzaName));
            System.out.println(YELLOW+"Pizza: "+RESET+pizzaName);
            System.out.println(YELLOW+"Quantity: "+RESET+qty);
            System.out.println(YELLOW+"Base Price: "+RESET+(price*qty));
            if(extraCheese) System.out.println(YELLOW+"Extra Cheese: "+RESET+(cheesePrice*qty));
            if(extraToppings) System.out.println(YELLOW+"Extra Toppings: "+RESET+(toppingsPrice*qty));
            if(takeaway) System.out.println(YELLOW+"Take Away: "+RESET+takeawayPrice);
            System.out.println(BLUE+"Order Total: "+RESET+orderTotal);
            System.out.println(GREEN+"=================="+RESET);

            billText += "\n--- Order ---\n";
            billText += pizzaName+"\nQuantity: "+qty+"\nBase Price: "+(price*qty)+"\n";
            if(extraCheese) billText+="Extra Cheese: "+(cheesePrice*qty)+"\n";
            if(extraToppings) billText+="Extra Toppings: "+(toppingsPrice*qty)+"\n";
            if(takeaway) billText+="Take Away: "+takeawayPrice+"\n";
            billText+="Order Total: "+orderTotal+"\n--------------------\n";

            totalBill+=orderTotal;
            loyaltyPoints+=totalBill/100;

            System.out.print("\nAdd more pizzas? (y/n): "); if(!sc.next().equalsIgnoreCase("y")) moreOrders=false;
        }

        billText+="\n========= FINAL BILL =========\nTotal Amount Payable: "+totalBill+"\n";
        billText+="Loyalty Points Earned: "+loyaltyPoints+"\n";
        billText+="Thank you for ordering! 🍕 Come Again!\n==============================\n";

        fireworks();
        System.out.println(RED+billText+RESET);

        try{
            FileWriter writer = new FileWriter("PizzaBill_Ultimate.txt");
            writer.write(billText); writer.close();
            System.out.println(GREEN+"Bill saved successfully as PizzaBill_Ultimate.txt ✅"+RESET);
        }catch(IOException e){ System.out.println(RED+"Error saving bill: "+e.getMessage()+RESET); }

        sc.close();
    }
}