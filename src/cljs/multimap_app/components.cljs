(ns multimap-app.components
  (:require
   [reagent.core :as r]
   [oops.core :refer [oget]]))

(defn load-class [component class]
  (r/adapt-react-class (oget component class)))

(def ^:private components (partial load-class (js/require "@ui-kitten/components")))

(def eva (js/require "@eva-design/eva"))

(def ReactNative (js/require "react-native"))

;; eva
(def mapping (oget eva "mapping"))
(def light-theme (oget eva "light"))

;; ui-kitten
(def application-provider (components "ApplicationProvider"))
(def layout (components "Layout"))
(def text  (components "Text"))
(def button  (components "Button"))
(def modal  (components "Modal"))
