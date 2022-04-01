function div_diff = DividedDifferencesTable(x, f)
  n = length(x)
  div_diff = [f', zeros(n, n-1)];
  for k = 2 : n
    for i = 1 : n-k+1
      div_diff(i, k) = (div_diff(i + 1, k-1) - div_diff(i, k - 1)) / (x(i + k - 1) - x(i));
    endfor
  endfor
endfunction
