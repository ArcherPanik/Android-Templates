package search.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import search.Action;
import search.Node;

public class LimitedDepth extends MainAlgorithm{
	
	Stack <Node> frontier = new Stack <Node>();
	static int limit = 20;
        @Override
        public void search() {
            totalCost = 0;
            expandedNodes = 0;
            searchTime = System.currentTimeMillis();
            // Creates a new sequence of actions
            actionSequence= new ArrayList<Action>();
            // Auxiliary variables.
            Node chosen;
            ArrayList <Node> successors = new ArrayList<Node>();

            // Creates the root of the tree
            chosen = new Node(problem.initialState());       
            addToFrontier(chosen);

            do {
                if(frontierIsEmpty())
                    return;
                chosen = extractFromFrontier();
                
                if(chosen.getDepth() < limit) {
                    successors = getSuccessors(chosen);
                
                    for(Node n : successors) {
                        addToFrontier(n);
                    }
                }
                
            } while(!problem.testGoal(chosen.getState()));
		
        // Calculates the search time.
            searchTime = System.currentTimeMillis()-searchTime;
            
            totalCost = chosen.getCost();
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
        
        @Override
        public boolean frontierIsEmpty() {
            return frontier.isEmpty();
        }
}
