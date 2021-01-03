import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class Term implements Comparable<Term> {

    private final String qry; // search query
    private final long wt; // weight

    // Initializes a term with the given query string and weight
    public Term(String query, long weight) {
        if (query == null)
            throw new IllegalArgumentException("No query given");
        if (weight < 0)
            throw new IllegalArgumentException("Weight cannot be negative");

        qry = query;
        wt = weight;
    }

    // Compares the two terms in descending order by weight
    public static Comparator<Term> byReverseWeightOrder() {
        return new DescWeightOrder();
    }

    // Private nested class comparing two terms according to weight
    private static class DescWeightOrder implements Comparator<Term> {
        public int compare(Term a, Term b) {
            return Long.compare(b.wt, a.wt); // returns -1 for if a.wt > b.wt
        }
    }

    // Compares the two terms in lexicographic order, but using only the first r
    // characters of each query
    public static Comparator<Term> byPrefixOrder(int r) {
        if (r < 0)
            throw new IllegalArgumentException("r cannot be negative");
        return new PrefixOrder(r);
    }

    // Private nested class comparing first r-characters of two terms
    // in lexicographic order
    private static class PrefixOrder implements Comparator<Term> {
        private final int r; // num characters to compare

        // constructs the Comparator using the given num of chars to compare
        public PrefixOrder(int r) {
            this.r = r;
        }

        public int compare(Term a, Term b) {
            // compare the first r chars one by one and return an order as soon
            // as a difference is found; if no difference after going through
            // loop, consider them equal
            for (int i = 0; i < r; i++) {
                // use String compareTo() if one of the terms is shorter than r
                if (a.qry.length() < r || b.qry.length() < r)
                    return a.compareTo(b);
                if (a.qry.charAt(i) < b.qry.charAt(i))
                    return -1;
                if (a.qry.charAt(i) > b.qry.charAt(i))
                    return 1;
            }
            return 0;
        }
    }

    // Compares the two terms in lexicographic order by query, using the String
    // compareTo()
    public int compareTo(Term that) {
        return this.qry.compareTo(that.qry);
    }

    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query
    public String toString() {
        return wt + "\t" + qry;
    }

    // Private helper method for printing out queries from an array of terms
    private static void print(Term[] array) {
        for (Term t : array)
            StdOut.print(t.qry + " ");
        StdOut.println();
    }

    // Unit testing
    public static void main(String[] args) {
        // Construct an array of 10 terms for testing with the query strings
        // specified
        int n = 10;
        String[] queries = {
                "a", "b", "aa", "ab", "ba", "bb",
                "aaa", "aab", "aba", "abb"
        };
        Term[] alpha = new Term[n];
        for (int i = 0; i < n; i++) {
            alpha[i] = new Term(queries[i], i + 1); // add weights from 1 to 10
        }

        // Print the terms in their original order
        StdOut.println("weight\tquery:");
        for (int i = 0; i < n; i++) {
            StdOut.println(alpha[i].toString());
        }

        // Print terms in lexicographic sorted order
        StdOut.print("lexicographic order: ");
        Arrays.sort(alpha);
        print(alpha);

        // Print terms in reverse weight order (should be the opposite of the
        // original order)
        StdOut.print("reverse weight order: ");
        Arrays.sort(alpha, byReverseWeightOrder());
        print(alpha);

        // Print terms in prefix order for r = 1, r = 2, and r = 3
        StdOut.print("prefix order (r = 1): ");
        Arrays.sort(alpha, byPrefixOrder(1));
        print(alpha); // should resemble order left from previous sort but with
        // a__ terms before b__ terms

        StdOut.print("prefix order (r = 2): ");
        Arrays.sort(alpha, byPrefixOrder(2));
        print(alpha); // terms with less than 2 chars should come before longer
        // terms starting with the same letter  (e.g. a < aa, b < ba)

        StdOut.print("prefix order (r = 3): ");
        Arrays.sort(alpha, byPrefixOrder(3));
        print(alpha); // should be same as lexicographic order since length of
        // queries do not exceed 3
    }

}
