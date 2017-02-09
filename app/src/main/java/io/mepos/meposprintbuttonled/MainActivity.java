package io.mepos.meposprintbuttonled;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.uniquesecure.meposconnect.MePOSConnectionType;
import com.uniquesecure.meposconnect.MePOSReceipt;

import persistence.MePOSSingleton;
import printer.ReceiptBuilder;

public class MainActivity extends MePOSAbstractActivity {
    Button mBtnPrintLED;
    EditText mIpText;
    CheckBox mPrintWifiBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnPrintLED = (Button) findViewById(R.id.btnPrintLED);
        mIpText = (EditText) findViewById(R.id.ipText);
        mPrintWifiBox = (CheckBox) findViewById(R.id.printWifiBox);


        mBtnPrintLED.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MePOSReceipt receipt = new ReceiptBuilder(MainActivity.this).getShortReceipt();
                MePOSSingleton.getInstance().print(receipt, MainActivity.this);
                Toast.makeText(MainActivity.this, R.string.printing_message, Toast.LENGTH_SHORT).show();
            }
        });

        mPrintWifiBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPrintWifiBox.isChecked()){
                    Toast.makeText(MainActivity.this, R.string.wifi, Toast.LENGTH_SHORT).show();
                    String ipAddress = mIpText.getText().toString();
                    MePOSSingleton.createInstance(getApplicationContext(), MePOSConnectionType.WIFI);
                    MePOSSingleton.getInstance().getConnectionManager().setConnectionIPAddress(ipAddress);
                }else {
                    createUSB();
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        createUSB();
    }

    protected void onStop() {
        super.onStop();
    }

    public void createUSB (){
        MePOSSingleton.createInstance(getApplicationContext(), MePOSConnectionType.USB);
    }

}
