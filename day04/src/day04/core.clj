(ns day04.core)
(use '[clojure.string :only (join split)])


(defn check-repetitions [tokens]
    (= (count (distinct tokens)) (count tokens))
  )

(defn get-tokens [lines]
  (map (fn [line] (split line #"\s")) lines)
  )

(defn sort-tokens [token-list]
   (map (fn [tokens] (map (fn [token] (apply str (sort token))) tokens)) token-list)

  )

(defn part1 [lines]
  (let [token-list (get-tokens lines)]
      (count (filter check-repetitions token-list))
    )
)

(defn part2 [lines]
  (let [tokens (get-tokens lines)
        sorted-tokens (sort-tokens tokens)]
    (count (filter check-repetitions sorted-tokens))
  )
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
