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
    lateinit var gson = Gson()
    ```
   Menjadi:
   ```kotlin
        @Inject
        lateinit var gson:Gson
    ```
   Component yang sudah disediakan Dependency Injection dari module `data`
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
   
5. Alur Aplikasi
    Setiap halaman/component untuk memproses atau mngembil data maka harus menggunakan `ViewModel` dari view model yang akan meneruskan ke masing-masiing module yang diperlukan
   - Untuk mengambil data baik dari local maupun dari internet/backend
 
     di dalam view model:
    ```kotlin
    @HiltViewModel
    class MainViewModel @Inject constructor(
    private val cexupRepository: ICexupRepository
    ) :ViewModel(){
    
          //fungsi mengambil data  
        fun getUsers(){
           cexuprepository.getListUsers()
        }
   }

   ```
    maka di activity/component
   ```kotlin
        @AndroidEntryPoint
        class MainActivity : ComponentActivity() {
            //inject viewmodel
            private val viewModel: MainViewModel  by viewModels()
        
            @ExperimentalPagerApi
            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContent {

                }
            } 
   
            fun ambilData(){
                viewModel.getUsers()
            }
        }

   ```
   di component compose:
    ```kotlin
        @Composable
        fun Greeting(viewModel:MainViewModel) {
           
             Button(onClick =  {viewModel.getUsers()}) {
                Text(text = "Login")
            }
        }
   ```