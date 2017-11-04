package search.algorithms;
import java.util.PriorityQueue;
import search.Node.EvaluationComparator;
import search.Node;

public class AStar extends MainAlgorithm{
	
	EvaluationComparator comp = new EvaluationComparator();
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
