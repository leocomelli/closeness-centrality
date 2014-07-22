(ns closeness-centrality.fb_edges_handler
  (:use [clj-facebook-graph.auth])
  (:require [clj-facebook-graph.client :as client]))


  (defn list-access-token-by-user [app-id access-token]
    (into {}
      (map 
        (fn [data] (into {} {(keyword (data :id)) (data :access_token)}))
          (((with-facebook-auth {:access-token access-token}
          (client/get (str "https://graph.facebook.com/" app-id "/accounts/test-users"))) :body) :data))))

  (defn load-friends [user-id access-token]
  	(loop [queue [user-id]
  		     visited #{user-id}
  		     edges []]

      (if (empty? queue)
        edges
        (let [current (nth queue 0)
        	    facebook-auth {:access-token (get access-token current)}
              friends (((with-facebook-auth facebook-auth
                      (client/get (str "https://graph.facebook.com/" (name current) "/friends"))) :body) :data)
              friend-ids (into [] (map (fn [data] (data :id)) friends))
              friend-ids-unvisited (remove visited (map (fn[x] (keyword x)) friend-ids))]
              (recur
              	(subvec (into queue friend-ids-unvisited) 1)
              	(into visited friend-ids-unvisited)
              	(into edges (map (fn [v] [(keyword current) (keyword v)]) friend-ids)))))))  