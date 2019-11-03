//Cameron Clark
//CS110
//class that creates a Card from the game Set. After I was far too deep to change anything, I realized
//that "SetCard" was a fairly confusing name for the class, but it was too late.
public class SetCard {
    private Shape shape;
    private Number number;
    private Color color;
    private Shade shade;

    public static enum Shape {
        OVALS, SQUIGGLES, DIAMONDS;
    }

    public static enum Number {
        ONE, TWO, THREE;
    }

    public static enum Color {
        RED, BLUE, GREEN;
    }

    public static enum Shade {
        STRIPED, SOLID, OUTLINED;
    }

    /*
    The Default constructor will take all the input parameters and set the shape, number, and color of the cards.
    */
    public SetCard(Shape shape, Number number, Color color, Shade shade) {
        this.shape = shape;
        this.number = number;
        this.color = color;
        this.shade = shade;
    }

    /*
    toString method returns the card in a human readable form
    @returns str String that contains all information about the card.
    */
    @Override
    public String toString() {
        String str = this.shape.name() + "_" + this.number.name() + "_" + this.color.name() + "_" + this.shade.name();
        return str;
    }

    /*
    shapeSet will check if all of the shapes in the card will produce a set -
    either all shapes the same, or all different.
    @returns boolean true if they will produce a set, false otherwise.
    @param card1 first card to be compared
    @param card2 second card to be compared
    @param card3 third card to be compared
    */
    public static boolean shapeSet(SetCard card1, SetCard card2, SetCard card3) {
        if (card1.shape == card2.shape && card1.shape == card3.shape && card2.shape == card3.shape)
            return true;
        else if (card1.shape != card2.shape && card1.shape != card3.shape && card2.shape != card3.shape)
            return true;
        else
            return false;
    }

    /*
    numberSet will check if all of the number in the card will produce a set -
    either all number the same, or all different.
    @returns boolean true if they will produce a set, false otherwise.
    @param card1 first card to be compared
    @param card2 second card to be compared
    @param card3 third card to be compared
    */
    public static boolean numberSet(SetCard card1, SetCard card2, SetCard card3) {
        if (card1.number == card2.number && card1.number == card3.number && card2.number == card3.number)
            return true;
        else if (card1.number != card2.number && card1.number != card3.number && card2.number != card3.number)
            return true;
        else
            return false;
    }

    /*
    colorSet will check if all of the color in the card will produce a set -
    either all color the same, or all different.
    @returns boolean true if they will produce a set, false otherwise.
    @param card1 first card to be compared
    @param card2 second card to be compared
    @param card3 third card to be compared
    */
    public static boolean colorSet(SetCard card1, SetCard card2, SetCard card3) {
        if (card1.color == card2.color && card1.color == card3.color && card2.color == card3.color)
            return true;
        else if (card1.color != card2.color && card1.color != card3.color && card2.color != card3.color)
            return true;
        else
            return false;
    }

    /*
    colorSet will check if all of the shade in the card will produce a set -
    either all shade the same, or all different.
    @returns boolean true if they will produce a set, false otherwise.
    @param card1 first card to be compared
    @param card2 second card to be compared
    @param card3 third card to be compared
    */
    public static boolean shadeSet(SetCard card1, SetCard card2, SetCard card3) {
        if (card1.shade == card2.shade && card1.shade == card3.shade && card2.shade == card3.shade)
            return true;
        else if (card1.shade != card2.shade && card1.shade != card3.shade && card2.shade != card3.shade)
            return true;
        else
            return false;
    }

    /*
    isSet will compare three cards and see if they will produce a set, using the past four methods.
    @returns boolean true if they will produce a set, false otherwise.
    @param card1 first card to be compared
    @param card2 second card to be compared
    @param card3 third card to be compared
    */
    public static boolean isSet(SetCard card1, SetCard card2, SetCard card3) {
        if (SetCard.shapeSet(card1, card2, card3) && SetCard.numberSet(card1, card2, card3) && SetCard.colorSet(card1, card2, card3) && SetCard.shadeSet(card1, card2, card3))
            return true;
        else
            return false;
    }
}