import customExceptions.SerializationExceptions
import java.io.*

/**
 *  serializes a serializable object 'this' in byteArray
 */
fun Any.serialize() : ByteArray {
    val output = ByteArrayOutputStream()
    val outputStream = ObjectOutputStream(output)
    outputStream.writeObject(this)
    return output.toByteArray()
}

/**
 *  deserializes a byteArray 'this' in a serializable object
 */
fun ByteArray.deserialize() : Any? {
    val input = ByteArrayInputStream(this)
    val inputStream = ObjectInputStream(input)
    return inputStream.readObject()
}

/**
 *  function write serialized content 'obj' in in bytes to a file 'this'
 *
 *  @param obj any object that you want to write in the file
 */
fun File.writeContent(obj: Any) {
    var bytes : ByteArray? = null

    try {
        bytes = obj.serialize()
    }
    catch(e : SerializationExceptions) {
        println(e.message)
    }

    if (bytes != null) {
        this.writeBytes(bytes)
        println("write content: $obj")
    }
    else
        println("failed to write bytes in file!")
}

/**
 *  function read a file 'this' in bytes, deserialize it in object and print/return the objects
 *
 *  @return the read objects
 */
fun File.readContent() : Any? {
    try {
        val obj = this.readBytes().deserialize()
        println("read content: $obj ")
        return obj
    } catch(e : SerializationExceptions) {
        println(e.message)
        return null
    }

}