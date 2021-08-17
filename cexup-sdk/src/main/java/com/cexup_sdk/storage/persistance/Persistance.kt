package library.storage.persistance


import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import library.storage.room.entity.Nurse
import library.storage.room.entity.Patient

@SuppressLint("CommitPrefEdits")
class Persistance(context: Context) {
    private var sharedPreferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null
    private val gson = Gson()

    companion object{
        const val key_loading ="loading"
        const val key_message ="message";
        const val key_islogggedin ="isloggedin"
        const val key_current_multiparameter="multiparameter_id"
        const val key_current_wave ="current_wave"

    }


    init {
        if (sharedPreferences == null) {
            sharedPreferences =
                context.getSharedPreferences("xaxsasd", Context.MODE_PRIVATE)
        }
        editor = sharedPreferences!!.edit()
    }

    fun setLogin(isloggedin:Boolean){
        editor!!.putBoolean(key_islogggedin,isloggedin)
        editor!!.apply()
    }
    fun getLogin():Boolean{
        return sharedPreferences!!.getBoolean(key_islogggedin,false)
    }
    private val key_current_nurse ="nurse";
    fun setCurrentNurse(nurse: Nurse){
        editor!!.putString(key_current_nurse,gson.toJson(nurse))
        editor!!.apply()
    }
    fun currentNurse(): Nurse? {
        val data = sharedPreferences!!.getString(key_current_nurse,null) ?: return null;

        val nurse: Nurse = gson.fromJson(data, Nurse::class.java);
        return nurse;
    }
    private val key_current_patient ="patient"
    fun setCurrentPatient(patient: Patient){
        editor!!.putString(key_current_patient,gson.toJson(patient))
        editor!!.apply()
    }
    fun currentPatient(): Patient?{
        val data = sharedPreferences!!.getString(key_current_patient,null)?: return null;
        val patient: Patient = gson.fromJson(data, Patient::class.java);
        return patient;
    }
    private val key_token ="token"
    fun setToken(token:String){
        editor!!.putString(key_token,token);
        editor!!.apply()
    }
    fun currentToken():String?{
        return sharedPreferences!!.getString(key_token,null);
    }
    fun setKey(key:String,value:Int){
        editor!!.putInt(key,value)
        editor!!.apply()
    }
    fun setKey(key:String,value:String){
        editor!!.putString(key,value)
        editor!!.apply()
    }
    fun setKey(key:String,value:Boolean){
        editor!!.putBoolean(key,value)
        editor!!.apply()
    }
    fun getKey(key:String,type:Any):Any?{
        when(type){
            is Boolean->{
                return sharedPreferences!!.getBoolean(key,false);
            }
            is String->{
                return sharedPreferences!!.getString(key,null);
            }
            is Int ->{
                return sharedPreferences!!.getInt(key,0);
            }

        }
        return null;
    }


    //TODO("should remove all persistance")
    fun removePatient(){
        editor!!.remove(key_current_patient)
        editor!!.apply()
    }
    fun signOut(){
        editor!!.remove(key_current_nurse)
        editor!!.remove(key_current_patient)
        editor!!.apply()
    }

}