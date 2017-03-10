package io.mepos.meposprintbuttonled;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;

import com.uniquesecure.meposconnect.MePOSColorCodes;
import com.uniquesecure.meposconnect.MePOSConnectionType;
import com.uniquesecure.meposconnect.MePOSException;
import com.uniquesecure.meposconnect.MePOSPrinterCallback;

import java.util.HashMap;

import persistence.MePOSSingleton;

public abstract class MePOSAbstractActivity extends AppCompatActivity implements MePOSPrinterCallback {
    CheckBox mCheckbox;

    public static final int MEPOS_VENDOR_ID = 11406;
    public static final int MEPOS_PRODUCT_ID = 9220;

    private static final String TAG = MePOSAbstractActivity.class.getSimpleName();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onPrinterStarted(MePOSConnectionType mePOSConnectionType, String s) {
        MePOSSingleton.getInstance().setCosmeticLedCol(MePOSColorCodes.COSMETIC_RED);
        Snackbar.make(findViewById(android.R.id.content), R.string.print_started, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onPrinterCompleted(MePOSConnectionType mePOSConnectionType, String s) {
        MePOSSingleton.getInstance().setCosmeticLedCol(MePOSColorCodes.COSMETIC_GREEN);
        try {
            Thread.sleep(1000);
            MePOSSingleton.getInstance().setCosmeticLedCol(MePOSColorCodes.LED_OFF);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Snackbar.make(findViewById(android.R.id.content), R.string.print_completed, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onPrinterError(MePOSException e) {
        Snackbar.make(findViewById(android.R.id.content), R.string.print_error, Snackbar.LENGTH_SHORT).show();
    }

    protected boolean isAMePOS(UsbDevice device) {
        return device.getVendorId() == MEPOS_VENDOR_ID &&
                device.getProductId() == MEPOS_PRODUCT_ID;
    }

    protected void findMePOSUSB() {
        UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);

        HashMap<String, UsbDevice> deviceList = manager.getDeviceList();
        for (UsbDevice device : deviceList.values()) {
            if (isAMePOS(device)) {
                MePOSSingleton.createInstance(getApplicationContext(), MePOSConnectionType.USB);
                MePOSSingleton.lastStateUsbAttached = true;
            }
        }
    }

}
