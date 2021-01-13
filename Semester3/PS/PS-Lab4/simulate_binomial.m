% Simulate Binomial distr. Bino(n,p) variables.
n = input('n (in N) = ');
p = input('p (in (0,1)) = '); % the parameters of the Bino distr.
% n = 20;
% p = 0.3;
U = rand(n,1);
X = sum(U < p);


% Generate a sample of such variables

%clear U
%clear X
N = input('nr. of simulations = '); % try different numbers, 10, 100,
% 1e4, 1e6
%for i = 1 : N
%    U = rand(n,1);
%    X(i) = sum(U < p); % generate one variable at a time
%end

% OR
 U = rand(n,N);
 X = sum(U < p); % sum is computed on each column
                 % generate all at once  


% Compare it to the Bino(n,p) distribution, graphically.
k = 0 : n; % all the values of a binomial distr.
p_k = binopdf(k,n,p) % the probabilities of a binomial distr.
U_X = unique(X) % the values of X listed ONLY ONCE, no repetitions
n_X = hist(X,length(U_X)) % the frequency of each value in U_X (how many times each occurs)
rel_freq = n_X/N % the relative freq. n_X/N approximates the probability
clf
plot(U_X, n_X/N, '*', k, p_k, 'ro', 'Markersize', 10, 'LineWidth', 2) % graphical comparison
% title('Binomial B(20,0.7) distribution simulation')
legend('simulation', 'Binomial distr.')
