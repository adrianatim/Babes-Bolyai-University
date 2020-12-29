%3. 
% a. Write a predicate to test if a list is a set.
% b. Write a predicate to remove the first three occurrences of an element in a list.
% If the element occurs less than three times, all occurrences will be removed.

%a)
% nrOccurrences(L:list, E:element, S:integer)
% flow model: (i, i, o) or (i, i, i)
% L � list in which we count the occurrences
% E � the element we are looking for
% S � the result, the number of occurrences
%
% nr_occurrences(l1...ln, e) =
%	0, n = 0
%	1 + count_occurences(l2...ln, e), l1 = e
%	count_occurences(l2...ln, e), l1 != e

nr_occurrences([], _, 0).
nr_occurrences([H|T], E, S):-
    H=E,
    nr_occurrences(T, E, S1),
    S is S1+1.
nr_occurrences([H|T], E, S):-
    H\=E,
    nr_occurrences(T, E, S).

%is_a_set(L:list)
%L - list in which we count the occurrences
%
%Mathematical model:
%
%  is_a_set(l1l2...ln) =
%           false,  if nr_occurrences(l1l2...ln,l1,S)!=1
%           true, n=0
is_a_set([]).
is_a_set([H|T]):-
    nr_occurrences([H|T], H, S),
    S =:= 1,
    is_a_set(T).


%b)
%
%remove_n_elements(L:list, Counter:counter, Elem:element, R:List)
%flow model: (i,i,i,o)
%L - list in which we delete the elements
%Counter - how many elements we want to delete
%Elem -  the element that we want to delete
%R - resulting list
%
%Mathematical model:
%
% remove_n_elements(l1l2...ln, counter, elem)=
%             -counter1 = counter-1 AND
%             remove_n_elements(l1l2...ln,counter1, elem), if l1 = elem
%             && counter>0
%             -remove_n_elements(l1l2...ln, counter, elem),
%             otherwise
remove_n_elements([],_,_,[]):-!.
remove_n_elements(L,_,0,L):-!.
remove_n_elements([H|T], Counter, Elem, R):-
    H=:=Elem,
  %  Counter>0,
   % Counter1 is Counter-1,
    remove_n_elements(T, Counter, Elem, R).
remove_n_elements([H|T], Counter, Elem, [H|R]):-
    remove_n_elements(T, Counter, Elem, R).
