;10. Define a function that replaces one node with another one in a n-tree represented as: root
;    list_of_nodes_subtree1... list_of_nodes_subtreen)
; Eg: tree is (a (b (c)) (d) (e (f))) and node 'b' will be replace with node 'g' => tree (a (g (c)) (d) (e (f)))}

;Mathematical model:
;       change(l1l2...ln, el1, el2) = el2, if l==e an l-is atom
;                                   = l, if l-is atom
;                                   = change(l1, el1, el2) U change(l2, el1, el2)...U change(ln, el1, el2) , if l-list

; ex: (change '(a (b (c)) (d) (e (f))) 'b 'g)
; =>(A (G (C)) (D) (E (F)))
(defun change (l e r)
    (cond
        ((and (atom l) (if (equal l e) r l)))
        ((listp l) (mapcar #'(lambda (a) (change a e r)) l))
    )
)
