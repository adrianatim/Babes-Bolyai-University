;11. a) Determine the least common multiple of the numerical values of a nonlinear list

;Mathematical model:
;         cmmdc(x, y) = cmmdc(x, y-x), if x < y
;                     = cmmdc(x-y, y), if x > y
;                     = x, otherwise
(defun cmmdc (x y)
(cond
     ((> x y) (cmmdc (- x y) y))
     ((< x y) (cmmdc x (- y x)))
     ((= x y) x)
))

;Mathematical model:
;         cmmc(x, y) = nil, if x- not a number and y- not a number
;                    = x, if y- not a number 
;                    = y, if x- not a number
;                    = (x*y)/cmmdc(x, y), otherwise
(defun cmmmc(x y)
(cond
     ((and (not (numberp x)) (not (numberp y))) nil)
     ((not (numberp x)) y)
     ((not (numberp y)) x)
	((/ (* x y) (cmmdc x y))) 
))

;Mathematical model:
;         leastCommonMultiple(l1l2...ln) = l1, if l1- is an atom and l2l3...ln- is null
;                                        = cmmc(leastCommonMultiple(l1), leastCommonMultiple(l2...ln)), if l1- is a list
;                                        = cmmc(l1, leastCommonMultiple(l2...ln)), otherwise
; ex: (leastCommonMultiple '(2 7 1 (10 a bb) 5))
; => 70
(defun leastCommonMultiple (l)
(cond 
     ((and (atom (car l)) (null (cdr l))) (car l))
	((listp (car l)) (cmmmc (leastCommonMultiple (car l)) (leastCommonMultiple (cdr l))))
     (T (cmmmc (car l) (leastCommonMultiple (cdr l))))
))

;b)Write a function to test if a linear list of numbers has a "mountain" aspect (a list has a "mountain" aspect if the items increase to a certain point and then decreases.
; Eg. (10 18 29 17 11 10). The list must have at least 3 atoms to fullfil this criteria

;Mathematical model:
;         mountain(l1l2...ln, c, k) = T, if length(l1l2...ln)=1 and c=0
;                                   = false, if l1<l2 and c=0
;                                   = false, if l1<l2 and c=0
;                                   = false, if l1>l2 and k=0
;                                   = mountain(l2l3...ln, 1, k+1), if l1<l2 and c=1
;                                   = mountain(l2l3...ln, 0, k), if l1>l2 and c=0
;                                   = mountain(l2l3...ln, 0, k), otherwise
(defun mountain (l c k) 
(cond 
     ((and (= (list-length l) 1) (= c 0)) T)
     ((and (= (list-length l) 1) (= c 1)) nil)
     ((and (< (car l) (cadr l)) (= c 0)) nil)
     ((and (> (car l) (cadr l)) (= k 0)) nil)
     ((and (< (car l) (cadr l)) (= c 1)) (mountain (cdr l) 1 (+ k 1)))
     ((and (> (car l) (cadr l)) (= c 0)) (mountain (cdr l) 0 k))
     (T (mountain (cdr l) 0 k))
))

;Mathematical model:
;         atLeast3(l1l2...ln) = nil, if length(l)<3
;                             = mountain(l1l2...ln, 1), otherwise
; ex: (atLeast3 '(1 2 7 6 3 0 -2))
; => T
; ex: (atLeast3 '(1 2 7 6 3 0 2))
; => nil
; ex: (atLeast3 '(9 7 5 3 1))
; => nil
; ex: (atLeast3 '(1 2 3 4))
; => nil
(defun atLeast3 (l)
(cond
     ((< (list-length l) 3) nil)
     (T (mountain l 1 0))
))

(defun vectors (l1 l2 element1)
(cond
     ((not (equal element1 nil)) (cons element1 (vectors l1 l2 element1)))
     (T (vectors (cdr l1) (cdr l2) (+ (car l1) (car l2))))
))

;c)Remove all occurrences of a maximum numerical element from a nonlinear list

;Mathematical model:
;         getMaxElement(x y) = x, if x>y
;                            = y, if x<y
(defun getMaxElement (x y)
     (if (> x y) x y)
)

;Mathematical model:
;         getMaxElFromList(l1l2...ln) = -1, if l1l2...ln- is null
;                                     = l1, if l2l3...ln- is null and l1- is number 
;                                     = getMaxElement(l1, getMaxElFromList(l2l3...ln)), if l1- is number
;                                     = getMaxElement(getMaxElFromList(l1), getMaxElFromList(l2l3...ln)), if l1- is a list
;                                     = getMaxElFromList(l2l3...ln), otherwise
(defun getMaxElFromList (l)
    (cond
        ((null l) -1)
        ((and (null (cdr l)) (numberp (car l))) (car l))
        ((numberp (car l)) (getMaxElement (car l) (getMaxElFromList (cdr l))))
        ((listp (car l)) (getMaxElement (getMaxElFromList (car l)) (getMaxElFromList (cdr l))))
        (t (getMaxElFromList (cdr l)))
     )
)

;Mathematical model:
;         deleteNoOfOccurrences(l1l2...ln, element) = (), if l-is nil
;                                                   = deleteNoOfOccurrences(l2l3...ln, element), if l1- is atom and l1=element
;                                                   = l1 U deleteNoOfOccurrences(l2l3...ln, element)), if l- is atom and l1 !=element
;                                                   = deleteNoOfOccurrences(l1, element)UdeleteNoOfOccurrences(l2l3...ln, element)), otherwise
(defun deleteNoOfOccurrences (l element)
(cond 
     ((equal l nil) nil)  
     ((and (atom (car l)) (equal (car l) element)) (deleteNoOfOccurrences (cdr l) element))
     ((and (atom (car l)) (not (equal (car l) element)) (cons (car l) (deleteNoOfOccurrences (cdr l) element))))
     (T (cons (deleteNoOfOccurrences (car l) element) (deleteNoOfOccurrences (cdr l) element)))
))

;Mathematical model:
;         deleteMaxNoOcc(l1l2...ln) = deleteNoOfOccurrences(l1l2...ln, (getMaxElFromList l1l2...ln)), if l- is a list
; ex: (deleteMaxNoOcc '(1 2 5 (2 5 a 5 b) 1 0 -5 5))
; => (1 2 (2 A B) 1 0 -5)
(defun deleteMaxNoOcc (l)
(cond 
     ((listp l) (deleteNoOfOccurrences l (getMaxElFromList l)))
))

;d)Write a function which returns the product of numerical even atoms from a list, to any level

;Mathematical model:
;         productNN(l1l2...ln, product) = product, if l-is nil
;                                       = productNN(l2l3...ln, product*l1), if l1- is a number and (l1 mod 2 = 0) 
;                                       = productNN(l1, product), if l1- is a list
;                                       = productNN(l2l3...ln, product), otherwise
(defun productNN (l product)
(cond 
     ((equal l nil) product)
     ((and (numberp (car l)) (= (mod (car l) 2) 0)) (productNN (cdr l) (* product (car l))))
     ((listp (car l)) (productNN (cdr l) (productNN (car l) product)))
     (T (productNN (cdr l) product))
))

;Mathematical model:
;         finalFct(l1l2...ln) = productNN(l1l2...ln, 1), always
; ex: (finalFct '(1 2 a (2 3) 4  6 (2 1)))
; => 192
(defun finalFct (l)
(cond 
     ((listp l) (productNN l 1))
))
