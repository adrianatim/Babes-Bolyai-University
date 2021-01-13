% Simulate Geometric distr. Geo(p) variables.
p = input('p (in (0,1)) = '); % the parameter of the Geo distr.
% A geometric variable is the number of failures that occur before the
% first success. So we count that number of failures
X = 0; % initial number of failures
while rand >= p % "rand < p" represents success, so "rand >= p" represents failure
   X = X + 1;  % add the number of failures
end           % stop at the first success


% Generate a sample of such variables
clear X
N = input('nr. of simulations = '); % at least 10000
for i = 1:N
    X(i) = 0;
    while rand >= p
        X(i) = X(i) + 1;
    end  
end

% Compare it to the Geo(p) distribution, graphically.
k = 0 : 20; % "all" the values of a geometric distr. (not all, all are infinity...)
            % adjust the number for each individual case
p_k = geopdf(k,p); % the probabilities of a geo. distr.
U_X = unique(X); % the values of X listed ONLY ONCE, no repetitions
n_X = hist(X,length(U_X)); % the frequency of each value in UX (how many times each occurs)
clf
plot(U_X, n_X/N, '*', k, p_k, 'ro', 'Markersize', 10, 'LineWidth', 2) % the relative freq. n_X/N approximates the probability
legend('simulation', 'Geometric distr')
