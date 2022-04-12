
public class UnionFind {
    private int[] parent;
    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        parent = new int[n];
        for(int i=0; i<n; i++){
            parent[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if(vertex >= parent.length){
            throw new IndexOutOfBoundsException("vertex out of bounds, please choose a smaller one");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        return -1*parent[findRoot(v1)];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        return parent[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        return findRoot(v1) == findRoot(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        int Root1 = findRoot(v1);
        int Root2 = findRoot(v2);
        int size1 = sizeOf(v1);
        int size2 = sizeOf(v2);
        if (Root1 == Root2)
            return;
        if(size1 > size2){
            parent[Root2] = Root1;
            parent[Root1]-=size2;
        }

        else{
            parent[Root1] = Root2;
            parent[Root2]-=size1;
        }

    }

    /* Returns the ROOT of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int findRoot(int vertex) {
        int item = vertex;
        if(parent[vertex] < 0)
            return vertex;

        while(parent[item] >= 0){
            item = parent[item];
        }
        parent[vertex] = item;
        return item;
    }

}
