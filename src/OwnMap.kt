import objects.MapObject
import java.io.File

/**
 *  1.1 Implementation Task b)
 *  Implementation of persistent storage with standard provided map implementations
 *
 */
class OwnMap {

    // Filename
    private val fileName = "ownMap.txt"
    private val file = File(fileName)

    /**
     *  function puts key/value pair in the map
     *  it creates two arrayLists and combines it to the object 'objects.MapObject'
     *  'objects.MapObject' is saved in the textFile 'fileName'
     *
     *  @param key of the value
     *  @param value to store in map
     */
    fun put(key : String?, value : Any?) = when {
        (value != null && key != null && !(key.isEmpty()) && file.createNewFile()) -> {
            val newKeys: ArrayList<String> = arrayListOf(key)
            val newValues: ArrayList<Any> = arrayListOf(value)
            val newMap = MapObject(newKeys, newValues)
            file.writeContent(newMap)
        }

        (value != null && key != null && !(key.isEmpty()) && !(file.createNewFile())) -> {
            val newKeys: ArrayList<String> = arrayListOf(key)
            val newValues: ArrayList<Any> = arrayListOf(value)
            val oldMap : MapObject? =
                file.readContent() as? MapObject

            if(oldMap != null) {
                newKeys.addAll(oldMap.keys)
                newValues.addAll(oldMap.values)
                val newMap = MapObject(newKeys, newValues)
                file.writeContent(newMap)
            }
            else {
                println("Old map is null")
                val newMap = MapObject(newKeys, newValues)
                file.writeContent(newMap)
            }
    }

        else -> println("Failed to put $key with $value in map!")
    }

    /**
     *  function gets object of a specific key
     *
     *  @param key of the value
     *
     *  @return value of 'key' in the map
     */
    fun get(key : String?): Any? {
        val keyPos = keyPosition(key)

        if(keyPos != null) {
            val oldMap : MapObject? =
                file.readContent() as? MapObject

            if(oldMap != null)
                return oldMap.values[keyPos]
            else {
                println("Old map is null")
                return null
            }
        }
        else {
            println("key doesn't exist")
            return null
        }
    }

    /**
     *  function test if map contains 'key'
     *
     *  @param key of the value
     *
     *  @return true if the map contains 'key'
     */
    fun contains(key: String?): Boolean {
        return (keyPosition(key) != null)
    }

    /**
     *  function remove a key/value pair and returns true if its's successful
     *
     *  @param key of the value
     *
     *  @return true if 'key' is removed
     */
    fun remove(key : String?): Boolean {
        val oldMap: MapObject? =
            file.readContent() as? MapObject
        val keyPos = keyPosition(key)


        if(!(file.createNewFile()) && oldMap != null && keyPos != null) {
            val oldKeys = oldMap.keys
            val oldValues = oldMap.values
            oldKeys.removeAt(keyPos)
            oldValues.removeAt(keyPos)
            val newMap = MapObject(oldKeys, oldValues)
            file.writeContent(newMap)

            val contKeyBool = !contains(key)
            if(contKeyBool)
                println("remove successful $key")
            return contKeyBool
        }
        else {
            return false
        }
    }

    /**
     *  function searches for key in the map
     *
     *  @param key of the value
     *
     *  @return position of the key if the map contains 'key'
     *          null if the key is not in the map
     */
    private fun keyPosition(key : String?): Int? {
        val oldMap: MapObject? =
            file.readContent() as? MapObject

        if (oldMap != null && !(file.createNewFile())) {
            val oldKeys = oldMap.keys
            return oldKeys.indexOf(key)
        } else
            return null
    }

    /**
     *  function deletes the whole file with the Map inside
     *
     *  @return true if file is deleted successfully
     */
    fun deleteMap(): Boolean {
        return file.deleteRecursively()
    }
}

/**
 *  1.1 Implementation Task c)
 *
 *  An array with the IP-addresses of the machines should be passed to this file 'OwnMap.kt'.
 *  This code should handle the outputs to all of the IP-addresses over apache thrift or some other communication protocol.
 *
 */