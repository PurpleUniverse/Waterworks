import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Pattern;

/***
 * This is a module that is designed to aid the secretary of Uptown waterworks company
 * with the process of storing customer water consumption information.
 * When prompted, the secretary is asked to add data, which will be stored in the appropriate table
 * within the company database.
 *
 * @author Vanda Bovina
 * @version 1.0
 * @testers Nicolai Barasinski, Daniela Petkova, Kevin Srirangathurai
 */

public class DBInputReadingCard {

    static Scanner in = new Scanner(System.in);
    static String userInput = "";


    public static void main(String[] args) {

        String CprCvrNumber = "";
        int WaterMeterTotalConsumption = 0;
        LocalDate readingDate=LocalDate.of(1111,11,11);
        boolean inProgress=true;


        //intro screen for the convenience of the secretary.
        System.out.println("You are about to start entering the information from the customer reading cards into the database.");
        System.out.println("Would you like to continue? (y/n)");

        while(inProgress) {
            userInput=in.next();
            if (true != userInput.contains("n") && true != userInput.contains("y")) {
                System.out.println("Please enter \"y\" to continue, or \"n\" to exit.");
            } else if (true == userInput.contains("n")) {
                System.out.println("Exiting reading card processing.");
                System.exit(0);
            } else {
                System.out.println("Customer reading card input confirmed.\n");
                inProgress = false;
            }
        }
        //Input CPR/CVR

        System.out.println("Please enter the customer's CPR/CVR number");
        inProgress = true;
        while (inProgress) {
             if (in.hasNext()){
                 userInput = in.next();

                 if(checkNbrOfDigits(userInput)==true&&AllowedNumbers(userInput)==true){
                     CprCvrNumber=userInput;

                 System.out.println("CPR/CVR number received - "+CprCvrNumber+"\n\n");
                 inProgress = false;

                 }
                 else System.out.println("CPR number can only be 10 digits long. \n CVR number can only be 8 digits long." +
                         "\nPlease try again.");

             }
        else
                 System.out.println("Your input is invalid. Please try again.");
        }

        //Input the date of the reading

        System.out.println("Please input the date of reading. (yyyy-mm-dd)");
        inProgress=true;

        while(inProgress){

            userInput=in.next();
            if (checkDateNumbers(userInput)==true) {
                int [] dateReturnResult = parseStringToInt(userInput);
                readingDate = LocalDate.of(dateReturnResult[0],dateReturnResult[1],dateReturnResult[2]);
                System.out.println("Reading date received "+readingDate+"\n\n\n");
                inProgress=false;
            }
            else
                System.out.println("A valid date contains arabic (0-9) numerals. Please try again.");
        }


        //Input water consumed (based on how many meters the customer has on the tblCustomerProfile)
        //return the number and prompt the addition loop.

        System.out.println("You are now about to start entering water consumption data. " +
                "\nThe customer with matching CPR/CVR number has "+WaterMeterCount(CprCvrNumber)+" water meters."+
                "\n\nPlease enter water consumption readings one by one. (digits only)");

            for (int i = WaterMeterCount(CprCvrNumber);i>0;i--){
                int waterReadingSingle;
                while(!in.hasNextInt()){
                    System.out.println("There was an error in your input. Please try entering the current reading again.");
                    in.next();
                }
                waterReadingSingle = in.nextInt();
                WaterMeterTotalConsumption += waterReadingSingle;
            }
        System.out.printf("Water consumption readings received in total - %10d m³ ",WaterMeterTotalConsumption);

        //Return all input and ask if it is correct before executing the SQL code
        System.out.println("\n\nData received during this session: \n\n");
        System.out.print("\nCustomer CPR/CVR number:          "+CprCvrNumber);
        System.out.print("\nThe date of reading:              "+readingDate);
        System.out.printf("\nCustomer water consumption:       %10d m³", WaterMeterTotalConsumption);

        //Input review screen for the convenience of the secretary.

        System.out.println("\n\nAre the above data correct? (y/n)");
        inProgress=true;
        while(inProgress){
            userInput=in.next();
            if (true != userInput.contains("n") && true != userInput.contains("y")) {
                System.out.println("Please enter \"y\" to continue, or \"n\" to exit.");
            } else if (true == userInput.contains("n")) {
                System.out.println("Please re-start the program to begin reading card input anew.");
                System.exit(0);
            } else {
                System.out.println("Customer reading card input confirmed. The data will be transferred to the database.\nHave a nice day!");
                finalSQLInputQuery(CprCvrNumber,readingDate,WaterMeterTotalConsumption);
                inProgress = false;
            }
        }


    }
    /***
     * The purpose of this method is to check the user input for containing digits.
     * @param userInput is the variable that represents user input.
     * @return when input contains numbers, the check is passed and true is returned.
     */
    private static boolean AllowedNumbers (String userInput){

        if(userInput.matches(".*[0-9].*")){
            return true;}
        else
            return false;
    }

    /***
     * The purpose of this method is to check user input for the amount of digits entered.
     * Since a CPR number can only be 10 digits long, and CVR number is 8 digits long, only inputs of these lengths are accepted.
     * @param userInput represents user-entered variable.
     * @return if the input length matches 10 or 8, method returns true and the input is accepted.
     */
    private static boolean checkNbrOfDigits (String userInput){

        Pattern pattern = Pattern.compile("(^\\S{10}$|^\\S{8}$)");
        if (true!=pattern.matcher(userInput).find()) {

            return false;
        }
        else
            return true;
    }

    /***
     * The purpose of this method is to check the entered date input for containing letters.
     * @param userInput is user-entered string.
     * @return method returns true if the string doesn't contain letters. Symbols are allowed, since dates have separators.
     */
    private static boolean checkDateNumbers (String userInput){

        if (userInput.matches(".*[a-z].*")){
            return false;
        }
        else
            return true;
    }

    /***
     * This method parses user input into appropriate values to take place in a date variable.
     * @param userInput is user input string
     * @return method returns an array of values that will be transformed into a date, appropriate for input into the database.
     */
    private static int[] parseStringToInt (String userInput){

        int year = Integer.parseInt(userInput.substring(0,4));
        int month = Integer.parseInt(userInput.substring(5,7));
        int day = Integer.parseInt(userInput.substring(8,10));

        int [] dateReturn = new int[3];
        dateReturn[0]=year;
        dateReturn[1]=month;
        dateReturn[2]=day;

        return dateReturn;



    }

    /***
     * this method retrieves the count of water meters based on the related customer, which is identified by their CPR/CVR number
     * @param CprCvrNumber this is the value of the CPR/CVR number which was entered by the user earlier
     * @return the method returns an int which is then used as a control variable for water meter reading addition loop.
     */
    private static int WaterMeterCount (String CprCvrNumber){

        DB.selectSQL("Select fldWaterMeter from tblCustomerProfile where fldCPRCVR = '" + CprCvrNumber + "'");
        String WaterMeter = DB.getData();
        int waterMeterCount = Integer.parseInt(WaterMeter);
        return waterMeterCount;
    }

    /***
     *this method combines all received input into an SQL query, which enters the data into the database.
     * @param CprCvrNumber
     * @param readingDate
     * @param WaterMeterTotalConsumption
     */
    private static void finalSQLInputQuery(String CprCvrNumber,LocalDate readingDate,int WaterMeterTotalConsumption){

    DB.purgeSelection();
    DB.insertSQL("Insert into tblCustomerReading(fldCPRCVR, fldReadingDate, fldWaterConsumption) " +
            "values("+CprCvrNumber+",'"+readingDate+"',"+WaterMeterTotalConsumption+")");

    }
}
