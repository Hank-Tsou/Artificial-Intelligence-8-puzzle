import java.util.ArrayList;
import java.util.List;

public class Board {

	private char[] BoardArray = new char[9];
	private int zero;
	private int h1;
    private int h2;

    public Board(char[] blocks, int init_zero) {
    		zero = init_zero;
    		for (int j = 0; j < 9; j++) {
    			BoardArray[j] = (char)Integer.parseInt(String.valueOf(blocks[j]));
    		}
        h1 = cal_h1(BoardArray);
        h2 = cal_h2(BoardArray);
    }
    
    private int cal_h1(char[] chars) {
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
        		if (chars[i] == 0) {
        			continue;
        		}
        		else if (chars[i] != i) {
            		count = count + 1;
            }
        }
        return count;
    }

    private int cal_h2(char[] chars) {
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
        		if (chars[i] == 0) {
        			continue;
        		} 
        		else {
        			count = count + Math.abs(i / 3 - chars[i] / 3) + Math.abs(i % 3 - chars[i] % 3);
        		}
        }
        return count;
    }
    
	public boolean isGoal() {
		return h1 == 0;          
	}
	
	public Iterable<Board> neighbors() {
        List<Board> neighbours = new ArrayList<>();
        // move up
        if (zero / 3 != 0) {
            neighbours.add(neighbour(zero - 3));
        }
        // move down
        if (zero / 3 != 2 ) {
            neighbours.add(neighbour(zero + 3));
        }
        // move left
        if (zero % 3 != 0) {
            neighbours.add(neighbour(zero - 1));
        }
        // move right
        if (zero % 3 != 2) {
            neighbours.add(neighbour(zero + 1));
        }
        return neighbours;
    }
	
	public Board neighbour(int i) {
        char[] copy = BoardArray.clone();
        copy[zero] = copy[i];
        copy[i] = 0;
        
        h1 = cal_h1(copy);
        h2 = cal_h2(copy);
        
        return new Board(copy, i, h1, h2);
    }
	
	private Board(char[] BoardArray, int zero, int h1, int h2) {
        this.BoardArray = BoardArray;
        this.zero = zero;
        this.h1 = h1;
        this.h2 = h2;
    }
	
	public int[] to_String() {
		int[] temp = new int[9];
		
		for(int i = 0; i < 9; i++) {
			temp[i] = (int)BoardArray[i];
			System.out.println(temp[i]);
		}
		return temp;
	}
	
	public int h1() {
		return h1;
	}
	public int h2() {
		return h2;
	}
	public int zero() {
		return zero;
	}
	public char[] BoardArray() {
		return BoardArray;
	}  
}
