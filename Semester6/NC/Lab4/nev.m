function p = nev(x0,x,y)
%Inputs: x0-- the point at which to evaluate
%        x -- the matrix of the x terms of the ordered pairs
%        y -- the matrix of the y terms of the ordered pairs
%Output: p -- the value of the polynomial going through the n data points

n = length(x)-1;       % n is the degree of the polynomial                         (1)
p = zeros(n+1,n+1) % creates the zero matrix p                                 (2)

for i = 1: n+1       % runs loop from i equals 1 until it reaches end value      (3) 
  p(i,i) = y(i);     % when i is equal to j, set the element equal to the corresponding y value
 end
for j = 1: n+1       % runs loop from j equal to 1 until it reaches end value    (4)
  for i = 1: n+1-j     % runs loop from i equal to 1 until it reached end value    (5)
    p(i,i+j) = ((x(i+j)-x0)*p(i,i+j-1) + (x0-x(i))*p(i+1,i+j))/(x(i+j)-x(i));
                     % evaluates each element of the matrix, when i is not equal to j, using Neville's Algorithm
  end
 end
p = p(1,n+1);    % outputs the value of the polynomial going through the 
                     % n data points at the point x0   