package boundary;

import entity.Programme;
import java.util.Scanner;
import entity.Date;
import utility.ProgrammeUtility;

/**
 * @author Hafiz Chew Hoe Leong
 */
public class ProgrammeUI {

    Scanner scanner = new Scanner(System.in);

    public int getChoice() {
        boolean valid = false;
        int choice = -1;

        while (valid == false) {
            System.out.println("Programme SubSystem");
            System.out.println("1. Add new programme");
            System.out.println("2. Remove programmes");
            System.out.println("3. Find a programme");
            System.out.println("4. Amend a programmes details");
            System.out.println("5. List all programmes");
            System.out.println("6. Generate programme report");
            System.out.println("0. Quit");
            System.out.print("\nEnter choice: ");

            String choiceString = scanner.nextLine();
            System.out.println();
            valid = ProgrammeUtility.validateDigit(choiceString, 0, 6);

            if (valid == true) {
                choice = Integer.parseInt(choiceString);
            } else{
                ProgrammeUtility.displayInvalidChoiceMessage(0, 6);
            }
        }

        return choice;
    }

    public Programme inputProgrammeDetails() {
        String code = inputCode();
        String level = inputLevel();
        String name = inputName();
        int duration = inputDuration();
        int fees = inputFees();
        Date dateIntroduce = inputDateIntroduce();
        return new Programme(code, level, name, duration, fees, dateIntroduce);
    }

    public String inputCode() {
        boolean valid = false;
        String code = "";

        while (valid == false) {
            System.out.print("Enter programme code: ");
            code = scanner.nextLine();
            System.out.println();
            valid = ProgrammeUtility.validateStringLength(code, 3);

            if (valid == false) {
                System.out.println("Enter programme code in 3 letters.\n");
            }
        }

        return code.toUpperCase();
    }

    public String inputLevel() {
        boolean valid = false;
        String level = "";

        while (valid == false) {
            System.out.print("Enter programme level: ");
            level = scanner.nextLine();
            System.out.println();
            valid = ProgrammeUtility.validateStringLength(level, 8);

            if (valid == false) {
                System.out.println("Enter programme level in 8 letters.\n");
            }
        }

        return level;
    }

    public String inputName() {
        boolean valid = false;
        String name = "";

        while (valid == false) {
            System.out.print("Enter programme name: ");
            name = scanner.nextLine();
            System.out.println();
            valid = ProgrammeUtility.validateStringLength(name, 40);

            if (valid == false) {
                System.out.println("Enter programme name in 40 letters.\n");
            }
        }

        return name;
    }

    public int inputDuration() {
        boolean valid = false;
        int duration = -1;

        while (valid == false) {
            System.out.print("Enter duration: ");
            String durationString = scanner.nextLine();
            System.out.println();
            valid = ProgrammeUtility.validateDigit(durationString, 1, 10);

            if (valid == true) {
                duration = Integer.parseInt(durationString);
            } else {
                System.out.println("Enter programme duration in range of 1 to 10.\n");
            }
        }

        return duration;
    }

    public int inputFees() {
        boolean valid = false;
        int fees = -1;

        while (valid == false) {
            System.out.print("Enter fees: ");
            String feesString = scanner.nextLine();
            System.out.println();
            valid = ProgrammeUtility.validateDigit(feesString, 0, 100000);

            if (valid == true) {
                fees = Integer.parseInt(feesString);
            } else {
                System.out.println("Enter programme fees in range of 0 to 99999.\n");
            }
        }

        return fees;
    }

