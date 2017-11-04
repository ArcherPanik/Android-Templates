package search.algorithms;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Collection;

import problems.maze.MazeState;
import search.*;

public abstract class MainAlgorithm extends SearchAlgorithm {

    @Override
    public void search() {
        totalCost = 0;
        expandedNodes = 0;
        searchTime = System.currentTimeMillis();
        // Creates a new sequence of actions
        actionSequence = new ArrayList<Action>();
        // Auxiliary variables.
        Node chosen;
        ArrayList<Node> successors = new ArrayList<Node>();

        // Creates the root of the tree
        chosen = new Node(problem.initialState());
        addToFrontier(chosen);

        do {
            if (frontierIsEmpty()) {
                return;
            }
            chosen = extractFromFrontier();
            successors = getSuccessors(chosen);

            for (Node n : successors) {
                addToFrontier(n);
            }

        } while (!problem.testGoal(chosen.getState()));

        // Calculates the search time.
        searchTime = System.currentTimeMillis() - searchTime;

            
            totalCost = chosen.getCost();
            
            while (!chosen.getState().equals(problem.initialState())) {
                actionSequence.add(chosen.getAction());
                chosen = chosen.getParent();
            }



        // As the list of actions has been obtained upwards, reverses it.
        Collections.reverse(actionSequence);

    }

    public void addToFrontier(Node n) {
    }

    public Node extractFromFrontier() {
        return null;
    }

    public boolean frontierIsEmpty() {
        return false;
    }
}
