(ns seqcess.core-test
  (:require [clojure.test :refer :all]
            [seqcess.core :refer :all]))

(deftest seq->-test
  (testing "Seeking arrow with"
    (testing "no binding"
      (is (nil?              (seq-> _)))
      (is (= '(1)            (seq-> _ 1)))
      (is (= '(5 4 3 2 1)    (seq-> _ 1 2 3 4 5)))
      (is (= '((1 2 3 4 5))  (seq-> _ (range 1 6)))))
    (testing "simple binding"
      (is (= '(nil)          (seq-> s s)))
      (is (= '((nil) nil)    (seq-> s s s)))
      (is (= '(true 2 (1 2)) (seq-> s (range 1 3) (last (first s)) (< (first (second s)) (first s))))))
    (testing "positional destructuring"
      (is (= '(nil)          (seq-> [s] s)))
      (is (= '(nil nil)      (seq-> [s] s s)))
      (is (= '(32 16 8 4 2)  (seq-> [s] 2 (* 2 s) (* 2 s) (* 2 s) (* 2 s))))
      (is (= '(8 5 3 2 1 1)  (seq-> [x y] 1 1 (+ x y) (+ x y) (+ x y) (+ x y)))))
    (testing "advanced destructing"
      (is (= '({:name "Crab", :id 3}
               {:name "Bob", :sex :male, :id 2}
               {:name "Alice", :sex :female, :id 1})
             (seq-> [{id :id :or {id 0}}]
                    {:name "Alice" :sex :female :id (inc id)}
                    {:name "Bob" :sex :male :id (inc id)}
                    {:name "Crab" :id (inc id)}))))))
