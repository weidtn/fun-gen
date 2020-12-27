(ns weidtn.fun-gen
  (:gen-class)
  (:require [oz.core :as oz]))

(defn deg-to-rad
  [deg]
  (* deg (/ Math/PI 180)))

(defn rad-to-deg
  [rad]
  (* rad (/ 180 Math/PI)))

(defn sin
  "Returns the sine wave with amplitude, frequency and phase shifted with given datapoints"
  ([& {:keys [points ampl freq phase] :or {points 1000 ampl 1 freq 1 phase 0}}]
   (let [phase-rad (deg-to-rad phase)]
     (for [x (range 0 (* 2 Math/PI) (/ (* 2 Math/PI) points))] ;; this line works!
       {:x x :y (* ampl (Math/sin (* freq (+ x phase-rad))))}))))

;; plotting 
(oz/start-server!)

(def line-plot
  {:data {:values (sin)}
   :encoding {:x {:field "x" :type "quantitative"}
              :y {:field "y" :type "quantitative"}}
   :mark "line"})

;; Render the plot
(oz/view! line-plot)
