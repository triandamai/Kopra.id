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
   ```
2. `analisis`/`analytics` menggunakan prefix `analyze` misal untuk BPM `analyzeBPM(systole,diastole)` dan memberikan nilai kembali `Result`
   ```kotlin
    data class Result(var color:Int?,var result: String,var status:Any?) 
   ```
3. `utility` (masih dalam tahap perancangan )