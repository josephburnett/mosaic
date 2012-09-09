(ns mosaic.tile
  (import java.io.File)
  (import java.awt.Color)
  (import java.awt.image.BufferedImage)
  (import java.awt.image.Raster)
  (import javax.imageio.ImageIO)
  (import com.mortennobel.imagescaling.ResampleOp)
  (import java.awt.Rectangle))

(defn test-mosaic []
  (ImageIO/read
   (File. "test/resources/image.jpg")))

(defn rescale [x y ^BufferedImage b]
  (. (ResampleOp. x y) (filter b nil)))

(defn tile-rects [n x y]
  "divide dimensions x,y into Rectangles of size n
   omitting regions that are not complete tiles"
  (for [a (range 0 (- x n -1) n) b (range 0 (- y n -1) n)]
    (Rectangle. a b n n)))

(defn tile-rasters [n ^BufferedImage b]
  "divide buffered image into rasters of size n"
  (let [h (.getHeight b)
	w (.getWidth b)]
    (map #(.getData b %) (tile-rects n w h))))

