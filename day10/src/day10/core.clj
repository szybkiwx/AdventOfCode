(ns day10.core)
(use '[clojure.string :only (join split trim)])

(defn parse-int [s]
   (Integer. (re-find  #"-?\d+" s )))


(defn get-subvec [input-vec position len]
  (loop [result [] current position index 0]
    (println index len)
    (if (< index len)
      (recur
        (conj result (nth input-vec current))
        (mod (inc current) (count input-vec))
        (inc index)
        )
      result
      )

    )
  )

(defn replace-subvec [input-vec part position]
  (loop [result input-vec current position index 0]
     (if (< index (count part))
       (recur (assoc result current (nth part index)) (mod (inc current) (count input-vec)) (inc index))
       result
       )
    )
  )

(defn reverse-subvec [input-vec position len]
  (let [part  (reverse (get-subvec input-vec position len))
        result (replace-subvec input-vec part position)
        ]
      result
    )
  )


(defn part1 [input]
  (loop [input-vec (vec (range 256)) ranges input position 0 shift 0]
    (let [rng (first ranges)
          result-vec (reverse-subvec input-vec position rng)
          ]
        (if-not (empty? ranges)
          (recur result-vec (rest ranges) (mod (+ (+ position rng) shift) 256) (inc shift))
          result-vec
          )

      )
    )
  )

(defn -main
  []
  (let [input (vec (map parse-int (split "147,37,249,1,31,2,226,0,161,71,254,243,183,255,30,70" #",")))]
    (println (part1 input))
      ;(println (reverse-subvec [1 2 3 4 5 6 7 8] 6 5))
      )


  )
