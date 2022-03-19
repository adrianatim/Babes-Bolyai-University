function ex3(N)
  hold on;
  T = @(x) 1;
  fplot(T, [-1, 3]);
  for n = 1 : N
    T = @(x) T(x) + ((x.^n) + exp(0)) / factorial(n);
    fplot(T, [-1, 3]);
  endfor
  
endfunction