#a)
##x = [-2 -1 0 1 2];
##fx = 3.^x;
##t = 1./2;
##
##
####plot(x, fx)
####hold on;
##
##n = length(x);
##Q = zeros(n,n);
##
##
##for i = 1:n
##    Q(i,1) = fx(i);
##end
##for i = 2:n
##    for j = 2:i
##        Q(i,j) = ((t-x(i-j+1)) * Q(i,j-1) + ((x(i)-t) * Q(i-1,j-1)))/(x(i)-x(i-j+1));
##    end
##end
##
##disp(Q)
##err = abs(Q-sqrt(t))

#b)
x1 = [0 1 2 4 5]
fx1 = sqrt(x1)
t1 = 3;

##  plot(x1, fx1)
##hold on;

n = length(x1);
Q = zeros(n,n);

for i = 1:n
    Q(i,1) = fx1(i);
end
for i = 2:n
    for j = 2:i
        Q(i,j) = ((t1-x1(i-j+1)) * Q(i,j-1) + ((x1(i)-t1) * Q(i-1,j-1)))/(x1(i)-x1(i-j+1));
    end
end

disp(Q)
##err = abs(Q-sqrt(t1))



