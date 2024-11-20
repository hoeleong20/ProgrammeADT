package control;

import adt.*;
import boundary.ProgrammeUI;
import entity.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import utility.ProgrammeUtility;

/**
 * @author Hafiz Chew Hoe Leong
 */
public class ProgrammeControl {

    private ListInterface<Programme> programmeList = new ArrayList<>();
    private ProgrammeUI programmeUI = new ProgrammeUI();
    static Scanner scanner = new Scanner(System.in);
    static BufferedReader enter = new BufferedReader(new InputStreamReader(System.in));

    public void runProgrammeSubSystem() throws IOException {
        int choice = 0; 
        do {
            choice = programmeUI.getChoice();
            switch (choice) {
                case 0:
                    ProgrammeUtility.displayExitMessage();
                    break;
                case 1:
                    addProgramme();
                    break;
                case 2:
                    removeProgramme();
                    break;
                case 3:
                    findProgramme();
                    break;
                case 4:
                    amendProgramme();
                    break;
                case 5:
                    listAllProgramme();
                    break;
                case 6:
                    generateProgrammeReport();
                    break;

                default:
                    ProgrammeUtility.displayInvalidChoiceMessage(0, 6);
            }

            ProgrammeUtility.pressEnterMessage();
            enter.readLine();
        } while (choice != 0);
    }

    public void addProgramme() {
        Programme programme = programmeUI.inputProgrammeDetails();
        programmeUI.printProgrammeDetails(programme);

        String word = "add";
        boolean confirm = programmeUI.confirmationChoice(word);

        if (confirm) {
            programmeList.add(programme);
            ProgrammeUtility.confirmChoice(word);
        } else {
            ProgrammeUtility.revertChoice(word);
        }
    }

    //return all programmeList.get(i) for all i
    public String getAllProgrammes() {
        String outputStr = "";
        for (int i = 1; i <= programmeList.getNumberOfEntries(); i++) {
            outputStr += programmeList.get(i) + "\n";
        }
        return outputStr;
    }

    public void removeProgramme() {
        int choice = programmeUI.removeProgrammeMenu();
        String word = "";
        boolean confirm = false;

        switch (choice) {
            case 1:
                word = "remove all";
                confirm = programmeUI.confirmationChoice(word);
                if (confirm) {
                    programmeList.clear();
                    ProgrammeUtility.confirmChoice(word);
                } else {
                    ProgrammeUtility.revertChoice(word);
                }
                break;
            case 2:
                String code = programmeUI.inputCode();
                int programmeIndex = findProgrammeCode(code);

                if (programmeIndex != -1) {
                    programmeUI.printProgrammeDetails(programmeList.get(programmeIndex));

                    word = "remove";
                    confirm = programmeUI.confirmationChoice(word);
                    if (confirm) {
                        programmeList.removeAt(programmeIndex);
                        ProgrammeUtility.confirmChoice(word);
                    } else {
                        ProgrammeUtility.revertChoice(word);
                    }
                } else {
                    ProgrammeUtility.displayDontFoundProgrammeMessage();
                }
                break;
            case 3:
                int removeByDateChoice = programmeUI.removeByDateChoiceMenu();
                Date removeByDateTime = programmeUI.inputDateIntroduce();

                programmeList.sort(new DateComparator());

                int removeIndex = 0;
                boolean found = false;

                word = "remove";
                confirm = programmeUI.confirmationChoice(word);
                if (confirm) {
                    if (removeByDateChoice == 1) {
                    for (int i = 0; (i < programmeList.getNumberOfEntries()) && found == false; i++) {
                        if (programmeList.get(i).getDateIntroduce().compareTo(removeByDateTime) >= 0) {
                            removeIndex = programmeList.indexOf(programmeList.get(i));
                            found = true;
                        }
                    }
                        programmeList.removeBefore(removeIndex);
                    } else {
                    for (int i = 0; (i < programmeList.getNumberOfEntries()) && found == false; i++) {
                        if (programmeList.get(i).getDateIntroduce().compareTo(removeByDateTime) >= 0) {
                            if (programmeList.get(i).getDateIntroduce().compareTo(removeByDateTime) == 0) {
                                removeIndex = programmeList.indexOf(programmeList.get(i));
                            } else {
                                removeIndex = programmeList.indexOf(programmeList.get(i - 1));
                            }
                            found = true;
                        }
                    }
                        programmeList.removeAfter(removeIndex);
                    }
                    ProgrammeUtility.confirmChoice(word);
                } else {
                    ProgrammeUtility.revertChoice(word);
                }

                break;

        }

    }

