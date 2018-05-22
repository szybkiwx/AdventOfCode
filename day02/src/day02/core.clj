(ns day02.core)
(use '[clojure.string :only (join split)])

(defn parse-int [s]
   (Integer. (re-find  #"\d+" s )))

(defn get-sum-from-row [row]
    (let [numbers (map #(parse-int %) (split row #"\s"))
           result  (loop [nums numbers smallest Integer/MAX_VALUE largest Integer/MIN_VALUE]
                      (if-not (empty? nums)
                          (let [x (first nums)]
                              (recur (rest nums) (min x smallest) (max x largest))
                          )
                          [smallest largest]
                      )


                   )]
        (let[smallest (first result) largest (last result)]
              (- largest smallest)

        )
    )
)

(defn get-division-from-row [row]
    (let [numbers (map #(parse-int %) (split row #"\s"))]
        (loop [pairs (for [x numbers y numbers] [x y])]
            (let [[x y] (first pairs)]
                (if (and (= (mod x y) 0) (not= x y))
                    (/ x y)
                    (recur (rest pairs))
                )

            )

          )
    )
)


(defn part1 [lines]
    (reduce + (map #(get-sum-from-row %) lines))
)

(defn part2 [lines]

    (reduce + (map #(get-division-from-row %) lines))
)

(defn -main
  "I don't do a whole lot."
  [path]
  (let [input (slurp path )
        lines (doall (split input #"\n"))]
      (println (part1 lines))
      (println (part2 lines))

  )
)
