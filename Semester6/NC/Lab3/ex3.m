f = @(a) (1 + cos(pi*a)) ./ (1 + a);

x = linspace(0, 10, 21); # 21 points wanted
y = f(x); 

xx = linspace(0, 10, 42);

plot(xx, f(xx), 'c');  
hold on;

L = lagrange(x, y, xx);  

plot(xx, L,'d'); 