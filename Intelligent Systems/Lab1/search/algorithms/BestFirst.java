package search.algorithms;
import java.util.PriorityQueue;
import search.Node.HeuristicComparator;
import search.Node;

public class BestFirst extends MainAlgorithm{
	
	HeuristicComparator comp = new HeuristicComparator();
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
