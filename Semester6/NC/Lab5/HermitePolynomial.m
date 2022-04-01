1;

#div diff adapted
function div_diff = DividedDiff(x, y, z, yz, d)
  n = length(x);
  m = zeros(2*n, 2*n);
  m(:, 1) = yz';
  m(1: 2 : end, 2) = d';
  m(2: 2 : 2*n-1, 2) = (y(2 : n) - y(1 : n-1)) ./ (x(2 : n) - x(1 : n-1));
  for k = 3 : 2*n
      for i = 1 : 2*n-k+1
          m(i, k) = (m(i+1, k-1) - m(i, k-1)) / (z(i+k-1) - z(i));
      end
  end
  div_diff = m;
endfunction


#Hermite alg
# x = nodes
# y = the value of the function in nodes
# d = the value of the derivative of the function in nodex
# xx = the vector of the elements in which we want to aproxx the function
function HIP = HermitePolynomial(x,y,d,xx)
  n = length(x);

  z = zeros(1, 2*n);  
  z(1 : 2 : end) = x; 
  z(2 : 2 : end) = x;
  yz = zeros(1, 2*n);  
  yz(1 : 2 : end) = y; 
  yz(2 : 2 : end) = y;
 
  table = DividedDiff(x, y, z, yz, d)

  nx = length(xx);   # the Hermite interpolation
  p = ones(nx, 1);
  s = table(1, 1) * ones(nx, 1);   % --> f(z0) = table(1,1)
  for j = 1 : nx
    for i = 1 : 2*n-1
      #product * (x - z(i))
      p(j) = p(j) * (xx(j) - z(i));
      #sum + product * div_diff(i)(x0)
      s(j) = s(j) + p(j) * table(1, i+1);
    end
  end
  HIP = s';
endfunction