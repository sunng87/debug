# debug

A Clojure clone of node [debug](https://github.com/visionmedia/debug) utility.

## Usage

### Leiningen

![latest version on clojars](https://clojars.org/info.sunng/debug/latest-version.svg)

### Code

```clojure
(ns user)
(require '[debug.core :refer [debug]])

(debug "anything you would like to print...")
```

Run the code with environment `DEBUG=user` so you can see the log.

## License

Copyright © 2014 Sun Ning

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
