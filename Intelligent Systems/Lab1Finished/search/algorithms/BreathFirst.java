package search.algorithms;

import java.util.LinkedList;
import java.util.Queue;
import search.*;

public class BreathFirst extends MainAlgorithm{
	
    Queue <Node> frontier = new LinkedList <Node>();
	
	@Override
	public void addToFrontier(Node n) {
		frontier.offer(n);
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