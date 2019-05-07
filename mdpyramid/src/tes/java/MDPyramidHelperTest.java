import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

public class MDPyramidHelperTest {

    /* Test Data
       1
       8 9
       1 5 9
       4 5 2 3
     */
    int inputArray[][]={{1},{8,9},{1,5,9},{4,5,2,3}};
    int [] leafNodes=inputArray[inputArray.length-1];
    int depth = inputArray.length;

    BinaryTree binaryTree;
    MDPyramidHelper helper;

    @Before
    public void init(){
        helper = new MDPyramidHelper();
    }

    /*verify that only one valid path exists from
      leaf node '2' to root node '1'
    */
    @Test
    public void testFindPathsWhenPathExists() {

        binaryTree = new BinaryTree(leafNodes[2]);
        binaryTree.insertElement(inputArray, inputArray.length - 1, 2, binaryTree.root);
        helper.findPaths(binaryTree.root, inputArray.length);
        assertEquals("Number of valid paths from leaf '2' to root '1' is 1",
                1, helper.getTraversedPath().size());
    }

    /*verify that no valid path exists from
      leaf node '3' to root node '1'
    */
    @Test
    public void testFindPathsWhenNoPathExists() {

        binaryTree = new BinaryTree(leafNodes[3]);
        binaryTree.insertElement(inputArray, inputArray.length - 1, 3, binaryTree.root);
        helper.findPaths(binaryTree.root, inputArray.length);
        assertEquals("Number of valid paths from leaf '3' to root '1' is 0",
                0, helper.getTraversedPath().size());
    }

    @Test
    public void testMaxPathSum() {

        TreeMap<Integer, Integer[]> traversedPath = new TreeMap();
        Integer[] expectedMaxPath = { 1, 8, 5, 2 };
        Integer[] expectedPathForSumFourteen = { 1, 8, 1, 4 };

        for (int i = 0; i < leafNodes.length; i++) {
            binaryTree = new BinaryTree(leafNodes[i]);
            binaryTree.insertElement(inputArray, inputArray.length - 1, i, binaryTree.root);
            helper.findPaths(binaryTree.root, depth);
            traversedPath.putAll(helper.getTraversedPath());
        }

        assertTrue("Maximum sum is 16",traversedPath.lastKey().intValue() == 16);
        assertArrayEquals("Checking traversed path and expected path are same",
                expectedMaxPath, traversedPath.get(traversedPath.lastKey()));
        assertThat("Total No. of valid paths are 2",traversedPath.size(),is(2));
        assertArrayEquals("Expected path for sum 14",
                expectedPathForSumFourteen, traversedPath.get(14));
    }

}
