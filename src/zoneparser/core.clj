(ns zoneparser.core
  (:require [instaparse.core :as insta])
  (:require [clojure.pprint :refer [pprint]])
  (:gen-class))

(def parse
  "Pass in the zonefile as a string.

  See example-output.clj
  "
  (insta/parser
     "S = (soa|record|variable|<comment>)+;
      record = (normal-record|mx-record)
      normal-record = name <whitespace> ttl <whitespace> internet <whitespace> record-type <whitespace> value <whitespace>+

      mx-record = name <whitespace> ttl <whitespace> internet <whitespace> record-type <whitespace> priority <whitespace> value <whitespace>+
      priority = number

      soa =   name <whitespace>
              ttl <whitespace>
              \"IN\" <whitespace>
              \"SOA\" <whitespace>
              primary-nameserver <whitespace>
              admin-email <whitespace>

              <\"(\"> <whitespace?> <newline>?
              serial <whitespace?> <comment?> <whitespace?>
              refresh <whitespace?> <comment?> <whitespace?>
              retry <whitespace?>  <comment?> <whitespace?>
              expire <whitespace?> <comment?> <whitespace?>
              negative-response-caching-ttl <whitespace?> <comment?> <whitespace?>
              <\")\"> <whitespace?>

      variable = <\"$\"> name <whitespace> value <whitespace>?

      comment = \";\" (#'.*') <whitespace?>

      nameserver = alphanum
      primary-nameserver = alphanum
      admin-email = alphanum
      newline = \"\\n\"
      serial = number
      refresh = number
      retry = number
      expire = number
      negative-response-caching-ttl = number

      name = alphanum
      whitespace = #'\\s+'
      ttl = #'[0-9]+'
      internet = \"IN\"
      record-type = #'[a-zA-Z0-9]+'
      value = #'.*'
      number = #'[0-9]+'
      alphanum = #'[a-zA-Z0-9\\._\\-]+'"))

(defn -main
  "Takes a zone file as a filename and prints out the resulting Clojure datastructure"
  [filename & args]
  (pprint (parse (slurp filename))))
