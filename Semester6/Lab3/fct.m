function z = fct(i,x)
  m = length(x);
  p = 1;
for j = 1:m
  if i~=j #true if i is not equal with j
    p = p*(x(i)-x(j));
  endif
end
z = 1/p;
endfunction