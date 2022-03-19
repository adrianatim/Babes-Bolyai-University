function c = chebyshev(n, x)
  if n == 0
    c = 1;
  elseif n == 1
    c = x;
  else 
    c = 2 * x .* chebyshev(n-1,x) - chebyshev(n-2,x);
  endif
endfunction