/**
 * Represents a single Scrabble tile.
 * Each tile contains a letter and a point value.
 *
 * @author Mohammad Albatainah
 * @author Isaiah Johnson
 * @version 2025-11-17
 */
public class Tile {

    private char letter;
    private int value;

    /**
     * Creates a Tile with a given letter and value.
     *
     * @param letter the letter on the tile
     * @param value  the score value of the tile
     */
    Tile(char letter, int value) {
        this.letter = letter;
        this.value = value;
    }

    /**
     * Sets the letter on this tile.
     * @param letter the new letter
     */
    public void setLetter(char letter) {
        this.letter = letter;
    }

    /**
     * Sets the value of this tile.
     * @param value the new point value
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Returns the letter shown on this tile.
     * @return the tile's letter
     */
    public char getLetter() {
        return this.letter;
    }

    /**
     * Returns the point value of this tile.
     * @return the tile's value
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Checks whether this tile has the same letter as another tile.
     * @param other the tile to compare with
     * @return true if both tiles have the same letter, false otherwise
     */
    public boolean equals(Tile other) {
        return this.letter == other.letter;
    }

    /**
     * Returns a formatted text version of this tile.
     * @return a string that describes the tile
     */
    public String toString() {
        return String.format("%c (value = %d)", letter, value);
    }
}