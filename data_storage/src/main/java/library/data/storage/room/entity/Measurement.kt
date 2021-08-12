package library.data.storage.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "tb_measurement")
data class Measurement(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    var id_patient:String?,
    var device_id:String?,
    var type:Int?,
    var result:String?,
    var asset:String?,
    var created_at:String?,
    var test_method:String?,
    var timestamp: Long?,
    var is_upload:Boolean?,

){
    constructor():this(0,"","",0,"","","","",0,false)
    constructor(id_patient: String?,device_id: String?,type: Int?,result: String?,asset: String?,created_at: String?,test_method: String?,timestamp: Long?,is_upload: Boolean?):this(null,id_patient,device_id,type,result,asset,created_at,test_method,timestamp,is_upload)
}
