import java.util.TreeMap;

// Helper class to find possible paths and store them in a TreeMap

public class MDPyramidHelper {

    private TreeMap<Integer, Integer[]> traversedPath ;

    MDPyramidHelper(){
        traversedPath=new TreeMap<Integer, Integer[]>();
    }

    public TreeMap<Integer, Integer[]> getTraversedPath() {
        return traversedPath;
    }

    void findPaths(Node node, int depth) {
        Integer path[] = new Integer[1000];
        findPathsRecursively(node, path, 0,depth);
    }

    /* finds path to the leaf node recursively
     */
    void findPathsRecursively(Node node, Integer path[], int pathLen, int depth) {
        if (node == null)
            return;

        /* append this node to the path array */
        path[pathLen] = node.data;
        pathLen++;

        /* it's a leaf node, so find the path that leads to root node */
        if (node.left == null && node.right == null)
            calculateSum(path, pathLen, depth);
        else {
            findPathsRecursively(node.left, path, pathLen, depth);
            findPathsRecursively(node.right, path, pathLen, depth);
        }
    }

    void calculateSum(Integer reversePath[], int len, int depth) {
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
            traversedPath.put(maxSum, path);
        }
    }
}
