function L = lagrange(x,y,xy)  
m = length(x);
P = zeros(1,m);
N = length(xy);
L = zeros(1,N);

for j = 1:N
  s1 = 0;
  s2 = 0;
  for i = 1:m
    P(i) = fct(i,x);
    s1 = s1 + P(i)*y(i)/(xy(j)-x(i));
    s2 = s2 + P(i)/(xy(j)-x(i));
endfor
L(j) = s1/s2;
end
endfunction