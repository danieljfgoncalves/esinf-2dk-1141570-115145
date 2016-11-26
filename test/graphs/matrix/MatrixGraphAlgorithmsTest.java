package graphs.matrix;

import java.util.Iterator;
import java.util.LinkedList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Tests the algorithms to manipulate a matrix graph.
 *
 * @author Daniel Gonçalves 1151452@isep.ipp.pt
 * @author Eric Jorge Schipper Ferreira do Amaral 1141570@isep.ipp.pt
 */
public class MatrixGraphAlgorithmsTest {

    MatrixGraph <String, String> completeMap = new MatrixGraph<>();
    MatrixGraph <String, String> incompleteMap;
    
    public MatrixGraphAlgorithmsTest() {
    }

    @SuppressWarnings("unchecked")
    @Before
    public void setUp() throws Exception {
		
		completeMap.insertVertex("Porto");
		completeMap.insertVertex("Braga");
		completeMap.insertVertex("Vila Real");
		completeMap.insertVertex("Aveiro");
		completeMap.insertVertex("Coimbra");
		completeMap.insertVertex("Leiria");

		completeMap.insertVertex("Viseu");
		completeMap.insertVertex("Guarda");
		completeMap.insertVertex("Castelo Branco");
		completeMap.insertVertex("Lisboa");
		completeMap.insertVertex("Faro");

		completeMap.insertEdge("Porto", "Aveiro", "A1");
		completeMap.insertEdge("Porto", "Braga", "A3");
		completeMap.insertEdge("Porto", "Vila Real", "A4");
		completeMap.insertEdge("Viseu", "Guarda", "A25");
		completeMap.insertEdge("Guarda",  "Castelo Branco", "A23");
		completeMap.insertEdge("Aveiro", "Coimbra", "A1");
		completeMap.insertEdge("Coimbra", "Lisboa", "A1");
		completeMap.insertEdge("Coimbra",  "Leiria",  "A34");
		completeMap.insertEdge("Aveiro", "Leiria", "A17");
		completeMap.insertEdge("Leiria", "Lisboa", "A8");

		
		incompleteMap = (MatrixGraph<String, String>) completeMap.clone();
		
		completeMap.insertEdge("Aveiro", "Viseu", "A25");
		completeMap.insertEdge("Leiria", "Castelo Branco", "A23");
		completeMap.insertEdge("Lisboa", "Faro", "A2");

	}

	
	@Test
	public void testDFS() {
		System.out.println("Test of DFS");

		LinkedList<String> path;

		assertTrue("Should be null if vertex does not exist", MatrixGraphAlgorithms.DFS(completeMap, "LX")==null);

		path = MatrixGraphAlgorithms.DFS(incompleteMap, "Faro");

		assertTrue("Should be just one", path.size()==1);

		Iterator<String> it = path.iterator();

		assertTrue("it should be Faro", it.next().compareTo("Faro")==0);
		
		path = MatrixGraphAlgorithms.DFS(completeMap, "Porto");
		
		assertTrue("Should give all vertices ", path.size()==11);

		it = path.iterator();

		assertTrue("First in visit should be Porto", it.next().compareTo("Porto")==0);
		assertTrue("then Braga", it.next().compareTo("Braga")==0);
		assertTrue("then Vila Real", it.next().compareTo("Vila Real")==0);
		assertTrue("then Aveiro", it.next().compareTo("Aveiro")==0);

		assertTrue("then Coimbra", it.next().compareTo("Coimbra")==0);
		assertTrue("then Leiria", it.next().compareTo("Leiria")==0);
		assertTrue("then Castelo Branco", it.next().compareTo("Castelo Branco")==0);
		assertTrue("then Guarda", it.next().compareTo("Guarda")==0);
		assertTrue("then Viseu", it.next().compareTo("Viseu")==0);
		assertTrue("then Lisboa", it.next().compareTo("Lisboa")==0);
		assertTrue("then Faro", it.next().compareTo("Faro")==0);

		
		path = MatrixGraphAlgorithms.DFS(incompleteMap, "Viseu");
		
		assertTrue("Should give 3 vertices", path.size()==3);

		it = path.iterator();

		assertTrue("First in visit should be Viseu", it.next().compareTo("Viseu")==0);
		assertTrue("then Guarda", it.next().compareTo("Guarda")==0);
		assertTrue("then Castelo Branco", it.next().compareTo("Castelo Branco")==0);
	}

