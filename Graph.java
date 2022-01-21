package ch09graph.basic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Implementation of directed and undirected graphs using adjacency lists representation
 * 
 * @author G. Schied, Hochschule Ulm
 */
public class Graph {

	/** Colors for coloring nodes */
	public enum Color {
		WHITE, GREY, BLACK
	};

	/** Node with adjacency list and additional informations */
	public class Node {
		/** information assigned to the node */
		Object info;
		
		/** adjacency list of the node */
		List<Node> neighbors = new ArrayList<Node>(); 
		
		/** in-degree of the node */
		int indegree = 0; // in-degree of node in case of directed graph
					      // degree of node in case of undirected graph

		// additional information used for breadth-first search, depth-first search, etc.
		Color color = Color.WHITE;  // node coloe
		int distance; // distance to start node (-1 = infinity)
		Node predecessor; // parent node in breadth-first tree
		int begin;    // start time-stamp for depth-first search
		int end;      // end time stamp for depth-first search

		Node(Object info) {
			this.info = info;
		};

		public void setColor(Color c) {
			color = c;
		}

		public void print() {
			System.out.println("  Node " + info + ", " + color + ", distance "
					+ distance + ", pred " + predecessor + "(" + begin + "/"
					+ end + ") , indegree " + indegree);
			for (Node n : neighbors) {
				System.out.println("    --> " + n.toString());
			}
		}

		public String toString() {
			return "Node " + info;
		}
	}

	private boolean isDirected;
	private int nodecount = 0;
	private int edgecount = 0;
	private List<Node> allNodes = new ArrayList<Node>();

	private boolean isAcyclic; //flag used for cycle detection
	private int time; // time counter for depth-first search

	/** generates an empty directed graph */
	public Graph() {
		this.isDirected = true;
	};

	/** 
	 * generates an empty directed or undirected graph
	 * 
	 * @param isDirected specifies if graph is directed
	 */
	public Graph(boolean isDirected) {
		this.isDirected = isDirected;
	}

	/**
	 * generates a new node with the given info
	 * 
	 * @param info
	 *            value of the node
	 * @return new node
	 */
	public Node addNode(Object info) {
		Node newNode = new Node(info);
		allNodes.add(newNode);
		nodecount++;
		return newNode;
	}

	/**
	 * adda an edge to the graph
	 * 
	 * @param src
	 *           source node of the edge
	 * @param dest
	 *           target node of the edge
	 */
	public void addEdge(Node src, Node dest) {
		src.neighbors.add(dest);
		dest.indegree++;
		if (!isDirected) {
			// add inverse neighborhood information in case of undirected graph
			dest.neighbors.add(src);
			src.indegree++;
		}
		edgecount++;
	}

	/**
	 * prints all nodes and vertices of the graph
	 */
	public void print() {
		if (isDirected) {
			System.out.println("Directed graph: ");
		} else {
			System.out.println("Undirected graph: ");
		}
		System.out.println("  # nodes:    " + nodecount);
		System.out.println("  # edges:    " + edgecount);
		for (Node n : allNodes) {
			n.print();
		}
	}

	/**
	 * auxilliary method used for initialization of diverse graph algorithms
	 */
	private void prepare() {
		for (Node n : allNodes) {
			n.color = Color.WHITE;
			n.distance = -1;
			n.predecessor = null;
			n.begin = -1;
			n.end = -1;
		}
		time = 0;
	}

	/**
	 * executes breadth-first search starting from start node 'startnode'
	 */
	public void breadthFirstSearch(Node startnode) {
		prepare();

		startnode.color = Color.GREY;
		startnode.distance = 0;

		Queue<Node> queue = new ArrayDeque<Node>();
		queue.add(startnode);

		while (!queue.isEmpty()) {
			Node u = queue.remove();
			for (Node v : u.neighbors) {
				if (v.color == Color.WHITE) {
					// new neighbor was discovered, color it grey
					v.color = Color.GREY;
					v.distance = u.distance + 1;
					v.predecessor = u;

					// add newly discovered node the the queue
					queue.add(v);
				}
			}
			u.color = Color.BLACK;
		}
	}

	/**
	 * Computes the shortest path from the start node to the end node
	 * */
	public void labyrinthBFS(Node startNode, Node endNode) {
		breadthFirstSearch(startNode);
		printPath(startNode, endNode);
	}

	/**
	 * Auxilliary method used to reverse the backward chaining from the end node to 
	 * the start node
	 */
	private void printPath(Node start, Node end) {
		if (start == end) {
			System.out.println(end);
		} else {
			if (end.predecessor == null) {
				System.out.println("No path from " + start + " to "
						+ end + " exists!");
			} else {
				printPath(start, end.predecessor);
				System.out.println(end);
			}
		}
	}



