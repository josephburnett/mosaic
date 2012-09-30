(ns mosaic.core
  (:use [mosaic image tile])
  (:use [clojure.tools.cli :only [cli]])
  (:require [mosaic.tile :as tile])
  (:gen-class))

(defn help [banner]
  (println "\nExample: lein run target.jpg tiles/*.jpg\n")
  (println banner)
  (System/exit 0))

(defn -main [& args]
  (let [[options files banner]
	(cli args
	     ["-i" "--tile-size" "Tile size in pixels"
	      :parse-fn #(Integer. %) :default "50"]
	     ["-s" "--step-size" "Step size in pixels"
	      :parse-fn #(Integer. %) :default "50"]
	     ["-a" "--samples" "Number of sample regions (width)"
	      :parse-fn #(Integer. %) :default "6"] 
	     ["-o" "--output" "Filename of output"
	      :default "output.jpg"]
	     ["-w" "--width" "Output width in tiles"
	      :parse-fn #(Integer. %) :default "30"]
	     ["-h" "--help" "Show help"
	      :default false :flag true])]
    (when (:help options) (help banner))
    (if (< (count files) 2)
      (help banner)
      (let [target (load-image (first files))
	    tiles (map load-image (rest files))]
	(save-image
	 (mosaic tiles
		      target
		      (:tile-size options)
		      (:step-size options)
		      (:width options)
		      (:samples options))
	 (:output options))))))
