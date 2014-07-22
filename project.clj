(defproject closeness-centrality "0.1.0-SNAPSHOT"
  :description "An implementation of the metric called 'Closeness Centrality'"
  :url "http://github.com/leocomelli/closeness-centrality"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"], 
                 [org.clojure/data.csv "0.1.2"],
				 [clj-facebook-graph "0.4.0"],
				 [org.clojure/tools.cli "0.3.1"]]
  :main closeness-centrality.core			         
;:repositories {"sonatype-oss-public" "https://oss.sonatype.org/content/groups/public/"}
)