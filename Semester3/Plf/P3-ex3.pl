% 3.
%  Write a predicate to determine all decomposition of n (n given,
% positive), as sum of consecutive natural numbers.

%getSolution(Nr:integer, E:integer, R:list)
%flow model:(i, i, o)
%
%Nr - the number that we decompose in the sum of consecutive numbers
%E - the number from where we start to check if adding consecutive
%    numbers it will result the number Nr
%R - the resulting list
%
%Mathematical model:
%getSolution(Nr, E)=
%                   = Nr=NR-El and E=E+1 and getSolution(Nr, E),if Nr
%                     >= E
%                   = [], if Nr=0

getSolution(0, _, []):-!.
getSolution(Nr, E, [E|R]):-
    Nr >= E,
    Nr1 is Nr-E,
    E1 is E+1,
    getSolution(Nr1, E1, R).

%decompose(Nr:integer, E:integer, R:list)
%flow model:(i, i, o)
%
%Nr - the number that we decompose in the sum of consecutive numbers
%E - the number from where we start to check if adding consecutive
%    numbers it will result the number Nr
%R - the resulting list
%
%Mathematical model:
%decompose(Nr, E)=
%                = getSolution(Nr, E),if Nr>E
%                = decompose(Nr,E),if Nr>E

%decompose(Nr, Nr, []).
decompose(Nr, E, R):-
    Nr > E,
    getSolution(Nr, E, R).
decompose(Nr, E, R):-
    Nr > E ,
    NE is E+1,
    decompose(Nr, NE, R).



% function that get all the list that results from 'decompose()'
% function and put them in a new list(heterogenous)
getAllDecompositions(Nr, R):-
    findall(Result, decompose(Nr, 1, Result), R).



