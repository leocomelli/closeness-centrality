;
;         --- 3 ---
;        /         \
; 1 --- 2          4
;        \         /
;         --- 5 ---
;
(ns closeness-centrality.cc-test
  (:require [clojure.test :refer :all]
            [closeness-centrality.edges_handler :refer :all]
            [closeness-centrality.cc :refer :all]))

  (deftest test-mapping-vertices
    (def e [[:1 :2] [:2 :3] [:3 :4] [:4 :5] [:2 :5]])

    (let [edges (mapping-vertices e)]
      (is (= (count edges) 4))
      (is (= (count (get edges :3)) 1))
      (is (= (count (get edges :2)) 2))))

  (deftest test-add-connected-vertices
    (let [new-map (add-connected-vertices {:1 1} [:2 :3] 0)]
      (is (= (count new-map) 3))
      (is (= (get new-map :2) 0))
      (is (= (get new-map :1) 1))))

  (deftest test-short-path
    (def e {:4 [:5], :3 [:4], :2 [:3 :5], :1 [:2]})

    (let [sp (short-path e :1)]
      (is (= (get sp :1) 0))
      (is (= (get sp :2) 1))
      (is (= (get sp :3) 2))
      (is (= (get sp :4) 3))
      (is (= (get sp :5) 2))))

  (deftest test-merge-list-of-maps
    (def lom '({:a 1, :e 2} {:b 2} {:c 3}))
    

    (let [mm (merge-list-of-maps lom)]
      (is (= (count lom) 3))
      (is (= (count mm) 4))))

  (deftest test-sort-map-by-value
    (def m {:a 3, :b 1, :c 2, :d 4, :e 2})
    
    (let [sm (sort-map-by-value m)]
      (is (= {:d 4, :a 3, :c 2, :e 2, :b 1} sm))))

  (deftest test-closeness-centrality
    (def expected (sort-map-by-value {:2 4/5, :3 2/3, :4 4/7, :1 1/2}))
    
    (let [r (closeness-centrality (load-edges "resources/tests/edges"))]
      (is (= expected r))))


