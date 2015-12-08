import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;


public class Solver{

	public int[] solutionState;
	public int[] currentState; //state that is achieved through n random moves (what we will search for in tree)
	public int[] testState; //state manipulated in search functions
	private int moveSize;
	int sideLength;
	private int blankLocation;
	private int solutionZero;
	private Queue<Node> toCheck;
	private LinkedList<Node> possibleSolns;
	private HashSet<int[]> seenStates;
	
	
	//START CONSTRUCTORS
	//Constructor w/ default solution (INPUT: how many steps to take in randomization)
	//ASSUMPTIONS: solutionState is length 9.
	Solver(int moveSize) {
		toCheck = new LinkedList<Node>();
		this.moveSize = moveSize;
		sideLength = 3;
		solutionState = new int[] {0,1,2,3,4,5,6,7,8};
		currentState = new int[] {0,1,2,3,4,5,6,7,8};
		blankLocation = 0;
		solutionZero = 0;
		randomize();
	}
	//Constructor w/ input solution (INPUT: how many steps to take in randomization, initialize state)
	//ASSUMPTIONS: solutionState is length 9 and contains 0.
	Solver(int moveSize, int[] solutionState) {
		toCheck = new LinkedList<Node>();
		this.moveSize = moveSize;
		sideLength = 3;
		this.solutionState = solutionState;
		currentState = solutionState.clone();
		blankLocation = -1;
		for(int i = 0; i < 9; i++) {
			if(solutionState[i] == 0) {
				blankLocation = i;
				solutionZero = i;
				break;
			}
		}
		randomize();
	}
	//Constructor w/InputSize (INPUT: how many steps to take in randomization, How many filled spaces in puzzle [i.e. 15 for 4x4])
	//ASSUMPTIONS: NPuzzle is a perfect square - 1.
	Solver(int moveSize, int NPuzzle) {
		solutionState = new int[NPuzzle+1];
		toCheck = new LinkedList<Node>();
		this.moveSize = moveSize;
		sideLength = (int) Math.sqrt(NPuzzle+1);
		for(int i = 0; i < NPuzzle+1; i++) {
			solutionState[i] = i;
		}
		currentState = solutionState.clone();
		blankLocation = 0;
		solutionZero = 0;
		randomize();
	}
	//Constructor w/ inputSize and SolutionState (INPUT: how many steps to take in randomization, initialize state, How many filled spaces in puzzle [i.e. 15 for 4x4])
	//ASSUMPTIONS: solutionsState is of length NPuzzle+1 and contains 0. NPuzzle is a perfect square - 1.
	Solver(int moveSize, int[] solutionState, int NPuzzle) {
		sideLength = (int) Math.sqrt(NPuzzle+1);
		toCheck = new LinkedList<Node>();
		this.moveSize = moveSize;
		this.solutionState = solutionState;
		currentState = solutionState.clone();
		blankLocation = -1;
		for(int i = 0; i < NPuzzle+1; i++) {
			if(solutionState[i] == 0) {
				blankLocation = i;
				solutionZero = i;
				break;
			}
		}
		randomize();
	}
	//END CONSTRUCTORS
	
	
	//Randomizes SolutionState
	private void randomize() {
		for(int i = 0; i < moveSize; i++) {
			//move zero location (take into account that not all spaces can move in any direction!) 
			//corners			:	2 possible moves
			//non-corner edges	:	3 possible moves
			//central space		:	4 possible moves
			boolean check = true;
			int random = (int) (Math.random() * 4);
			while(check) {
				switch(random) { 
					case 0: if(blankLocation % sideLength != 0) { //Move Left
								int temp = currentState[blankLocation];
								currentState[blankLocation] = currentState[blankLocation-1];
								blankLocation--;
								currentState[blankLocation] = temp;
								check = false;
								continue;
							}
					case 1: if(blankLocation % sideLength != sideLength-1) { //Move Right
								int temp = currentState[blankLocation];
								currentState[blankLocation] = currentState[blankLocation+1];
								blankLocation++;
								currentState[blankLocation] = temp;
								check = false;
								continue;
							}
					case 2:	if(blankLocation >= sideLength) { //Move Up
								int temp = currentState[blankLocation];
								currentState[blankLocation] = currentState[blankLocation-sideLength];
								blankLocation = blankLocation - sideLength;
								currentState[blankLocation] = temp;
								check = false;
								continue;
							}
					case 3:	if(blankLocation < sideLength*(sideLength-1)) { //Move Down
								int temp = currentState[blankLocation];
								currentState[blankLocation] = currentState[blankLocation+sideLength];
								blankLocation = blankLocation + sideLength;
								currentState[blankLocation] = temp;
								check = false;
								continue;
							}
							random = 0;// to ensure no one gets stuck in case 3 loop
				}
			}
		}
	}
	
