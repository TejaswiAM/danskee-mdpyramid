public class BinaryTree {

    Node root;

    public BinaryTree(int data) {
        root = new Node(data);
    }

    /**
     * Inserts @root node in the tree if and only if it satisfies
     * odd->even->odd->even logic
     * @param inputArray 2 dimensional input array,
     * @param i,j indices of the current element in 2 dimensional array
     * @param root current node in the binary tree
     *
     */
    public void insertElement(int inputArray[][], int i, int j, Node root) {

        if (i == 0 && j == 0) {
            return;
        } else if (j == 0) {

            if (isTraversable(inputArray[i][j], inputArray[i - 1][j])) {
                root.left = new Node(inputArray[i - 1][j]);
                insertElement(inputArray, i - 1, j, root.left);
            }
        } else if (i == j) {

            if (isTraversable(inputArray[i][j], inputArray[i - 1][j - 1])) {
                root.left = new Node(inputArray[i - 1][j - 1]);
                insertElement(inputArray, i - 1, j - 1, root.left);
            }
        } else {

            if (isTraversable(inputArray[i][j], inputArray[i - 1][j - 1])) {
                root.left = new Node(inputArray[i - 1][j - 1]);
                insertElement(inputArray, i - 1, j - 1, root.left);
            }

            if (isTraversable(inputArray[i][j], inputArray[i - 1][j])) {
                root.right = new Node(inputArray[i - 1][j]);
                insertElement(inputArray, i - 1, j, root.right);
            }
        }
    }

    private Boolean isEven(int num) {

        if (num % 2 == 0)
            return true;
        return false;
    }

    public boolean isTraversable(int childNodeValue, int parentNodeValue) {

        return !(isEven(childNodeValue).equals(isEven(parentNodeValue)));
    }
}
