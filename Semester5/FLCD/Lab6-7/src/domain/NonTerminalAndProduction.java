package domain;

public class NonTerminalAndProduction {
    private String nonTerminal;
    private Integer productionIndex;

    public NonTerminalAndProduction(String nonTerminal, Integer productionIndex) {
        this.nonTerminal = nonTerminal;
        this.productionIndex = productionIndex;
    }

    public String getNonTerminal() {
        return nonTerminal;
    }

    public void setNonTerminal(String nonTerminal) {
        this.nonTerminal = nonTerminal;
    }

    public Integer getProductionIndex() {
        return productionIndex;
    }

    public void setProductionIndex(Integer productionIndex) {
        this.productionIndex = productionIndex;
    }

    @Override
    public String toString() {
        return "(" + this.getNonTerminal() + "->" + this.getProductionIndex() + ")";
    }
}