	@Test
	public void testBFS() {
		System.out.println("Test of BFS");

		LinkedList<String> path;

		assertTrue("Should be null if vertex does not exist", MatrixGraphAlgorithms.BFS(completeMap, "LX")==null);

		path = MatrixGraphAlgorithms.BFS(incompleteMap, "Faro");

		assertTrue("Should be just one", path.size()==1);

		Iterator<String> it = path.iterator();

		assertTrue("it should be Faro", it.next().compareTo("Faro")==0);

		
		path = MatrixGraphAlgorithms.BFS(completeMap, "Porto");
		
		assertTrue("Should give all vertices ", path.size()==11);

		it = path.iterator();

		assertTrue("First in visit should be Porto", it.next().compareTo("Porto")==0);
		assertTrue("then Braga", it.next().compareTo("Braga")==0);
		assertTrue("then Vila Real", it.next().compareTo("Vila Real")==0);
		assertTrue("then Aveiro", it.next().compareTo("Aveiro")==0);

		assertTrue("then Coimbra", it.next().compareTo("Coimbra")==0);
		assertTrue("then Leiria", it.next().compareTo("Leiria")==0);
		assertTrue("then Viseu", it.next().compareTo("Viseu")==0);
		assertTrue("then Lisboa", it.next().compareTo("Lisboa")==0);
		assertTrue("then Castelo Branco", it.next().compareTo("Castelo Branco")==0);
		assertTrue("then Guarda", it.next().compareTo("Guarda")==0);
		assertTrue("then Faro", it.next().compareTo("Faro")==0);

		
		path = MatrixGraphAlgorithms.BFS(incompleteMap, "Viseu");
		
		assertTrue("Should give 3 vertices", path.size()==3);

		it = path.iterator();

		assertTrue("First in visit should be Viseu", it.next().compareTo("Viseu")==0);
		assertTrue("then Guarda", it.next().compareTo("Guarda")==0);
		assertTrue("then Castelo Branco", it.next().compareTo("Castelo Branco")==0);
		
	}

	@Test
	public void testAllPaths() {

		System.out.println("Test of all paths");
		
		LinkedList<LinkedList<String>> paths = new LinkedList<>();
		
		assertFalse("Should be false if vertex does not exist", 
				MatrixGraphAlgorithms.allPaths(completeMap, "Porto", "LX", paths));
		
		assertTrue("There should be paths between Porto and Lisboa in the map", 
				MatrixGraphAlgorithms.allPaths(incompleteMap, "Porto", "Lisboa", paths));
		
		assertTrue("Should give 4 paths", paths.size()==4);
		
		Iterator<LinkedList<String>> it = paths.iterator();
		
		
		// First path should be Porto, Aveiro, Coimbra, Leiria, Lisboa
		// Second path should be Porto, Aveiro, Coimbra, Lisboa
		// Third path should be Porto, Aveiro, Leiria, Coimbra, Lisboa
		// Fourth path shuold be Porto, Aveiro, Leiria, Lisboa
		
		String[][] pathsString = {{"Porto", "Aveiro", "Coimbra", "Leiria", "Lisboa"},
				                  {"Porto", "Aveiro", "Coimbra", "Lisboa"},
						          {"Porto", "Aveiro", "Leiria", "Coimbra", "Lisboa"}, 
		                          {"Porto", "Aveiro", "Leiria", "Lisboa"}};
		
		for(int i = 0 ; i < 4; i ++)
		{
			LinkedList<String> path = it.next();
			Iterator<String> cities = path.iterator();
			for(int j = 0; j < path.size(); j++){
				assertTrue("City should be" + pathsString[i][j], cities.next().compareTo(pathsString[i][j])==0);
			}
		}
		
		MatrixGraphAlgorithms.allPaths(incompleteMap, "Porto", "Faro", paths);
		
		assertTrue("There should not be paths between Porto and Faro in the incomplete map", paths.isEmpty());
	}
}
