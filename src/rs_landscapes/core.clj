(ns rs-landscapes.core)

(defn make-matcher
  [substring]
  (re-pattern
    (clojure.string/replace substring
                            #"#"
                            "."
                            )))

(defn count-substring
  [substring string]
  (let [chunks (partition
                (count substring) 1 string)]
    (count
      (filter
        #(string?
          (re-matches
            (make-matcher substring)
            (apply str %)))
        chunks)
        )))


(defn score-substring
  [substring string score]
  (* score (count-substring substring string)))

(defn score-string
  [string score-hash]
  (apply +
    (map
    #(score-substring
      (first %)
      string
      (second %))
    score-hash)
    ))
