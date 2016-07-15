object App {
  def square(x: Int): Int =
    x * x

  square(2)

  type Set = Int => Boolean

  /**
    * Indicates whether a set contains a given element.
    */
  def contains(s: Set, elem: Int): Boolean = s(elem)

  /**
    * Returns the set of the one given element.
    */
  def singletonSet(elem: Int): Set =
    Set {
      elem
    }

  val s1 = singletonSet(2)
  val s2 = singletonSet(2)
  val s3 = singletonSet(3)

  def someFunc(s: Set, t: Set): Set =
    i => s(i) & t(i)

  val ss = someFunc(s1, s2)
  val ss1 = someFunc(ss, s3)
  contains(ss1, 1)
  contains(ss1, 2)
  contains(ss1, 3)
  contains(ss1, 4)
  contains(ss1, 5)

}