(ns day06.core)
(use '[clojure.string :only (join split)])


(defn parse-int [s]
   (Integer. (re-find  #"-?\d+" s )))

(defn find-biggest [banks-init]
  (loop [banks banks-init max-value Integer/MIN_VALUE max-position 0 index 0]
      (let [value (first banks)]
        (if-not (empty? banks)
          (recur (rest banks) (max max-value value) (if (> value max-value) index max-position) (inc index))
          max-position

          )
    )

  )
)


(defn fill-banks [banks current-position]
  (let [current (nth banks current-position)
        new-banks (assoc banks current-position 0)
        size (count banks)
        ]

      (loop [banks new-banks position (inc current-position) value current]

          (let [index (mod position size)]
            (if (> value 0)
                (recur (assoc banks index (inc (nth banks index ))) (inc position) (dec value))
                banks

              )
            )
        )
    )
)

(defn part1 [banks]

  (loop [index 1 old-banks banks prev-states []]

    (let [position (find-biggest old-banks)
          new-banks (fill-banks old-banks position)
          ]

      (if-not (some #(= new-banks %) prev-states)
        (recur (inc index) new-banks (conj prev-states old-banks))
        index
        )

      )
    )

  )

(defn find-bank [prev-states needle]


  )

(defn part2 [banks]

  (loop [index 1 old-banks banks prev-states []]

    (let [position (find-biggest old-banks)
          new-banks (fill-banks old-banks position)
          ]

      (if-not (some #(= new-banks %) prev-states)
        (recur (inc index) new-banks (conj prev-states old-banks))
        (- index (.indexOf prev-states new-banks))
        )

      )
    )

  )

(defn -main
  "I don't do a whole lot."
   [path]
  (let [input (slurp path )
        tokens (doall (split input #"\s"))]
        (let [numbers (vec (map parse-int tokens))]


          (println (part2 numbers))

          ;(println (find-biggest numbers))
          ;(println (fill-banks [4 5 14 0 2] 2))
          )
    )
  )
