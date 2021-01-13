%Lab #3, approx. of Binomial distr. by Normal Distr.
% If 0.05 <= p <= 0.95 and n large. then
% Bino(n,p) = approx = Norm(mu = np, sigm = sqrt(npq)) (q = 1-p)

p = input('p (0.05 <= p <= 0.95) = '); % Parameter p for the binomial distribution;
% the other parameter will vary, getting larger and larger
for n = 1 : 3 : 100 % or n = 1:2:100, or n = 1:5:100
    k = 0 : n;
    prob = binopdf(k, n, p);
    plot(k, prob)
    pause(0.5)
end