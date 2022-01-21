package ch09graph.basic;

public class GraphDemo {
	/** Usage exemples for class Graph */
	
	public static void main(String[] args) {
		testBreadthFirstSearch();
		
		testMaze();
		
		testDepthFirstSearch();
		
		testMazeDFS();
		
		testDFS_iterative();
		
		testCycleDetection();
		
		testCycleDetection_2();
		
		testTopologicalSort();
	}
	
	/** Breadth-first search example */
	public static void testBreadthFirstSearch() {
		// build graph
		Graph gd = new Graph();
		Graph.Node n0 = gd.addNode("n0");
		Graph.Node n1 = gd.addNode("n1");
		Graph.Node n2 = gd.addNode("n2");
		
		gd.addEdge(n0, n1);
		gd.addEdge(n1, n2);
		gd.addEdge(n2, n1);
		gd.addEdge(n2, n2);
		
		gd.print();
		
		Graph gu = new Graph(false);
		Graph.Node k0 = gu.addNode("k0");
		Graph.Node k1 = gu.addNode("k1");
		Graph.Node k2 = gu.addNode("k2");
		Graph.Node k3 = gu.addNode("k3");
		
		gu.addEdge(k0,k1);
		gu.addEdge(k1,k2);
		gu.addEdge(k2,k3);
		gu.addEdge(k3,k0);

		gu.print();
		
		
		// execute BFS
		System.out.println();
		System.out.println("Graph gd after BFS: ");
		gd.breadthFirstSearch(n0);
		gd.print();

		System.out.println();
		System.out.println("Graph gu after BFS: ");
		gu.breadthFirstSearch(k3);
		gu.print();

	}
	
	/** Example: Maze problem */
	public static void testMaze() {
		Graph gu = new Graph(false);  //undirected graph
		Graph.Node n1 = gu.addNode("1");
		Graph.Node n2 = gu.addNode("2");
		Graph.Node n3 = gu.addNode("3");
		Graph.Node n4 = gu.addNode("4");
		Graph.Node n5 = gu.addNode("5");
		Graph.Node n6 = gu.addNode("6");
		Graph.Node n7 = gu.addNode("7");
		Graph.Node n8 = gu.addNode("8");
		
		gu.addEdge(n1, n2);
		gu.addEdge(n2, n3);
		gu.addEdge(n2, n4);
		gu.addEdge(n3, n4);
		gu.addEdge(n3, n6);
		gu.addEdge(n4, n5);
		gu.addEdge(n5, n6);
		gu.addEdge(n5, n7);
		gu.addEdge(n6, n8);

		System.out.println("Maze: ");
		gu.print();
		System.out.println("Path from node 5 to node 1");
		gu.labyrinthBFS(n5, n1);
		gu.print();
		
		System.out.println("Path from node 8 to node 1");
		gu.labyrinthBFS(n8, n1);
		gu.print();
		
	}
	
	/** Depth-first search example */
	public static void testDepthFirstSearch() {
		Graph gd = new Graph();
		Graph.Node n0 = gd.addNode("n0");
		Graph.Node n1 = gd.addNode("n1");
		Graph.Node n2 = gd.addNode("n2");
		
		gd.addEdge(n0, n1);
		gd.addEdge(n1, n2);
		gd.addEdge(n2, n1);
		gd.addEdge(n2, n2);
		
		gd.print();
		
		Graph gu = new Graph(false);
		Graph.Node k0 = gu.addNode("k0");
		Graph.Node k1 = gu.addNode("k1");
		Graph.Node k2 = gu.addNode("k2");
		Graph.Node k3 = gu.addNode("k3");
		
		gu.addEdge(k0,k1);
		gu.addEdge(k1,k2);
		gu.addEdge(k2,k3);
		gu.addEdge(k3,k0);

		gu.print();
		
		
		//execute depth-first search
		System.out.println();
		System.out.println("Graph gd after depth-first search: ");
		gd.dephtFirstSearch(n0);
		gd.print();

		System.out.println();
		System.out.println("Graph gu after depth-first search: ");
		gu.dephtFirstSearch(k3);
		gu.print();

	}

	
	public static void testDFS_iterative() {
		Graph labyrinth = new Graph(false);  //undirected graph
		Graph.Node n1 = labyrinth.addNode("1");
		Graph.Node n2 = labyrinth.addNode("2");
		Graph.Node n3 = labyrinth.addNode("3");
		Graph.Node n4 = labyrinth.addNode("4");
		Graph.Node n5 = labyrinth.addNode("5");
		Graph.Node n6 = labyrinth.addNode("6");
		Graph.Node n7 = labyrinth.addNode("7");
		Graph.Node n8 = labyrinth.addNode("8");
		
		labyrinth.addEdge(n1, n2);
		labyrinth.addEdge(n2, n3);
		labyrinth.addEdge(n2, n4);
		labyrinth.addEdge(n3, n4);
		labyrinth.addEdge(n3, n6);
		labyrinth.addEdge(n4, n5);
		labyrinth.addEdge(n5, n6);
		labyrinth.addEdge(n5, n7);
		labyrinth.addEdge(n6, n8);
		
		labyrinth.dephtFirstSearchStack(n1);
		labyrinth.print();

	}
	
	
	
