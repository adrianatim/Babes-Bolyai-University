% save in something like "probII.m"
%    --the name doesn't matter since it's not a function
% plot out the n=2, 4, 8, 16 polynomials which interpolate the 
%    function f(x) = 1/(1+25x^2) on the interval [-1,1] using 
%    equally spaced points

xx=[-1:.01:1];  % plot a couple hundred points in each case

% first plot the original function
yy=1./(1+25*xx.^2); 
plot(xx,yy);
hold on;               % this makes the plots that follow pile up


for n=[2 4 8 16]
   x=-1:2/n:1;         % (check this works for n=4, for instance)
   Q=1./(1+25*x.^2);   % get the interpolation points
   for i=1:201
      yy(i)=nev(xx(i),n,x,Q);  %interpolate
   end;
   plot(xx,yy);
end;

%  inessential embellishments:
axis([-1 1 -2 2]);     % experimentation showed this was nice
plot(x,Q,'o');         % show the 16 interpolation points--last values of x and Q
title('Polynomial interpolation of y=1/(1+25*x^2)--the Runge example'); 

hold off;              % so future plots start over