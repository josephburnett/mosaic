(ns mosaic.core
  (:require [mosaic.tile :as tile])
  (:gen-class))

(defn -main [output-file
	     tile-size
	     step-size
	     width-in-tiles
	     sample-size
	     target
	     & sources]
  (let [s  (map tile/load-image sources)
	t  (tile/load-image target)
	ss (Integer. sample-size)
	w  (Integer. width-in-tiles)
	ts (Integer. tile-size)
	tz (Integer. step-size)]
    (tile/save-image
     (tile/mosaic s t ts tz w ss)
     output-file)))