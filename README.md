# mosaic

## Overview

A photo mosaic is an image composed of many small images (see http://en.wikipedia.org/wiki/Photo_mosaic).  This project generates a photo mosaic from a collection of tiles.  Tiles are formed from a set of input images by breaking them into squares of the same size.

## Usage

   lein run target.jpg tiles/*.jpg
   
   lein run \
   --tile-size 100 \
   --tile-step 20 \
   --samples 3 \
   --output "example-output.txt" \
   --width 50

## License

Copyright (C) 2012 Joseph Burnett.  All rights reserved.

Distributed under the MIT License; see the file LICENSE at the root of this distribution.