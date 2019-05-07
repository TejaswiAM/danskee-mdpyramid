import java.io.FileNotFoundException;
import java.util.TreeMap;

/*
    Program that finds maximum sum of nodes from it's root to the leaf node

    I've used bottom-up approach to solve this problem by constructing a binary tree
    for each leaf node, where it's children's are odd/even depending upon current node.
    if current node is odd; then it's children's will be even and vice versa
 */

public class MDPyramid {

    static int depth = 0;
    static TreeMap<Integer, Integer[]> traversedPath = null;

    public static void main(String[] args) throws FileNotFoundException {

        // Stores all valid paths in array as a 'value' and
        // sum of the array elements as their 'key'
        traversedPath = new TreeMap<Integer, Integer[]>();

        MDPyramidHelper mdPyramidHelper = new MDPyramidHelper();

        // STEP1::Read Input File & convert to 2D Array
        FileReaderUtil fileReader = new FileReaderUtil();
        int[][] inputArray = fileReader.getTwoDimensionalArrayOfData("D:/sandeep/localwokspace/local/src/local/input.txt");
        depth = inputArray.length;

        // Step2 : Finding all valid paths from leaf node to root node
        // and constructs binary tree for each leaf node

        int leafNodes[] = inputArray[inputArray.length - 1];
        BinaryTree binaryTree;
        for (int i = 0; i < leafNodes.length; i++) {
            binaryTree = new BinaryTree(leafNodes[i]);
            Node root = binaryTree.root;
            binaryTree.insertElement(inputArray, inputArray.length - 1, i, root);
            mdPyramidHelper.findPaths(root,depth);
            traversedPath.putAll(mdPyramidHelper.getTraversedPath());
        }

        // Printing the result
        // traversedPath.lastKey() will have maximum sum as it's a TreeMap it stores in sorted order
        System.out.println("Maximum Sum is:" + traversedPath.lastKey());
        Integer[] res = traversedPath.get(traversedPath.lastKey());
        System.out.println("Traversal path for maximum sum is: ");
        for (int i = 0; i < depth; i++) {
            System.out.print(res[i]);
            if (i != depth - 1) {
                System.out.print("-->");
            }
        }
    }

}
