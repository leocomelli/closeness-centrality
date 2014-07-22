(ns closeness-centrality.edges_handler
  (:require [clojure.java.io :as io]
            [clojure.data.csv :as csv])
  (:use [closeness-centrality.cc]))


  (defn load-edges [filename]
    (into []
      (let [edges (with-open [in-file (io/reader filename)]
        (doall
          (csv/read-csv in-file :separator \space)))]
        (for [[v1 v2] edges] [(keyword v1) (keyword v2)]))))
