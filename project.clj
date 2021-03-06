(defproject mosaic "1.0.0-SNAPSHOT"
  :description "A simple program for creating photo mosaics."
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [org.clojure/tools.cli "0.2.2"]
                 [com.mortennobel/java-image-scaling "0.8.5"]
                 [clj-kdtree "1.2.0" :exclusions [org.clojure/clojure]]]
  :main mosaic.main
  :jvm-opt ["-Xmx512m"
            "-Dcom.sun.management.jmxremote"
            "-Dcom.sun.management.jmxremote.ssl=false"
            "-Dcom.sun.management.jmxremote.authenticate=false"
            "-Dcom.sun.management.jmxremote.port=43210"])
