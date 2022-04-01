
#f(x)=sin(2x), 15 nodex, plot f and hermite pol
f = @(x)(sin(2*x))
x = linspace(-5, 5 , 15)
y = f(x)

df = @(x)2*cos(2*x)
d = df(x)

xx = linspace(-5,5,50);

H = HermitePolynomial(x,y,d,xx);

hold on;
plot(x, f(x),'o')
plot(xx, H)

legend('f(x)', 'Hermite')