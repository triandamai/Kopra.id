package com.cexup_sdk.services

import com.cexup_sdk.storage.models.*
import com.cexup_sdk.storage.room.entity.Measurement
import com.cexup_sdk.utils.DeviceUtils.TYPE_BMI
import com.cexup_sdk.utils.DeviceUtils.TYPE_BPM
import com.cexup_sdk.utils.DeviceUtils.TYPE_HEARTRATE
import com.cexup_sdk.utils.DeviceUtils.TYPE_HEIGHT
import com.cexup_sdk.utils.DeviceUtils.TYPE_RESPIRATION
import com.cexup_sdk.utils.DeviceUtils.TYPE_SLEEP
import com.cexup_sdk.utils.DeviceUtils.TYPE_SPO2
import com.cexup_sdk.utils.DeviceUtils.TYPE_STETHOSCOPE
import com.cexup_sdk.utils.DeviceUtils.TYPE_TEMPERATURE
import com.cexup_sdk.utils.DeviceUtils.TYPE_WAIST
import com.cexup_sdk.utils.DeviceUtils.TYPE_WEIGHT
import com.google.gson.Gson

object Endpoint {
        const val BASE_URL = "http://app.cexup.com/api"
        const val BASE_URL_MEASUREMENT = "http://app.cexup.com/api"


}
fun populateListMeasurement(measurements: List<Measurement>):String{
    var jsonArray = """"""

    measurements.forEachIndexed { index, measurement ->
        if(index > 0){
            jsonArray += ","
        }
        jsonArray += (populateSingleMeasurement(measurement))


    }
    return jsonArray.trimIndent()
}

