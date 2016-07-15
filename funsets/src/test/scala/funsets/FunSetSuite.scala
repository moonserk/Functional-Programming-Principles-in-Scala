package funsets

import org.scalatest.FunSuite


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  // test("string take") {
  //   val message = "hello, world"
  //   assert(message.take(5) == "hello")
  // }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  // test("adding ints") {
  //   assert(1 + 2 === 3)
  // }


  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val su1 = union(s1, s2)
    val su2 = union(su1, s3)
   // printSet(s1)
    //printSet(su2)
   // printSet(filter(su2, x => x < 3))
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton 1")
      assert(contains(s2, 2), "Singleton 2")
      assert(contains(s3, 3), "Singleton 3")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), s)
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }
  
  test("intersection of the two given sets") {
    new TestSets {
      val s = intersect(su1, s1)
      val si2 = intersect(su1, s2)
      assert(contains(s, 1), "intersect 1")
      assert(contains(si2, 2), "intersect 2")
      assert(!contains(s, 2), "intersect 3")
    }
  }

  test("diff of the given sets") {
    new TestSets {
      val s = diff(su1, s2)
      assert(contains(s, 1), "diff 1")
    }
  }
  
  test("filter of the given set") {
    new TestSets {
      val s = filter(su1, x => x > 1)
      val sf2 = filter(su2, x => x < 3)
      val sf3 = filter(su2, x => x == 3)
      assert(contains(s, 2), "filter 1")
      assert(!contains(s, 1), "filter 1 !contains 1")
      assert(contains(sf2, 1), "filter 2 contains 1")
      assert(contains(sf2, 2), "filter 2 contains 2")
      assert(!contains(sf2, 3), "filter 2 !conains 3") 
      assert(contains(sf3, 3), "filter 3 contains 3")
    }
  }
  test("forall of the given set") {
    new TestSets {
      val s = forall(su2, x => x < 4) 
      val sf2 = forall(su2, x => x < 3)
      assert(s, "forall 1 true")
      assert(!sf2, "forall 2 false")   
    }
  }

  test("exists of given set") {
     new TestSets {
       val s = exists(su2, x => x == 2)
       val se2 = exists(su2, x => x == 4)
       assert(s, "exist 1 true")
       assert(!se2, "exist 2 false")
    }
  }
  
  test("map of given set") {
    new TestSets {
      val s = map(su2, x => x * 2)
      val test = Set(2,3,4)
      printSet(s)
      printSet(su2)
      assert(contains(s, 6), "map 1 true")
    }
  }
}

