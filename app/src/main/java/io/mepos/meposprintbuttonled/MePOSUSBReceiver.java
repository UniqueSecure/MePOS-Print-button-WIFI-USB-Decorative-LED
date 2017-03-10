package io.mepos.meposprintbuttonled;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.util.Log;
import android.widget.Toast;

import com.uniquesecure.meposconnect.MePOSConnectionType;

import persistence.MePOSSingleton;

public class MePOSUSBReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        UsbDevice device = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
        if (UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action) && isAMePOS(device)) {
            MePOSSingleton.createInstance(context.getApplicationContext(), MePOSConnectionType.USB);
            MePOSSingleton.lastStateUsbAttached = true;
        } else if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action) && isAMePOS(device))  {
            try {
            Toast.makeText(context, "MePOS disconnected", Toast.LENGTH_SHORT).show();
                MePOSSingleton.lastStateUsbAttached = false;
            } catch (Exception e) {
                Log.e("USB", e.getMessage());
            }
            MePOSSingleton.lastStateUsbAttached = true;
        }
    }

    protected boolean isAMePOS(UsbDevice device) {
        return device.getVendorId() == MePOSAbstractActivity.MEPOS_VENDOR_ID &&
                device.getProductId() == MePOSAbstractActivity.MEPOS_PRODUCT_ID;
    }
}
