import java.util.Scanner;
/**
 *This class is supposed to print a reading card for the customer to fill in.
 *The customer will then fill in the reading card, send it back to the waterworks.
 * The secretary will then be able to fill in the readings into the database.
 * @author Nicolai Barasinski
 * Testers: Vanda Bovina, Daniela Petkova, Kevin Srirangathurai
 */
public class DBGenerateReadingCard {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the CPR/CVR of the customer you'd like to send a card to (Please no hyphen (-))");
        String userInput = in.next();
        while(userInput.length() > 10){
            System.out.println("Sorry, that was too long, please re-enter CPR/CVR");
            userInput = in.next();
        }

        DB.selectSQL("Select fldFirstName,fldLastName from tblCustomerProfile where fldCPRCVR='"+userInput+"'");
        String data = DB.getData() + " ";
        data += DB.getData();
        System.out.println("------------------------------------------------------");
        System.out.println("| Customer Name: "+data +"                  |");
        System.out.println("| Please write your water reading here: ____________ |");
        System.out.println("| Please write the date here: ____-__-__ (yyyy-mm-dd)|");
        System.out.println("------------------------------------------------------");
    }
}