import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import Helper.ConsoleHelper;
import Helper.TextHelper;

/**
 * Represents a game
 */
public class Game {

    private Player player1;
    private Player player2;
    private int playerPlay;
    private String playerWinner;
    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Setup game
     * 
     * @return void
     */
    public void init() {
        printGameLauncher();

        player1 = new PlayerHuman();

        String opponentType = setOpponentType();

        if (opponentType.equals("A")) {
            System.out.println("You will play against a human opponent.");
            System.out.println("Now, you must input the details of the second player.");
            System.out.println();
            player2 = new PlayerHuman();
        } else {
            System.out.println("You will play against an AI opponent");
            System.out.println();
            player2 = new PlayerComputer();
        }

        ConsoleHelper.eraseConsole();
        System.out.println("The deployment of the ships will start!");
        ConsoleHelper.sleep(2500);

        player1.placeBoats();
        player2.placeBoats();

        setFirstPlayer();
    }

    /**
     * Takes care of turns
     * 
     * @return void
     */
    public void play() {
        do {
            if (playerPlay == 1) {
                ConsoleHelper.eraseConsole();
                if (player1 instanceof PlayerHuman) {
                    System.out.println(player1.getPlayerName() + ", it's your turn to play!");
                    ConsoleHelper.sleep(2000);
                }
                player1.shoot(player2);
                playerWinner = player1.getPlayerName();
                playerPlay = 2;

            } else {
                ConsoleHelper.eraseConsole();
                if (player2 instanceof PlayerHuman) {
                    System.out.println(player2.getPlayerName() + ", it's your turn to play!");
                    ConsoleHelper.sleep(2000);
                }
                player2.shoot(player1);
                playerWinner = player2.getPlayerName();
                playerPlay = 1;
            }
        } while (!over());
    }

    /**
     * Shows winner's name and game stats
     * 
     * @return void
     */
    public void end() {
        ConsoleHelper.eraseConsole();
        System.out.println("╔═════════════════════════════════════════╗");
        System.out.print("║");
        TextHelper.generateSpaceBlanks((80 - (50 + playerWinner.length())) / 2);
        System.out.print(playerWinner + " has won the game!");
        TextHelper.generateSpaceBlanks((80 - (50 + playerWinner.length())) / 2);
        System.out.println("║");
        System.out.println("╚═════════════════════════════════════════╝");
        player1.printStats();
        player2.printStats();
        System.out.println();
        System.out.println("     ╔══════════════════════╗  ╔════════════════╗  ╔══════════════════════╗  ╔═════════════╗");
        System.out.println("     ║    0. Return to menu     ║  ║   1. Play again   ║  ║   2. Rules of the game   ║  ║ 3. Know more  ║");
        System.out.println("     ╚══════════════════════╝  ╚════════════════╝  ╚══════════════════════╝  ╚═════════════╝");
    }

    /**
     * Checks if the game is over
     * 
     * @return boolean
     */
    private boolean over() {
        boolean response1 = true;
        boolean response2 = true;
        for (int i = 0; i < player1.getBoard().getBoats().length; i++) {
            if (!player1.getBoard().getBoats()[i].isSunk()) {
                response1 = false;
            }
        }
        for (int i = 0; i < player2.getBoard().getBoats().length; i++) {
            if (!player2.getBoard().getBoats()[i].isSunk()) {
                response2 = false;
            }
        }
        return (response1 || response2);
    }

    /**
     * Sets the type of the opponent
     * 
     * @return String
     */
    private String setOpponentType() {
        String input = "";
        System.out.println();
        System.out.println("╔═══════════╗  ╔════════════╗");
        System.out.println("║  A. Player  ║  ║ B. Computer  ║");
        System.out.println("╚═══════════╝  ╚════════════╝");
        System.out.println();
        do {
            System.out.print("Select your opponent? ");
            try {
                input = in.readLine();
            } catch (java.io.IOException e) {
                System.out.println("An error has occurred : " + e);
            }
        } while (!Pattern.matches("[AaBb]{1}", input));
        return input.toUpperCase();
    }

    /**
     * Randomly sets the order of play between the two players
     *
     * @return void
     */
    private void setFirstPlayer() {
        this.playerPlay = (int) Math.round(Math.random() * 2 + 1);
    }

    /**
     * Shows the game launcher
     *
     * @return void
     */
    private void printGameLauncher() {
        ConsoleHelper.eraseConsole();
        ConsoleHelper.sleep(350);
        
        System.out.println("██████╗░░█████╗░████████╗████████╗██╗░░░░░███████╗░██████╗██╗░░██╗██╗██████╗░");
        ConsoleHelper.sleep(350);
        System.out.println("██╔══██╗██╔══██╗╚══██╔══╝╚══██╔══╝██║░░░░░██╔════╝██╔════╝██║░░██║██║██╔══██╗");
        ConsoleHelper.sleep(350);
        System.out.println("██████╦╝███████║░░░██║░░░░░░██║░░░██║░░░░░█████╗░░╚█████╗░███████║██║██████╔╝");
        ConsoleHelper.sleep(350);
        System.out.println("██╔══██╗██╔══██║░░░██║░░░░░░██║░░░██║░░░░░██╔══╝░░░╚═══██╗██╔══██║██║██╔═══╝░");
        ConsoleHelper.sleep(350);
        System.out.println("██████╦╝██║░░██║░░░██║░░░░░░██║░░░███████╗███████╗██████╔╝██║░░██║██║██║░░░░░");
        ConsoleHelper.sleep(350);
        System.out.println("╚═════╝░╚═╝░░╚═╝░░░╚═╝░░░░░░╚═╝░░░╚══════╝╚══════╝╚═════╝░╚═╝░░╚═╝╚═╝╚═╝░░░░░");
        ConsoleHelper.sleep(350);
        System.out.println();
        System.out.println("Welcome to Battleship!");
        System.out.println();
    }
}