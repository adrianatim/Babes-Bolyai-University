package service;

import domain.GrammarModel;
import domain.NonTerminalAndProduction;
import domain.NonTerminalParentIndex;
import domain.TableModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TableBuilder {
    private final GrammarModel grammarModel;
    private final List<Object> workingStack;
    private final List<TableModel> result = new ArrayList<>();
    private int nextNonTerminal = 1;

    public TableBuilder(GrammarModel grammarModel, Stack<Object> workingStack) {
        this.grammarModel = grammarModel;
        this.workingStack = this.fromStackToList(workingStack);
    }

    public void buildTable() throws Exception {
        NonTerminalAndProduction nonterminalAndProduction = (NonTerminalAndProduction) this.workingStack.get(0);
        List<NonTerminalParentIndex> newNonTerminals = new ArrayList<>();

        generateTableRows(nonterminalAndProduction, 0, -1, false, newNonTerminals);

        int iterator = 0;
        while (newNonTerminals.size() > iterator) {
            NonTerminalParentIndex currentNonTerminalParentIndex = newNonTerminals.get(iterator);
            generateTableRows(currentNonTerminalParentIndex.getNonTerminalAndProduction(),
                    currentNonTerminalParentIndex.getIndex(), currentNonTerminalParentIndex.getParent(), true,
                    newNonTerminals);
            iterator++;
        }
        printResult();
    }

    private void printResult() {
        this.result.forEach(System.out::println);
    }

    private void generateTableRows(NonTerminalAndProduction nonterminalAndProduction, int index, int parentIndex,
                                   boolean foundFirst, List<NonTerminalParentIndex> nonTerminalParentIndices) throws Exception {

        if (!foundFirst) {
            addElement(index, nonterminalAndProduction.getNonTerminal(), parentIndex, -1);
            parentIndex = index;
        }

        List<String> productionExpanded = this.grammarModel
                .getProductionsForNonTerminal(nonterminalAndProduction.getNonTerminal())
                .get(nonterminalAndProduction.getProductionIndex()).getProductionRule();
        int leftSiblingIndex = -1;
        for (String element : productionExpanded) {
            index++;
            addElement(index, element, parentIndex, leftSiblingIndex);
            if (grammarModel.getNonTerminals().contains(element)) {
                NonTerminalAndProduction nextNonTerminal = findNextNonTerminal(this.nextNonTerminal);
                if (nextNonTerminal != null) {
                    nonTerminalParentIndices.add(new NonTerminalParentIndex(nextNonTerminal, index, index));
                }
            }
            leftSiblingIndex = index;
        }
        final int finalIndex = index;
        nonTerminalParentIndices.forEach(element -> element.setIndex(finalIndex));
    }

    private void addElement(int index, String value, int parent, int leftSibling) {
        TableModel tableModel = new TableModel(index, value, parent, leftSibling);
        this.result.add(tableModel);
    }

    //return the next nonTerminal found in the working stack
    private NonTerminalAndProduction findNextNonTerminal(int startIndex) {
        for (int index = startIndex; index < this.workingStack.size(); index++) {
            if (this.workingStack.get(index) instanceof NonTerminalAndProduction) {
                this.nextNonTerminal = index + 1;
                return (NonTerminalAndProduction) this.workingStack.get(index);
            }
        }
        return null;
    }

    private List<Object> fromStackToList(Stack<Object> stack) {
        return new ArrayList<>(stack);
    }
}