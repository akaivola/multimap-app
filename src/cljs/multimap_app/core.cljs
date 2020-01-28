(ns multimap-app.core
  (:require
   ;;[multimap-app.macros :refer-macros [import-js]]
   [reagent.core :as r]
   [re-frame.core :refer [subscribe dispatch dispatch-sync]]
   [oops.core :refer [oget ocall]]
   [multimap-app.handlers]
   [multimap-app.subs]
   [multimap-app.components :refer [mapping light-theme application-provider layout text button modal]]))

(def expo (js/require "expo"))

(defn map-view []
  [layout
   [text "Map"]])

(defn controls []
  [layout
   [text "Controls"]])

(defn app-root []
  [application-provider {:mapping mapping :theme light-theme}
     [layout {:style {:flex 1}}
      [layout {:style {:flex 8}}
       [map-view]]
      [layout {:style {:flex 1}}
       [controls]]]])

(defn init []
  (dispatch-sync [:initialize-db])
  (ocall expo "registerRootComponent" (r/reactify-component app-root)))
