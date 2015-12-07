# From project root: `source test/resources/benchmark.sh`

lein run --output "/tmp/mosaic-benchmark.jpg" --tile-size 10 --step-size 10 --width 50 --samples 3 "test/resources/image.jpg" "test/resources/image.jpg"
