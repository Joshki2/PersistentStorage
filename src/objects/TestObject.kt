package objects

import java.io.Serializable

data class TestObject(var calendarDayId: Int,
                      var day: Int,
                      var month: Int,
                      var year: Int,
                      var weekday: Int) : Serializable