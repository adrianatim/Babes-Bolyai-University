% Lab #2, f) Simulating 3 coin tosses.
N = input('Number of simulations: '); % Number of simulations

% Try different number of simulations, 10, 50, 100, 1e4, 1e5, 1e6, you will
% notice that the larger N is, the more the shape will resemble the
% binomial(3, 1/2) distribution

U = rand(3,N); % a 3 x N matrix of random numbers from [0,1], simulating 
% the 3 coin tosses 

Y = (U < 1/2); % Y = 1 (heads) if U < 1/2, otherwise Y = 0 (tails)

X = sum(Y); % sums across columns of Y, i.e. X = number of heads in 3 tosses

clf   % Clear the current figure Window
hist(X); % the histogram of X, the shape of the pdf

% NOTE: we will discuss hist later, in Descriptive Statistics, in greater
% detail, for now, it suffices to know that it gives a rough idea of the
% distribution of the values of X (i.e. the pdf of X)
