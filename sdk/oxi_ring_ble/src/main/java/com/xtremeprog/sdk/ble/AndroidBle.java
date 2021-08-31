/**
 * This XPG software is supplied to you by Xtreme Programming Group, Inc.
 * ("XPG") in consideration of your agreement to the following terms, and your
 * use, installation, modification or redistribution of this XPG software
 * constitutes acceptance of these terms.锟� If you do not agree with these terms,
 * please do not use, install, modify or redistribute this XPG software.
 * <p>
 * In consideration of your agreement to abide by the following terms, and
 * subject to these terms, XPG grants you a non-exclusive license, under XPG's
 * copyrights in this original XPG software (the "XPG Software"), to use and
 * redistribute the XPG Software, in source and/or binary forms; provided that
 * if you redistribute the XPG Software, with or without modifications, you must
 * retain this notice and the following text and disclaimers in all such
 * redistributions of the XPG Software. Neither the name, trademarks, service
 * marks or logos of XPG Inc. may be used to endorse or promote products derived
 * from the XPG Software without specific prior written permission from XPG.锟�
 * Except as expressly stated in this notice, no other rights or licenses,
 * express or implied, are granted by XPG herein, including but not limited to
 * any patent rights that may be infringed by your derivative works or by other
 * works in which the XPG Software may be incorporated.
 * <p>
 * The XPG Software is provided by XPG on an "AS IS" basis.锟� XPG MAKES NO
 * WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE IMPLIED
 * WARRANTIES OF NON-INFRINGEMENT, MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, REGARDING THE XPG SOFTWARE OR ITS USE AND OPERATION ALONE OR IN
 * COMBINATION WITH YOUR PRODUCTS.
 * <p>
 * IN NO EVENT SHALL XPG BE LIABLE FOR ANY SPECIAL, INDIRECT, INCIDENTAL OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) ARISING IN ANY WAY OUT OF THE USE, REPRODUCTION, MODIFICATION
 * AND/OR DISTRIBUTION OF THE XPG SOFTWARE, HOWEVER CAUSED AND WHETHER UNDER
 * THEORY OF CONTRACT, TORT (INCLUDING NEGLIGENCE), STRICT LIABILITY OR
 * OTHERWISE, EVEN IF XPG HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * <p>
 * ABOUT XPG: Established since June 2005, Xtreme Programming Group, Inc. (XPG)
 * is a digital solutions company based in the United States and China. XPG
 * integrates cutting-edge hardware designs, mobile applications, and cloud
 * computing technologies to bring innovative products to the marketplace. XPG's
 * partners and customers include global leading corporations in semiconductor,
 * home appliances, health/wellness electronics, toys and games, and automotive
 * industries. Visit www.xtremeprog.com for more information.
 * <p>
 * Copyright (C) 2013 Xtreme Programming Group, Inc. All Rights Reserved.
 */

package com.xtremeprog.sdk.ble;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.xtremeprog.sdk.ble.BleRequest.RequestType;

import org.apache.commons.codec.binary.Hex;

@SuppressLint("NewApi")
public class AndroidBle implements IBle, IBleRequestHandler {

    protected static final String TAG = "blelib";

    private BleService mService;
    private BluetoothAdapter mBtAdapter;
    private Map<String, BluetoothGatt> mBluetoothGatts;
    // private BTQuery btQuery;

