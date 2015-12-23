# From project root: `source test/resources/benchmark.sh`

lein run --output "/tmp/lcp-demo.jpg" --tile-size 20 --step-size 20 --width 100 --samples 3 ~/Downloads/demo/dragonfly.jpg ~/Downloads/demo/tiles/*
