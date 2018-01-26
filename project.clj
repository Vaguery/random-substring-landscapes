(defproject rs-landscapes "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://vaguery.com"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/math.combinatorics "0.1.4"]]
  :profiles {:dev {:dependencies [[midje "1.9.1"]]
                   :plugins [[lein-midje "3.2.1"]]}}
  )
