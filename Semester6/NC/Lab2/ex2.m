#a) Tn(t) = cos(narccost), t included in [-1,1]

##x = -1:0.01:1;
##hold on
##T1=@(t) cos(1.*acos(t));
##plot(x, T1(x))
##T2=@(t) cos(2.*acos(t));
##plot(x, T2(x))
##T3=@(t) cos(3.*acos(t));
##plot(x, T3(x))

#b)

hold on;
x = -1:0.01:1;
for n=1:4
  l = chebyshev(n,x);
  plot(x,l)
 endfor