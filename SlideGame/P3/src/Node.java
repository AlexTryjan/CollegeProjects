public class Node {
			Node parent;
			int[] state;
			int blankLocation;
			int height;
			
			//Constructor needing only state
			public Node(int[] s) {
				state = s;
			}
			//Constructor needing only height
			public Node(int h) {
				height = h;
			}
			//Constructor needing only state and blankLocation
			public Node(int[] s, int loc) {
				state = s;
				blankLocation = loc;
			}
			//Constructor needing state, parent, and blankLocation
			public Node(int[] s, Node p, int loc) {
				state = s;
				parent = p;
				blankLocation = loc;
			}
			//Constructor needing state, parent, blankLocation, and height
			public Node(int[] s, Node p, int loc, int h) {
				state = s;
				parent = p;
				blankLocation = loc;
				height = h;
			}
		}