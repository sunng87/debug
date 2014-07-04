(ns debug.core
  (:import [java.util Date]
           [java.text SimpleDateFormat]))

(def enabled-namespaces
  (if-let [nss (System/getenv "DEBUG")]
    (map #(clojure.string/replace % "*" ".*?")
         (clojure.string/split nss  #","))
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
  (println (current-time) current-ns (apply format args)))

(defn match-index-of [v m]
  (loop [i 0]
    (if (< i (count v))
      (let [p (nth v i)]
        (if (re-matches (re-pattern p) m)
          i
          (recur (inc i))))
      -1)))

(defmacro debug [& args]
  (let [current-ns (str (ns-name *ns*))
        idx (match-index-of enabled-namespaces current-ns)]
    (when (> idx -1)
      (let [color (nth colors (mod idx (count colors)))]
       `(pprint (wrap-color-text ~color ~current-ns) ~@args)))))
