  x2 = [2 4 6 8]
  f2 = [4 8 14 16]

function dividedDifferences = ex5(x, f)
  n = length(x)
  dividedDifferences = [f', zeros(n, n-1)];
  for k = 2:n 
    for i = 1:n-k+1
      dividedDifferences(i,k) = (dividedDifferences(i+1,k-1)- dividedDifferences(i,k-1))/(x(i+k-1)-x(i));
    endfor
  endfor
  dividedDifferences = [x', dividedDifferences];
endfunction

 t5 = ex5(x2, f2)