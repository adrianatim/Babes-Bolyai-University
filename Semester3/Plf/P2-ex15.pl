%15
%a. Define a predicate to determine the predecessor of a number represented as digits in a list.
%Eg.: [1 9 3 6 0 0] => [1 9 3 5 9 9]
%b. For a heterogeneous list, formed from integer numbers and list of numbers, define a predicate to determine
%the predecessor of the every sublist considered as numbers.
%Eg.: [1, [2, 3], 4, 5, [6, 7, 9], 10, 11, [1, 2, 0], 6] =>
%[1, [2, 2], 4, 5, [6, 7, 8], 10, 11, [1, 1, 9] 6]
%a) A predicate to determine the predecessor of an number
%
%predecessor(L:list, C:integer, R:list)
%flow model:(i, i, o)
%L - the liste in which we are checking
%C - the semaphore, if the last digit is 0
%R - the resulting list
%
%Mathematical model:
%   predecessor(l1l2...ln, c) =
%                             =[], if n=0
%                             =[9], if n=1 and l1 = 0
%                             =[l1-1], if n=1 and l1!=0
%                             =9 + predecessor(l2l3...ln, 1), if c=1 and
%                              l1=0
%                             =l1-c + predecessor(l2l3...ln), if
%                              l1!=0
predecessor([], _, []).
predecessor([0], 1, [9]) :- !.
predecessor([N], 0, [NR]) :- NR is N - 1, !.
predecessor([0|T], 1, [9|R]) :-
    predecessor(T, 1, R), !.
predecessor([H|T], 0, [HR|R]) :-
    predecessor(T, C, R),
    HR is H - C.

%b) For a heterogeneous list, formed from integer numbers and list of numbers, define a predicate to determine
%the predecessor of the every sublist considered as numbers
%
%process(L:list, R:list)
%flow model: (i,o)
%L - the liste in which we are checking
%R - the resulting list
%
%Mathematical model:
%  process(l1l2...ln) =
%                     =[], if n=0
%                     =predecessor(l1, c) + process(l2l3...ln), if l1 is
%                                                                a list
%                     =process(l2l3...ln), otherwise

process([], []).
process([H|T],[LR|R]):-
    is_list(H),!,
    predecessor(H, _, LR),
    process(T, R).
process([H|T], [H|R]):-
    process(T, R).
