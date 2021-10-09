(ns zoneparser.core
  (:require [instaparse.core :as insta])
  (:gen-class))

(def zonefile
  (insta/parser
     "S = (record|mx-record)+;
      record = name <whitespace> ttl <whitespace> internet <whitespace> record-type <whitespace> value <whitespace>
      mx-record = name <whitespace> ttl <whitespace> internet <whitespace> \"MX\" <whitespace> priority <whitespace> value <whitespace>
      name = #'[a-zA-Z0-9\\.]+'
      whitespace = #'\\s+'
      ttl = #'[0-9]+'
      internet = \"IN\"
      record-type = #'[a-zA-Z0-9]+'
      priority = alphanum
      value = #'.*'
      number = #'[0-9]+'
      alphanum = #'[a-zA-Z0-9]+'"))

;   (zonefile (slurp "a-records.db")
;   (zonefile (slurp "zome-mx.db")

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (zonefile (slurp "a-records.db"))
           ))
