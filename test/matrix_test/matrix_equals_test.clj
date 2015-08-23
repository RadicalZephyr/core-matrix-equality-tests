(ns matrix-test.matrix-equals-test
  (:require  [clojure.test :refer :all]
             [clojure.core.matrix :as m]))

(def identity-matrix
  [[1.0 0.0 0.0]
   [0.0 1.0 0.0]
   [0.0 0.0 1.0]])

(def not-identity-matrix
  [[1.0 0.0 0.0]
   [0.0 1.0 10]
   [0.0 1.0 1.0]])

;;; ----------------------------------------------------------------------
;;; All of these pass
;;; ----------------------------------------------------------------------

(deftest mutable-matrix-e=-test
  (let [tm (m/mutable (m/identity-matrix :ndarray 3))]
    (is (not (m/e= tm
                   (m/array :ndarray
                            not-identity-matrix)))))

  (let [tm (m/mutable (m/identity-matrix :vectorz 3))]
    (is (not (m/e= tm
                   (m/array :vectorz
                            not-identity-matrix))))))


;;; ----------------------------------------------------------------------
;;; Of these two test sets, the only assertions that pass are the
;;; ones using the vectorz array implementation.
;;; ----------------------------------------------------------------------

(deftest mutable-matrix-e==-test
  ;; Fails
  (let [tm (m/mutable (m/identity-matrix :ndarray 3))]
    (is (not (m/e== tm
                    (m/array :ndarray
                             not-identity-matrix)))))

  (let [tm (m/mutable (m/identity-matrix :vectorz 3))]
    (is (not (m/e== tm
                    (m/array :vectorz
                             not-identity-matrix))))))

(deftest mutable-matrix-equals-test
  ;; Fails
  (let [tm (m/mutable (m/identity-matrix :ndarray 3))]
    (is (not (m/equals tm
                       (m/array :ndarray
                                not-identity-matrix)))))

  (let [tm (m/mutable (m/identity-matrix :vectorz 3))]
    (is (not (m/equals tm
                       (m/array :vectorz
                                not-identity-matrix))))))


;;; ----------------------------------------------------------------------
;;; When the identity matrix hasn't been made mutable things are even
;;; stranger.
;;; ----------------------------------------------------------------------


;;; These all still pass

(deftest matrix-e=-test
  (let [tm (m/identity-matrix :persistent-vector 3)]
    (is (not (m/e= tm
                   (m/array :persistent-vector
                            not-identity-matrix)))))

  (let [tm (m/identity-matrix :ndarray 3)]
    (is (not (m/e= tm
                   (m/array :ndarray
                            not-identity-matrix)))))

  (let [tm (m/identity-matrix :vectorz 3)]
    (is (not (m/e= tm
                   (m/array :vectorz
                            not-identity-matrix))))))

(deftest matrix-e==-test
  (let [tm (m/identity-matrix :persistent-vector 3)]
    (is (not (m/e== tm
                    (m/array :persistent-vector
                             not-identity-matrix)))))

  ;; Fails
  (let [tm (m/identity-matrix :ndarray 3)]
    (is (not (m/e== tm
                    (m/array :ndarray
                             not-identity-matrix)))))

  (let [tm (m/identity-matrix :vectorz 3)]
    (is (not (m/e== tm
                    (m/array :vectorz
                             not-identity-matrix))))))

(deftest matrix-equals-test
  (let [tm (m/identity-matrix :persistent-vector 3)]
    (is (not (m/equals tm
                       (m/array :persistent-vector
                                not-identity-matrix)))))

  ;; Fails
  (let [tm (m/identity-matrix :ndarray 3)]
    (is (not (m/equals tm
                       (m/array :ndarray
                                not-identity-matrix)))))

  (let [tm (m/identity-matrix :vectorz 3)]
    (is (not (m/equals tm
                       (m/array :vectorz
                                not-identity-matrix))))))
