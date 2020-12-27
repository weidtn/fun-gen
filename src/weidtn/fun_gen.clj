(ns weidtn.fun-gen
  (:gen-class)
  (:require [oz.core :as oz]))

;; Create x-data
;;
(defn create-x
  "Creates x-data between zero and 2PI with a certain number of points to create functions with"
  [points]
  (range 0 (* 2 Math/PI) (/ (* 2 Math/PI) points)))
  

(defn sin 
  "Returns the sine wave with amplitude, frequency and phase shifted for given x"
  ([x & {:keys [ampl freq phase] :or {ampl 1 freq 1 phase 0}}]
   (let [phase-rad (* phase (/ Math/PI 180))]
     (->> x
          (map #(+ phase-rad %) ,,,)
          (map #(* freq %) ,,,)
          (map #(Math/sin %) ,,,)
          (map #(* ampl %) ,,,)))))

;; Testing
(def x (create-x 1000)) ; 1000 datapoints between 0 and 2pi

(take 3 (sin x)) ; default works
;; => (0.0 0.006283143965558951 0.012566039883352607)

(apply max (sin x :ampl 4 :freq 1)) ; amplitude works
;; => 4.0

(take 3 (sin x :freq 10)) ; frequency works
;; => (0.0 0.06279051952931337 0.12533323356430426)

(take 3 (sin x :phase 30)) ; phase shifting works
;; => (0.49999999999999994 0.5054314927178775 0.5108430318658598)

;; plotting 
;; (oz/start-server!)
