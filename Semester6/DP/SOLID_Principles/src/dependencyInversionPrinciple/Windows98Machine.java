package dependencyInversionPrinciple;

public class Windows98Machine {

    //before
    /*private final StandardKeyboard keyboard;
    private final Monitor monitor;

    public Windows98Machine() {
        monitor = new Monitor();
        keyboard = new StandardKeyboard();
    }*/

    //By declaring the StandardKeyboard and Monitor with the new keyword, we've tightly coupled these three classes together.
    //after
    private final Keyboard keyboard;
    private final Monitor monitor;

    public Windows98Machine(Keyboard keyboard, Monitor monitor) {
        this.keyboard = keyboard;
        this.monitor = monitor;
    }

    //Now our classes are decoupled and communicate through the Keyboard abstraction
    //We can follow the same principle for the Monitor class.
}