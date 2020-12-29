;3. Return the number of levels of a tree of type (1)
; ex: (A 2 B 0 C 2 D 0 E 0)

;Mathematical model
;       finalFinalTry(l1l2...ln, contor, level) = level, if l4=nil
;                                               = finalFinalTry(l3l4...ln, l2, level+1), if l2!=2 and contor=2
;                                               = finalFinalTry(l3l4...ln, 2, level+1), if l2=1 and l4!=0
;                                               = finalFinalTry(l3l4...ln, 2, level), if l2=2
;                                               = finalFinalTry(l3l4...ln, contor, level), otherwise
(defun finalFinalTry(l contor level)
(cond
    ((equal (cadr (cdr (cdr l))) nil) level)
    ((and (/= (cadr l) 0) (= contor 2) (finalFinalTry (cdr (cdr l)) (cadr l) (+ level 1) )))
    ((and (= (cadr l) 1) (/= (cadr (cdr (cdr l))) 0) (finalFinalTry (cdr (cdr l)) 2 (+ level 1))))
    ((= (cadr l) 2) (finalFinalTry (cdr (cdr l)) 2 level))
    (T (finalFinalTry (cdr (cdr l)) contor level))
))

;Mathematical model
;       verifyList(l1l2...ln) = T, l1l2...ln = nil 
;                             = false, if l1- is not atom or l2- is not a number
;                             = verify(l3l4...ln), otherwise
(defun verifyList (l)
(cond
    ((equal l nil) T)
    ((or (not (atom (car l))) (not (numberp (cadr l)))) nil)
    (T (verifyList (cdr (cdr l))))
))

;Mathematical model
;       getNoLevels(l1l2...ln) = 0, if l2=0
;                              = finalFinalTry(l1l2...ln, 2, 1), if verifyList(l1l2...ln) = T
;                              = nil, otherwise
;
; ex: (getNoLevels '(A 2 B 0 C 2 D 0 E 0))
; => 2
; ex: (getNoLevels '(a 2 b 1 c 1 d 0 e 2 f 1 g 0 h 2 i 0 j 1 k 0 ))
; => 4
; ex: (getNoLevels '(A 2 B 2 c 2 d 0 e 0 f 0 g 0))
; => 3
(defun getNoLevels (l)
(cond
    ((= 0 (cadr l)) 0)
    ((equal (verifyList l) T) (finalFinalTry (cdr (cdr l)) 2 1))
    (T nil)
))