    public Date inputDateIntroduce() {
        boolean valid = false;

        int day = 0, month = 0, year = 0;

        System.out.print("Enter year introduce (in format of 13/01/2020)\n");

        while (valid == false) {
            System.out.print("Enter day : ");
            String dayString = scanner.nextLine();
            System.out.println();

            valid = ProgrammeUtility.validateDigit(dayString, 1, 31);

            if (valid == true) {
                day = Integer.parseInt(dayString);
            } else {
                System.out.println("Enter day in range of 1 to 31.\n");
            }

        }

        valid = false;

        while (valid == false) {
            System.out.print("Enter month : ");
            String monthString = scanner.nextLine();
            System.out.println();

            valid = ProgrammeUtility.validateDigit(monthString, 1, 12);

            if (valid == true) {
                month = Integer.parseInt(monthString);
            } else {
                System.out.println("Enter month in range of 1 to 12.\n");
            }

        }

        valid = false;

        while (valid == false) {
            System.out.print("Enter year : ");
            String yearString = scanner.nextLine();
            System.out.println();

            valid = ProgrammeUtility.validateDigit(yearString, 2000, 2023);

            if (valid == true) {
                year = Integer.parseInt(yearString);
            } else {
                System.out.println("Enter year in range of 2000 to 2023.\n");
            }

        }

        return new Date(day, month, year);
    }

    public void printProgrammeDetails(Programme programme) {
        System.out.println("Programme Details");
        System.out.println("-----------------");
        System.out.println("Programme code: " + programme.getCode());
        System.out.println("Programme level: " + programme.getLevel());
        System.out.println("Programme name: " + programme.getName());
        System.out.println("Programme duration: " + programme.getDuration());
        System.out.println("Programme fees: " + programme.getFees());
        System.out.println("Programme date introduce: " + programme.getDateIntroduce());
        System.out.println("");
    }

    public void listProgrammeBrief(String outputStr) {
        System.out.println("List of Programme:\n" + outputStr);
    }

    public boolean confirmationChoice(String word) {
        boolean valid = false;
        char choice = ' ';

        while (valid == false) {
            System.out.print("Are you sure you wanted to " + word + "? (Y=Yes,N=No) : ");
            String choiceString = scanner.nextLine();
            System.out.println("");
            valid = ProgrammeUtility.validateYesNo(choiceString);
            choice = choiceString.charAt(0);

            if (valid == false) {
                ProgrammeUtility.displayInvalidYesNoMessage();
            }
        }

        return Character.toUpperCase(choice) == 'Y';
    }

    public int chooseAmendField() {

        boolean valid = false;
        int choice = -1;

        while (valid == false) {
            System.out.println("Amend Field");
            System.out.println("1. code\n2. level\n3. name\n4. duration\n5. fees\n6. dateIntroduce");
            System.out.print("\nEnter your choice : ");
            String choiceString = scanner.nextLine();
            System.out.println();

            valid = ProgrammeUtility.validateDigit(choiceString, 0, 6);

            if (valid == true) {
                choice = Integer.parseInt(choiceString);
            } else {
                ProgrammeUtility.displayInvalidYesNoMessage();
            }
        }
        return choice;
    }

    public int reportCategoryChoice() {
        boolean valid = false;
        int choice = -1;

        while (valid == false) {
            System.out.println("Generate Report");
            System.out.println("1. All Programme ");
            System.out.println("2. Diploma Programme");
            System.out.println("3. Bachelor Programme");
            System.out.print("\nEnter your choice : ");

            String choiceString = scanner.nextLine();
            System.out.println();

            valid = ProgrammeUtility.validateDigit(choiceString, 0, 3);

            if (valid == true) {
                choice = Integer.parseInt(choiceString);
            } else {
                ProgrammeUtility.displayInvalidChoiceMessage(0,3);
            }

        }
        return choice;
    }

    public void reportTitle(String word) {
        displayReportHorizontalLine();
        System.out.printf("%-22sReport for %-20s%22s\n", "|", word + " Programmes", "|");
        displayReportHorizontalLine();
    }

    public void displayReportHorizontalLine() {
        System.out.print("+");
        for (int i = 0; i < 73; i++) {
            System.out.print("-");
        }
        System.out.print("+\n");
    }

