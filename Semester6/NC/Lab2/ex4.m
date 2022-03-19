  x1 = 1:0.25:2.5
  f1 = @(x1) sqrt(5 * x1.^2 + 1)

function finiteDifferences = ex4(x, f)
  n = length(x);
  finiteDifferences = [f(x)', zeros(n, n-1)];
  for k = 2:n  
    for i = 1:n-k+1
      finiteDifferences(i,k) = finiteDifferences(i+1, k-1) - finiteDifferences(i, k-1);
    endfor
  endfor
  finiteDifferences = [x', finiteDifferences];
endfunction

  t4 = ex4(x1, f1)