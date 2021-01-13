% Lab #3, approx. of Binomial distr. by Poisson Distr.
% If n >= 30, p <= 0.05, then Bino(n,p) = approx = Poisson(lambda = np)
n = input('n (n >= 30) = ');
p = input('p (p <= 0.05) = '); % Parameters for the binomial distribution

lambda = n * p; % the parameter for the corresponding Poisson distribution

% Bino(n,p)
k = 0 : n;
bpdf = binopdf(k, n, p);

% Poisson(lambda)

ppdf = poisspdf(k, lambda);

% Compare the two distributions graphically
plot(k, bpdf, k, ppdf, 'r--')
legend('Binomial', 'Poisson')

% Try different values (n,p)= (30,0.05), (100, 0.05), (1000, 0.3), 
% (10, 0.6) and see the different approximations (or lack of)