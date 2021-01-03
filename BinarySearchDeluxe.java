/* *****************************************************************************
 *  Name:    Vicki Yang
 *  NetID:   vyang
 *  Precept: P04
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Description: This class is a modified implementation of binary search (see
 *  citation). firstIndexOf() and lastIndexOf() respectively returns the first
 *  and last keys matching the search key in a sorted array.
 *
 *  @citation Adapted from: https://www.cs.princeton.edu/courses/archive/fall20/
 *  cos226/lectures/21ElementarySorts.pdf. Accessed 9/27/2020.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class BinarySearchDeluxe {

    // Returns the index of the first key in the sorted array a[] that is equal
    // to the search key, or -1 if no such key
    public static <Key> int firstIndexOf(Key[] a, Key key,
                                         Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null)
            throw new IllegalArgumentException("Arguments cannot be null");

        // implement modified binary search
        int lo = 0;
        int hi = a.length - 1;
        // keep index of leftmost key found; -1 means no matching keys
        int leftmost = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int compare = comparator.compare(key, a[mid]);

            if (compare < 0)
                hi = mid - 1;
            else if (compare > 0)
                lo = mid + 1;
            else {
                leftmost = mid; // save the key found but keep searching
                hi = mid - 1; // search to the left of found key
            }
        }
        return leftmost;
    }

    // Returns the index of the last key in the sorted array a[] that is equal
    // to the search key, or -1 if no such key
    public static <Key> int lastIndexOf(Key[] a, Key key,
                                        Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null)
            throw new IllegalArgumentException("Arguments cannot be null");

        // implement modified binary search
        int lo = 0;
        int hi = a.length - 1;
        // keep index of rightmost key found; -1 means no matching keys
        int rightmost = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int compare = comparator.compare(key, a[mid]);

            if (compare < 0)
                hi = mid - 1;
            else if (compare > 0)
                lo = mid + 1;
            else {
                rightmost = mid; // save the key found but keep searching
                lo = mid + 1; // search to the right of found key
            }
        }
        return rightmost;
    }

    // Unit testing
    public static void main(String[] args) {
        // test with an array with duplicates
        String[] a = { "A", "A", "A", "B", "B", "B", "B", "B", "B" };
        // print the array
        for (String letter : a)
            StdOut.print(letter + " ");
        StdOut.println();

        // search for B
        int aFirstB =
                BinarySearchDeluxe.firstIndexOf(a, "B",
                                                String.CASE_INSENSITIVE_ORDER);
        int aLastB =
                BinarySearchDeluxe.lastIndexOf(a, "B",
                                               String.CASE_INSENSITIVE_ORDER);

        StdOut.println("first B in array a found at:\t" + aFirstB);
        StdOut.println("last B in array a found at:\t" + aLastB);

        // search for A
        int aFirstA =
                BinarySearchDeluxe.firstIndexOf(a, "A",
                                                String.CASE_INSENSITIVE_ORDER);
        int aLastA =
                BinarySearchDeluxe.lastIndexOf(a, "A",
                                               String.CASE_INSENSITIVE_ORDER);

        StdOut.println("first A in array a found at:\t" + aFirstA);
        StdOut.println("last A in array a found at:\t" + aLastA);

        // search for C
        int aFirstC =
                BinarySearchDeluxe.firstIndexOf(a, "C",
                                                String.CASE_INSENSITIVE_ORDER);
        int aLastC =
                BinarySearchDeluxe.lastIndexOf(a, "C",
                                               String.CASE_INSENSITIVE_ORDER);

        StdOut.println("first C in array a found at:\t" + aFirstC);
        StdOut.println("last C in array a found at:\t" + aLastC);
    }

}
