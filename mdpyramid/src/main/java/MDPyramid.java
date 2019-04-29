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
    static TreeMap<Integer, Integer[]> finalMap = null;

    public static void main(String[] args) throws FileNotFoundException {

        // Stores all valid paths in array as a 'value' and
        // sum of the array elements as their 'key'
        finalMap = new TreeMap<Integer, Integer[]>();

        // STEP1::Read Input File & convert to 2D Array
        FileReaderUtil fileReader = new FileReaderUtil();
        int[][] inputArray = fileReader.getTwoDimensionalArrayOfData("D:/sandeep/localwokspace/local/src/local/input2.txt");
        depth = inputArray.length;

        // Step2 : Finding all valid paths from leaf node to root node
        // and constructs binary tree for each leaf node

        int leafNodes[] = inputArray[inputArray.length - 1];
        BinaryTree binaryTree;
        for (int i = 0; i < leafNodes.length; i++) {
            binaryTree = new BinaryTree(leafNodes[i]);
            Node root = binaryTree.root;
            binaryTree.insertElement(inputArray, inputArray.length - 1, i, root);
            findPaths(root);
        }

        // Printing the result
        // finalMap.lastKey() will have maximum sum as it's a TreeMap it stores in sorted order
        System.out.println("Maximum Sum is:" + finalMap.lastKey());
        Integer[] res = finalMap.get(finalMap.lastKey());
        System.out.println("Traversal path for maximum sum is: ");
        for (int i = 0; i < depth; i++) {
            System.out.print(res[i]);
            if (i != depth - 1) {
                System.out.print("-->");
            }
        }
    }

    static void findPaths(Node node) {
        Integer path[] = new Integer[1000];
        findPathsRecursively(node, path, 0);
    }

    /* finds path to the leaf node recursively
     */
    static void findPathsRecursively(Node node, Integer path[], int pathLen) {
        if (node == null)
            return;

        /* append this node to the path array */
        path[pathLen] = node.data;
        pathLen++;

        /* it's a leaf node, so find the path that leads to root node */
        if (node.left == null && node.right == null)
            calculateSum(path, pathLen);
        else {
            findPathsRecursively(node.left, path, pathLen);
            findPathsRecursively(node.right, path, pathLen);
        }
    }

    static void calculateSum(Integer reversePath[], int len) {
        int maxSum = 0;
        if (len == depth) {
            for (int i = 0; i < len; i++) {
                maxSum = reversePath[i] + maxSum;
            }
            Integer path[] = new Integer[depth];
            for (int i = depth - 1; i >= 0; i--) {
                //reversing the path as we constructed binary tree from leaf to root node
                path[i] = reversePath[depth - 1 - i];
            }
            finalMap.put(maxSum, path);
        }
    }
}
