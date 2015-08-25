(ns ^:figwheel-always witan.ui.components.projection
  (:require [om.core :as om :include-macros true]
            [om-tools.dom :as dom :include-macros true]
            [om-tools.core :refer-macros [defcomponent defcomponentmethod]]
            [sablono.core :as html :refer-macros [html]]
            [inflections.core :as i]
            [schema.core :as s :include-macros true]
              ;;
            [witan.ui.widgets :as widgets]
            [witan.schema.core :refer [Projection]]
            [witan.ui.async :refer [raise!]]
            [witan.ui.refs :as refs]))

(def valid-actions
  #{:input
    :output
    :model})

(defcomponent
  header
  [projection owner]
  (render [_]
          (html
           [:div.witan-pw-header
            [:h1 (:name projection)]])))

(defmulti action-view
  (fn [[action projection] owner] action))

(defcomponentmethod action-view
  :input
  [[action projection] owner]
  (render [_]
          (html
           [:div "Input view"])))

(defcomponentmethod action-view
  :output
  [[action projection] owner]
  (render [_]
          (html
           [:div "Output view"])))

(defcomponentmethod action-view
  :model
  [[action projection] owner]
  (render [_]
          (html
           [:div "Model view"])))

(defcomponent view
  [cursor owner & opts]
  (render [_]
          (let [{:keys [id action]} (first opts)
                kaction (keyword action)
                projection (some #(if (= (:id %) id) %) (:projections cursor))]
            (html
             [:div
              (om/build header projection)
              (if (not (contains? valid-actions kaction))
                [:span "Unknown projection action"]
                [:div
                 [:div.pure-g.witan-pw-area-header
                  [:div.pure-u-1
                   {:class action}
                   [:h2 (i/capitalize action)]]]
                 (om/build action-view [kaction projection])])]))))
