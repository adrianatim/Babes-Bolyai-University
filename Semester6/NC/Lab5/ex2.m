x = [1 2]
y = [0 0.6931]
d = [1 0.5]
xx = [1.5]

H = HermitePolynomial(x,y,d,xx)

err = abs(log(xx) - H)