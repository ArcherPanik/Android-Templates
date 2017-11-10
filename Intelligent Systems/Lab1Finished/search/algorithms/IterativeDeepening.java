package search.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import search.Action;
import search.Node;

public class IterativeDeepening extends MainAlgorithm {

    Stack<Node> frontier = new Stack<Node>();

    @Override
    public void search() {
        int limit = 1;
        Node chosen;
        actionSequence = new ArrayList<Action>();
        totalCost = 0;
        expandedNodes = 0;
        searchTime = System.currentTimeMillis();
        ArrayList<Node> successors = new ArrayList<Node>();
        do {
            
            chosen = new Node(problem.initialState());
            addToFrontier(chosen);

            do  {
                
                chosen = extractFromFrontier();

                if (chosen.getDepth() < limit) {
                    successors = getSuccessors(chosen);

                    for (Node n : successors) {
                        addToFrontier(n);
                    }
                }
                
                if(problem.testGoal(chosen.getState()))
                    break;
            } while ((!frontierIsEmpty()));
            
            resetFrontier();
            limit++;
            
        } while (!problem.testGoal(chosen.getState()));

        // Calculates the search time.
        searchTime = System.currentTimeMillis() - searchTime;
        totalCost = chosen.getCost();

        // As the list of actions has been obtained upwards, reverses it.
        while (!chosen.getState().equals(problem.initialState())) {
            actionSequence.add(chosen.getAction());
            chosen = chosen.getParent();
        }
        Collections.reverse(actionSequence);
    }

    @Override
    public void addToFrontier(Node n) {
        frontier.push(n);
    }

    @Override
    public Node extractFromFrontier() {
        return frontier.pop();
    }

    public void resetFrontier() {
        frontier.clear();
        System.out.println(frontier.toString());
    }
    
    @Override
    public boolean frontierIsEmpty() {
        return frontier.isEmpty();
    }
}
