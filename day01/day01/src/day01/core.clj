(ns day01.core)


(defn part1 [numbers]
      (let [final-result
          (loop [nums numbers last-val -1 result 0]

              (if-not (empty? nums)
                  (let [x (first nums)]
                      (if (= x last-val)
                          (recur (rest nums) x (+ result x))
                          (recur (rest nums) x result)
                      )
                  )
                  result
              )
          )]
          (if (= (first numbers) (last numbers))
              (+ final-result (first numbers))
              final-result

          )
      )
  )


(defn part2 [numbers]
    (loop [nums numbers counter 0 result 0]

        (if-not (empty? nums)
            (let [x (first nums)
                  max-cnt (count numbers)
                  next-idx (mod (+ counter (/ max-cnt 2) ) max-cnt)
                  next-element (nth numbers next-idx)
                  ]
                (if (= x next-element)
                    (recur (rest nums) (inc counter) (+ result x))
                    (recur (rest nums) (inc counter) result)
                )
            )
            result
        )
    )


)

(defn -main
  "I don't do a whole lot."
  [path]
  (let [input (slurp path )
        numbers (map #(Character/getNumericValue %) input)]
        (println (part1 numbers))
        (println (part2 numbers))


  )

)

