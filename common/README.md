# CEXUP_SDK
this module contains utility for cexup-mobile app

module ini berisi:
1. `utility` menyediakan static method,extension method untuk kebutuhan analisis,perhitungan dsb yang berhubungan dengan utility
   File `Analyze.kt`
   ```kotlin
      //contoh
     val spo2:Float = 0f
   
        //contoh
        //pemakaian
      val result:Result = spo2.analyzeSpo2()

      val analyze = analyzeBPM(121,85) //123 = systole,85 = diastole
    
      print("${analyze.result}")
      print("${result.result}")
    
        //type data dari Result
        data class Result(var color:Int?,var result: String,var status:Any?) 
      ```
2. `route` berisi object untuk kebutuhan navigation pada aplikasi
   File `MainAppRouteName.kt`
   ```kotlin
        enum class Routes{
         SPLASH,
         ONBOARD,
         DASHBOARD,
         LOGIN,
         REGISTER
        }
   ```
3. coming soon
