import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class UnionFindTest {
    static UnionFind DisjointSet = new UnionFind(7);

    @Test
    public void testMyClass(){
        DisjointSet.union(0, 1);
        DisjointSet.union(2, 3);
        assertEquals(2, DisjointSet.sizeOf(2));
        assertFalse(DisjointSet.connected(0, 3));
        DisjointSet.union(0,3);
        assertEquals(4, DisjointSet.sizeOf(2));
        DisjointSet.union(0, 4);
        assertEquals(3,DisjointSet.parent(0));
        assertEquals(3,DisjointSet.findRoot(4));
        assertTrue(DisjointSet.connected(0,4));
        DisjointSet.union(5,5);
        assertEquals(1,DisjointSet.sizeOf(5));
    }
}
