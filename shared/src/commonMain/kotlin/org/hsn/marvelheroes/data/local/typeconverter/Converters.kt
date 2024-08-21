/**
 * Created by Hussain on 9/17/2022
 *
 */

package org.hsn.marvelheroes.data.local.typeconverter

import androidx.room.TypeConverter
import com.hsn.marvelheroes.models.characterresponse.Event
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.hsn.marvelheroes.model.characterresponse.Comic
import org.hsn.marvelheroes.model.characterresponse.SeriesDetails
import org.hsn.marvelheroes.model.characterresponse.Url

class Converters {
    @TypeConverter
    fun comicsToString(comics: List<Comic>): String {
        return Json.encodeToString(comics)
    }

    @TypeConverter
    fun stringToComics(comic: String): List<Comic> {
        return Json.decodeFromString(comic)
    }

    @TypeConverter
    fun seriesToString(series: List<SeriesDetails>): String {
        return Json.encodeToString(series)
    }

    @TypeConverter
    fun stringToSeries(series: String): List<SeriesDetails> {
        return Json.decodeFromString(series)
    }

    @TypeConverter
    fun eventToString(events: List<Event>): String {
        return Json.encodeToString(events)
    }

    @TypeConverter
    fun stringToEvent(events: String): List<Event> {
        return Json.decodeFromString(events)
    }


    @TypeConverter
    fun urlsToString(urls: List<Url>): String {
        return Json.encodeToString(urls)
    }

    @TypeConverter
    fun stringToUrls(urls: String): List<Url> {
        return Json.decodeFromString(urls)
    }
}