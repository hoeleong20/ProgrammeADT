package utility;

/**
 * @author Hafiz Chew Hoe Leong
 */
public class ProgrammeUtility {

    public static void displayInvalidChoiceMessage(int digit1, int digit2) {
        System.out.println("\nInvalid choice");
        System.out.println("Please enter the choice in range of " + digit1 + " until " + digit2 + ".\n");
    }
    
    public static void displayInvalidYesNoMessage() {
        System.out.println("Please enter (Y) or (N).\n");
    }

    public static void pressEnterMessage() {
        System.out.println("Press enter to back to previous page.\n");
    }

    public static void displayExitMessage() {
        System.out.println("Exiting Programme SubSystem\n");
    }
    
    public static void displayDontFoundProgrammeMessage(){
        System.out.println("The Programme is not found.\n");
    }
    
    public static void revertChoice(String word){
        System.out.println("Your "+word+" has been reverted.\n");
    }
    
    public static void confirmChoice(String word){
        System.out.println("Your "+word+" has been done.\n");
    }
    

    public static boolean validateDigit(String input, int minRange, int maxRange) {

        try {
            if (input == null) {
                return false;
            }

            // Parse the input string to an integer
            int value = Integer.parseInt(input);

            // Check if the parsed integer is within the specified range
            if (value >= minRange && value <= maxRange) {
                return true; // Valid integer within the range
            } else {
                return false; // Integer is outside the range
            }
        } catch (NumberFormatException e) {
            // Parsing failed, input is not a valid integer
            return false;
        }
    }

    public static boolean validateYesNo(String input) {

        if (input == null) {
            return false;
        }

        // Check if the input string has a length of 1
        if (input.length() == 1) {
            char option = input.charAt(0);

            // Check if the character is 'Y' or 'N' (uppercase)
            if (Character.toUpperCase(option) == 'Y' || Character.toUpperCase(option) == 'N') {
                return true; // Valid option
            }
        }

        return false; // Invalid option
    }

    public static boolean validateStringLength(String input, int maxLength) {
        if (input == null) {
            return false;
        }

        return input.length() <= maxLength;
    }
}
