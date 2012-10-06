# From project root: `source test/resources/benchmark.sh`

lein run --output "/tmp/mosaic-benchmark.jpg" --tile-size 300 --step-size 1 --width 5 --samples 3 "test/resources/image.jpg" "test/resources/image.jpg"