    public void printProgrammeDetailsInLine(Programme programme) {

        System.out.printf("| %s | %-40s | %d | %5d | %s |\n", programme.getCode(), programme.getName(),
                programme.getDuration(), programme.getFees(), programme.getDateIntroduce().toString());

    }

    public int reportPromptMessage() {
        boolean valid = false;
        int choice = -1;

        while (valid == false) {
            System.out.println("1. Do sorting");
            System.out.println("0. Quit");
            System.out.print("\nEnter your choice : ");
            String choiceString = scanner.nextLine();
            System.out.println();
            valid = ProgrammeUtility.validateDigit(choiceString, 0, 1);

            if (valid == true) {
                choice = Integer.parseInt(choiceString);
            } else {
                ProgrammeUtility.displayInvalidChoiceMessage(0,1);
            }
        }
        return choice;
    }

    public int sortingMenu() {
        boolean valid = false;
        int choice = -1;

        while (valid == false) {
            System.out.println("Sorting Option : ");
            System.out.println("1. Alphabetical Order");
            System.out.println("2. Fees Amount");
            System.out.println("3. Date Introduce");
            System.out.print("\nEnter your choice : ");
            String choiceString = scanner.nextLine();
            System.out.println();
            valid = ProgrammeUtility.validateDigit(choiceString, 0, 3);

            if (valid == true) {
                choice = Integer.parseInt(choiceString);
            } else {
                ProgrammeUtility.displayInvalidChoiceMessage(0,3);
            }
        }
        return choice;
    }

    public int sortingOrderMenu(int sortingOrderChoice) {
        boolean valid = false;
        int choice = -1;

        while (valid == false) {
            System.out.println("Sorting Order Option : ");
            switch (sortingOrderChoice) {
                case 1:
                    System.out.println("1. Ascending Order  : A -> Z");
                    System.out.println("2. Descending Order : Z -> A");
                    break;
                case 2:
                    System.out.println("1. Ascending Order  : Lower  Fees -> Higher Fees");
                    System.out.println("2. Descending Order : Higher Fees -> Lower  Fees");
                    break;
                case 3:
                    System.out.println("1. Ascending Order  : Early  Date -> Latest Date");
                    System.out.println("2. Descending Order : Latest Date -> Early  Date");
                    break;
            }

            System.out.print("\nEnter your choice : ");
            String choiceString = scanner.nextLine();
            System.out.println();
            valid = ProgrammeUtility.validateDigit(choiceString, 0, 2);

            if (valid == true) {
                choice = Integer.parseInt(choiceString);
            } else {
                ProgrammeUtility.displayInvalidChoiceMessage(0,2);
            }
        }
        return choice;
    }
    
    public int removeProgrammeMenu(){
        boolean valid = false;
        int choice = -1;

        while (valid == false) {
            System.out.println("Remove Option : ");
            System.out.println("1. All");
            System.out.println("2. By Programme Code");
            System.out.println("3. By Programme Date Introduce ");
            System.out.print("\nEnter your choice : ");
            String choiceString = scanner.nextLine();
            System.out.println();
            valid = ProgrammeUtility.validateDigit(choiceString, 0, 3);

            if (valid == true) {
                choice = Integer.parseInt(choiceString);
            } else {
                ProgrammeUtility.displayInvalidChoiceMessage(0,3);
            }
        }
        return choice;
    }
    
    
    public int removeByDateChoiceMenu(){
        boolean valid = false;
        int choice = -1;

        while (valid == false) {
            System.out.println("Remove By Date Option : ");
            System.out.println("1. Before Date");
            System.out.println("2. After Date");
            System.out.print("\nEnter your choice : ");
            String choiceString = scanner.nextLine();
            System.out.println();
            valid = ProgrammeUtility.validateDigit(choiceString, 0, 2);

            if (valid == true) {
                choice = Integer.parseInt(choiceString);
            } else {
                ProgrammeUtility.displayInvalidChoiceMessage(0,3);
            }
        }
        return choice;
    }
    
    
}