	/** 
	 * Executes depth-first search starting from the specified start node.
	 */
	public void dephtFirstSearch(Node startnode) {
		prepare();
		dfsVisit(startnode);
	}

	/**
	 * rekursive Methode, die die Tiefensuche durchführt Für jeden Knoten wird
	 * dabei die Startzeit und die Endzeit bestimmt Die Startzeit gibt an, wann
	 * die Bearbeitung des Knoten begonnen hat Die Endzeit gibt an, wann die
	 * Bearbeitung des Knotens fertig war. Es wird bei der Suche auch geprüft,
	 * ob der Graph Rückwärtskanten enthält (für die Zyklenerkennung)
	 * 
	 * @param node
	 *            Ausgangsknoten für die Suche
	 */
	public void dfsVisit(Node node) {
		node.color = Color.GREY;
		time++;
		node.begin = time;

		// System.out.println(" DFS " + time + ": " + node + " begin");

		for (Node v : node.neighbors) {
			if (v.color == Color.WHITE) {
				// Nachbarknoten v ist neu entdeckt
				v.distance = node.distance + 1;
				v.predecessor = node;

				dfsVisit(v); // mit Nachbarknoten weitermachen
			} else if (v.color == Color.GREY) {
				// Rückwärtskante
				System.out.println("Zyklus - Rückwärtskante von " + node
						+ " nach " + v);
				isAcyclic = false;
			}
		}
		// Knoten ist fertig bearbeitet
		node.color = Color.BLACK;
		time++;
		node.end = time;
		// System.out.println(" DFS " + time + ": " + node + " end");

	}

	/**
	 * Iterative depth-first search implementation using a stack to store
	 * newly discovered nodes.
	 */
	public void dephtFirstSearchStack(Node startnode) {
		prepare();

		Stack<Node> stack = new Stack<Node>();
		startnode.color = Color.GREY;
		startnode.distance = 0;
		startnode.predecessor = null;
		stack.add(startnode);

		while (!stack.isEmpty()) {
			Node u = stack.peek(); // inspect top element of the stack
			if (u.color == Color.GREY) {
				// start proceccing of the top node
				time++;
				u.begin = time;
				System.out.println("   >" + u + " (" + u.begin + "/.)");
				for (Node v : u.neighbors) {
					if (v.color == Color.WHITE) {
						// new (white) neighbor found, color grey and push it on the stack
						v.color = Color.GREY;
						v.distance = u.distance + 1;
						v.predecessor = u;
						stack.push(v);
					} else if (v.color == Color.GREY) {
						// grey neighbor found ==> cycle detected
						System.out.println("  Zyklus erkannt bei " + u
								+ " --> " + v);
					}
				}
				u.color = Color.BLACK;

			} else if (u.color == Color.BLACK) {
				// end of processing
				time++;
				u.end = time;
				System.out.println("   <" + u + " (" + u.begin + "/" + u.end
						+ ")");
				stack.pop();
			}
		}
	}

	/**
	 * Searches a path from the given start node to the given target node using
	 * depth-first search.
	 */
	public void labyrinthDFS(Node startNode, Node endNode) {
		dephtFirstSearchStack(startNode);
		printPath(startNode, endNode);
	}

	/** checks if graph is acycliy, using depth-first search*/
	public boolean isAcyclic() {
		prepare();
		isAcyclic = true;
		for (Node n : allNodes) {
			if (n.color == Color.WHITE) {
				dfsVisit(n);
			}
		}
		return isAcyclic;
	}

	/**
	 * Performs a topological sort for the given acyclic directed graph.
	 */
	public void topologicalSort() {
		prepare();
		Stack<Node> resultStack = new Stack<Node>();
		for (Node node : allNodes) {
			if (node.indegree == 0) {
				dfsVisitTopoSort(resultStack, node);
			}
		}

		// print result
		System.out.println("Topological Sort: ");
		int pos = 1;
		while (! resultStack.isEmpty()) {
			Node node = resultStack.pop();
			System.out.println(pos + ". " + node);
			pos++;
		}
	}

	/**
	 * Auxilliary method for toplogical sort
	 * 
	 * @param result
	 *            stack used to collect completely processed nodes
	 * @param node
	 *            start node for depth-first search
	 */

	private void dfsVisitTopoSort(Stack<Node> result, Node node) {
		node.color = Color.GREY;
		for (Node v : node.neighbors) {
			if (v.color == Color.WHITE) {
				//new white color detected
				dfsVisitTopoSort(result, v);
			} else if (v.color == Color.GREY) {
				// backward edge found ==> cycle exists
				System.out.println("backward edge from " + node + " to " + v);
				throw new RuntimeException(
						"cycle exists - no topological sort possible");
			}
		}
		node.color = Color.BLACK;
		result.push(node);
	}

}
