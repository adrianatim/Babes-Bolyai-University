n = input('Nr. of trials n =');
p = input('Probability of success p =');
x = 0:n; % nr. of successes , x-values for pdf
px = binopdf(x,n,p);
plot(x,px,'r+');
hold on
xx=0 : 0.01 : n;
pxx = binocdf(xx,n,p);
plot(xx, pxx, 'm');
legend('pdf','cdf');