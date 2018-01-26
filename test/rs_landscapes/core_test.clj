(ns rs-landscapes.core-test
  (:require [clojure.test :refer :all]
            [rs-landscapes.core :refer :all])
  (:use midje.sweet)
  )

(fact
  "make-matcher"
  (make-matcher "123") => #"123"
  (make-matcher "12#") => #"12."
  )

(fact
  "counting substrings"
   (count-substring "12" "121212") => 3
   (count-substring "11" "111111") => 5
   (count-substring "111" "111111") => 4
   (count-substring "101" "111111") => 0
   )

(fact
  "it works with wildcards"
  (count-substring "12" "121212") => 3
  (count-substring "##" "121212") => 5
  (count-substring "##2" "121212") => 2
  (count-substring "1#2" "111222111222") => 4
  )

(fact
  "assigning scores for substrings"
  (score-substring "12" "121212" 11) => 33
  (score-substring "11" "111111" 2.2) => 11.0
  (score-substring "111" "111111" -9) => -36
  (score-substring "101" "111111" 17) => 0
  )

(fact
  "scoring a whole string"
  (score-string "1122"
    {"1" 1 "2" 2
    "11" 11 "12" 12 "21" 21 "22" 22}) => 51
    ;; 1 + 1 + 2 + 2 + 11 + 12 + 22
  (score-string "1122"
    {"1" 1 "2" 2
    "11" 1 "22" -2}) => 5
    ;; 1 + 1 + 2 + 2 + 1 + -2
    )
