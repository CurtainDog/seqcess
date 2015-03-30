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
         bindings (interleave temps
                              (cons nil (map #(list cons % temp) forms))
                              (repeat s)
                              temps)
         destructured (destructure bindings)]

     `(let [~@destructured]
        ~temp))))
