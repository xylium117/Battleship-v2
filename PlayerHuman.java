import java.util.regex.Pattern;
import Helper.ConsoleHelper;
import Helper.CoordinateHelper;
import Helper.TextHelper;

/**
 * Represents a human player
 */
public class PlayerHuman extends Player {

    public PlayerHuman() {
        setPlayerName();
    }

    /**
     * Asks human player to shoot on an enemy cell
     * 
     * @param Player enemy
     * 
     * @return void
     */
    protected void shoot(Player enemy) {
        boolean error = true;
        int x = 0;
        int y = 0;

        ConsoleHelper.eraseConsole();
        board.showPlayBoard(this, enemy);

        while (error) {
            String input = "";
            System.out.print(playerName + ", which square do you want to fire? ");
            try {
                input = in.readLine();
            } catch (java.io.IOException e) {
                System.out.println("An error has occured : " + e);
            }
            if (Pattern.matches("[A-Ja-j][1-9]{1}[0]{0,1}", input)) {
                x = Integer.valueOf(input.substring(1)) - 1;
                y = Integer.valueOf(CoordinateHelper.letterCoordinateToNumber(input.substring(0, 1).toUpperCase()));
                if (CoordinateHelper.isValid(x, y)) {
                    if (!enemy.getBoard().getCell(x, y).isShot()) {
                        error = false;
                    } else {
                        System.out.println("You have already fired in the square.");
                    }
                } else {
                    System.out.println("This coordinate is invalid.");
                }
            }
        }

        Cell targetCell = enemy.getBoard().getCell(x, y);
        int cellValue = targetCell.getId();

        targetCell.shoot();
        setLastCellShot(x, y);
        incrementStatNbTotalShot();

        if (cellValue > 0) {
            Boat boatHitted = enemy.getBoard().getBoats(cellValue);
            boatHitted.getCells(x, y).shoot();
            incrementStatNbSuccessfullShot();
            board.showPlayBoard(this, enemy);
            if (boatHitted.isSunk()) {
                incrementStatNbBoatShot();
                System.out.println("You sunk the enemy's " + boatHitted.getName().toLowerCase() + "!");
            } else {
                System.out.println("You hit an opponent's ship!");
            }
        } else {
            board.showPlayBoard(this, enemy);
            System.out.println("Miss, try again next turn!");
        }
        ConsoleHelper.sleep(4000);
    }

    /**
     * Asks the player to place his ships.
     * A check is made to ensure that the placement of the boats respects the rules of the game.
     * 
     * @return void
     */
    protected void placeBoats() {
        for (int i = 0; i < Config.getNbBoats(); i++) {
            boolean error = true;
            String input = "";
            ConsoleHelper.eraseConsole();
            System.out.println("╔══════════════════════════════════════════════════════════════════╗");
            System.out.println("║                            Create Your War Fleet                             ║");
            System.out.println("╚══════════════════════════════════════════════════════════════════╝");
            System.out.println();
            System.out.println(playerName + ", you must place your ships in the grid.");
            System.out.println("To do this, you must indicate the orientation of your ship as well as its position of deployment.");
            System.out.println("If you enter \"" + TextHelper.ANSI_GREEN + "V" + TextHelper.ANSI_RESET + ""+ TextHelper.ANSI_PURPLE + "C6" + TextHelper.ANSI_RESET+ "\" for a ship of 3 squares, the boat will be deployed " + TextHelper.ANSI_GREEN + "vertically"+ TextHelper.ANSI_RESET + ", starting from " + TextHelper.ANSI_PURPLE + "C6" + TextHelper.ANSI_RESET+ " and ending in F6.");
            System.out.println("If you enter \"" + TextHelper.ANSI_GREEN + "H" + TextHelper.ANSI_RESET + ""+ TextHelper.ANSI_PURPLE + "I3" + TextHelper.ANSI_RESET+ "\" for a ship of 3 squares, the boat will be deployed " + TextHelper.ANSI_GREEN+ "horizontally" + TextHelper.ANSI_RESET + ", starting from " + TextHelper.ANSI_PURPLE + "I3"+ TextHelper.ANSI_RESET + " and ending in I6.");
            System.out.println("Be careful not to jump out of the game board, overlap two ships, or stick two ships together, in which case you will have to start placing your ship again.");
            System.out.println("Place them strategically! The battle with commence soon...");
            System.out.println();
            System.out.println("═════════════════════════════════════════════════════════════════════");
            System.out.println();
            ConsoleHelper.sleep(1500);
            int boatSize = Integer.valueOf(Config.getBoatsConfig(i)[2]);
            board.showPersonnalBoard();
            System.out.println(playerName + ", you have to place your " + Config.getBoatsConfig(i)[1] + " (" + boatSize + " squares).");
            while (error) {
                System.out.print("Enter a direction and a position (ex : VC6) : ");
                try {
                    input = in.readLine();
                } catch (java.io.IOException e) {
                    System.out.println("An error has occured : " + e);
                }
                if (Pattern.matches("^[HVhv][abcdefghijABCDEFGHIJ][1-9][0]{0,1}", input)) {
                    String direction = input.substring(0, 1).toUpperCase();
                    int x = Integer.valueOf(input.substring(2)) - 1;
                    int y = Integer.valueOf(CoordinateHelper.letterCoordinateToNumber(input.substring(1, 2).toUpperCase()));
                    Cell[] boatCoordinates = board.generateBoatCoordinates(x, y, direction, boatSize, Integer.valueOf(Config.getBoatsConfig(i)[0]));

                    System.out.println();
                    if (x < 0 || x > 9) {
                        System.out.println("The X-coordinate is invalid.");
                    } else if (y < 0 || y > 9) {
                        System.out.println("The Y-coordinate is invalid.");
                    } else if (!board.isInBoard(boatCoordinates)) {
                        System.out.println("Your ship is leaving the board.");
                    } else if (board.existsOverlap(boatCoordinates)) {
                        System.out.println("Your ship overlaps with another ship of yours.");
                    } else if (board.existsNeighbors(boatCoordinates)) {
                        System.out.println("Your ship must not be attached to another of your ships.");
                    } else {
                        board.addBoat(new Boat(boatCoordinates, Integer.valueOf(Config.getBoatsConfig(i)[0]), Config.getBoatsConfig(i)[1]));
                        error = false;
                    }
                }
            }
            if (i == Config.getNbBoats() - 1) {
                ConsoleHelper.eraseConsole();
                board.showPersonnalBoard();
                System.out.println(playerName + ", you have placed all your ships. Here is your board!");
                ConsoleHelper.sleep(3500);
            }
        }
    }

    /**
     * Asks the player to choose his pseudo
     * 
     * @return void
     */
    protected void setPlayerName() {
        String input = "";
        boolean error = true;
        do {
            System.out.print("How do you wish to be called (3-12 characters) ? ");
            try {
                input = in.readLine().replaceAll("\\s", "-");
            } catch (java.io.IOException e) {
                System.out.println("An error occured : " + e);
            }
            if (Pattern.matches("[A-Za-z-éèï]+", input) && (input.length() >= 3) && (input.length() <= 12)) {
                error = false;
            }
        } while (error);
        this.playerName = input;
    }
}