    private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(final BluetoothDevice device, int rssi,
                             byte[] scanRecord) {
            mService.bleDeviceFound(device, rssi, scanRecord,
                    BleService.DEVICE_SOURCE_SCAN);
        }
    };

    private BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status,
                                            int newState) {
            String address = gatt.getDevice().getAddress();
            LogUtils.d("onConnectionStateChange " + address + " status "
                    + status + " newState " + newState);
            if (status != BluetoothGatt.GATT_SUCCESS) {
                disconnect(address);
                mService.bleGattDisConnected(address);
                return;
            }

            if (newState == BluetoothProfile.STATE_CONNECTED) {
                mService.bleGattConnected(gatt.getDevice());
                mService.addBleRequest(new BleRequest(
                        RequestType.DISCOVER_SERVICE, address));
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                mService.bleGattDisConnected(address);
                disconnect(address);
            }
        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            String address = gatt.getDevice().getAddress();
            LogUtils.d("onServicesDiscovered " + address + " status " + status);
            if (status != BluetoothGatt.GATT_SUCCESS) {
                mService.requestProcessed(address,
                        RequestType.DISCOVER_SERVICE, false);
//                mService.addBleRequest(new BleRequest(
//                        RequestType.DISCOVER_SERVICE, address));
                return;
            }
            //修改MTU
//            if (Build.VERSION.SDK_INT >= 21) {
//                gatt.requestMtu(53);
//            }
            mService.bleServiceDiscovered(gatt.getDevice().getAddress());
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt,
                                         BluetoothGattCharacteristic characteristic, int status) {
            String address = gatt.getDevice().getAddress();
            LogUtils.d("onCharacteristicRead " + address + " status " + status);
            if (status != BluetoothGatt.GATT_SUCCESS) {
                mService.requestProcessed(address,
                        RequestType.READ_CHARACTERISTIC, false);
                return;
            }
            // Log.d(TAG, "data " + characteristic.getStringValue(0));
            mService.bleCharacteristicRead(gatt.getDevice().getAddress(),
                    characteristic.getUuid().toString(), status,
                    characteristic.getValue());
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt,
                                            BluetoothGattCharacteristic characteristic) {
            String address = gatt.getDevice().getAddress();
            LogUtils.d("onCharacteristicChanged " + address);
            LogUtils.d(new String(Hex.encodeHex(characteristic.getValue())));
            mService.bleCharacteristicChanged(address, characteristic.getUuid()
                    .toString(), characteristic.getValue());
        }

        public void onCharacteristicWrite(BluetoothGatt gatt,
                                          BluetoothGattCharacteristic characteristic, int status) {
            String address = gatt.getDevice().getAddress();
            LogUtils.d("onCharacteristicWrite " + address + " status " + status);
            if (status != BluetoothGatt.GATT_SUCCESS) {
                mService.requestProcessed(address,
                        RequestType.WRITE_CHARACTERISTIC, false);
                return;
            }
            mService.bleCharacteristicWrite(gatt.getDevice().getAddress(),
                    characteristic.getUuid().toString(), status);
        }

        ;

        public void onDescriptorWrite(BluetoothGatt gatt,
                                      BluetoothGattDescriptor descriptor, int status) {
            String address = gatt.getDevice().getAddress();
            LogUtils.d("onDescriptorWrite " + address + " status " + status);
            BleRequest request = mService.getCurrentRequest();
            if (request.type == RequestType.CHARACTERISTIC_NOTIFICATION
                    || request.type == RequestType.CHARACTERISTIC_INDICATION
                    || request.type == RequestType.CHARACTERISTIC_STOP_NOTIFICATION) {
                if (status != BluetoothGatt.GATT_SUCCESS) {
                    mService.requestProcessed(address,
                            RequestType.CHARACTERISTIC_NOTIFICATION, false);
                    return;
                }
                if (request.type == RequestType.CHARACTERISTIC_NOTIFICATION) {
                    mService.bleCharacteristicNotification(address, descriptor
                                    .getCharacteristic().getUuid().toString(), true,
                            status);
                } else if (request.type == RequestType.CHARACTERISTIC_INDICATION) {
                    mService.bleCharacteristicIndication(address, descriptor
                            .getCharacteristic().getUuid().toString(), status);
                } else {
                    mService.bleCharacteristicNotification(address, descriptor
                                    .getCharacteristic().getUuid().toString(), false,
                            status);
                }
                return;
            }
        }

        ;

