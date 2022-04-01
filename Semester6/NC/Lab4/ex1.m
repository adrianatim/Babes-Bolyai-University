x = [1 1.5 2 3 4];
fx = [0 0.17609 0.30103 0.47712 0.60206];
i = 10:35;
yi = i./10;

xx = [2.5 3.25];
NP = NewtonPolinomial(x, fx, xx)

NP = NewtonPolinomial(x, fx, yi);
E = max(abs(log10(yi) - NP))
