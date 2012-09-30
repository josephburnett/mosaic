(ns mosaic.core
  (use mosaic.image)
  (import java.lang.Math)
  (import java.awt.image.BufferedImage))

(defn- grid
  "Divide region 0,0,x,y into grid squares of size n.
   Partial grids squares are omitted. Optional parameter
   s sets the step size to allow for overlapping squares."
  ([n x y] (grid n x y n))
  ([n x y s]
     (for
	 [a (range 0 (- x n -1) s)
	  b (range 0 (- y n -1) s)]
       [a,b,n,n])))

(defn- gen-tiles 
  "Generate tiles of size n from image b.
   Returns a list of BufferedImages."
  ([n ^BufferedImage b] (gen-tiles n b n))
  ([n ^BufferedImage b s]
     (map sub-image
	  (grid n (.getWidth b) (.getHeight b) s)
	  (repeat b))))

(defn- gen-tiles-coll
  "Generate tiles of size n from images coll."
  ([n coll] (gen-tiles-coll n coll n))
  ([n coll s]
     (flatten (map gen-tiles (repeat n) coll))))

(defn- delta [seq-a seq-b]
  "Calculate the difference between sequences a and b.
   This is like a k-d manhattan distance."
  (reduce + (map #(Math/abs %) (map - seq-a seq-b))))

(defn- sample-tiles [n tiles]
  "Resample tiles to size n by n.
   Output format: {:tile :sample}"
  (for [t tiles] {:tile t
		  :samples (get-samples (rescale n n t))}))

(defn- best-match [n samples ^BufferedImage b]
  "Find the best matching tile to image b."
  (let [s (get-samples (rescale n n b))]
    (reduce #(if (< (delta s (:samples %1))
		    (delta s (:samples %2)))
	       %1 %2)
	    (first samples)
	    (rest samples))))
    
(defn- gen-canvas [n w ^BufferedImage b]
  "Rescale and crop image b to evenly fit tiles of
   size n, with w tiles across."
  (let [x (* n w)]
    (image-floor n (rescale-fixed-ratio x b))))

(defn mosaic [tiles ; collection of tile sources
	      ^BufferedImage target
	      n  ; tile size
	      ns ; tile step
	      w  ; width in tiles
	      s] ; sample size
  (let [canvas (gen-canvas n w target)
	tiles (sample-tiles s (gen-tiles-coll n tiles ns))]
    (dorun (map #(insert!
		  (:tile (best-match n tiles (sub-image % canvas)))
		  canvas (first %) (second %))
		(grid n (.getWidth canvas) (.getHeight canvas))))
    canvas))
