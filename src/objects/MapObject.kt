package objects

import java.io.Serializable

data class MapObject(var keys: ArrayList<String>,
                     var values: ArrayList<Any>) : Serializable