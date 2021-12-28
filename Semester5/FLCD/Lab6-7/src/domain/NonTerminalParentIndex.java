package domain;

public class NonTerminalParentIndex {
    private NonTerminalAndProduction nonterminalAndProduction;
    private int index;
    private int parent;

    public NonTerminalParentIndex(NonTerminalAndProduction nonterminalAndProduction, int index, int parent) {
        this.nonterminalAndProduction = nonterminalAndProduction;
        this.index = index;
        this.parent = parent;
    }

    public NonTerminalAndProduction getNonTerminalAndProduction() {
        return nonterminalAndProduction;
    }

    public void setNonTerminalAndProduction(NonTerminalAndProduction nonterminalAndProduction) {
        this.nonterminalAndProduction = nonterminalAndProduction;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "NonTerminalParentIndex{" +
                "nonTerminalAndProduction=" + nonterminalAndProduction +
                ", index=" + index +
                ", parent=" + parent +
                '}';
    }
}