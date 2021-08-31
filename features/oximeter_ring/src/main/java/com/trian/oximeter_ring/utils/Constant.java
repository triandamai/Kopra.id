package com.trian.oximeter_ring.utils;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Environment;

import com.xtremeprog.sdk.ble.LogUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Timer;

public class Constant {
    public static final String EXTRA_DEVICE = "no.nordicsemi.android.blinky.EXTRA_DEVICE";


    /**
     * initialize directory
     */
    public static void initDir(Context context) {
        root = Environment.getExternalStorageDirectory();
        if (root == null) {
            root = Environment.getDataDirectory();
        }

        pic_dir = new File(Environment.getExternalStorageDirectory(), "DCIM/Camera");
        if (!pic_dir.exists()) {
            pic_dir.mkdir();
        }

        dir = new File(root, "ePlusebitO2/");
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                LogUtils.d("Create dir failed");
            }
        }
        LogUtils.d("Current dir:" + dir.toString());
    }

    // Local directory
    public static File root;
    public static File dir;
    public static File pic_dir;
    //url
    public final static String VERSION_URL = "http://api.viatomtech.com.cn/update";
    public final static String LOGIN_URL = "https://cloud.bodimetrics.com/user/login";
    public final static String CLOUD_URL = "https://cloud.bodimetrics.com/register";
    public final static String ACCESS_URL = "https://cloud.bodimetrics.com";
    public final static String MEDICAL_ID_SEARCH_URL = "https://cloud.bodimetrics.com/fhir/patient/search/";
    public final static String PATIENT_RESOURCE_URL = "https://cloud.bodimetrics.com/fhir/patient";
    public final static String SYSTEM_URL = "https://cloud.bodimetrics.com/fhir";
    public final static String OBSERVATION_URL = "https://cloud.bodimetrics.com/fhir/observation";

    public final static int CONNECT_TIMEOUT = 60 * 1000;
    public final static String SSL_NAME = "99952fe2cf4144c9.crt";

    //BLEConnectState
    public static boolean connected = false;

    public static boolean isReConnect = true;
    public static boolean reConnectEvent = false;

    public static boolean requestFailed = false;

    public static boolean writeSuccess = false;
    public static boolean notifySuccess = false;

    public static boolean switchClick = false;

    public static boolean intensityClick = false;
    public static boolean thresholdClick = false;
    public static boolean getInfoClick = false;
    public static boolean dashBoardClick = false;

    public static byte uploading = 0;


    public static Timer batteryTimer;


    //GATT_MAX_DATA_length
    public final static int GATT_MAX_DATA_LENGTH = 20;
    public final static int GATT_NEW_MAX_DATA_LENGTH = 50;

    //Eventbus event type
    public final static int EVENT_START_READ = 2001;
    public final static int REQUEST_TURN_ON_BT = 2002;
    //BLEDevice
    public static BluetoothDevice sDevice;
    public static String sDeviceAddress;
    //O2Device
    public static String deviceInfo;

    public static void destroyVail() {
        root = null;
        dir = null;
        pic_dir = null;
        sDevice = null;
        sDeviceAddress = null;
        sCurMotor = null;
        sCurOxiThr = null;
        sCurPedtar = null;
        sCurTime = null;
    }

    //DATAStatus
    public static int status;
    public final static int GET_INFO = 1;
    public final static int START_READ = 2;
    public final static int END_READ = 3;
    public final static int READ_CONTENT = 4;
    public final static int START_UPDATE = 5;
    public final static int END_UPDATE = 6;
    public final static int UPDATE_CONTENT = 7;
    public final static int PARA_SYNC_MOTOR = 8;
    public final static int PARA_SYNC_OXI_THR = 9;
    public final static int PARA_SYNC_PED_TAR = 10;
    public final static int PARA_SYNC_SET_TIME = 11;
    public final static int PARA_INSTANT = 12;
    public final static int DATA_RESET = 13;
    public final static int BURN_SN = 14;
    public final static int LOCK_FLASH = 15;

    //BLErequst remark
    public final static String WRITE_INFO = "write info";
    public final static String WRITE_START_READ = "write start read";
    public final static String WRITE_READ_CONTENT = "write read content";
    public final static String WRITE_END_READ = "write end read";
    public final static String WRITE_PARA_SYNC = "write para sync";
    public final static String WRITE_START_UPDATE = "write start update";
    public final static String WRITE_END_UPDATE = "write end update";
    public final static String WRITE_UPDATE_CONTENT = "write update content";
    public final static String WRITE_PARA_INSTANT = "write para instant";
    public final static String WRITE_DATA_RESET = "write data reset";
    public final static String WRITE_LOCK_FLASH = "write lock flash";
    public final static String WRITE_SN = "write SN&&hardware version";

    //sharedPreferencce name
    public final static String DEVICE_ADDRESS = "device_address";
    public final static String CURRENT_DEVICE_NAME = "current_device_name";
    public final static String CURRENT_DEVICE_SN = "current_device_SN";
    public final static String CURRENT_DEVICE_POWER = "current_device_power";

    public final static String CURRENT_EMAIL = "email";
    public final static String CURRENT_USER_ID = "user_id";
    public final static String CURRENT_PATIENT_ID = "patientId";
    public final static String CURRENT_USER_NAME = "name";
    public final static String CURRENT_PASSWORD = "password";
    public final static String CURRENT_MEDICAL_ID = "medicalId";
    public final static String CURRENT_GENDER = "gender";
    public final static String CURRENT_HEIGHT = "height";
    public final static String CURRENT_HEIGHT_LEFT = "height_left";
    public final static String CURRENT_HEIGHT_RIGHT = "height_right";
    public final static String CURRENT_WEIGHT = "weight";
    public final static String CURRENT_BIRTHDAY = "birthday";
    public final static String CURRENT_BIRTHDAY_Y = "year";
    public final static String CURRENT_BIRTHDAY_M = "month";
    public final static String CURRENT_BIRTHDAY_D = "day";
    public final static String CURRENT_STEP_SIZE = "stepSize";
    public final static String AUTO_SYNC = "auto_sync";

    public final static String CODE_LOCK_SWITCH = "code_lock_switch";
    public final static String LOCKED_BRANCHCODE = "locked_branchcode";

    public final static SimpleDateFormat SDF =
            new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    public final static SimpleDateFormat SDF_Y =
            new SimpleDateFormat("yyyy", Locale.getDefault());
    public final static SimpleDateFormat SDF_M =
            new SimpleDateFormat("MM", Locale.getDefault());
    public final static SimpleDateFormat SDF_D =
            new SimpleDateFormat("dd", Locale.getDefault());
    //SQLite name
    public final static String DB_ADDRESS = "PlusebitO2.db";

    //fileDownLoadNum
    public static int sFileNum = -1;
    // O2 detail
    public final static byte O2_INFO_O2 = 1;
    public final static byte O2_INFO_DROPS = 2;
    public final static byte O2_INFO_LOWEST_SPO2 = 3;
    public final static byte O2_INFO_SPO2 = 4;
    public final static byte O2_INFO_HR = 5;
    public final static byte O2_INFO_STEPS = 6;
    //device mode
    public final static byte SLEEP_MODE = 0;
    public final static byte FITNESS_MODE = 1;

    //Message
    public final static int MSG_GET_VERSION = 1002;
    public final static int MSG_UPDATE_AVAILABLE = 1003;
    public final static int MSG_DOWNLOAD_PART_FINISH = 1004;
    public final static int MSG_DOWNLOAD_FAILED = 1005;
    public final static int MSG_SHOW_CANT_UPDATE_IN_OFFLINE = 1006;
    public final static int MSG_SHOW_UPDATE_SUCCESS = 1007;
    public final static int MSG_FLUSH_HR = 1008;
    public final static int MSG_FLUSH_BV = 1009;
    public final static int MSG_SHARE_CLICKED = 1010;
    public final static int MSG_SAVE_BMP_SUCCESS = 1011;
    public final static int MSG_SAVE_BMP_FAILED = 1012;
    public final static int MSG_CHOOSE_DEVICE = 1013;
    public final static int MSG_DEVICE_FOUND = 1014;

    public final static int MSG_BNSETTINGS_CLICKED = 1019;
    public final static int MSG_BNABOUT_APP_CLICKED = 1020;
    public final static int MSG_BNSPOT_CLICKED = 1021;

    public final static String SET_MOTOR = "SetMotor";
    public final static String SET_PEDTAR = "SetPedtar";
    public final static String SET_OXI_THR = "SetOxiThr";
    public final static String SET_TIME = "SetTIME";
    //当前参数值
    public static String sCurMotor;
    public static String sCurOxiThr;
    public static String sCurPedtar;
    public static String sCurTime;


}
