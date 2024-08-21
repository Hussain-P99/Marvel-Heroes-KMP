package org.hsn.marvelheroes.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hsn.marvelheroes.models.characterresponse.Event
import org.hsn.marvelheroes.model.characterresponse.SeriesDetails
import org.hsn.marvelheroes.model.characterresponse.Url
import org.hsn.marvelheroes.model.characterresponse.Comic

@Entity
data class Character(
    @PrimaryKey(autoGenerate = false)
    val characterId : String,
    val name : String,
    val description : String,
    val comics : List<Comic>,
    val series : List<SeriesDetails>,
    val events : List<Event>,
    val urls : List<Url>,
    val thumbnail: String,
    var isBookmarked : Boolean = false
)
