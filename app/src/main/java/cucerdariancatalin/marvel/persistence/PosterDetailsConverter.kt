package cucerdariancatalin.marvel.persistence

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import cucerdariancatalin.marvel.model.PosterDetails

class IntegerListConverter {

    @TypeConverter
    fun fromString(value: String): List<PosterDetails>? {
        val listType = object : TypeToken<List<PosterDetails>>() {}.type
        return Gson().fromJson<List<PosterDetails>>(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<PosterDetails>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}