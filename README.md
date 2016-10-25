# MePOS-Print-button-WIFI-USB with decorative LED

# MePOS Connect printing

This is a sample code to quickly integrate the MePOS print feature to your application.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites


* Go to [Mepos.io](http://mepos.io/developers) and register as developer. There you will find the last SDK file for your project.

### Installing

* Prepare you manifest.xml file (necessary for wifi-printing) and include the following line:
```
<uses-permission android:name="android.permission.INTERNET" />
```
* Create a BroadcastReceiver for the USB device.

```
android.hardware.usb.action.USB_DEVICE_ATTACHED
android.hardware.usb.action.USB_DEVICE_DETACHED
```

## Running the tests

1. Create MePOS receipt.
```
public class ReceiptBuilder {

    public MePOSReceipt getShortReceipt() {
        MePOSReceipt receipt = new MePOSReceipt();
        MePOSReceiptTextLine rcpt = new MePOSReceiptTextLine();

        rcpt.setText("RECEIPT", MePOS.TEXT_STYLE_BOLD, MePOS.TEXT_SIZE_NORMAL, MePOS.TEXT_POSITION_CENTER);
        receipt.addLine(rcpt);

    return receipt;
    }
}
```

2. In order to control the UI messages and set the Cosmetic LED colors during the printing cycle, implement MePOSPrinterCallback on your printing activity and its methods.

```
@Override
   public void onPrinterStarted(MePOSConnectionType mePOSConnectionType, String s) {
   mePOS.setCosmeticLedCol(MePOSColorCodes.COSMETIC_RED);   
       // some message
   }
   @Override
   public void onPrinterCompleted(MePOSConnectionType mePOSConnectionType, String s) {
   mePOS.setCosmeticLedCol(MePOSColorCodes.COSMETIC_GREEN);
       // some message
   }
   @Override
   public void onPrinterError(MePOSException e) {
       // some message
   }   
   
```

###### Note.- You can select from seven diferent colors.
* MePOSColorCodes.COSMETIC_GREEN
* MePOSColorCodes.COSMETIC_BLUE
* MePOSColorCodes.COSMETIC_CYAN
* MePOSColorCodes.COSMETIC_RED
* MePOSColorCodes.COSMETIC_YELLOW
* MePOSColorCodes.COSMETIC_MAGENTA
* MePOSColorCodes.COSMETIC_WHITE
* MePOSColorCodes.COSMETIC_OFF

3. Creating a USB MePOS instance.
```
MePOS mePOS = new MePOS(context, MePOSConnectionType.USB);
```
4. Finally send to the printer the receipt previously created.
```
MePOSReceipt receipt = new ReceiptBuilder().getShortReceipt();
mePOS.print(receipt);
```
5. For wifi printing create a wifi instance as follows:
```
MePOS mePOS = new MePOS(context, MePOSConnectionType.WIFI);
mePOS.getConnectionManager().setConnectionIPAddress("192.168.1.64");
```


### About the test

The code on this repository is an example from the SDK and for MePOS developers willing to implement it.

## Reference

* [Android SDK guide](http://mepos.io/) - Developers section.


## Contact

Please send us an email: us.unique.secure@gmail.com
