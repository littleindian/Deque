import java.util.*;
public class Deque<Item> implements Iterable<Item> {
    
    private class Node {
        Item s;
        Node next;
        Node prev;
    }
    
    private Node first, last;
    private int size;
    
    private class DequeIterator implements Iterator<Item> {
        private Node iter = first;
        
        public boolean hasNext() {
            return iter != null;
        }
        
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
        
        public Item next() {
            if(iter != null) {
                Item i = iter.s;
                iter = iter.next;
                return i;
            }
            throw new java.util.NoSuchElementException();
        }
    }
    
    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }
    
   // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }
    
    // return the number of items on the deque
    public int size() {
        return size;
    }
        
    private void add(Item item, Node position) {
        Node newNode = new Node();
        newNode.s = item;
        size++;
        if(position == first) {
            newNode.next = first;
            if(first != null) {
                first.prev = newNode;
            }
            first = newNode;
            first.prev = null;
            if(size == 1) last = first;            
        } else {
            if(last != null) {
                last.next = newNode;
                newNode.prev = last;
                last = newNode;
                last.next = null;
            } 
        }
     }
    
    // insert the item at the front
    public void addFirst(Item item) {
        if(item == null) throw new java.lang.UnsupportedOperationException();
        add(item, first);
    }
    
    // insert the item at the end
    public void addLast(Item item) {
        if(size == 0) {
            add(item, first);
        } else {
            add(item, last);
        }
    }
    
    // delete and return the item at the front
    public Item removeFirst() {
        Item ret;
        if(size < 1) throw new java.util.NoSuchElementException();
        size--;
        ret = first.s;
        first = first.next;
        first.prev = null;
        if(size == 0) last = null;
        return ret;
    }
    
    // delete and return the item at the end
    public Item removeLast() {
        Item ret;
        if(size < 1) throw new java.util.NoSuchElementException();
        if(size == 1) return removeFirst();
        size--;
        ret = last.s;
        last = last.prev;
        last.next = null;
        return ret;
    }
    
    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }
    
    // unit testing
    public static void main(String[] args) {
        Deque testDeque = new Deque<String>();
        Iterator i;
        testDeque.addFirst("Hello");
        testDeque.addLast("Girish");
        testDeque.addFirst("Now this is great");
        testDeque.addLast("New Last");
        StdOut.println("Iterating over deque...");
        i = testDeque.iterator();
        while(i.hasNext())
            StdOut.println("Element is : " + i.next());
        
        String s = (String) testDeque.removeLast();
        StdOut.println("Last element is: " + s);
        s = (String) testDeque.removeFirst();
        StdOut.println("First one is: " + s);
        //testDeque.addFirst(null);
        i = testDeque.iterator();
        while(i.hasNext())
            StdOut.println("Element is : " + i.next());
    }
}