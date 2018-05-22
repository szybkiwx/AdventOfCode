(ns day07.core)
(use '[clojure.string :only (join split trim)])

(defn parse-int [s]
   (Integer. (re-find  #"-?\d+" s )))

(defn build-nodes [[node-token children-token]]
   (let [[node-name value] (vec (rest (re-find #"(.*) \((.*)\)" node-token)))
         children (if-not (= children-token nil) (split children-token #", ") [])
         ]
     {:name node-name :value value :children children }
     )
  )

(defn update-children [node]
  (let [parent-name (get node :name)]
      (assoc node :children (vec (map (fn [x] {:name x :parent parent-name}) (get node :children))))
    )
  )

(defn name-match [node child]
    (= (get node :name ) (get child :name ) )
  )

(defn update-parents [nodes]

  (let [all-children (vec (apply concat (map #(get % :children) nodes)))]
      (map (fn [node]
           (assoc node :parent (get (first (filter #((partial name-match node) %) all-children ) ) :parent) )) nodes)
    )

  )


(defn find-root [nodes]
    (loop [current (first nodes)]
      (let [parent-name (get current :parent)]
        (if (= parent-name nil)
          current
          (recur (first (filter #(= (get % :name) parent-name) nodes) ))
          )
        )

      )
  )


(defn find-node-by-name [nodes node-name]
    ;(println nodes)
    ;(println node-name)
    (first (filter #(= (get % :name) node-name) nodes) )
  )

(defn part1 [nodes]
  (find-root nodes)
  )

(defn most-common [sub]
  (if (=

  )

(defn calc-weight
  ([ nodes current]
   (let [children (get current :children)
         current-value (parse-int (get current :value))
         child-nodes (map #((partial find-node-by-name nodes) (get % :name)) children)
         ]

      (let [sub (map #((partial calc-wight nodes) %) child-nodes)]
        )

         (if (> (count sub) 1)
           (let [[target failure] (most-common sub)]

             )

           )



     )

   )
 )


(defn find-unequal [current nodes]
  (let  [children (get current :children)
         current-value (parse-int (get current :value))
         child-nodes (map #((partial find-node-by-name nodes) (get % :name)) children)
         ]


        (println (map #(get % :value) child-nodes))

        (for [x child-nodes]
          (find-node-by-name child-nodes (get x :name))
          )

    )

  )

(comment defn part2 [nodes]
   (let [root (find-root nodes)]
      (doall (for [x (get root :children)] (println [ (get x :name) (sum-children (find-node-by-name nodes (get x :name)) nodes)])))

     )
  )

(defn part2 [nodes]
   (let [root (find-root nodes)]
      (find-unequal root nodes)

     )
  )


(defn -main
  "I don't do a whole lot."
  [path]
    (let [input (slurp path)
        lines (doall (split input #"\n"))
        nodes-updated-children (vec (map update-children (map build-nodes (map #(split (trim %) #" -> ") lines))))
        updated-parents (update-parents nodes-updated-children)]
        ;(println (part1 updated-parents))
        (println (part2 updated-parents))
    )
  )

