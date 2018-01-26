# rs-landscapes

A little exploration of a novel class of fitness surfaces for metaheuristics research: "random substring landscapes".

## Background

The fitness landscapes I describe here are based on some conversations I had with Terry Jones back around 1998.

In Terry's Ph.D. thesis with John Holland, he explored the multi-scale correlation structures of a number of fitness landscapes then in use among complexologists, and also proposed a number of variants intended to surface aspects of evolutionary(-ish) search that the standard models (such as NK models) didn't address.

One of the landscapes he proposed but didn't do much with was called "random schema landscapes", or "RS models". These were intended as an improvement over the then-popular NK model, in the sense that they provided a simple mechanism for "neutral networks" to be easily introduced into what would otherwise be an homogeneous landscape structure. [I discuss RS landscapes (with Terry) in this gist.](https://gist.github.com/Vaguery/d46d95aaf1920d59bae6)

**But that's not what _this library provides_.** The "RS landscapes" I'm working with here are _Random Substring_ landscapes, not "Random Schemata".

### Random Substring landscapes

Let me define first, then explain.

Define a _contribution map_ as a key-value map, each containing a _substring_ and a numerical _contribution score_. For example, we can define a contribution table like this one (presented in Clojure syntax):

~~~ text
  {"a"    1,
   "b"   -3,
   "ab"   7,
   "bb"  11,
   "aab" -4
   }
~~~

For any given string we seek to score, we count the number of (potentially overlapping) occurrences of each string in the contribution map, and add the associated score.

So for example, here are several strings scored using that contribution map:

~~~ text
"" : 0
"aaa" : 3 (1 + 1 + 1)
"abbaa" : 15 (1 - 3 - 3 + 1 + 1 + 7 + 11)
"bbaab" : 3 (-3 - 3 + 1 + 1 - 3 + 11 + 7 - 4)
~~~

As a result, we have established a mapping from the set of all strings of arbitrary length into the (real-valued) scores.

Note that the possibility of negative contribution scores gives us room to explore complex "epistatic" interactions of various kinds, and to produce landscapes of subtly differing structure.

Take into account also that there are inhomogeneities in the ways substrings can overlap with one another. For instance, the substring `"aa"` overlaps with itself, while `"ab"` does not. As a result, we can potentially "fit" far more copies of the former into a string of fixed length than the latter. Similarly, two match strings like `"aaa"` and `"aabaa"` are more "compatible" with one another, in that they can be packed more tightly into a given string, than (for example) `"aaa"` and `"ababa"`.

### wildcards

One minor extension, implemented here in this code, is the carry-over from Terry's Random Schema of "wildcard" characters. In this work I've used the traditional `#` octothorpe "don't care" symbol from Holland's original schemata theory work. Whenever it appears in the key of a contribution map, we can interpret an octothorpe as a _single character_ of any type. This permits match strings of the type `"a#b"`, which will match any three-character substring starting with `a` and ending with `b`, regardless of the middle character.

Again, this permits a remarkably flexible collection of "overlaps" to exist.

## Usage

Still in exploratory stages. Basically some simple Clojure functions to produce a score value from a string and a "contribution table".
