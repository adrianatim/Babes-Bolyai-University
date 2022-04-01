x = [64 81 100];
y = [8 9 10];

a = 115;

ans = AitkenAlgorithm(x, y , a)

err = abs(ans-sqrt(a))
 