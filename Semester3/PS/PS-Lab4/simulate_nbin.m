% Simulate Pascal (Neg. Bin.) distr. NBin(n,p) variables.
n = input('n (in N) = '); %
p = input('p (in (0,1)) = '); % the parameters of the NBin distr.
% A NBin(n,p) variable is the sum of n independent geometric variables (and
% represents the number of failures occurred before the nth success)
for j = 1:n
    X(j) = 0; % initial number of failures
    while rand >= p % "rand < p" represents success, so "rand >= p" represents failure
        X(j) = X(j) + 1;  % add the number of failures
    end           % stop at the first success
end
Y = sum(X);
clear Y
clear X


% Generate a sample of such variables
N = input('nr. of simulations = '); % at least 10000
for i = 1:N
    for j = 1:n
        X(j) = 0;
        while rand >= p
            X(j) = X(j) + 1;
        end 
    end
    Y(i) = sum(X);
end

% Compare it to the Nbin(p) distribution, graphically.
k = 0 : 150; % "all" the values of an NBin distr. (not all, all are infinity...)
            % adjust the number for each individual case
p_k = nbinpdf(k,n,p) % the probabilities of a geo. distr.
U_Y = unique(Y) % the values of Y listed ONLY ONCE, no repetitions
n_Y = hist(Y,length(U_Y)) % the frequency of each value in U_Y (how many times each occurs)
clf
plot(U_Y, n_Y/N, '*', k, p_k, 'ro', 'Markersize', 10, 'LineWidth', 2) % the relative freq. n_Y/N approximates the probability
legend('simulation', 'Negative Binomial distr')
