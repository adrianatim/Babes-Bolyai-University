# x  - nodes
# fx - the values of the function in x
# xx - value of the approximation
function NIP = NewtonPolinomial(x, fx, xx)

  n = length(x) - 1;

  # divided differences table
  table = DividedDifferencesTable(x, fx);

  m = length(xx);
  p = ones(1, m);
  s = table(1, 1) * ones(1, m);
  for j = 1 : m
    for i = 1 : n
      p(j) = p(j) * (xx(j) - x(i));
      s(j) = s(j) +  p(j) * table(1, i + 1);
    end
  end 

  NIP = s;

  endfunction