	//First solution Node we find is an answer with the shortest number of steps!
	public Node breadthSearch() {
		seenStates = new HashSet<int[]>();
		possibleSolns = new LinkedList<Node>();
		toCheck.add(new Node(solutionState,null,solutionZero));
		if(isEqual(toCheck.peek())) return toCheck.poll();
		createBreadth(toCheck.remove());
		while(!toCheck.isEmpty()) {
			if(isEqual(toCheck.peek()))
				return possibleSolns.getFirst(); //solution found
			createBreadth(toCheck.remove()); //make more children
		}
		return null; //No possible solution.
	}
	//Creates children for a node in breadth search (left,right,up,down)
		private void createBreadth(Node parent) {
			moveLeft(parent);
			moveRight(parent);
			moveUp(parent);
			moveDown(parent);
		}
	
	//Must check all nodes as simpler solution may be other end of "tree". unfortunate, but necessary
	public Node depthSearch() {
		//if(moveSize>31) moveSize = 31; //Set max number of steps for depth search if over 31.
		seenStates = new HashSet<int[]>();
		possibleSolns = new LinkedList<Node>();
		toCheck.add(new Node(solutionState,null,solutionZero,0));
		if(isEqual(toCheck.peek())) return toCheck.peek();
		createDepth(toCheck.remove());
		Node curr;
		Iterator<Node> iter = possibleSolns.iterator();
		Node solution = iter.next();
		while(iter.hasNext()) {
			curr = iter.next();
			if(curr.height < solution.height)
				solution = curr;
		}
		return solution;
		//Search possibleSolns for smallest solution
	}
	//Creates children for a node in Depth search
	private void createDepth(Node parent) {
		if(isEqual(parent)) return;
		if(parent.height==moveSize) return;
		if(moveLeft(parent)) createDepth(toCheck.remove());
		if(moveRight(parent)) createDepth(toCheck.remove());
		if(moveUp(parent)) createDepth(toCheck.remove());
		if(moveDown(parent)) createDepth(toCheck.remove());
		seenStates.remove(parent.state);
	}
	
	//Tries to move empty space left,right,up,and down (Allows us to reuse code for both searches. Originally had to separate methods for each, seemed redundant)
	private boolean moveLeft(Node parent) {
		if(parent.blankLocation % sideLength != 0) {
			testState = parent.state.clone();
			int temp = testState[parent.blankLocation];
			testState[parent.blankLocation] = testState[parent.blankLocation-1];
			testState[parent.blankLocation-1] = temp;
			if(seenStates.contains(testState)) //Check for loops
				return false;
			else 
				seenStates.add(testState);
			toCheck.add(new Node(testState,parent,parent.blankLocation-1,parent.height+1));
			return true;
		}
		return false;
			
	}
	private boolean moveRight(Node parent) {
		if(parent.blankLocation % sideLength != sideLength-1) {
			testState = parent.state.clone();
			int temp = testState[parent.blankLocation];
			testState[parent.blankLocation] = testState[parent.blankLocation+1];
			testState[parent.blankLocation+1] = temp;
			if(seenStates.contains(testState)) //Check for loops
				return false;
			else 
				seenStates.add(testState);
			toCheck.add(new Node(testState,parent,parent.blankLocation+1,parent.height+1));
			return true;
		}
		return false;
	}
	private boolean moveUp(Node parent) {
		if(parent.blankLocation >= sideLength) {
			testState = parent.state.clone();
			int temp = testState[parent.blankLocation];
			testState[parent.blankLocation] = testState[parent.blankLocation-sideLength];
			testState[parent.blankLocation-sideLength] = temp;
			if(seenStates.contains(testState)) //Check for loops
				return false;
			else 
				seenStates.add(testState);
			toCheck.add(new Node(testState,parent,parent.blankLocation-sideLength,parent.height+1));
			return true;
		}
		return false;
	}
	private boolean moveDown(Node parent) {
		if(parent.blankLocation < sideLength*(sideLength-1)) {
			testState = parent.state.clone();
			int temp = testState[parent.blankLocation];
			testState[parent.blankLocation] = testState[parent.blankLocation+sideLength];
			testState[parent.blankLocation+sideLength] = temp;
			if(seenStates.contains(testState)) //Check for loops
				return false;
			else 
				seenStates.add(testState);
			toCheck.add(new Node(testState,parent,parent.blankLocation+sideLength,parent.height+1));
			return true;
		}
		return false;
	}
	
	//Checks if a node is a possible solution Node
	private boolean isEqual(Node current) {
		for(int i =0; i < sideLength*sideLength; i++) {
			if(current.state[i]!=currentState[i])
				return false;
		}
		possibleSolns.add(current); //List is helpful for depth search, not so much for breadth search...
		return true;
	}
	
}