fun populateSingleMeasurement(measurement: Measurement):String{
    when(measurement.type){
        TYPE_TEMPERATURE->{
            val temp = gson().fromJson(measurement.result, TemperatureModel::class.java)

            return  """
                    {
                        "device_id":"${measurement.device_id}",
                        "type":"${measurement.type}",
                        "asset":"",
                        "created_at":"${measurement.created_at}",
                        "test_method":"${measurement.test_method}",
                        "result":[
                            {
                                "name":"temperature",
                                "value":"${temp.temperature}"
                            }
                        ]
                    }
                """.trimIndent()
        }
        TYPE_HEARTRATE->{
            val temp = gson().fromJson(measurement.result, HeartRateModel::class.java)

            return  """
                    {
                        "device_id":"${measurement.device_id}",
                        "type":"${measurement.type}",
                        "asset":"",
                        "created_at":"${measurement.created_at}",
                        "test_method":"${measurement.test_method}",
                        "result":[
                            {
                                "name":"hr",
                                "value":"${temp.hrperminute}"
                            }
                        ]
                    }
                """.trimIndent()
        }
        TYPE_BPM->{
            val temp = gson().fromJson(measurement.result, BloodPressureModel::class.java)

            return  """
                    {
                        "device_id":"${measurement.device_id}",
                        "type":"${measurement.type}",
                        "asset":"",
                        "created_at":"${measurement.created_at}",
                        "test_method":"${measurement.test_method}",
                        "result":[
                            {
                                "name":"systole",
                                "value":"${temp.systole}"
                            },
                            {
                                "name":"diastole",
                                "value":"${temp.diastole}"
                            },
                            {
                                "name":"hr",
                                "value":"${temp.pulse}"
                            }
                        ]
                    }
                """.trimIndent()
        }
        TYPE_BMI->{
            val temp = gson().fromJson(measurement.result, BMIModel::class.java)

            return  """
                    {
                        "device_id":"${measurement.device_id}",
                        "type":"${measurement.type}",
                        "asset":"",
                        "created_at":"${measurement.created_at}",
                        "test_method":"${measurement.test_method}",
                        "result":[
                            {
                                "name":"height",
                                "value":"${temp.height}"
                            },
                            {
                                "name":"weight",
                                "value":"${temp.weight}"
                            },
                            {
                                "name":"bmi",
                                "value":"${temp.bmi}"
                            }
                        ]
                    }
                """.trimIndent()
        }
        TYPE_HEIGHT->{
            val temp = gson().fromJson(measurement.result, HeightModel::class.java)

            return  """
                    {
                        "device_id":"${measurement.device_id}",
                        "type":"${measurement.type}",
                        "asset":"",
                        "created_at":"${measurement.created_at}",
                        "test_method":"${measurement.test_method}",
                        "result":[
                            {
                                "name":"height",
                                "value":"${temp.height}"
                            }
                        ]
                    }
                """.trimIndent()
        }
        TYPE_WEIGHT->{
            val temp = gson().fromJson(measurement.result, WeightModel::class.java)

            return  """
                    {
                        "device_id":"${measurement.device_id}",
                        "type":"${measurement.type}",
                        "asset":"",
                        "created_at":"${measurement.created_at}",
                        "test_method":"${measurement.test_method}",
                        "result":[
                            {
                                "name":"weight",
                                "value":"${temp.weight}"
                            }
                        ]
                    }
                """.trimIndent()
        }
        TYPE_WAIST->{
            val temp = gson().fromJson(measurement.result, WaistModel::class.java)

            return  """
                    {
                        "device_id":"${measurement.device_id}",
                        "type":"${measurement.type}",
                        "asset":"",
                        "created_at":"${measurement.created_at}",
                        "test_method":"${measurement.test_method}",
                        "result":[
                            {
                                "name":"waist",
                                "value":"${temp.waist}"
                            }
                        ]
                    }
                """.trimIndent()
        }
        TYPE_SPO2->{
            val temp = gson().fromJson(measurement.result, BloodOxygenModel::class.java)

            return  """
                    {
                        "device_id":"${measurement.device_id}",
                        "type":"${measurement.type}",
                        "asset":"",
                        "created_at":"${measurement.created_at}",
                        "test_method":"${measurement.test_method}",
                        "result":[
                            {
                                "name":"spo2",
                                "value":"${temp.spo2}"
                            }
                        ]
                    }
                """.trimIndent()
        }
        TYPE_RESPIRATION->{
            val temp = gson().fromJson(measurement.result, RespirationModel::class.java)

            return  """
                    {
                        "device_id":"${measurement.device_id}",
                        "type":"${measurement.type}",
                        "asset":"",
                        "created_at":"${measurement.created_at}",
                        "test_method":"${measurement.test_method}",
                        "result":[
                            {
                                "name":"respiration",
                                "value":"${temp.breathPerminute}"
                            }
                        ]
                    }
                """.trimIndent()
        }
        TYPE_SLEEP->{
            val temp = gson().fromJson(measurement.result, SleepModel::class.java)

            return  """
                    {
                        "device_id":"${measurement.device_id}",
                        "type":"${measurement.type}",
                        "asset":"",
                        "created_at":"${measurement.created_at}",
                        "test_method":"${measurement.test_method}",
                        "result":[
                            {
                                "name":"lightsleep",
                                "value":"${temp.lightSleepCount}"
                            },
                            {
                                "name":"deepsleep",
                                "value":"${temp.deepSleepCount}"
                            },
                            {
                                "name":"sleepduration",
                                "value":"${temp.hours}.${temp.minutes}"
                            }
                        ]
                    }
                """.trimIndent()
        }
        TYPE_STETHOSCOPE->{
            return  """
                    {
                        "device_id":"${measurement.device_id}",
                        "type":"${measurement.type}",
                        "asset":"${measurement.result}",
                        "created_at":"${measurement.created_at}",
                        "test_method":"${measurement.test_method}",
                        "result":[]
                    }
                """.trimIndent()
        }
        else->{ return """""".trimIndent()}
    }
}
fun gson():Gson = Gson()

fun String.postMeasurement(type:String):String{
    return "$this/v2/${type}/record-measurement"
}
fun String.loginNurse():String{
    return "$this/login/nurse"
}
fun String.getAllUsers():String{
    return "$this/get/patient"
}
