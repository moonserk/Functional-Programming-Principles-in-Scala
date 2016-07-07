def fact(n: Int): Int =
  if (n <= 1) 1 else n * fact(n - 1)

fact(3)

def pascal(c: Int, r: Int): Int =
  fact(r) / (fact(c) * fact(r - c))

pascal(0, 2);






