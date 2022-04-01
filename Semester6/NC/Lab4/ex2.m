# a)
    
  x = [1 2 3 4 5];
  fx = [22 23 25 30 28];

  yi = [2.5];

  NP = NewtonPolinomial(x, fx, yi)

# b)
  
  z = 0 : 0.01 : 5;
  NP = NewtonPolinomial(x, fx, z);

  plot(x, fx, '*');
  hold on;
  plot(z, NP, 'r');