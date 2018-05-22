(ns day05.core)
(use '[clojure.string :only (join split)])


(defn parse-int [s]
   (Integer. (re-find  #"-?\d+" s )))

(defn part1 [numbers]
    (let [size (count numbers)]
      (loop [nums (vec numbers) index 0 counter 0]

        (if (< index size)
            (let [instruction (nth nums index) ]
              (recur (assoc nums index (inc instruction)) (+ index instruction) (inc counter))
              )
            counter
          )
        )
      )

  )

(defn part2 [numbers]
    (let [size (count numbers)]
      (loop [nums (vec numbers) index 0 counter 0]

        (if (< index size)
            (let [instruction (nth nums index) ]
                (if (>= instruction 3)
                  (recur (assoc nums index (dec instruction)) (+ index instruction) (inc counter))
                  (recur (assoc nums index (inc instruction)) (+ index instruction) (inc counter))
                  )
              )
            counter
          )
        )
      )

  )

(defn -main
  "I don't do a whole lot."
   [path]
  (let [input (slurp path )
        lines (doall (split input #"\n"))]
        (let [numbers (doall (map parse-int lines))]
          ;(println (part1 numbers))
          (println (part2 numbers))

          )
    )
  )