    //list all programmes, enter programme code, if found then display
    public void findProgramme() {
        String code = programmeUI.inputCode();
        int programmeIndex = findProgrammeCode(code);

        if (programmeIndex != -1) {
            programmeUI.printProgrammeDetails(programmeList.get(programmeIndex));
        } else {
            ProgrammeUtility.displayDontFoundProgrammeMessage();
        }

    }

    public void amendProgramme() {
        String code = programmeUI.inputCode();
        int programmeIndex = findProgrammeCode(code);

        if (programmeIndex != -1) {
            programmeUI.printProgrammeDetails(programmeList.get(programmeIndex));

            int choice = programmeUI.chooseAmendField();

            String tempString = "";
            int tempInt = 0;
            Date tempDate = null;

            switch (choice) {
                case 1:
                    tempString = programmeUI.inputCode();
                    break;
                case 2:
                    tempString = programmeUI.inputLevel();
                    break;
                case 3:
                    tempString = programmeUI.inputName();
                    break;
                case 4:
                    tempInt = programmeUI.inputDuration();
                    break;
                case 5:
                    tempInt = programmeUI.inputFees();
                    break;
                case 6:
                    tempDate = programmeUI.inputDateIntroduce();
                    break;
                default:
            }

            String word = "amend";
            boolean confirm = programmeUI.confirmationChoice(word);
            if (confirm) {
                switch (choice) {
                    case 1:
                        programmeList.get(programmeIndex).setCode(tempString);
                        break;
                    case 2:
                        programmeList.get(programmeIndex).setLevel(tempString);
                        break;
                    case 3:
                        programmeList.get(programmeIndex).setName(tempString);
                        break;
                    case 4:
                        programmeList.get(programmeIndex).setDuration(tempInt);
                        break;
                    case 5:
                        programmeList.get(programmeIndex).setFees(tempInt);
                        break;
                    case 6:
                        programmeList.get(programmeIndex).setDateIntroduce(tempDate);
                        break;
                }

                ProgrammeUtility.confirmChoice(word);
            } else {
                ProgrammeUtility.revertChoice(word);
            }
        } else {
            ProgrammeUtility.displayDontFoundProgrammeMessage();
        }
    }

    public void listAllProgramme() {
        String outputStr = "";
        for (int i = 0; i < programmeList.getNumberOfEntries(); i++) {
            outputStr += programmeList.get(i) + "\n";
        }
        programmeUI.listProgrammeBrief(outputStr);
    }

    public void generateProgrammeReport() {
        int choice = programmeUI.reportCategoryChoice();

        System.out.println("1. All Programmes");
        System.out.println("2. Diploma Programmes");
        System.out.println("3. Bachelor Programmes");
        System.out.println("");

        ListInterface<Programme> programmeListReport = new ArrayList<>();
        String programmeLevelString = "All";

        switch (choice) {
            case 1:
                programmeListReport = programmeList;
                break;
            case 2:
                programmeLevelString = "Diploma";
                for (int i = 0; i < programmeList.getNumberOfEntries(); i++) {
                    if (programmeLevelString.equals(programmeList.get(i).getLevel())) {
                        programmeListReport.add(programmeList.get(i));
                    }
                }
                break;
            case 3:
                programmeLevelString = "Bachelor";
                for (int i = 0; i < programmeList.getNumberOfEntries(); i++) {
                    if (programmeLevelString.equals(programmeList.get(i).getLevel())) {
                        programmeListReport.add(programmeList.get(i));
                    }
                }
                break;
            default:
        }

        generateProgrammeReportResult(programmeListReport, programmeLevelString);

        int reportPromptChoice;
        do {
            reportPromptChoice = programmeUI.reportPromptMessage();
            if (reportPromptChoice == 1) {
                int sortChoice = programmeUI.sortingMenu();
                int sortOrderChoice = programmeUI.sortingOrderMenu(sortChoice);

                switch (sortChoice) {
                    case 1:
                        programmeListReport.sort();
                        break;
                    case 2:
                        programmeListReport.sort(new FeesComparator());
                        break;
                    case 3:
                        programmeListReport.sort(new DateComparator());
                        break;
                }

                if (sortOrderChoice == 2) {
                    programmeListReport.reverseOrder();
                }

                generateProgrammeReportResult(programmeListReport, programmeLevelString);
            }

        } while (reportPromptChoice == 1);

    }

