package com.chintankhetiya.mvvm.architectural.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tb_universities")
data class UniversitiesEntity(
    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name: String,

    @ColumnInfo(name = "country")
    @SerializedName("country")
    var country: String,

    @ColumnInfo(name = "alpha_two_code")
    @SerializedName("alpha_two_code")
    var code: String,

    ) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uni_id")
    var uniId: Int? = null

    var isClicked: Boolean = false
}


