(ns mosaic.tile
  (import java.io.File)
  (import javax.imageio.ImageIO)
  (import java.awt.image.BufferedImage)
  (import com.mortennobel.imagescaling.ResampleOp)
  (import java.lang.Math))

(defn test-image []
  (ImageIO/read
   (File. "test/resources/image.jpg")))

(defn rescale [x y ^BufferedImage b]
  (. (ResampleOp. x y) (filter b nil)))

(defn grid
  "Divide region 0,0,x,y into grid squares of size n.
   Partial grids squares are omitted. Optional parameter
   s sets the step size to allow for overlapping squares."
  ([n x y] (grid n x y n))
  ([n x y s]
     (for
	 [a (range 0 (- x n -1) s)
	  b (range 0 (- y n -1) s)]
       [a,b,(+ a n),(+ b n)])))

(defn gen-tiles [n ^BufferedImage b]
  "Generate tiles of size n from image b.
   Returns a list of BufferedImages."
  (map #(.getSubimage b (first %) (second %) n n)
       (grid 100 (.getWidth b) (.getHeight b))))

(defn save-image [^BufferedImage b ^String f]
  "Save a BufferedImage to filename f."
  (ImageIO/write b "jpg" (File. f)))

(defn tile-names [^String base]
  "Infinite sequence of tile names."
  (map #(str base % ".jpg") (range)))

(defn save-tiles [tiles ^String d]
  "Save tiles (list of BufferedImages) to directory d."
  (map #(save-image %1 %2) tiles (tile-names d)))

(defn get-samples [^BufferedImage b]
  "Get a flat list of all samples in an image."
  (let [d (.getData b)]
    (for [x (range (.getWidth d))
	  y (range (.getHeight d))
	  z (range (.getNumBands d))]
      (.getSample d x y z))))

(defn delta [^BufferedImage a ^BufferedImage b]
  "Calculates the difference between images a and b."
  (reduce + (map #(Math/abs %)
		 (map - (get-samples a) (get-samples b)))))

(defn sample-tiles [n tiles]
  "Resample tiles to size n by n.
   Output format: {:tile :sample}"
  (for [t tiles] {:tile t :sample (rescale n n t)}))

