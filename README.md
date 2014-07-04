# debug

A Clojure clone of node [debug](https://github.com/visionmedia/debug) utility.

## Usage

### Leiningen

![latest version on clojars](https://clojars.org/info.sunng/debug/latest-version.svg)

### Code

```clojure
(ns user)
(require '[debug.core :refer [debug]])

(debug "anything you would like to print... %s" "format string supported")
```

Run the code with environment `DEBUG` set to comma separated
namespaces, e.g. `DEBUG=user`, so you can see the log. Wildcard `*` is
supported for namespace pattern.

## License

Copyright © 2014 Sun Ning

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
