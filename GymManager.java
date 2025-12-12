package members;


import java.util.InputMismatchException;
import java.util.Scanner;

public class GymManager {

 private MemberDAO dao = new MemberDAO(); 
 private Scanner sc = new Scanner(System.in);
 
 public static void main(String[] args) {
     new GymManager().run(); // Start the application
 }

 public void run() {
     sc.useDelimiter(System.lineSeparator()); 

     while (true) {
         try {
             displayMenu();
             int choice = sc.nextInt();
             
             switch (choice) {
                 case 1: promptAndAddMember(); break;
                 case 2: dao.viewMembers(); break;
                 case 3: promptAndUpdatePlan(); break;
                 case 4: promptAndDeleteMember(); break;
                 case 5: 
                     System.out.println("👋 Exiting Gym Manager. Goodbye!");
                     sc.close();
                     return;
                 default: 
                     System.out.println("❌ Invalid option. Please choose 1-5.");
             }
         } catch (InputMismatchException e) {
             System.out.println("❌ Invalid input. Please enter a number for the menu option.");
             sc.next(); // Clear the invalid input
         }
     }
 }
 
 // --- UI Helper Methods ---
 
 private void displayMenu() {
     System.out.println("\n=== 💪 GYM MANAGER ===");
     System.out.println("1. Add New Member");
     System.out.println("2. View All Members");
     System.out.println("3. Update Member Plan");
     System.out.println("4. Delete Member");
     System.out.println("5. Exit");
     System.out.print("Select Option: ");
 }

 private void promptAndAddMember() {
     try {
         System.out.print("Enter Member ID: ");
         int id = sc.nextInt();
         System.out.print("Enter Name: ");
         String name = sc.next();
         System.out.print("Enter Age: ");
         int age = sc.nextInt();
         System.out.print("Enter Plan (Monthly/Yearly): ");
         String plan = sc.next();
         
         // Delegate to the DAO
         dao.addMember(id, name, age, plan);
     } catch (InputMismatchException e) {
         System.out.println("❌ Invalid input for ID or Age.");
         sc.next();
     }
 }
 
 private void promptAndUpdatePlan() {
     try {
         System.out.print("Enter Member ID to Update: ");
         int id = sc.nextInt();
         System.out.print("Enter New Plan: ");
         String newPlan = sc.next();
         
         // Delegate to the DAO
         dao.updatePlan(id, newPlan);
     } catch (InputMismatchException e) {
         System.out.println("❌ Invalid input for Member ID.");
         sc.next();
     }
 }
 
 private void promptAndDeleteMember() {
     try {
         System.out.print("Enter Member ID to Remove: ");
         int id = sc.nextInt();
         
         // Delegate to the DAO
         dao.deleteMember(id);
     } catch (InputMismatchException e) {
         System.out.println("❌ Invalid input for Member ID.");
         sc.next();
     }
 }
}