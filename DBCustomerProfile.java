import java.util.Scanner;
/**
 * This module is meant to create a profile for the customers at the Uptown waterworks company by the input provided
 * by the secretary.
 *
 * @author Daniela Petkova
 * @version 1.0
 * @testers Vanda Bovina, Nicolai Barasinski, Kevin Srirangathurai
 */
public class DBCustomerProfile {
    static Scanner in = new Scanner(System.in);
    static boolean inputProofed = true;
    static String userInput = "";


    public static void main(String[] args) {

        /**
         * Declarations
         */

        //Defining variables
        Scanner in = new Scanner(System.in);
        boolean customerProfileCreate = true;
        boolean finished = false;
        boolean fNameInput = false;
        boolean lNameInput = false;
        boolean StreetNameInput = false;
        boolean EmailInput = false;
        boolean PhoneNumberInput = false;
        int maxLenght = 40;
        String fName = null;
        String lName = null;
        String Street = null;
        String Email = null;
        String PhoneNumber = null;


        //Making an intro screen for the convenience of the secretary
        while (customerProfileCreate) {
            System.out.println("You are about to create a new user profile in the waterworks database.");
            System.out.println("Would you like to continue? (y/n)");
            inputProofString();

            if (true != userInput.contains("n") && true != userInput.contains("y")) {
                System.out.println("Please enter \"y\" to continue, or \"n\" to exit.");
            } else if (true == userInput.contains("n")) {
                System.out.println("Exiting customer profile creation.");
                System.exit(0);
            } else {
                System.out.println("Customer profile creation confirmed.\n");
                break;
            }
        }


        //Making a customer profile
        System.out.println("Enter the customers CPR/CVR number:");
        String CPRCVR = in.next();

        System.out.println("Enter the amount of water meters:");
        int WaterMeter = in.nextInt();


        while (!finished){ //loop for the validation of input on columns with a limit of characters

            if(!fNameInput) {
                System.out.println("Enter the customers first name (40 characters max, including spaces, letters only):");
                fName = in.next();
            }
                if (fName.length() <= maxLenght) {
                    fNameInput = true;
                }


            if(!lNameInput) {
                System.out.println("Enter the customer last name (40 characters max, including spaces, letters only):");
                lName = in.next();
            }
                if (lName.length() <= maxLenght) {
                    lNameInput = true;
                }


            if(!StreetNameInput) {
                System.out.println("Enter the customer Street (40 characters max, including spaces, letters only):");
                Street = in.next();
            }
                if(Street.length() <= maxLenght) {
                    StreetNameInput = true;
                }

            if(!EmailInput) {
                System.out.println("Enter the customer Email (40 characters max, including spaces, letters only):");
                Email = in.next();
            }
                if(Email.length() <= maxLenght) {
                    EmailInput = true;
                }

            if(!PhoneNumberInput) {
                System.out.println("Enter the customer Phone (40 characters max, including spaces, letters only):");
                PhoneNumber = in.next();
            }
                if(PhoneNumber.length() <= maxLenght){
                    PhoneNumberInput = true;
                }

            if (fNameInput && lNameInput && StreetNameInput && EmailInput && PhoneNumberInput) {
                finished = true;
            }


        }

        System.out.println("Enter the customer street number:");
        int StreetNo = in.nextInt();

        System.out.println("Enter the customer zip code:");
        int ZipCode = in.nextInt();


        userInputCustomerProfile(CPRCVR, WaterMeter, fName, lName, Street, StreetNo, ZipCode, Email, PhoneNumber);
    }

    /**
     *
     * The method is used to validate the input in the intro screen
     */

    private static void inputProofString() {

        if (in.hasNext()) {
            userInput = in.next();
        } else {
            userInput = "";
            inputProofed = true;
        }
    }

    /**
     * This method is used to send the data in the SQL server database
     *
     * @param CPRCVR     - the string will send the CPR/CVR into the database
     * @param WaterMeter - the int will send the amount of water meters in the database
     * @param fName      - the string will send the first name of the customer in the database
     * @param lName      - the string will send the last name of the customer in the database
     * @param Street     - the string will send the street address in the database
     * @param StreetNo   - the int will send the street number in the database
     * @param ZipCode    - the int will send the zip code in the database
     * @param Email      - the string will send the email in the database
     * @param PhoneNo    - the string will send the phone number in the database
     */

    private static void userInputCustomerProfile(String CPRCVR, int WaterMeter, String fName, String lName, String Street, int StreetNo, int ZipCode, String Email, String PhoneNo) {

        DB.insertSQL("Insert into tblCustomerProfile(fldCPRCVR,fldWaterMeter,fldFirstName,fldLastName,fldStreetName,fldStreetNo,fldZipCode,fldEmail,fldPhoneNo) values('" + CPRCVR + "','" + WaterMeter + "','" + fName + "', '" + lName + "', '" + Street + "', '" + StreetNo + "', '" + ZipCode + "', '" + Email + "', '" + PhoneNo + "')");


    }









}