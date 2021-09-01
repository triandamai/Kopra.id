package com.trian.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
/**
 * Persistence Class
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 01/09/2021
 */

@Entity(tableName = "tb_nurse")
data class Nurse(
    @PrimaryKey(autoGenerate = true)
    var id: Int? ,
    var name: String?,
    var email: String?,
    var gender: String? ,
    var phoneNumber: String? ,
    var type: String? ,
    var no_type: String?,
    var address: String?
)