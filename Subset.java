import java.util.*;
public class Subset {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        String[] s = StdIn.readAllStrings();
        RandomizedQueue<String> randQ = new RandomizedQueue<String>();
        for (int i = 0; i < s.length; i++)
            randQ.enqueue(s[i]);
        if ((k > 0) && (k <= s.length)) {
            Iterator<String> i = randQ.iterator();
            for (int j = 0; j < k; j++)
                StdOut.printf("%s ", i.next());
        }
    }
}
