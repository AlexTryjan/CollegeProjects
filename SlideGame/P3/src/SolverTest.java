import org.junit.Test;


public class SolverTest {
	//Reads across game like a book. i.e. base case is 012345678 for 8-tile game
	
	@Test
	public void testRandomizer() {
		Solver test = new Solver(346);//Change 346 to change number of steps taken to randomize
		System.out.println("Randomized (Current) State");
		for(int i = 0; i < 9; i++) 
			System.out.print(test.currentState[i]);
		System.out.println();
		System.out.println("SolutionState");
		for(int i = 0; i < 9; i++) 
			System.out.print(test.solutionState[i]);
		System.out.println();
		System.out.println();
	}
	
	@Test
		public void testBreadthSearchInitialized() {
			System.out.println("Breadth First Initialized");
			int[] array = {1,2,3,4,5,7,6,8,0};
			Solver test = new Solver(16,array);//change 16 to change number of randomized steps
			Node solution = test.breadthSearch();
			int step = 0;
			while(solution.parent != null) {
				for(int i = 0; i < 9; i++) 
					System.out.print(solution.state[i]);
				System.out.println("  Step " + step);
				step++;
				solution = solution.parent;
			}
			for(int i = 0; i < 9; i++) 
				System.out.print(solution.state[i]);
			System.out.println("  Step " + step);
			System.out.println();
		}
	
	@Test
	public void testBreadthSearch() {
		System.out.println("Breadth First");
		Solver test = new Solver(1);//change 1 to change number of randomized steps
		Node solution = test.breadthSearch();
		int step = 0;
		while(solution.parent != null) {
			for(int i = 0; i < 9; i++) 
				System.out.print(solution.state[i]);
			System.out.println("  Step " + step);
			step++;
			solution = solution.parent;
		}
		for(int i = 0; i < 9; i++) 
			System.out.print(solution.state[i]);
		System.out.println("  Step " + step);
		System.out.println();
	}
	
	@Test
	public void testDepthSearch() {
		System.out.println("Depth First");
		Solver test = new Solver(6);//change 6 to change number of randomized steps
		Node solution = test.depthSearch();
		int step = 0;
		while(solution.parent != null) {
			for(int i = 0; i < 9; i++) 
				System.out.print(solution.state[i]);
			System.out.println("  Step " + step);
			step++;
			solution = solution.parent;
		}
		for(int i = 0; i < 9; i++) 
			System.out.print(solution.state[i]);
		System.out.println("  Step " + step);
		System.out.println();
	}
	
	@Test
	public void testNPuzzle() {
		System.out.println("NPuzzleTest:");
		int NPuzzle = 15;//change puzzle size
		Solver test = new Solver(6,NPuzzle);//change 6 to change random steps
		Node solution = test.breadthSearch();
		int step = 0;
		while(solution.parent != null) {
			for(int i = 0; i < NPuzzle + 1; i++) 
				System.out.print(solution.state[i]);
			System.out.println("  Step " + step);
			step++;
			solution = solution.parent;
		}
		for(int i = 0; i < NPuzzle + 1; i++) 
			System.out.print(solution.state[i]);
		System.out.println("  Step " + step);
		System.out.println();
	}
	
	@Test
	public void testNPuzzleInitialized() {
		System.out.println("NPuzzleTestInitialized:");
		int NPuzzle = 15;//change puzzle size
		int[] array = {1,2,3,4,5,7,6,8,0,10,9,12,11,13,14,15}; //don't forget to alter array length if you change puzzle size
		Solver test = new Solver(6,array,NPuzzle);//change 6 to change random steps
		Node solution = test.breadthSearch();
		int step = 0;
		while(solution.parent != null) {
			for(int i = 0; i < NPuzzle + 1; i++) 
				System.out.print(solution.state[i]);
			System.out.println("  Step " + step);
			step++;
			solution = solution.parent;
		}
		for(int i = 0; i < NPuzzle + 1; i++) 
			System.out.print(solution.state[i]);
		System.out.println("  Step " + step);
		System.out.println();
	}

	
	
	//NOTES: (from before I started coding this assignment)
	//31 moves to solve max 8 puzzle
	//Use HashSet to store previous states means we don't have to worry about max moves.
	
	// !!! Tree Class !!!
	//Construct Tree based on Solution State
	
	// !!! Solver Class Traversal Method !!!
	//traverse to find randomized state 
	//move backwards from random state to find solution sequence
	//never go more than 31 deep? Or use HashSet
	
	// DONE !!! Breadth Search !!! 
	//Check node, if not same, generate children 
	
	// !!! Depth Search !!!
	//Check node, generate left child & call method on left, generate right child & call method on right (depth) 
	// !!! Make sure to limit depth to 31 moves !!! HashSet option
}
