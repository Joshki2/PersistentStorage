import java.io.File
import kotlin.collections.HashMap

/**
 *  1.1 Implementation Task a)
 *  Implementation of my own map including persistent storage by not using any of standard provided map implementations.
 *
 */
class StandardMap {

    // Filename
    private val fileName = "standardMap.txt"
    private val file = File(fileName)

    /**
     *  function puts key/value pair in the map and stores it persistent
     *
     *  @param key of the value
     *  @param value to store in map
     */
    fun put(key : String?, value : Any?) = when {
        (value != null && key != null && !(key.isEmpty()) && file.createNewFile()) -> {
            val newMap : HashMap<String, Any> = hashMapOf(key to value)
            file.writeContent(newMap)
        }

        (value != null && key != null && !(key.isEmpty()) && !(file.createNewFile())) -> {
            val newMap : HashMap<String, Any> = hashMapOf(key to value)
            val oldMap : HashMap<String, Any>? =
                file.readContent() as? HashMap<String, Any>

            println("Old Content: $oldMap")

            if(oldMap != null && !(oldMap.isEmpty()))
                newMap.putAll(oldMap)
            else
                println("Old map is null or empty")

            file.writeContent(newMap)
        }

        else -> println("Failed to put $key with $value in map!")
    }

    /**
     *  function gets object of a specific key out of the storage
     *
     *  @param key of the value
     *
     *  @return value of 'key' in the map
     */
    fun get(key : String?): Any? {
        if(contains(key)) {
            val oldMap: HashMap<String, Any>? =
                file.readContent() as? HashMap<String, Any>
            return oldMap!![key]
        }
        else {
            println("key doesn't exist")
            return null
        }
    }

    /**
     * function test if map contains 'key'
     *
     *  @return true if the map contains 'key'
     */
    fun contains(key : String?): Boolean {
        val oldMap : HashMap<String, Any>? =
            file.readContent() as? HashMap<String, Any>
        return (oldMap != null && !(oldMap.isEmpty()) && oldMap.containsKey(key) && key != null)
    }

    /**
     *  function remove a key/value pair and returns true if its's successful
     *
     *  @param key of the value
     *
     *  @return true if 'key' is removed
     */
    fun remove(key : String?): Boolean {

        val oldMap : HashMap<String, Any>? =
            file.readContent() as? HashMap<String, Any>

        if(!(file.createNewFile()) && oldMap != null && !(oldMap.isEmpty())) {
            oldMap.remove(key)
            file.writeContent(oldMap)

            val contKeyBool = !contains(key)
            if(contKeyBool)
                println("remove successful $key")
            return contKeyBool
        }
        else
            return false
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