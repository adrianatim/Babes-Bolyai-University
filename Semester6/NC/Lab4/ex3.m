f = @(x)(exp(sin(x)));
x = linspace(0, 6, 13);
y = f(x);
xx = 0 : 0.1 : 6;

NP = NewtonPolinomial(x, y, xx);

plot(x, f(x),'*');
hold on;
plot(xx, f(xx), 'b');
hold on;
plot(xx, NP, 'r');