(ns libpy-blender.core
  (:require [libpython-clj.require :refer [require-python]]
            [libpython-clj.python :refer [py. py.. py.-] :as py]))

(require-python 'bpy) ;;=> Failed to find module or class bpy
(require-python 'math);;=> :ok
(math/sin 1.0)
(require-python 'sys);;=> :ok
(sys/getwindowsversion)
(sys/getprofile)
