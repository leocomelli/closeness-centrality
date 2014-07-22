(ns closeness-centrality.core
  (:require [clojure.tools.cli :refer [parse-opts]])
  (:use [closeness-centrality.cc]
  	    [closeness-centrality.edges_handler]
  	    [closeness-centrality.fb_edges_handler])
  (:gen-class))

  (def app-id "1537090899852241")
  (def app-access-token "1537090899852241|mQtH-r01dEtXuUMcwcWsKuCZkrA")

  (def test-user-id :1459759470945924)
  ;(def test-user-access-token "CAAV1ZBd0Su9EBADHZCRhZBmZBd9yCRbcg0o0KCtch3MaH79ImCP0BoELmj7GlX8Cns963hyyFEoLBJOCujryymQAvrnIi0mDTGb2w6qFtv3bWr4ZAZBJXHIbBFwIw3eBNXD5KT3IXSBsYZAAHIt9tfWlZCuFBqZA7qPphPll32WXsHwvWg00kWRWKXZAji7tkrGyK1n6fHctzPQG6gTLZA12I6hZA0ITINSBIZCgZD")

  (def cli-options
    [])

  (defn -main [& args]
    (let [{:keys [options arguments errors summary]} (parse-opts args cli-options)]
      (if (= (first arguments) "fb")
        (print (closeness-centrality 
          (load-friends test-user-id 
            (list-access-token-by-user app-id app-access-token))))
        (print (closeness-centrality 
          (load-edges "resources/edges"))))))