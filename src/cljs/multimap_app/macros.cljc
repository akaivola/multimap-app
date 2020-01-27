(ns multimap-app.macros
  (:require [oops.core]))

#_(defmacro import-js [components from]
  `(do ~@(for [component components
               :let [req# (js/require from)]]
           `(def ~component (oops.core/oget+ ~req# (identity (str (quote ~component))))))))
