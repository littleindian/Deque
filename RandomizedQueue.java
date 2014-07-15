import java.util.*;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] s;
    private int size = 0;
    
    private class RandomizedQueueIterator implements Iterator<Item> {
        private int scanned = 0;
        private Item[] copy;
        public RandomizedQueueIterator() {
            copy = (Item[]) new Object[size];
            for (int i = 0; i < size; i++)
                copy[i] = s[i];
        }
                                          
        public boolean hasNext() {
            return scanned < size;
        }
        
        public void remove() {
            // Not supported
            throw new java.lang.UnsupportedOperationException();
        }
        
        public Item next() {
            int remaining = size - scanned;
            if (remaining == 0) throw new NoSuchElementException();
            int r = StdRandom.uniform(remaining);
            Item ret = copy[r];
            copy[r] = copy[remaining - 1];
            copy[remaining - 1] = ret;
            scanned++;
            return ret;
        }
    }
    
    // construct an empty randomized queue
    public RandomizedQueue() { 
        s = (Item[]) new Object[1];
    }   
    
    // is the queue empty?
    public boolean isEmpty() {
        return size == 0;
    }
   
    // return the number of items on the queue
    public int size() {
        return size;
    }
    
    private void resize(int len) {
        Item[] copy = (Item []) new Object[len];
        for (int i = 0; i < size; i++)
            copy[i] = s[i];
        s = copy;
    }
    
    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        if (size == s.length) {
            resize(s.length * 2);
        }
        s[size++] = item;
    }
    
    // delete and return a random item
    public Item dequeue() {
        if (size == 0) throw new java.util.NoSuchElementException();
        int r = StdRandom.uniform(size);
        Item ret = s[r];
        s[r] = s[size - 1];
        s[size - 1] = null;
        size--;
        if (4 * size < s.length) {
            resize(s.length / 2);
        }
        return ret;
    }
    
    // return (but do not delete) a random item
    public Item sample() {
        return (s[StdRandom.uniform(s.length)]);
    }
    
    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }
    
    // unit testing
    public static void main(String[] args) {
        RandomizedQueue<String> randQ = new RandomizedQueue<String>();
        randQ.enqueue("Java");
        randQ.enqueue("C++");
        randQ.enqueue("Ruby");
        randQ.enqueue("Python");
        randQ.enqueue("C");
 //       randQ.enqueue(null);
        
        Iterator<String> iter = randQ.iterator();
        while (iter.hasNext()) {
            StdOut.printf("[I]Got back %s\n", iter.next());
        }
        StdOut.println("");
        StdOut.println("Got back: " + randQ.dequeue());
        StdOut.println("Got back: " + randQ.dequeue());
        StdOut.println("Got back: " + randQ.dequeue());
        StdOut.println("Got back: " + randQ.dequeue());
        StdOut.println("Got back: " + randQ.dequeue());
        StdOut.println("");
        randQ.enqueue("PHP");
        randQ.enqueue("JavaScript");
        randQ.enqueue("Go");
        randQ.enqueue("Perl");
        randQ.enqueue("Scheme");
        
        
        iter = randQ.iterator();
        while (iter.hasNext()) {
            StdOut.printf("[I]Got back %s\n", iter.next());
        }
        
        StdOut.println("Got back: " + randQ.dequeue());
        StdOut.println("Got back: " + randQ.dequeue());
        StdOut.println("Got back: " + randQ.dequeue());
        StdOut.println("Got back: " + randQ.dequeue());
        StdOut.println("Got back: " + randQ.dequeue());

    }                                           
}
