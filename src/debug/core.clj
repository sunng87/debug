(ns debug.core
  (:import [java.util Date]
           [java.text SimpleDateFormat]))

(def enabled-namespaces
  (if-let [nss (System/getenv "DEBUG")]
    (clojure.string/split nss  #",")
    []))

(def colors
  ["\033[0;31m" ;;red
   "\033[0;32m" ;;green
   "\033[0;33m" ;;yellow
   "\033[0;34m" ;;blue
   "\033[0;35m" ;;purple
   "\033[0;36m" ;;cyan
   ])

(defn wrap-color-text [color text]
  (str color text "\033[0m"))

(defn current-time []
  (.format (SimpleDateFormat. "hh:mm:ss.SSS") (Date.)))

(defn pprint [current-ns & args]
  (apply println (current-time) current-ns args))

(defmacro debug [& args]
  (let [current-ns (str (ns-name *ns*))
        idx (.indexOf enabled-namespaces current-ns)]
    (when (> idx -1)
      (let [color (nth colors (mod idx (count colors)))]
       `(pprint (wrap-color-text ~color ~current-ns) ~@args)))))
