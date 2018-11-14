import java.util.Scanner;

/**
 * Client application for an implementation of a simple tennis match calculator.
 */
public class TennisMatch {

    private final static String EMPTY = "";

    /**
     * Main method of the application.
     * @param args Program arguments.
     */
    public static void main(String[] args){
        System.out.println("Welcome to Tennis Match. ");

        printMenu();

        Match match = new Match();

        int menuSelection = getMenuSelection();

        while(menuSelection != 0){

            switch (menuSelection){
                case 1:
                    match.scorePoint(true);
                    printSetScore(match);
                    break;
                case 2:
                    match.scorePoint(false);
                    printSetScore(match);
                    break;
                case 3:
                    printGameScore(match);
                    break;
                case 9: match = new Match();
                    break;
                    default:
                    printInvalidMenuOption();
            }



            menuSelection = getMenuSelection();
        }

        System.exit(0);
    }

    /**
     * Prints the current set score.
     * @param match Instance of the match.
     */
    private static void printSetScore(Match match){
        System.out.println(EMPTY);
        System.out.println(match.toString());
        System.out.println(EMPTY);
    }

    /**
     * Prints the game score.
     * @param match Instance of the match.
     */
    private static void printGameScore(Match match){
        System.out.println(EMPTY);
        System.out.println(match.getGameScore());
        System.out.println(EMPTY);
    }

    /**
     * Prints a message indicating an invalid menu option was selected.
     */
    private static void printInvalidMenuOption(){
        System.out.println(EMPTY);
        System.out.println("Invalid menu option.");
        System.out.println(EMPTY);
        printMenu();
    }

    /**
     * Prints a menu of options.
     */
    private static void printMenu(){
        System.out.println("0) Exit");
        System.out.println("1) Player 1 Scores a Point");
        System.out.println("2) Player 2 Scores a Point");
        System.out.println("3) Print Match Score");
        System.out.println("9) Start a New Match");
    }

    /**
     * Reads the user selection and returns a numeric value of the option selected.
     * @return Menu option
     */
    private static int getMenuSelection(){
        System.out.println("Select an option....");
        return new Scanner(System.in).nextInt();
    }
}
