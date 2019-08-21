import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Solver {
	private int size = 11;
	private PriorityQueue<Node> frontierList = new PriorityQueue<Node>(size, new Priority());
	private HashMap<String, Integer> exploredList  = new HashMap<String,Integer>();
	
	public Solver(Board initial) {
		
		Node parent = new Node(initial, 0, null);
		exploredList.put(CharToString(initial.BoardArray()), initial.h1() + parent.moves);

		int SearchCost = 0;
		
		while(!parent.board.isGoal()) {
			
			for (Board board : parent.board.neighbors()) {
				if(exploredList.containsKey(CharToString(board.BoardArray()))) {
					continue;
				}
				SearchCost = SearchCost + 1;
				frontierList.add(new Node(board, parent.moves+1, parent));
            }
			parent = frontierList.poll();
			exploredList.put(CharToString(parent.board.BoardArray()), parent.board.h1() + parent.moves);	
		}
		
		System.out.println();
		System.out.println("Heuristic Function: h1 (misplaced tiles)");
		//System.out.println("Heuristic Function: h2 (sum of the distances)");
		System.out.println("Search Cost: " + SearchCost);
		System.out.println("Moves: " + parent.moves);
		System.out.println("------------------");
		
		while(parent != null) {
			for(int i=0; i<9; i++) {
				System.out.print((int)parent.board.BoardArray()[i]);	
			}
			System.out.println("\n------------------");
			parent = parent.prev;
		}
	}
	
	public String CharToString(char[] input) {
		int[] intArray = new int[9];
		for(int i = 0; i < 9; ++i) {
		    intArray[i] = (int) input[i];
		}
		return Arrays.toString(intArray);
	}
	
	private class Node {
        private Board board;
        private int moves;
        private Node prev;

        public Node(Board board, int moves, Node prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
        }
    }
	
	private class Priority implements Comparator<Node> {
        public int compare(Node n1, Node n2) {
            int num1 = n1.board.h1() + n1.moves;
            int num2 = n2.board.h1() + n2.moves;
            return num1 - num2;
        }
    }
}