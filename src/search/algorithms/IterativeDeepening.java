package search.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import search.Action;
import search.Node;

public class IterativeDeepening extends MainAlgorithm{
	
	Stack <Node> frontier = new Stack <Node>();
	static int limit = 1;
        @Override
        public void search() {
            totalCost = 0;
            expandedNodes = 0;
            searchTime = System.currentTimeMillis();
            // Creates a new sequence of actions
            actionSequence= new ArrayList<Action>();
            // Auxiliary variables.
            Node result = new Node(problem.initialState());
            

            for ( int i = 1; i < Integer.MAX_VALUE; i++ ) {
                result = limitedDepthSearch(i);
                if(problem.testGoal(result.getState()))
                    return;
            }
            
            // Creates the root of the tree
            
            
            
            
		
        // Calculates the search time.
            searchTime = System.currentTimeMillis()-searchTime;
            
            totalCost = result.getCost();
            while (!result.getState().equals(problem.initialState())) {
                actionSequence.add(result.getAction());
                result = result.getParent();
            }
            
		Collections.reverse(actionSequence);        
        }
        
        public Node limitedDepthSearch(int l) {
            
            ArrayList <Node> successors = new ArrayList<Node>();
            Node chosen = new Node(problem.initialState());       
            addToFrontier(chosen);
            
            do {
                
                    if(frontierIsEmpty())
                        return chosen; 
                    chosen = extractFromFrontier();

                    if(chosen.getDepth() < limit) {
                        successors = getSuccessors(chosen);

                        for(Node n : successors) {
                            addToFrontier(n);
                        }
                    }
                
            } while(!problem.testGoal(chosen.getState()));
            
            return chosen;
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

