package test.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;

import com.musicquint.api.BarMap;
import com.musicquint.api.BarTime;
import com.musicquint.api.Measurable;

class BarMapTest {

    private BarMap<Measurable> createBarMapMockup() {
        NavigableMap<BarTime, Measurable> navigableMap = new TreeMap<>();

        return new BarMap<Measurable>() {

            @Override
            public Entry<BarTime, Measurable> lowerEntry(BarTime key) {
                return navigableMap.lowerEntry(key);
            }

            @Override
            public BarTime lowerKey(BarTime key) {
                return navigableMap.lowerKey(key);
            }

            @Override
            public Entry<BarTime, Measurable> floorEntry(BarTime key) {
                return navigableMap.floorEntry(key);
            }

            @Override
            public BarTime floorKey(BarTime key) {
                return navigableMap.floorKey(key);
            }

            @Override
            public Entry<BarTime, Measurable> ceilingEntry(BarTime key) {
                return navigableMap.ceilingEntry(key);
            }

            @Override
            public BarTime ceilingKey(BarTime key) {
                return navigableMap.ceilingKey(key);
            }

            @Override
            public Entry<BarTime, Measurable> higherEntry(BarTime key) {
                return navigableMap.higherEntry(key);
            }

            @Override
            public BarTime higherKey(BarTime key) {
                return navigableMap.higherKey(key);
            }

            @Override
            public Entry<BarTime, Measurable> firstEntry() {
                return navigableMap.firstEntry();
            }

            @Override
            public Entry<BarTime, Measurable> lastEntry() {
                return navigableMap.lastEntry();
            }

            @Override
            public Entry<BarTime, Measurable> pollFirstEntry() {
                return navigableMap.pollFirstEntry();
            }

            @Override
            public Entry<BarTime, Measurable> pollLastEntry() {
                return navigableMap.pollLastEntry();
            }

            @Override
            public NavigableMap<BarTime, Measurable> descendingMap() {
                return navigableMap.descendingMap();
            }

            @Override
            public NavigableSet<BarTime> navigableKeySet() {
                return navigableMap.navigableKeySet();
            }

            @Override
            public NavigableSet<BarTime> descendingKeySet() {
                return navigableMap.descendingKeySet();
            }

            @Override
            public NavigableMap<BarTime, Measurable> subMap(BarTime fromKey, boolean fromInclusive, BarTime toKey,
                    boolean toInclusive) {
                return navigableMap.subMap(fromKey, fromInclusive, toKey, toInclusive);
            }

            @Override
            public NavigableMap<BarTime, Measurable> headMap(BarTime toKey, boolean inclusive) {
                return navigableMap.headMap(toKey, inclusive);
            }

            @Override
            public NavigableMap<BarTime, Measurable> tailMap(BarTime fromKey, boolean inclusive) {
                return navigableMap.tailMap(fromKey, inclusive);
            }

            @Override
            public SortedMap<BarTime, Measurable> subMap(BarTime fromKey, BarTime toKey) {
                return navigableMap.subMap(fromKey, toKey);
            }

            @Override
            public SortedMap<BarTime, Measurable> headMap(BarTime toKey) {
                return navigableMap.headMap(toKey);
            }

            @Override
            public SortedMap<BarTime, Measurable> tailMap(BarTime fromKey) {
                return navigableMap.tailMap(fromKey);
            }

            @Override
            public Comparator<? super BarTime> comparator() {
                return navigableMap.comparator();
            }

            @Override
            public BarTime firstKey() {
                return navigableMap.firstKey();
            }

            @Override
            public BarTime lastKey() {
                return navigableMap.lastKey();
            }

            @Override
            public Set<BarTime> keySet() {
                return navigableMap.keySet();
            }

            @Override
            public Collection<Measurable> values() {
                return navigableMap.values();
            }

            @Override
            public Set<Entry<BarTime, Measurable>> entrySet() {
                return navigableMap.entrySet();
            }

            @Override
            public int size() {
                return navigableMap.size();
            }

            @Override
            public boolean isEmpty() {
                return navigableMap.isEmpty();
            }

            @Override
            public boolean containsKey(Object key) {
                return navigableMap.containsKey(key);
            }

            @Override
            public boolean containsValue(Object value) {
                return navigableMap.containsValue(value);
            }

            @Override
            public Measurable get(Object key) {
                return navigableMap.get(key);
            }

            @Override
            public Measurable remove(Object key) {
                return navigableMap.remove(key);
            }

            @Override
            public void putAll(Map<? extends BarTime, ? extends Measurable> m) {
                navigableMap.putAll(m);
            }

            @Override
            public void clear() {
                navigableMap.clear();
            }

            @Override
            public Measurable put(BarTime key, Measurable value) {
                if (fits(key, value)) {
                    return navigableMap.put(key, value);
                } else {
                    throw new IllegalStateException("The Measurable " + value + " does not fit in at the time " + key);
                }
            }

            @Override
            public BarTime capacity() {
                return BarTime.FOUR_QUARTER;
            }
        };
    }

