/* *****************************************************************************
 *  Name:    Vicki Yang
 *  NetID:   vyang
 *  Precept: P04
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Description:  This class implements the autocomplete functionality for a
 *  given set of strings and weights. Search terms are sorted in lexicographic
 *  order, then all matching query strings with a given prefix are found via
 *  binary search and sorted in descending order by weight.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Autocomplete {

    private final Term[] search; // search terms

    // Initializes the data structure from the given array of terms
    public Autocomplete(Term[] terms) {
        if (terms == null)
            throw new IllegalArgumentException("terms array cannot be null");

        search = new Term[terms.length]; // create defensive copy of sorted terms
        for (int i = 0; i < terms.length; i++) {
            if (terms[i] != null)
                search[i] = terms[i];
            else throw new IllegalArgumentException("null element in terms");
        }

        Arrays.sort(search); // sort terms in lexicographic order
    }

    // Returns all terms that start with the given prefix, in descending order
    // of weight
    public Term[] allMatches(String prefix) {
        if (prefix == null)
            throw new IllegalArgumentException("Arguments cannot be null");

        // use binary search to find first and last indices of terms that start
        // with given prefix
        int first = BinarySearchDeluxe.firstIndexOf(
                search,
                new Term(prefix, 0),
                Term.byPrefixOrder(prefix.length())
        );
        if (first == -1) // return array of length 0 if no matching terms found
            return new Term[0];
        int last = BinarySearchDeluxe.lastIndexOf(
                search,
                new Term(prefix, 0),
                Term.byPrefixOrder(prefix.length())
        );

        // create array to store matches; return sorted by desc weight order
        Term[] matches = new Term[last - first + 1];
        for (int i = 0; i < matches.length; i++) {
            matches[i] = search[first + i];
        }
        Arrays.sort(matches, Term.byReverseWeightOrder());
        return matches;
    }

    // Returns the number of terms that start with the given prefix
    public int numberOfMatches(String prefix) {
        if (prefix == null)
            throw new IllegalArgumentException("Arguments cannot be null");

        // use binary search to find first and last indices of terms that start
        // with given prefix
        int first = BinarySearchDeluxe.firstIndexOf(
                search,
                new Term(prefix, 0),
                Term.byPrefixOrder(prefix.length())
        );
        if (first == -1) // no matching terms found
            return 0;
        int last = BinarySearchDeluxe.lastIndexOf(
                search,
                new Term(prefix, 0),
                Term.byPrefixOrder(prefix.length())
        );

        return last - first + 1;
    }

    // Unit testing (sample client from assignment specification)
    public static void main(String[] args) {
        // read in the terms from a file
        String filename = args[0];
        In in = new In(filename);
        int n = in.readInt();
        Term[] terms = new Term[n];
        for (int i = 0; i < n; i++) {
            long weight = in.readLong();           // read the next weight
            in.readChar();                         // scan past the tab
            String query = in.readLine();          // read the next query
            terms[i] = new Term(query, weight);    // construct the term
        }

        // read in queries from standard input and print the top k matching terms
        int k = Integer.parseInt(args[1]);
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            StdOut.printf("%d matches\n", autocomplete.numberOfMatches(prefix));
            for (int i = 0; i < Math.min(k, results.length); i++)
                StdOut.println(results[i]);
        }
    }

}
