import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class BinaryTreeTest {

    /* Test Data
       1
       8 9
       1 5 9
       4 5 2 3
     */
    int inputArray[][]={{1},{8,9},{1,5,9},{4,5,2,3}};
    int [] leafNodes=inputArray[inputArray.length-1];

    /*Test case for testing creation of binary tree for last row of the
      2dimensional array when odd->even->odd pattern exist
      from leaf node to root node
    */
    @Test
    public void insertElementWhenOddEvenPatternExist() {

        Integer expectedPath[]={1,8,5,2,9};
        List<Integer>  traversedPath=new LinkedList<Integer>();

        //trying to construct tree from leaf node '2' to root node '1'
        BinaryTree binaryTree=new BinaryTree(leafNodes[2]);
        binaryTree.insertElement(inputArray, inputArray.length - 1, 2, binaryTree.root);
        binaryTree.inOrderTraversal(binaryTree.root,traversedPath);
        assertArrayEquals("Comparing expected path and actual traversed path",
                expectedPath,traversedPath.toArray(new Integer[0]));
        assertThat("Comparing length of the inorder traversal path",
                traversedPath.size(),is(5));
    }

    /*Test case for testing creation of binary tree for last row of the
      2dimensional array when odd->even->odd pattern does not exist
      from leaf node to root node
    */
    @Test
    public void insertElementWhenOddEvenPatternDoesNotExist() {

        Integer expectedPath[]={5};
        List<Integer>  traversedPath=new LinkedList<Integer>();

        //trying to construct tree from leaf node '5' to root node '1'
        BinaryTree binaryTree=new BinaryTree(leafNodes[1]);
        binaryTree.insertElement(inputArray, inputArray.length - 1, 1, binaryTree.root);
        binaryTree.inOrderTraversal(binaryTree.root,traversedPath);
        assertArrayEquals("Comparing expected path and actual traversed path",
                expectedPath,traversedPath.toArray(new Integer[0]));
        assertThat("Comparing length of the inorder traversal path ",
                traversedPath.size(),is(1));
    }
}
