f = @(a) 1 ./ (1 + a.^2);
a = -5:0.01:5

n = 2;
points1 = linspace(-5, 5, n+1);
y1 = f(points1);

##points2 = linspace(-5, 5, n+2+1);
##y2 = f(points2);
##
##points3 = linspace(-5, 5, n+2+2+1);
##y3 = f(points3);
##
##points4 = linspace(-5, 5, n+2+2+2+1);
##y4 = f(points4);

vector =[];
for i= 0:100
  yi = i./10 -5;
  xx = f(yi);
  L = lagrange(points1, y1, yi);
  solution= abs(f(yi)-L);
  vector = [vector solution];
endfor

disp(max(vector));
plot(vector);

newVector =[]
for j = -5:5
  L2= lagrange(a, f(a), j);
  sol = abs(f(j) - L2);
  newVector = [newVector sol];
endfor

hold on;
plot(newVector);



##printf("For n=2 %c \n")
##max(lagrange(points1, y1, max(vector)))

##printf("For n=4 %c \n")
##max(lagrange(points2, y2, max(xx)))
##
##printf("For n=6 %c \n")
##max(lagrange(points3, y3, max(xx)))
##
##printf("For n=8 %c \n")
##max(lagrange(points4, y4, max(xx)))
