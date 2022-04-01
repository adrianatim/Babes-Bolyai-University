 # x - nodes
# y - the values of the function in x
# a - value of the approximation
function z = AitkenAlgorithm(x, y, a)
  
  er = 1e-3; 
  [z, I] = sort(abs(x - a));
  x = x(I);
  y = y(I);
  n = length(x);
  ak = zeros(n, n);
  ak(:, 1) = y';
    
  for i = 1 : n
      for j = 1 : i - 1
      ##  determinant: (fjj * xi-x) - (fij * xj-x)
         ak(i, j + 1) = (1 / (x(i) - x(j))) * (ak(j,j) * (x(i) - a) - ak(i, j) * (x(j) - a));
      endfor
      ## |fii - fi-1i-1 | <= Epsilon
      if i > 1 && abs(ak(i - 1, i - 1) - ak(i, i)) <= er
            z = ak(i, i);
            disp(i);        
            return
      endif        
  endfor
    
endfunction