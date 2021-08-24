# CEXUP_SDK
this module contains utility for cexup-mobile app

ada 4 komponen utama pada module ini

- `data` yang kemudian disebut `storage` berisi persistence,local database,data model
- `service` sebagai jembatan antara aplikasi dan api backend(networking)
- `analisis` yang kemudian disebut `analytics` berisi tools untuk menganalisa hasil dari pemeriksaan sperti bpm,oxi,dll
- `utility` berisi tools untuk keperluan konektivitas aplikasi seperti convert tanggal,id sdk bluetooth,tipe-tipe perangkat yang tersedia dll

untuk mengakses 4 komponen dibagi menjadi 3:
1. `data`,`service` menggunakan `CexupRepository.kt` (karena ada sinkronisasi antara 2 komponen ini)
   ```kotlin
      val repository = CexupRepository(datastorage,persistence)
   
      //method yang tersedia semua return suspend/coroutine
       repository.login(username:String,password:String,result:(success:Boolean,nurse:Nurse)->Unit)
       repository.getListUsers(result:(patient:List<Patient>?)->Unit)
       repository.saveMeasurement(measurement: Measurement, type:String,result:(success:Boolean,message:String)->Unit)
       repository.saveMeasurement(measurements: List<Measurement>, type:String,result:(success:Boolean,message:String)->Unit)
   ```
2. `analisis`/`analytics` menggunakan prefix `analyze` misal untuk BPM `analyzeBPM(systole,diastole)` dan memberikan nilai kembali `Result`
   ```kotlin
    data class Result(var color:Int?,var result: String,var status:Any?) 
    
    //contoh
    val analyze = analyzeBPM(121,85) //123 = systole,85 = diastole
    
    print("${analyze.result}")
   ```
3. `utility` (masih dalam tahap perancangan )