    @Test
    void testZeroLength() {
        BarMap<Measurable> bMap = createBarMapMockup();

        assertEquals(BarTime.FOUR_QUARTER, bMap.capacity());
        assertEquals(BarTime.ZERO, bMap.length());
    }

    @Test
    void testHalfAndEightLength() {
        BarMap<Measurable> bMap = createBarMapMockup();
        bMap.put(BarTime.HALF, BarTime.EIGHTH);
        assertEquals(BarTime.FOUR_QUARTER, bMap.capacity());
        assertEquals(BarTime.of(5,2), bMap.length());
    }

    @Test
    void testNextInThreeEight() {
        BarMap<Measurable> bMap = createBarMapMockup();
        bMap.put(BarTime.HALF, BarTime.EIGHTH);
        assertEquals(BarTime.THREE_EIGHTH, bMap.next(BarTime.EIGHTH));
    }

    @Test
    void testNextAfterLastItem() {
        BarMap<Measurable> bMap = createBarMapMockup();
        bMap.put(BarTime.HALF, BarTime.EIGHTH);
        assertEquals(BarTime.of(7,4), bMap.next(BarTime.of(9,4)));
    }

    @Test
    void testLasting() {
        BarMap<Measurable> bMap = createBarMapMockup();
        bMap.put(BarTime.HALF, BarTime.EIGHTH);
        assertEquals(BarTime.of(1,4), bMap.lasting(BarTime.of(9,4)));
    }

    @Test
    void testLastingZero() {
        BarMap<Measurable> bMap = createBarMapMockup();
        bMap.put(BarTime.HALF, BarTime.EIGHTH);
        assertEquals(BarTime.ZERO, bMap.lasting(BarTime.of(11,4)));
    }

    @Test
    void testLastingZero2() {
        BarMap<Measurable> bMap = createBarMapMockup();
        bMap.put(BarTime.HALF, BarTime.EIGHTH);
        assertEquals(BarTime.ZERO, bMap.lasting(BarTime.of(5,2)));
    }

    @Test
    void testFitsTrueBeforeAnotherItem() {
        BarMap<Measurable> bMap = createBarMapMockup();
        bMap.put(BarTime.HALF, BarTime.EIGHTH);
        assertEquals(true, bMap.fits(BarTime.THREE_EIGHTH, BarTime.EIGHTH));
    }

    @Test
    void testFitsFalseBeforeAnotherItem() {
        BarMap<Measurable> bMap = createBarMapMockup();
        bMap.put(BarTime.HALF, BarTime.EIGHTH);
        assertEquals(false, bMap.fits(BarTime.THREE_EIGHTH, BarTime.EIGHTH_DOT));
    }

    @Test
    void testFitsfalseWhileItem() {
        BarMap<Measurable> bMap = createBarMapMockup();
        bMap.put(BarTime.HALF, BarTime.QUARTER);
        assertEquals(false, bMap.fits(BarTime.of(5,2), BarTime.EIGHTH_DOT));
    }

    @Test
    void testFitsfTrueAfterItem() {
        BarMap<Measurable> bMap = createBarMapMockup();
        bMap.put(BarTime.HALF, BarTime.EIGHTH);
        assertEquals(true, bMap.fits(BarTime.of(5,2), BarTime.THREE_EIGHTH));
    }

    @Test
    void testFitsfFalseAfterItem() {
        BarMap<Measurable> bMap = createBarMapMockup();
        bMap.put(BarTime.HALF, BarTime.EIGHTH);
        assertEquals(false, bMap.fits(BarTime.of(5,2), BarTime.HALF));
    }

    @Test
    void testFitsfFalseKeyLowerThanZero() {
        BarMap<Measurable> bMap = createBarMapMockup();
        bMap.put(BarTime.HALF, BarTime.EIGHTH);
        assertEquals(false, bMap.fits(BarTime.of(-1,2), BarTime.HALF));
    }
}
