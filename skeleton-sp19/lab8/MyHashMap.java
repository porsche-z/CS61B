import java.security.Key;
import java.util.*;
import java.lang.Math.*;

import static java.lang.Math.floorMod;

public class MyHashMap<K, V> implements Map61B<K,V> {
    private static final int INIT_CAPICITY = 16;
    private static final long INIT_LOADFACTOR = (long) 0.75;
    private int n; //number of key-value size
    private int m; //hasTable size
    private long loadFactor;
    private List<List<Node>> SeperateChainHash;
    private Set<K> keySet;

    private class Node{
        private K key;
        private V val;
        public Node(K key, V val){
            this.key = key;
            this.val = val;
        }
    }

    private int hash(K key){
        return floorMod(key.hashCode(),m);
    }
    public MyHashMap(){
        this(INIT_CAPICITY,INIT_LOADFACTOR);
    }
    public MyHashMap(int cap){
        this(cap,INIT_LOADFACTOR);
    }
    public MyHashMap(int cap, long LF){
        this.m = cap;
        this.loadFactor = LF;
        SeperateChainHash = new ArrayList<>();
        for(int i = 0; i < m; i++){
            SeperateChainHash.add(new LinkedList<Node>());
        }
        keySet = new HashSet<>();
    }
    @Override
    public void clear() {
        n = 0;
        m = INIT_CAPICITY;
        SeperateChainHash = new ArrayList<>();
        for(int i = 0; i < m; i++){
            SeperateChainHash.add(new LinkedList<Node>());
        }
        keySet = new HashSet<>();
    }

    @Override
    public boolean containsKey(K key) {
        List<Node> targetList = SeperateChainHash.get(hash(key));
        for (Node N :targetList){
            if(N.key.equals(key))
                return true;
        }
        return false;
    }

    @Override
    public V get(K key) {
        List<Node> targetList = SeperateChainHash.get(hash(key));
        for (Node N : targetList){
            if(N.key.equals(key))
                return N.val;
        }
        return null;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public void put(K key, V value) {
        List<Node> targetList = SeperateChainHash.get(hash(key));
        for (Node N :targetList){
            if(N.key.equals(key)){
                N.val = value;
                return;
            }
        }
        targetList.add(new Node(key,value));
        keySet.add(key);
        n++;
        if(n/m > loadFactor){
            expandTable();
        }
    }

    public void expandTable(){
        m = m*2;
        List<List<Node>> newSeperateChainHash = new ArrayList<>();
        for(int i = 0; i < m; i++){
            newSeperateChainHash.add(new LinkedList<Node>());
        }
        for(List<Node> L : SeperateChainHash){
            for(Node N :L){
                put(newSeperateChainHash, N.key, N.val);
            }
        }
        SeperateChainHash = newSeperateChainHash;
    }

    public void put(List<List<Node>> newMap, K key, V val){
        newMap.get(hash(key)).add(new Node(key, val));
    }

    @Override
    public Set<K> keySet() {
        return keySet;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
            return new MyHashMapIter();
    }

    private class MyHashMapIter<K> implements Iterator<K>{
        int curList;
        int curPos;
        int curKey;
        MyHashMapIter(){
            curList = 0;
            curPos = 0;
            curKey = 1;
        }
        @Override
        public boolean hasNext() {
            if (curKey < keySet.size())
                return true;
            else
                return false;
        }

        @Override
        public K next() {
            K ret = (K) SeperateChainHash.get(curList).get(curPos).key;
            if(curPos == SeperateChainHash.get(curList).size()-1){
                for(int i=curList;i<SeperateChainHash.size();i++){
                    if(SeperateChainHash.get(curList).size()>0)
                        curPos=0;
                }
            }
            else {
                curPos++;
            }
            curKey++;
            return ret;
        }
    }
}
