(ns mosaic.core
  (:use [clojure.tools.cli :only [cli]])
  (:require [mosaic.tile :as tile])
  (:gen-class))

(defn -main [& args]
  (let [[params source-files help]
	(cli args
	     ["-t" "--target" "Target image" :parse-fn tile/load-image]
	     ["-i" "--tile-size" "Tile size in pixels" :parse-fn #(Integer. %)]
	     ["-s" "--step-size" "Step size in pixels" :parse-fn #(Integer. %)]
	     ["-a" "--samples" "Number of sample regions" :parse-fn #(Integer. %)]
	     ["-o" "--output" "Filename of output" :default "output.jpg"]
	     ["-w" "--width" "Output width in tiles" :parse-fn #(Integer. %)])
	sources (map tile/load-image source-files)]
    (tile/save-image
     (tile/mosaic sources
		  (:target params)
		  (:tile-size params)
		  (:step-size params)
		  (:width params)
		  (:samples params))
     (:output params))))
