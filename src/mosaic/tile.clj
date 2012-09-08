(ns mosaic.tile
  (import java.io.File)
  (import java.awt.Color)
  (import java.awt.image.BufferedImage)
  (import java.awt.image.Raster)
  (import javax.imageio.ImageIO)
  (import com.mortennobel.imagescaling.ResampleOp))

(defn test-mosaic []
  (ImageIO/read
   (File. "test/resources/image.jpg")))

(defn rescale [x y ^BufferedImage b]
  (. (ResampleOp. x y) (filter b nil)))