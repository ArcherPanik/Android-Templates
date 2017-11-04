package search.algorithms;

import java.util.Stack;
import search.Node;

public class DepthFirst extends MainAlgorithm{
	
	Stack <Node> frontier = new Stack <Node>();
	
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
