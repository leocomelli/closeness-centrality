(ns closeness-centrality.cc)

  (defn mapping-vertices [& vertices]
    (reduce 
      (fn [m [k v]] 
        (update-in m [k] (fnil conj []) v)) 
      {} 
      (for [m vertices entry m] entry)))

  (defn add-connected-vertices [distances connected v_dist]
    (merge-with 
      #(or %1 %2) ; (fn[v1 v2] (or v1 v2))
      distances  
      (into {} (map (fn[x] {x v_dist}) connected))))

  (defn short-path [graph vertex]
    (loop [queue [vertex]
           visited #{vertex}
           distances {vertex 0}]
      (if (empty? queue)
        distances ;(into (sorted-map) distances)        
        (let [current (nth queue 0)
              n_queue (subvec queue 1) ; remove first pos
              ;connected (get graph current)
              connected (remove visited (get graph current))
              v_dist (get distances current)]
          (recur
            (into[] (into n_queue connected))
            (into visited connected)
            (add-connected-vertices distances connected (inc v_dist)))))))

  (defn merge-list-of-maps[lom]
    (reduce 
      (fn [m [k v]] 
        (update-in m [k] (fnil + (get m k 0)) v))
      {} 
      (for [m lom entry m] entry)))

  (defn sort-map-by-value [m]
    (into (sorted-map-by (fn [key1 key2]
                         (compare [(get m key2) key2]
                                  [(get m key1) key1])))
          m))

  (defn closeness-centrality [edges]
    (let [medges (mapping-vertices edges)
          sps (for [[v c] medges] 
                (short-path medges v))
          csps (merge-list-of-maps sps)]
    (sort-map-by-value
      (merge-list-of-maps
        (for [sp sps] 
          (let [v (first (filter (comp #{0} sp) (keys sp)))]
            {v (/ 
                 (- (count edges) 1) 
                 (+ (get csps v 0) (reduce + (vals sp))))}))))))

