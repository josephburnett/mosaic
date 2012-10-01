# mosaic

## Overview

A photo mosaic is an image composed of many small images (see http://en.wikipedia.org/wiki/Photo_mosaic).  This project generates a photo mosaic of a target image from a collection of tiles, which are the source images broken into squares of equal size.

## Usage

   lein run [target image] [source images]...

   lein run target.jpg tiles/*.jpg
   
   lein run --tile-size 100 --tile-step 20 --samples 3 --output "example-output.txt" --width 50 target.jpg tiles/*.jpg

   lein run --help

## License

Copyright (C) 2012 Joseph Burnett.  All rights reserved.

Distributed under the MIT License; see the file LICENSE at the root of this distribution.