	public static void testMazeDFS() {
		Graph gu = new Graph(false);  // undirected graph
		Graph.Node n1 = gu.addNode("1");
		Graph.Node n2 = gu.addNode("2");
		Graph.Node n3 = gu.addNode("3");
		Graph.Node n4 = gu.addNode("4");
		Graph.Node n5 = gu.addNode("5");
		Graph.Node n6 = gu.addNode("6");
		Graph.Node n7 = gu.addNode("7");
		Graph.Node n8 = gu.addNode("8");
		
		gu.addEdge(n1, n2);
		gu.addEdge(n2, n3);
		gu.addEdge(n2, n4);
		gu.addEdge(n3, n4);
		gu.addEdge(n3, n6);
		gu.addEdge(n4, n5);
		gu.addEdge(n5, n6);
		gu.addEdge(n5, n7);
		gu.addEdge(n6, n8);

		System.out.println("Maze: ");
		gu.print();
		System.out.println("Path from node 5 to node 1:");
		gu.labyrinthDFS(n5, n1);
		gu.print();
		
		System.out.println("Path from node 8 to node 1:");
		gu.labyrinthDFS(n8, n1);
		gu.print();
	
	}
	
	/** Cycle detection example */
	public static void testCycleDetection() {
		Graph cg = new Graph();  // directed graph
		Graph.Node s = cg.addNode("s");
		Graph.Node t = cg.addNode("t");
		Graph.Node u = cg.addNode("u");
		Graph.Node v = cg.addNode("v");
		Graph.Node w = cg.addNode("w");
		Graph.Node x = cg.addNode("x");
		Graph.Node y = cg.addNode("y");

		cg.addEdge(s,t);
		cg.addEdge(s,u);
		cg.addEdge(t,v);
		cg.addEdge(u,v);
		cg.addEdge(v,w);
		cg.addEdge(v,x);
		cg.addEdge(w,y);
		cg.addEdge(x,u);
		cg.addEdge(y,x);
		
		cg.print();
		

		System.out.println("cg acyclic? : " + cg.isAcyclic()); //--> false
	}
	
	
	/** Cycle detection example (2) */
	public static void testCycleDetection_2() {
		Graph cg = new Graph();  // directed graph
		Graph.Node s = cg.addNode("s");
		Graph.Node t = cg.addNode("t");
		Graph.Node u = cg.addNode("u");
		Graph.Node v = cg.addNode("v");
		Graph.Node w = cg.addNode("w");
		Graph.Node x = cg.addNode("x");
		Graph.Node y = cg.addNode("y");

		cg.addEdge(s,t);
		cg.addEdge(s,u);
		cg.addEdge(t,v);
		cg.addEdge(u,v);
		cg.addEdge(v,w);
		cg.addEdge(v,x);
		cg.addEdge(w,y);
		cg.addEdge(u,x);  // inverse direction compared to previous example
		cg.addEdge(y,x);
		
		cg.print();
		

		System.out.println("cg is acyclic? : " + cg.isAcyclic());  //--> true
	}

	/** Beispiel für Verwendung der topologischen Sortierung */
	public static void testTopologicalSort() {
		Graph dressing = new Graph();  //directed graph
		Graph.Node nUnterhose = dressing.addNode("underpants");
		Graph.Node nHose = dressing.addNode("trousers");
		Graph.Node nGürtel = dressing.addNode("belt");
		Graph.Node nHemd = dressing.addNode("shirt");
		Graph.Node nKrawatte = dressing.addNode("tie");
		Graph.Node nJackett = dressing.addNode("jacket");
		Graph.Node nSocken = dressing.addNode("socks");
		Graph.Node nSchuhe = dressing.addNode("shoes");
		Graph.Node nUhr = dressing.addNode("watch");
		
		dressing.addEdge(nHemd, nKrawatte);
		dressing.addEdge(nHemd, nGürtel);
		dressing.addEdge(nKrawatte, nJackett);
		dressing.addEdge(nGürtel, nJackett);
		dressing.addEdge(nUnterhose, nHose);
		dressing.addEdge(nHose, nGürtel );
		dressing.addEdge(nHose, nSchuhe);
		dressing.addEdge(nSocken, nSchuhe);
		
		System.out.println("Garment dependencies:");
		dressing.print();
		System.out.println("Dressing sequence:");
		dressing.topologicalSort();
		
	}
	
}
