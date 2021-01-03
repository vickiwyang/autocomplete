# autocomplete

This program implements autocomplete for a given set of *n* terms, where a term is a query string and an associated non-negative weight. That is, given a prefix, find all queries that start with the given prefix, in descending order of weight.

**Term.java** implements the immutable data type Term, which represents an autocomplete search term (a query string with an associated integer weight). Terms can be compared by: 1) lexicographic order of the query string, 2) in descending order by weight, and 3) by lexicographic order of the first r-characters of the query string.

**BinarySearchDeluxe.java** is a modified implementation of binary search adapted from [Bob Sedgewick and Kevin Wayne](https://algs4.cs.princeton.edu/11model/BinarySearch.java.html). firstIndexOf() and lastIndexOf() respectively returns the first and last keys matching the search key in a sorted array.

**Autocomplete.java** implements the autocomplete functionality for a given set of strings and weights. Search terms are sorted in lexicographic order, then all matching query strings with a given prefix are found via binary search and sorted in descending order by weight.

--

*This assignment was completed as part of COS 226 at Princeton University in Fall 2020.*
