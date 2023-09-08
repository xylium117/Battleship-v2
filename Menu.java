import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import Helper.ConsoleHelper;

/**
 * Represents the game launcher
 */
public class Menu {

    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Ask user to select a menu option
     * 
     * @return int
     */
    public int selectOption() {
        String input = "";
        do {
            System.out.print("     What do you wish to do? ");
            try {
                input = in.readLine();
            } catch (java.io.IOException e) {
                System.out.println("An error occured : " + e);
            }
        } while (!Pattern.matches("[0123]", input));
        return Integer.valueOf(input);
    }

    /**
     * Show main menu
     * 
     * @return void
     */
    public void showMenu() {
        ConsoleHelper.eraseConsole();
        System.out.println();
        System.out.println("                                     |__");
        System.out.println("                                     |\\/");
        System.out.println("                                     ---");
        System.out.println("                                     / | [");
        System.out.println("                              !      | |||");
        System.out.println("                            _/|     _/|-++'");
        System.out.println("                        +  +--|    |--|--|_ |-");
        System.out.println("                     { /|__|  |/\\__|  |--- |||__/");
        System.out.println("                    +---------------___[}-_===_.'____                 /\"");
        System.out.println("                ____`-' ||___-{]_| _[}-  |     |_[___\\==--            \\/   _");
        System.out.println(" __..._____--==/___]_|__|_____________________________[___\\==--____,------' .7");
        System.out.println("|                                                                     BB-61/");
        System.out.println(" \\_________________________________________________________________________|");
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                              Welcome to BattleShip                                  ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("     ╔════════════════╗  ╔══════════════════════╗  ╔═════════════╗");
        System.out.println("     ║ 1. Start a game   ║  ║   2. Rules of the game   ║  ║ 3. Know more  ║"); 
        System.out.println("     ╚════════════════╝  ╚══════════════════════╝  ╚═════════════╝");
        System.out.println();
    }

    /**
     * Show game rules
     * 
     * @return void
     */
    public void showRules() {
        ConsoleHelper.eraseConsole();
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                   Naval Battle : Rules                                     ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("     You will face an opponent in a fierce naval battle with your fleet of warships.");
        System.out.println();
        System.out.println("     Both will have a fleet consisting of "+ Config.getNbBoats() + " battleships :");
        for (int i = 0; i < Config.getBoatsConfig().length; i++) {
            String plural = Integer.valueOf(Config.getBoatsConfig()[i][2]) > 1 ? "s" : "";
            System.out.println("    - 1 " + Config.getBoatsConfig()[i][1] + " ("        + Config.getBoatsConfig()[i][2] + " case" + plural + ")");
        }
        System.out.println();
        System.out.println("     Your objective is simple: destroy your opponent's fleet as quickly as possible, before the latter destroys yours.");
        System.out.println("     However, your adversary is using a latest-generation camouflage technique that makes it impossible for his ships to be detected on your radar.");
        System.out.println("     So you have to be strategic to avoid wasting torpedoes and missiles!");
        System.out.println("     You have 100 torpedoes and missiles that you can fire towards your opponent.");
        System.out.println();
        System.out.println("     Prepare your strategy and play as a maritime fleet captain now!");
        System.out.println();
        System.out.println("     ╔══════════════════════╗  ╔════════════════╗  ╔══════════════════════╗  ╔═════════════╗");
        System.out.println("     ║    0. Return to menu     ║  ║ 1. Start a game   ║  ║   2. Rules of the game   ║  ║ 3. Know more  ║");
        System.out.println("     ╚══════════════════════╝  ╚════════════════╝  ╚══════════════════════╝  ╚═════════════╝");
        System.out.println();
        System.out.println();
    }

    /**
     * About this game
     * 
     * @return void
     */
    public void showAbout() {
        ConsoleHelper.eraseConsole();
        System.out.println();
        System.out.println("╔═══════════════════════════════════════════════════════╗");
        System.out.println("║                         About This Game                         ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("     This game is an improved version of my previous Battleship game.");
        System.out.println();
        System.out.println("     --------------- How to play? ---------------");
        System.out.println();
        System.out.println("     To play the game, run the main functino of 'Main' class");
        System.out.println("     The game is played entirely on console.");
        System.out.println("     It is possible to modify the number of boats of each player as well as their properties by editing the configuration file ");
        System.out.println();
        System.out.println("     ╔══════════════════════╗  ╔════════════════╗  ╔══════════════════════╗  ╔═════════════╗");
        System.out.println("     ║    0. Return to menu     ║  ║ 1. Start a game   ║  ║   2. Rules of the game   ║  ║ 3. Know more  ║");
        System.out.println("     ╚══════════════════════╝  ╚════════════════╝  ╚══════════════════════╝  ╚═════════════╝");
        System.out.println();
    }
}