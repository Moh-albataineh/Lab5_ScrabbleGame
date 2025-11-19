import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Scrabble like word game.
 * Generates random tiles for the player and lets them try to spell words.
 * If the word can be spelled, the tiles are removed and the score is shown.
 *
 * @author Mohammad Albataineh
 * @author Isaiah Johnson
 * @version 2025-11-17
 */
public class App {

    /** List containing all possible tiles (A–Z) with their scores. */
    private static ArrayList<Tile> allTiles = new ArrayList<>();

    /**
     * Program entry point.
     * Creates the tile set, runs the game loop, and interacts with the user
     * until they choose to quit.
     * @param args (not used)
     */
    public static void main(String[] args) {

        createTiles(allTiles);

        ArrayList<Tile> hand = new ArrayList<>();

        getNewTileSet(hand, 7);

        Scanner in = new Scanner(System.in);
        boolean keepPlaying = true;

        while (keepPlaying) {

            System.out.print("Here is your tile set: ");
            printHand(hand);

            System.out.print("Enter a word: ");
            String word = in.next().toLowerCase();

            if (canSpell(hand, word)) {
                System.out.println("yes, can spell " + word);
            } else {
                System.out.println("no, cannot spell " + word);


                System.out.print("Do you want to change one tile (yes,no)? ");
                String changeAns = in.next().toLowerCase();
                if (changeAns.equals("yes")) {
                    changeRandomTile(hand);
                    System.out.print("New tile set: ");
                    printHand(hand);
                }
            }

            System.out.print("Do you want to continue(yes,no)? ");
            String answer = in.next().toLowerCase();

            if (!answer.equals("yes")) {
                keepPlaying = false;
            } else {

                getNewTileSet(hand, 7);
            }
        }

        System.out.println("Goodbye!");
        in.close();
    }

    /**
     * Initializes the list of all possible tiles with their letters and values.
     *
     * @param tiles the list that will be filled with all tiles A–Z
     */
    public static void createTiles(ArrayList<Tile> tiles) {
        tiles.add(new Tile('A', 1));
        tiles.add(new Tile('B', 3));
        tiles.add(new Tile('C', 3));
        tiles.add(new Tile('D', 2));
        tiles.add(new Tile('E', 1));
        tiles.add(new Tile('F', 1));
        tiles.add(new Tile('G', 1));
        tiles.add(new Tile('H', 4));
        tiles.add(new Tile('I', 1));
        tiles.add(new Tile('J', 8));
        tiles.add(new Tile('K', 5));
        tiles.add(new Tile('L', 1));
        tiles.add(new Tile('M', 3));
        tiles.add(new Tile('N', 1));
        tiles.add(new Tile('O', 1));
        tiles.add(new Tile('P', 3));
        tiles.add(new Tile('Q', 10));
        tiles.add(new Tile('R', 6));
        tiles.add(new Tile('S', 1));
        tiles.add(new Tile('T', 1));
        tiles.add(new Tile('U', 1));
        tiles.add(new Tile('V', 4));
        tiles.add(new Tile('W', 4));
        tiles.add(new Tile('X', 8));
        tiles.add(new Tile('Y', 4));
        tiles.add(new Tile('Z', 10));
    }

     /**
     * Replaces one random tile in the player's hand with a new random tile
     * from the full list of tiles.
     * @param hand the current set of tiles in the player's hand
     */
    public static void changeRandomTile(ArrayList<Tile> hand) {
        if (hand.isEmpty()) {
            return;
        }

        Random rand = new Random();


        int handIndex = rand.nextInt(hand.size());


        int allIndex = rand.nextInt(allTiles.size());
        Tile base = allTiles.get(allIndex);


        hand.set(handIndex, new Tile(base.getLetter(), base.getValue()));
    }

    /**
     * Prints the letters in the player's current hand in a formatted way.
     * @param hand the current set of tiles in the player's hand
     */
    public static void printHand(ArrayList<Tile> hand) {
        for (int i = 0; i < hand.size(); i++) {
            char letter = hand.get(i).getLetter();
            letter = Character.toLowerCase(letter);

            System.out.print(letter);

            if (i < hand.size() - 1) {
                System.out.print(" // ");
            }
        }
        System.out.println();
    }

    /**
     * Fills the current set of tiles with random tiles until it reaches
     * the expected number of tiles.
     *
     * @param hand the current set of tiles in the player's hand
     * @param expectedSize the desired number of tiles ( 7)
     */
    public static void getNewTileSet(ArrayList<Tile> hand, int expectedSize) {
        Random rand = new Random();

        while (hand.size() < expectedSize) {
            int index = rand.nextInt(allTiles.size());
            Tile base = allTiles.get(index);
            hand.add(new Tile(base.getLetter(), base.getValue()));
        }
    }

    /**
     * Checks whether the given word can be spelled using the tiles in the hand.
     * If the word can be spelled, the used tiles are removed from the hand
     * and the score for the word is printed.
     *
     * @param hand the current set of tiles in the player's hand
     * @param word the word to be tested
     * @return { true} if the word can be spelled using the tiles;
     *         { false} otherwise
     */
    public static boolean canSpell(ArrayList<Tile> hand, String word) {

        ArrayList<Tile> temp = new ArrayList<>(hand);
        int score = 0;

        for (int i = 0; i < word.length(); i++) {
            char c = Character.toUpperCase(word.charAt(i));
            boolean found = false;

            for (int j = 0; j < temp.size(); j++) {
                Tile t = temp.get(j);
                if (t.getLetter() == c) {
                    score += t.getValue();
                    temp.remove(j);
                    found = true;
                    break;
                }
            }

            if (!found) {
                return false;
            }
        }
        
        hand.clear();
        hand.addAll(temp);

        System.out.println("Score = " + score);

        return true;
    }
}
