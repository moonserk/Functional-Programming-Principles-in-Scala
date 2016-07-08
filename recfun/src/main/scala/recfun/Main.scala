package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
    def pascal(c: Int, r: Int): Int = {
      def factorial(n: Int): Int =
        if(n <= 1) 1 else n * factorial(n - 1)
      factorial(r) / (factorial(c) * factorial(r - c))
    }
  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean = {
      def isEqual(ch: List[Char], flag: Boolean): Boolean =
        if(ch.isEmpty) flag else
          if(ch.head == '(') isEqual(ch.tail, false) else
            if(ch.head == ')') isEqual(ch.tail, true) else isEqual(ch.tail, flag)
      if(chars.isEmpty) true else
        if(chars.head == ')') false else
          if(chars.head == '(') isEqual(chars.tail, false) else balance(chars.tail)
    }
  
  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]): Int = {
      if(money == 0)
        1
      else
        if(coins.isEmpty || money < 0)
          0
        else
          countChange(money, coins.tail) + countChange(money - coins.head, coins)
    }
  }
