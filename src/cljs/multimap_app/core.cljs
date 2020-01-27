(ns multimap-app.core
  (:require
   ;;[multimap-app.macros :refer-macros [import-js]]
   [reagent.core :as r]
   [re-frame.core :refer [subscribe dispatch dispatch-sync]]
   [oops.core :refer [oget ocall]]
   [multimap-app.handlers]
   [multimap-app.subs]))

(def ReactNative (js/require "react-native"))
(def expo (js/require "expo"))
(def components (js/require "@ui-kitten/components"))
(def eva (js/require "@eva-design/eva"))

(def application-provider (r/adapt-react-class (oget components "ApplicationProvider")))
(def layout (r/adapt-react-class (oget components "Layout")))
(def text (r/adapt-react-class (oget components "Text")))
(def button (r/adapt-react-class (oget components "Button")))
(def modal (r/adapt-react-class (oget components "Modal")))

(def mapping (oget eva "mapping"))
(def light-theme (oget eva "light"))

(defn modal-component [show-modal]
  [layout {:level "3"
           :on-press #(swap! show-modal not)
           :style
           {:justify-content "center"
            :align-items "center"
            :width 256
            :padding 16}}
   [text "Hello from Kitty Modal"]
   [button {:style {:margin 20}
            :on-press #(swap! show-modal not)}
    "dismiss"]])

(defn app-root []
  (let [greeting (subscribe [:get-greeting])
        show-modal (r/atom false)]
    (fn []
      [application-provider {:mapping mapping :theme light-theme}
       [layout {:style {:margin 20}}
        [text {:style {:margin-bottom 20 :margin-top 50 :text-align "center"}
               :category "h1"} @greeting]
        [text {:style {:font-size 20 :color "red" :text-align "center" :margin-bottom 20}}
         "This is some profound text"]
        [button {:on-press #(do (println "press") (swap! show-modal not))}
         "press me, if you dare"]
        [modal {:visible @show-modal
                :backdrop-style {:background-color "rgba(0, 0, 0, 0.5)"}}
         [modal-component show-modal]]]])))

(defn init []
  (dispatch-sync [:initialize-db])
  (ocall expo "registerRootComponent" (r/reactify-component app-root)))
