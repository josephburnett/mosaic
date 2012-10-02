# mosaic

## Overview

A photo mosaic is an image composed of many smaller images (see the [Wikipedia arcticle][wiki]).  This project generates a photo mosaic of the target image from a collection of tiles, which are the source images broken into squares of equal size.

[wiki]: http://en.wikipedia.org/wiki/Photo_mosaic "Wikipedia article on photo mosaic"

## Usage

    lein run [target image] [source images]...
    lein run target.jpg tiles/*.jpg
    lein run --tile-size 100 --tile-step 20 --samples 3 --output "example-output.txt" --width 50 target.jpg tiles/*.jpg
    lein run --help

## Known Issues

* The implementation of best-match is ridiculously inefficient (a full scan of the tile collection).  The sample coordinates should be stored in a k-d tree, like this one: [clj-kdtree](https://github.com/abscondment/clj-kdtree)
* The heavy weigh operations like tile generation and tile matching could make use of multiple cores by using pmap instead of map.

## License

Copyright (C) 2012 Joseph Burnett.  All rights reserved.

Distributed under the MIT License; see the file LICENSE at the root of this distribution.