## App

1. `MainApplication.kt` Adalah `EntryPoint` dari aplikasi
2. `MainActivity.kt` Adalah halaman utama dari aplikasi dimana didalamnya menggunakan navigation dari Jetpack Compose
    Untuk masing-masing halaman tersedia di folder [`pages`](src/main/java/com/trian/module/ui/pages)
3. `MainViewModel` Adalah Tempat penyedia data yang dibutuhkan UI dan proses yang tidak membutuhkan campur tangan UI
    Penggunaanya cukup dengan
   ```kotlin 
           private val viewModel:MainViewModel  by viewModels()
   ```
    Property viewmodel akan otomatis di-`Inject` oleh Dependency Injection
4. Untuk Cara Penggunaan Dependency Injection 

   Cara lama:
   ```kotlin
    lateint var gson = Gson()
    ```
   Menjadi:
   ```kotlin
        @Inject
        lateint var gson:Gson
    ```
   Component yang sudah disediakan Dpeendency Injection
    ```kotlin
        //local database
        var cexupDatabase:CexupDatabase
        //coroutine context
        var dispatcherProvider:DispatcherProvider
        //network 
   var appRemoteDataSource:AppRemoteDataSource
    //network logging
   var httpLoggingInterceptor:HttpLoggingInterceptor
   //okhttp client
   var provideOkhttpClient:OkHttpClient
   var provideRetrofit:Retrofit
   var provideAppApiService:AppApiServices
   
   //data
   var provideRepository:CexupRepository
    ```