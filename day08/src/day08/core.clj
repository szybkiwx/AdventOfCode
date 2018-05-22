(ns day08.core)
(use '[clojure.string :only (join split trim)])

(defn parse-int [s]
   (Integer. (re-find  #"-?\d+" s )))


(defn parse-instruction [line]
  ;(println line)
  ;  (println ">>>>")

  (let [tokens (split line #"\s")]
    {:registry (nth tokens 0)
     :predicate-registry (nth tokens 4)
     :operation ( fn [x] (let [value (parse-int (nth tokens 2))
                                 op (if (= (nth tokens 1) "inc") + -)]
                               (op x value)
                             )
                           )
    :predicate (fn [value]  (let [test-value (parse-int (nth tokens 6)) ]
                                  (case (nth tokens 5)
                                      "==" (= value test-value)
                                      ">" (> value test-value)
                                      ">=" (>= value test-value)
                                      "<" (< value test-value)
                                      "<=" (<= value test-value)
                                      "!=" (not (= value test-value))
                                    )
                                )

                               )

     }

    )
  )

(defn part1 [lines]
  (loop [togo lines registries {} max-val Integer/MIN_VALUE]
     (let [line (first togo)
           instruction (parse-instruction line)
           registry (instruction :registry)
           registry-value (registries registry 0)
           predicate-registry (instruction :predicate-registry)
           predicate-registry-value (registries predicate-registry 0)
           predicate (instruction :predicate)
           operation (instruction :operation)
           next-val (if (predicate predicate-registry-value) (operation registry-value) registry-value)
           current-max (if (> (count registries) 0) (apply max (vals registries)) max-val)
           ]
        (if-not (= (count togo) 1)
           (recur (rest togo) (assoc registries registry next-val) (max current-max max-val))
            max-val
          )
        )
    )
  )


(defn -main
  "I don't do a whole lot."
  [path]
    (let [input (slurp path)
          lines (doall (split input #"\n"))]

        (println (part1 lines))
        ;(println (split (first lines) #"\s"))
      )
  )
