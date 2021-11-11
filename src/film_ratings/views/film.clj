(ns film-ratings.views.film
  (:require [film-ratings.views.template :refer [page labeled-radio]]
            [hiccup.form :refer [form-to label text-field text-area submit-button]]
            [ring.util.anti-forgery :refer [anti-forgery-field]]))

(defn create-film-view
  []
  (page
   [:div.container.jumbotron.bg-light
    [:div.row
     [:h2 "add a film"]]
    [:div
     (form-to [:post "/add-film"]
              (anti-forgery-field)
              [:div.form-group.col-12
               (label :name "name:")
               (text-field  {:class "mb-3 form-control" :placeholder "enter film name"} :name)]
              [:div.form-group.col-12
               (label :description "description:")
               (text-area {:class "mb-3 form-control" :placeholder "enter film description"} :description)]
              [:div.form-group.col-12
               (label :ratings "rating (1-5):")]
              [:div.form-group.col-12
               (map (labeled-radio "rating") (repeat 5 false) (range 1 6))]
              [:div.form-group.col-12.text-center
               (submit-button {:class "btn btn-primary text-center"} "add")])]]))

(defn- film-attributes-view
  [name description rating]
  [:div
   [:div.row
    [:div.col-2 "name:"]
    [:div.col-10 name]]
   (when description
     [:div.row
      [:div.col-2 "description:"]
      [:div.col-10 description]])
   (when rating
     [:div.row
      [:div.col-2 "rating:"]
      [:div.col-10 rating]])])

(defn film-view
  [{:keys [name :description rating]} {:keys [errors messages]}]
  (page
   [:div.container.jumbotron.bg-light
    [:div.row
     [:h2 "film"]]
    (film-attributes-view name description rating)
    (when errors
      (for [error (doall errors)]
        [:div.row.alert.alert-danger
         [:div.col error]]))
    (when messages
      (for [message (doall messages)]
        [:div.row.alert.alert-success
         [:div.col message]]))]))