//        @Override
//        public void onMtuChanged(BluetoothGatt gatt, int mtu, int status) {
//            super.onMtuChanged(gatt, mtu, status);
//            LogUtils.d("MTU:" + mtu + ";status:" + status);
//            String address = gatt.getDevice().getAddress();
//            if (status != BluetoothGatt.GATT_SUCCESS&&Build.VERSION.SDK_INT>=21) {
//                gatt.requestMtu(53);
//                return;
//            }
//            mService.bleMTUChanged(address, mtu, status);
//        }
    };


    public AndroidBle(BleService service) {
        mService = service;
        // btQuery = BTQuery.getInstance();
        if (!mService.getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_BLUETOOTH_LE)) {
            mService.bleNotSupported();
            return;
        }

        final BluetoothManager bluetoothManager = (BluetoothManager) mService
                .getSystemService(Context.BLUETOOTH_SERVICE);

        mBtAdapter = bluetoothManager.getAdapter();
        if (mBtAdapter == null) {
            mService.bleNoBtAdapter();
        }
        mBluetoothGatts = new HashMap<String, BluetoothGatt>();
    }

    @Override
    public void startScan() {
//        if (mBtAdapter==null){
//            mService.bleNoBtAdapter();
//        }else if (mBtAdapter.getState()!=BluetoothAdapter.STATE_ON){
//            mBtAdapter.enable();
//        }
        mBtAdapter.startLeScan(mLeScanCallback);
    }

    @Override
    public void stopScan() {
//        if (mBtAdapter==null){
//            mService.bleNoBtAdapter();
//        }else if (mBtAdapter.getState()!=BluetoothAdapter.STATE_ON){
//            mBtAdapter.enable();
//        }
        mBtAdapter.stopLeScan(mLeScanCallback);
    }

    @Override
    public boolean adapterEnabled() {
        if (mBtAdapter != null) {
            return mBtAdapter.isEnabled();
        }
        return false;
    }

    @Override
    public boolean connect(String address) {
        BluetoothDevice device = mBtAdapter.getRemoteDevice(address);
        BluetoothGatt gatt = device.connectGatt(mService, false, mGattCallback);
        if (!Build.MODEL.equals("MI NOTE LTE")){
            LogUtils.d("Android ble="+Build.MODEL);
            refreshDeviceCache(gatt);
        }
        if (gatt == null) {
            mBluetoothGatts.remove(address);
            return false;
        } else {
            // TODO: if state is 141, it can be connected again after about 15
            // seconds
            mBluetoothGatts.put(address, gatt);
            return true;
        }
    }

    private boolean refreshDeviceCache(BluetoothGatt gatt) {
        try {
            BluetoothGatt localBluetoothGatt = gatt;
            Method localMethod = localBluetoothGatt.getClass().getMethod("refresh", new Class[0]);
            if (localMethod != null) {
                boolean bool = ((Boolean) localMethod.invoke(localBluetoothGatt, new Object[0])).booleanValue();
                return bool;
            }
        } catch (Exception localException) {
            Log.e(TAG, "An exception occured while refreshing device");
        }
        return false;
    }

    @Override
    public void disconnect(String address) {
        if (mBluetoothGatts.containsKey(address)) {
            BluetoothGatt gatt = mBluetoothGatts.remove(address);
            if (gatt != null) {
                //修改
                mService.bleGattDisConnected(address);
                gatt.disconnect();
//                refreshDeviceCache(gatt);
                try {
                    gatt.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                gatt=null;
            }
        }
    }

    @Override
    public ArrayList<BleGattService> getServices(String address) {
        BluetoothGatt gatt = mBluetoothGatts.get(address);
        if (gatt == null) {
            return null;
        }

        ArrayList<BleGattService> list = new ArrayList<BleGattService>();
        List<BluetoothGattService> services = gatt.getServices();
        for (BluetoothGattService s : services) {
            BleGattService service = new BleGattService(s);
            // service.setInfo(btQuery.getGattServiceInfo(s.getUuid()));
            list.add(service);
        }
        return list;
    }

    @Override
    public boolean requestReadCharacteristic(String address,
                                             BleGattCharacteristic characteristic) {
        BluetoothGatt gatt = mBluetoothGatts.get(address);
        if (gatt == null || characteristic == null) {
            return false;
        }

        mService.addBleRequest(new BleRequest(RequestType.READ_CHARACTERISTIC,
                gatt.getDevice().getAddress(), characteristic));
        return true;
    }

    public boolean readCharacteristic(String address,
                                      BleGattCharacteristic characteristic) {
        BluetoothGatt gatt = mBluetoothGatts.get(address);
        if (gatt == null) {
            return false;
        }

        return gatt.readCharacteristic(characteristic.getGattCharacteristicA());
    }

//    private static int retryCount = 0;

    @Override
    public boolean discoverServices(String address) {
        BluetoothGatt gatt = mBluetoothGatts.get(address);
        if (gatt == null) {
            return false;
        }

        boolean ret = gatt.discoverServices();
//        if (!ret&& retryCount< 2) {
//            ++retryCount;
//            mService.addBleRequest(new BleRequest(
//                    RequestType.DISCOVER_SERVICE, address));
//        }
        if (!ret
//                && retryCount == 2
                ) {
//            retryCount = 0;
            disconnect(address);
        }
        return ret;
    }

    @Override
    public BleGattService getService(String address, UUID uuid) {
        BluetoothGatt gatt = mBluetoothGatts.get(address);
        if (gatt == null) {
            return null;
        }

        BluetoothGattService service = gatt.getService(uuid);
        if (service == null) {
            return null;
        } else {
            return new BleGattService(service);
        }
    }

    @Override
    public boolean requestCharacteristicNotification(String address,
                                                     BleGattCharacteristic characteristic) {
        BluetoothGatt gatt = mBluetoothGatts.get(address);
        if (gatt == null || characteristic == null) {
            return false;
        }

        mService.addBleRequest(new BleRequest(
                RequestType.CHARACTERISTIC_NOTIFICATION, gatt.getDevice()
                .getAddress(), characteristic));
        return true;
    }

    @Override
    public boolean characteristicNotification(String address,
                                              BleGattCharacteristic characteristic) {
        BleRequest request = mService.getCurrentRequest();
        BluetoothGatt gatt = mBluetoothGatts.get(address);
        if (gatt == null || characteristic == null) {
            return false;
        }

        boolean enable = true;
        if (request.type == RequestType.CHARACTERISTIC_STOP_NOTIFICATION) {
            enable = false;
        }
        BluetoothGattCharacteristic c = characteristic.getGattCharacteristicA();
        if (!gatt.setCharacteristicNotification(c, enable)) {
            return false;
        }

        BluetoothGattDescriptor descriptor = c
                .getDescriptor(BleService.DESC_CCC);
        if (descriptor == null) {
            return false;
        }

        byte[] val_set = null;
        if (request.type == RequestType.CHARACTERISTIC_NOTIFICATION) {
            val_set = BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE;
        } else if (request.type == RequestType.CHARACTERISTIC_INDICATION) {
            val_set = BluetoothGattDescriptor.ENABLE_INDICATION_VALUE;
        } else {
            val_set = BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE;
        }
        if (!descriptor.setValue(val_set)) {
            return false;
        }

        return gatt.writeDescriptor(descriptor);
    }

    @Override
    public boolean requestWriteCharacteristic(String address,
                                              BleGattCharacteristic characteristic, String remark) {
        BluetoothGatt gatt = mBluetoothGatts.get(address);
        if (gatt == null || characteristic == null) {
            return false;
        }

        mService.addBleRequest(new BleRequest(RequestType.WRITE_CHARACTERISTIC,
                gatt.getDevice().getAddress(), characteristic, remark));
        return true;
    }

    @Override
    public boolean writeCharacteristic(String address,
                                       BleGattCharacteristic characteristic) {
        BluetoothGatt gatt = mBluetoothGatts.get(address);
        if (gatt == null) {
            return false;
        }

        LogUtils.d(new String(Hex.encodeHex(characteristic.getGattCharacteristicA().getValue())));
        LogUtils.d(Arrays.toString(characteristic.getGattCharacteristicA().getValue()));
        return gatt
                .writeCharacteristic(characteristic.getGattCharacteristicA());
    }

    @Override
    public boolean requestConnect(String address) {
        BluetoothGatt gatt = mBluetoothGatts.get(address);
        if (gatt != null && gatt.getServices().size() == 0) {
            return false;
        }

        mService.addBleRequest(new BleRequest(RequestType.CONNECT_GATT, address));
        return true;
    }

    @Override
    public String getBTAdapterMacAddr() {
        if (mBtAdapter != null) {
            return mBtAdapter.getAddress();
        }
        return null;
    }

    @Override
    public boolean requestIndication(String address,
                                     BleGattCharacteristic characteristic) {
        BluetoothGatt gatt = mBluetoothGatts.get(address);
        if (gatt == null || characteristic == null) {
            return false;
        }

        mService.addBleRequest(new BleRequest(
                RequestType.CHARACTERISTIC_INDICATION, gatt.getDevice()
                .getAddress(), characteristic));
        return true;
    }

    @Override
    public boolean requestStopNotification(String address,
                                           BleGattCharacteristic characteristic) {
        BluetoothGatt gatt = mBluetoothGatts.get(address);
        if (gatt == null || characteristic == null) {
            return false;
        }

        mService.addBleRequest(new BleRequest(
                RequestType.CHARACTERISTIC_NOTIFICATION, gatt.getDevice()
                .getAddress(), characteristic));
        return true;
    }
}
