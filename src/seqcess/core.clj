(ns seqcess.core)

(defmacro seq->
  "Seqing Arrow. Takes a destructuring form and repeatedly binds
			  the form to a sequence of the cumulative results made by
			  evaluating each form in turn. Returns the entire sequence.
			  The behaviour is similar to as-> except that it always binds
			  to a sequence and returns a sequence."
  ([s & forms]
    (let [temp (gensym)
          temps (repeat temp)
          bindings (interleave
                     temps (cons nil (map #(list cons % temp) forms))
                     (repeat s) temps)]
      `(let [~@bindings]
         ~temp))))

(defmacro state->
  "'Stateful' Arrow. Takes a state and threads it through a set
			  of symbol s-exp pairs where the expression evaluates to
			  [next-state result]. Result will be bound to symbol, and
			  next-state to state. The return will be a seqable of the
			  final state followed by each result."
  ([state & forms]
    (let [pairs (map #(if (even? (count %)) % (cons (gensym) %)) (partition-all 2 forms))
          results (reverse (map first pairs))
          bindings (mapcat (fn [[s form]] [[state s] form]) pairs)]
      `(let [~@bindings]
         [~state ~@results]))))

(defn- -obj-> [f preds args]
  (fn
    ([] (apply f args))
    ([arg] (-obj-> f preds (map #(or %1 %2) (preds arg) args)))))

(defn obj->
  "Objective arrow. Takes a function and predicates. Returns a function that,
			when called without a parameter, evalutes the function by passing in
      the same number of args as there are predicates. If not set each arg
      is nil. When called with a parameter, evaluates each predicate against
      that parameter and sets the corresponding arg to the result if not falsey."
  [f & preds]
  (-obj-> f (apply juxt preds) (vec (repeat (count preds) nil))))