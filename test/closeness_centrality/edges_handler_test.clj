(ns closeness-centrality.edges_handler-test
  (:require [clojure.test :refer :all]
            [closeness-centrality.edges_handler :refer :all]))

  (deftest test-load-edges
    (let [edges (load-edges "resources/tests/edges")]
      (is (= (count edges) 5))
      (is (= (count (first edges)) 2))
      (is (= (first edges) [:1 :2]))
      (is (= (last edges) [:2 :5]))))