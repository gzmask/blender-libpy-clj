(ns libpy-blender.init
  (:require [libpython-clj.python.interpreter :as pi]
            [libpython-clj.python :as py]
            [clojure.java.shell :refer [sh]]
            [clojure.data.json :as json]))

(with-redefs [pi/python-system-data
              (fn  [executable]
                (let [{:keys [out err exit]}
                      (sh "/Applications/Blender.app/Contents/MacOS/Blender"
                          "--background"
                          "--python-expr"
"import sys, json; 
print(json.dumps(
{'platform':          sys.platform,
  'prefix':           sys.prefix,
  'base_prefix':      sys.base_prefix,
  'executable':       sys.executable,
  'base_exec_prefix': sys.base_exec_prefix,
  'exec_prefix':      sys.exec_prefix,
  'version':          list(sys.version_info)[:3]}))")]
                  (when (= 0 exit)
                    (json/read-str out :key-fn keyword))))]
  (py/initialize!))

