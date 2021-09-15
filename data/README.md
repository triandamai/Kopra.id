## Data
Module ini menyediakan semua kebutuhan yang berhubungan dengan data baik data dari local storage,backend,maupun persistence

komponent utama pada module ini
1. `di` a.k.a Dependency injection berisi komponen yang mnyediakan object untuk seluruh aplikasi
2. data berisi komponen untuk kebutuhan dari baik dari local maupun backend setiap komponen maupun halaman yang berhubungan dengan UI akan 
   menggunakan module ini sebagai penyedia datanya
   Contoh:
   ```kotlin
        //untuk mengambil data atau mngirim data cukup dengan
        //inject repository
        val cexupRepository:CexupRepository
       //panggil methodnya
       cexupRepository.getListUsers()
   ```
   untuk akses data cukup dengan repository tersebut

3. measurement

   
