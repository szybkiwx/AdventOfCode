(ns day09.core)

(def GROUP 1)
(def GARBAGE 2)

(defn inc-group [groups nested]
  (let [current (get groups nested 0)]
    (assoc groups nested (inc current))
    )

  )

(defn find-groups [input]
  (loop [stream input state GROUP nested 0 groups {}]
    (if (not (empty? stream))
      (let [character (first stream)]
        (case character
          \! (recur (drop 2 stream) state nested groups)
          \{ (if (not (= state GARBAGE))
                (recur (rest stream) GROUP (inc nested) groups)
                (recur (rest stream) state nested groups)
                )
          \} (if (not (= state GARBAGE))
                (recur (rest stream) GROUP (dec nested) (inc-group groups nested))
                (recur (rest stream) state nested groups)
                )
          \< (recur (rest stream) GARBAGE nested groups)
          \> (recur (rest stream) GROUP nested groups)
           (recur (rest stream) state nested groups)
          )
        )
        groups
      )
    )
  )

(defn find-garbage [input]
  (loop [stream input state GROUP garbage 0]
    (if (not (empty? stream))
      (let [character (first stream)]
        (case character
          \! (recur (drop 2 stream) state garbage)
          \< (recur (rest stream) GARBAGE garbage)
          \> (recur (rest stream) GROUP garbage)
          (if (= state GARBAGE)
            (recur (rest stream) state (inc garbage))
             (recur (rest stream) state garbage)
            )
          )
        )
        garbage
      )
    )
  )



(defn part1 [input]
  (let [groups (find-groups input)
        pairs (seq groups)
        muls (map (fn [[k v]] (* k v)) pairs)
        sums (reduce + muls)]
    sums
    )
  )

(defn part2 [input]
  (let [garbage (find-garbage input)]
    garbage
    )
  )

(defn -main
  "I don't do a whole lot."
  [path]
    (let [input (slurp path)]
        (println (part2 input))
      )

  )
