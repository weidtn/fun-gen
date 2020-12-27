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
  ([points & {:keys [ampl freq phase] :or {ampl 1 freq 1 phase 0}}]
   (let [phase-rad (deg-to-rad phase)]
     (for [x (range 0 (* 2 Math/PI) (/ (* 2 Math/PI) points))] ;; this line works!
       {:x x :y (* ampl (Math/sin (* freq (+ x phase-rad))))}))))

;; plotting 
(oz/start-server!)

(def line-plot
  {:data {:values (sin 5000 :ampl 3 :freq 3 :phase 30)}
   :encoding {:x {:field "x" :type "quantitative"}
              :y {:field "y" :type "quantitative"}}
   :mark "line"})

;; Render the plot
(oz/view! line-plot)

(defn play-data [& names]
  (for [n names
        i (range 20)]
    {:time i :item n :quantity (+ (Math/pow (* i (count n)) 0.8) (rand-int (count n)))}))

(take 1 (play-data "hello"))
