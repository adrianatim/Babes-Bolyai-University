% Simulate Bernoulli distr. Bern(p).
p = input('p (in (0,1)) = '); % the parameter of the Bern distr.
U = rand; % random number from U(0,1)
X = (U < p);



% Generate a sample of N such variables
N = input('nr. of simulations = '); % try different numbers, 10, 100,
                                    % 1e4, 1e6
% 
% for i = 1 : N
%     U = rand;
%     X(i) = (U < p); % generate one at a time
% end

% OR
 U = rand(1,N);
 X = (U < p); % generate all at once

% 
% Compare it to the Bern(p)= Bino(1,p) distribution
U_X = unique(X) % the values of X listed ONLY ONCE, no repetitions
n_X = hist(X,length(U_X)) % the frequency of each value in U_X (how many times each occurs)
rel_freq = n_X/N % the relative freq. n_X/N approximates the probability; Try different runs
% for different numbers of simulations, look at the values of U_X and rel_freq





