(ns day03.core)

(defn find-size [x]
    (loop [number 1]
        (if (<= x (* number number))
              number
              (recur (+ number 2))
          )
      )
  )

(defn update-matrix [matrix x y v]
  (let [row (nth matrix y)
        new-row (assoc row x v)
        new-matrix (assoc matrix y new-row)
        ]
        new-matrix
    )
  )

(defn get-steps [size]
    (let [iterations (range size)]
      (loop [x 0 y 0 dx 0 dy -1 iterations iterations result []]
          (if-not (empty? iterations)
            (if (or (= x y) (and (< x 0) (= x (- y))) (and (> x 0) (= x (- 1 y))))
              (recur (- x dy) (+ y dx) (- dy) dx (rest iterations) (conj result [x y]))
              (recur (+ x dx) (+ y dy) dx dy (rest iterations) (conj result [x y]))
              )
            result
            )
        )
      )
  )

(defn build-matrix [target]
  (let [dim (find-size target)
        size (* dim dim)
        init-pos (int (/ dim 2))
        empty-matrix (vec (repeat dim (vec (repeat dim 0))))
        steps (rest (get-steps target))
        ]
        (loop [steps-left steps matrix empty-matrix x init-pos y init-pos value 1]

            (if (< value target)
                (let [next-step (first steps-left)]
                    (recur (rest steps-left) (update-matrix matrix x y value) (+ init-pos (first next-step)) (- init-pos (last next-step)) (inc value))
                  )
                [(update-matrix matrix x y value) (last steps)]
              )
          )

  )
)

(defn part1 [input]
     (let [[matrix last-step] (build-matrix input)
           dim (count matrix)
           init-pos (int (/ dim 2))
           dx (first last-step)
           dy (last last-step)
          ]
            (+ (Math/abs (- 0 dx)) (Math/abs (- 0 dy)))
       )
)

(defn sum-neighbours [matrix x0 y0]

  (let [startx (max 0 (dec x0))
        starty (max 0 (dec y0))
        result []
        ]

     (let [coords (for [x (range startx (+ x0 2))
                        y (range starty (+ y0 2))]
                    [x y]
                     )
           values (map (fn [[x y]] (nth (nth matrix y) x)) coords)
           result (reduce + values)

           ]
             result
        )
    )

)

(defn part2 [target]

      (let[
        dim (find-size target)
        size (* dim dim)
        init-pos (int (/ dim 2))
        empty-matrix (vec (repeat dim (vec (repeat dim 0))))
        steps (get-steps target)]
             (loop [steps-left steps matrix empty-matrix x init-pos y init-pos value 1]
                 (if (< value target)
                  (let [next-step (first steps-left)
                        newx (+ init-pos (first next-step))
                        newy (- init-pos (last next-step))
                        new-matrix (update-matrix matrix x y value)
                        new-value (sum-neighbours new-matrix newx newy)
                        ]
                      (recur (rest steps-left) new-matrix newx newy new-value)
                    )
                   value
                  )
               )



        )
  )
(defn -main
  "I don't do a whole lot."
  []
  (println (part2 312051))

  ;(println (sum-neighbours [[0 1 2] [2 3 4] [3 4 5]] 1 1))

)
