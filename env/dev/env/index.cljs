(ns env.index
  (:require [env.dev :as dev]))

;; undo main.js goog preamble hack
(set! js/window.goog js/undefined)

(-> (js/require "../../../js/figwheel-bridge")
    (.withModules #js {"./assets/icons/loading.png" (js/require "../../../assets/icons/loading.png"), "@eva-design/eva" (js/require "@eva-design/eva"), "expo" (js/require "expo"), "./assets/images/cljs.png" (js/require "../../../assets/images/cljs.png"), "./assets/icons/app.png" (js/require "../../../assets/icons/app.png"), "@ui-kitten/components" (js/require "@ui-kitten/components"), "react-native" (js/require "react-native"), "react" (js/require "react"), "create-react-class" (js/require "create-react-class")}
)
    (.start "main" "expo" "192.168.10.47"))
