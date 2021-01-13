% Lab 3. Prob & Stat.
% Problem 1.
alpha = input('alpha = ');
beta = input('beta = ');
option = input('Choose option: normal, Student, chi^2, Fischer: \n','s');
switch option
case 'normal'
    fprintf('Normal Distribution\n')
    % distribution parameters
    mu = input('mu = ');
    sigma = input('sigma = ');
    
    Pa1 = normcdf(0, mu, sigma); % the cdf F(0)
    Pa2 = 1 - Pa1; % compl. event
    
    Pb1 = normcdf(1, mu, sigma) - normcdf(-1, mu, sigma); % F(1) - F(-1)
    Pb2 = 1 - Pb1; % compl. event
    % c)
    x_alpha = norminv(alpha, mu, sigma); % quantile of order alpha, inverse cdf
    % d)
    x_beta = norminv(1 - beta, mu, sigma); % quantile of order 1 - beta
    
case 'Student'
    fprintf('Student (T) Distribution\n')
    % distribution parameters
    n = input('n = ');
    
    Pa1 = tcdf(0, n); % the cdf F(0)
    Pa2 = 1 - Pa1; % compl. event
    
    Pb1 = tcdf(1, n) - tcdf(-1, n); % F(1) - F(-1)
    Pb2 = 1 - Pb1; % compl. event
    % c)
    x_alpha = tinv(alpha, n); % quantile of order alpha, inverse cdf
    % d)
    x_beta = tinv(1 - beta, n); % quantile of order 1 - beta
    
case 'chi^2'
    fprintf('Chi Squared Distribution\n')
    % distribution parameter
    n = input('n = ');
    
    Pa1 = chi2cdf(0, n); % the cdf F(0)
    Pa2 = 1 - Pa1; % compl. event
    
    Pb1 = chi2cdf(1, n) - chi2cdf(-1, n); % F(1) - F(-1)
    Pb2 = 1 - Pb1; % compl. event
    % c)
    x_alpha = chi2inv(alpha, n); % quantile of order alpha, inverse cdf
    % d)
    x_beta = chi2inv(1 - beta, n); % quantile of order 1 - beta
    
case 'Fischer'
    fprintf('Fischer Distribution\n')
    % distribution parameters
    m = input('m = ');
    n = input('n = ');
    
    Pa1 = fcdf(0, m, n); % the cdf F(0)
    Pa2 = 1 - Pa1; % compl. event
    
    Pb1 = fcdf(1, m, n) - fcdf(-1, m, n); % F(1) - F(-1)
    Pb2 = 1 - Pb1; % compl. event
    % c)
    x_alpha = finv(alpha, m, n); % quantile of order alpha, inverse cdf
    % d)
    x_beta = finv(1 - beta, m, n); % quantile of order 1 - beta
otherwise
    fprintf('Wrong option!')
end

% Displaying the results.
fprintf('First probability in part a) is: %1.5f \n', Pa1)
fprintf('Second probability in part a) is: %1.5f \n', Pa2)
fprintf('First probability in part b) is: %1.5f \n', Pb1)
fprintf('Second probability in part b) is: %1.5f \n', Pb2)
fprintf('Answer in part c) x_alpha is: %1.5f \n', x_alpha)
fprintf('Answer in part d) x_beta is: %1.5f \n', x_beta)

