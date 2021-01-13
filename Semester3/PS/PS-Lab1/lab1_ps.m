%Lab #1, Prob & Stat.
clear all % clear the values of all variables
% clf % clear figure
% clc % clear command window

% Problem 1. For the matrices A = 
% [1  0  -2
%  2  1  3 
%  0  1  0]
%  and B = 
% [2  1  1
%  1  0  -1 
%  1  1  0]
% print the matrices C = A - B, D = A * B and E = [e_{ij}], where e_{ij} = a_{ij} * b_{ij}.

A = [1  0  -2; 2  1  3; 0  1  0];
B = [2  1  1; 1  0  -1; 1  1  0];

C = A - B;
D = A * B; % matrix multiplication
E = A .* B; % dot multiplication

fprintf ('C = \n')
fprintf (' %2d %2d %2d\n', C') % print the transpose, Matlab goes on columns, not on rows!!!
fprintf ('D = \n')
fprintf (' %2d %2d %2d\n', D')
fprintf ('E = \n')
fprintf (' %2d %2d %2d\n', E')


% Problem 2. For x in [0, 3], graph on the same set of axes the functions x^4/10, x*sin x and
% cos x, in different colors and linestyles. Display a title and a legend
% on your graph. Then plot them on different pictures, but in the same window.

x = 0 : 0.01 : 3; % simulate continuity of the interval
y4 = x .^5 ./ 10;
ysin = x .* sin(x);
ycos = cos(x);
% plot(x, y4, x, ysin, 'r-.', x, ycos, 'm--')
legend('fourthpower','xsin(x)','cos(x)') % the location of the legend can also be given
title('Titlu')
% for subplot:
 subplot(3,1,1): plot(x,y4); % title... legend...
 subplot(3,1,2): plot(x,ysin); % title... legend...
 subplot(3,1,3): plot(x,ycos); % title... legend...

