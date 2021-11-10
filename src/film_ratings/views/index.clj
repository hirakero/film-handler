(ns film-ratings.views.index
  (:require [film-ratings.views.template :refer [page]]))

(defn list-options []
  (page
   [:div.container.jumbotron.bg-white.text-conter
    [:row
     [:p
      [:a.btn.btn-primary {:href "/add-film"} "add a film"]]]
    [:row
     [:p
      [:a.btn.btn-primary {:href "/list-films"} "list flims"]]]]))
