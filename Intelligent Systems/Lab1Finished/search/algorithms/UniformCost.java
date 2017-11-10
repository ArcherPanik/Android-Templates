package search.algorithms;

import java.util.PriorityQueue;
import search.Node;
import search.Node.CostComparator;

public class UniformCost extends MainAlgorithm{
	
	CostComparator comp = new CostComparator();
	PriorityQueue <Node> frontier = new PriorityQueue <Node>(comp);
	
	@Override
	public void addToFrontier(Node n) {
		frontier.add(n);
	}
	
	@Override
	public Node extractFromFrontier() {
		return frontier.poll();
	}
        
        @Override
        public boolean frontierIsEmpty() {
            return frontier.isEmpty();
        }
	
}