    public void generateProgrammeReportResult(ListInterface<Programme> programmeListReport, 
            String programmeLevelString) {

        programmeUI.reportTitle(programmeLevelString);

        for (int i = 0; i < programmeListReport.getNumberOfEntries(); i++) {
            programmeUI.printProgrammeDetailsInLine(programmeListReport.get(i));
        }
        programmeUI.displayReportHorizontalLine();
        System.out.println("");
    }

    public int findProgrammeCode(String code) {
        int foundIndex = -1;

        for (int i = 0; (foundIndex == -1) && i < programmeList.getNumberOfEntries(); i++) {
            if (code.equals(programmeList.get(i).getCode())) {
                foundIndex = i;
            }
        }

        return foundIndex;
    }

    public void hardCodedProgrammeData() {
        Date RSWDate = new Date(1, 1, 2022);
        Programme RSW = new Programme("RSW", "Bachelor", "Software Engineering", 3, 34600, RSWDate);
        programmeList.add(RSW);

        Date RDSDate = new Date(5, 5, 2023);
        Programme RDS = new Programme("RDS", "Bachelor", "Data Science", 3, 36600, RDSDate);
        programmeList.add(RDS);

        Date RMMDate = new Date(1, 3, 2022);
        Programme RMM = new Programme("RMM", "Bachelor", "Management Mathematics with Computing", 3, 
                33600, RMMDate);
        programmeList.add(RMM);

        Date RISDate = new Date(20, 4, 2022);
        Programme RIS = new Programme("RIS", "Bachelor", "Information Security", 3, 32600, RISDate);
        programmeList.add(RIS);

        Date RITDate = new Date(1, 4, 2022);
        Programme RIT = new Programme("RIT", "Bachelor", "Internet Technology", 3, 31600, RITDate);
        programmeList.add(RIT);

        Date RSTDate = new Date(31, 5, 2023);
        Programme RST = new Programme("RST", "Bachelor", "Interactive Software Technology", 3, 30600, 
                RSTDate);
        programmeList.add(RST);

        Date DISDate = new Date(1, 1, 2022);
        Programme DIS = new Programme("DIS", "Diploma", "Information Systems", 3, 17600, DISDate);
        programmeList.add(DIS);

        Date DFTDate = new Date(2, 1, 2022);
        Programme DFT = new Programme("DFT", "Diploma", "Internet Technology", 3, 16600, DFTDate);
        programmeList.add(DFT);

        Date DCSDate = new Date(20, 1, 2022);
        Programme DCS = new Programme("DCS", "Diploma", "Computer Science", 3, 18600, DCSDate);
        programmeList.add(DCS);

        Date DACDate = new Date(17, 2, 2023);
        Programme DAC = new Programme("DAC", "Diploma", "Accounting", 3, 19600, DACDate);
        programmeList.add(DAC);

        Date DMKDate = new Date(20, 4, 2023);
        Programme DMK = new Programme("DMK", "Diploma", "Marketing", 3, 20600, DMKDate);
        programmeList.add(DMK);

    }

    public static void main(String[] args) throws IOException {
        ProgrammeControl control = new ProgrammeControl();
        control.hardCodedProgrammeData();
        control.runProgrammeSubSystem();
    }
}
