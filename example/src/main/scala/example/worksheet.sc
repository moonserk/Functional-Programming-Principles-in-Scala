def sum(xs: List[Int]) : Int =
  if (xs.isEmpty)
    return 0
  else
    return xs.head + sum(xs.tail)

sum(List(1,1))
