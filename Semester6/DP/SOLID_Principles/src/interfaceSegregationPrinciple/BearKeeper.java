package interfaceSegregationPrinciple;

/*
* larger interfaces should be split into smaller ones. By doing so, we can ensure that implementing classes only need
* to be concerned about the methods that are of interest to them.
* */
public interface BearKeeper {
    void washTheBear();
    void feedTheBear();
    void petTheBear();
}

//Let's fix this by splitting our large interface into three separate ones:
//ex: BearCleaner, BearFeeder, BearPetter

