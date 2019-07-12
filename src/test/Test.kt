package test

import OwnMap
import objects.TestObject


/**
 *
 *  testcode
 *
 */
fun main(args : Array<String>) {

    val testObj1 = TestObject(25, 15, 6, 74, 69)
    val testObj2 = TestObject(67, 9, 2354, 52, 135)
    val testObj7 = TestObject(4, 363, 6, 35, 96)
    val testObj3 = TestObject(167, 56, 257, 1, 3)
    val testObj4 = TestObject(58, 6, 14, 5679, 1)
    val testObj5 = TestObject(25, 20, 2, 14, 69)
    val testObj6 = TestObject(1, 23, 3534, 86, 263)

    val map = OwnMap()

    // tests 'put' funktion
    map.put("test1", testObj1)
    map.put("test2", testObj2)
    map.put("test3", testObj3)
    map.put("test4", testObj4)
    map.put("test5", testObj5)
    map.put("test6", testObj6)
    map.put("test7", testObj7)




    // tests getter
    val getObj = map.get("test2")
    println("getObject: $getObj")

    // tests if map contains key
    map.contains("test5")

    // tests to remove key/value pair
    map.remove("test1")

    // delete Map
    //map.deleteMap()
}