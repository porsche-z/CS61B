import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V>  implements Map61B<K, V>  {

    private Node root;             // root of BST
    public K Krecord;
    public V Vrecord;


    private class Node {
        private K key;           // sorted by key
        private V val;         // associated data
        private Node left, right;  // left and right subtrees
        private int size;          // number of nodes in subtree

        public Node(K key, V val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node x, K key) {
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.val;
    }

    @Override
    public int size() {
        return size(root);
    }
    private int size(Node x) {
        if (x == null) return 0;
        else return x.size;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("calls put() with a null key");
        if (value == null) throw new IllegalArgumentException("calls put() with a null value");
        root = put(root, key, value);
    }

    private Node put(Node x, K key, V val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    public void printInOrder(){
        printInOrder(root);
    }

    public void printInOrder(Node X){
        if(X == null)
            return;
        printInOrder(X.left);
        System.out.print(X.key.toString()+" ");
        printInOrder(X.right);
    }


    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key){
        if (key == null) throw new IllegalArgumentException("calls delete() with a null key");
        root=remove(root, key);//This sentence is essential, because java only have value-pass, and "root" is a destination,remove(root,key) can't change root itself, but the content in the root address
        return Vrecord;
    }
    public Node remove(Node x, K key){
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if(cmp < 0) x.left=remove(x.left,key);
        else if (cmp > 0) x.right=remove(x.right,key);
        else {
            Vrecord = x.val;
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node oldX = x;
            x = min(oldX.right);//take place with old x with min in old X's right subtree
            x.right = deleteMin(oldX.right);
            x.left = oldX.left;
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Node min(Node x){
        if(x.left == null)
            return x;
        else return min(x.left);
    }

    public Node deleteMin(Node x){
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = size(x.left)+1+size(x.right);
        return x;
    }
    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value){
        return null;
    }

    @Override
    public Iterator<K> iterator(){return new BSTMapIterator();}

    private class BSTMapIterator implements Iterator<K> {
        private List<Node> NodeList;
        private int cur;

        public BSTMapIterator() {
            NodeList =new ArrayList<Node>();
            listInOrder(root);
            cur = 0;
        }

        public void listInOrder(Node X){
            if(X == null)
                return;
            listInOrder(X.left);
            NodeList.add(X);
            listInOrder(X.right);
        }
        @Override
        public boolean hasNext() {
            return NodeList.get(cur) != null;
        }

        @Override
        public K next() {
            K ret = NodeList.get(cur).key;
            cur++;
            return ret;
        }


        /** Stores the current key-value pair. */

    }

    /* Returns a Set view of the keys contained in this map. */
    public Set<K> keySet(){
        return null;
    }

}
