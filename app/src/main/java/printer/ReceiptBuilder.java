package printer;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.uniquesecure.meposconnect.MePOS;
import com.uniquesecure.meposconnect.MePOSReceipt;
import com.uniquesecure.meposconnect.MePOSReceiptBarcodeLine;
import com.uniquesecure.meposconnect.MePOSReceiptFeedLine;
import com.uniquesecure.meposconnect.MePOSReceiptImageLine;
import com.uniquesecure.meposconnect.MePOSReceiptSingleCharLine;
import com.uniquesecure.meposconnect.MePOSReceiptTextLine;

import java.io.IOException;
import java.io.InputStream;

public class ReceiptBuilder {

    private Context context;

    public ReceiptBuilder(Context context) {
        this.context = context;
    }

    public MePOSReceipt getShortReceipt() {
        MePOSReceipt receipt = new MePOSReceipt();
        MePOSReceiptTextLine rcpt = new MePOSReceiptTextLine();

        rcpt.setText("RECEIPT", MePOS.TEXT_STYLE_BOLD, MePOS.TEXT_SIZE_NORMAL, MePOS.TEXT_POSITION_CENTER);
        receipt.addLine(rcpt);

        receipt.addLine(new MePOSReceiptSingleCharLine('-'));
        receipt.addLine(new MePOSReceiptImageLine(getBitmapFromAsset(context, "ic_launcher.bmp")));


        rcpt = new MePOSReceiptTextLine();
        rcpt.setText("VAT", MePOS.TEXT_STYLE_NONE, MePOS.TEXT_STYLE_NONE, MePOS.TEXT_POSITION_LEFT);
        receipt.addLine(rcpt);
        receipt.addLine(new MePOSReceiptFeedLine(1));

        receipt.addLine(new MePOSReceiptSingleCharLine('-'));
        rcpt = new MePOSReceiptTextLine();
        rcpt.setText("User's name  " + "  Terminal  " + "  Time stamp", MePOS.TEXT_STYLE_NONE, MePOS.TEXT_STYLE_NONE, MePOS.TEXT_POSITION_CENTER);
        receipt.addLine(rcpt);
        receipt.addLine(new MePOSReceiptSingleCharLine('-'));

        rcpt = new MePOSReceiptTextLine();
        rcpt.setText("User name example", MePOS.TEXT_STYLE_NONE, MePOS.TEXT_STYLE_NONE, MePOS.TEXT_POSITION_LEFT);
        receipt.addLine(rcpt);
        receipt.addLine(new MePOSReceiptSingleCharLine('-'));

        rcpt = new MePOSReceiptTextLine();
        rcpt.setText("Order No.                   Table No.", MePOS.TEXT_STYLE_NONE, MePOS.TEXT_STYLE_NONE, MePOS.TEXT_POSITION_CENTER);
        receipt.addLine(rcpt);
        receipt.addLine(new MePOSReceiptSingleCharLine('-'));

        rcpt = new MePOSReceiptTextLine();
        rcpt.setText("1 Corona                      3.60", MePOS.TEXT_STYLE_NONE, MePOS.TEXT_STYLE_NONE, MePOS.TEXT_POSITION_CENTER);
        receipt.addLine(rcpt);

        rcpt = new MePOSReceiptTextLine();
        rcpt.setText("1 Soup of the day             4.55", MePOS.TEXT_STYLE_NONE, MePOS.TEXT_STYLE_NONE, MePOS.TEXT_POSITION_CENTER);
        receipt.addLine(rcpt);

        rcpt = new MePOSReceiptTextLine();
        rcpt.setText("1 Sirlon                     12.00", MePOS.TEXT_STYLE_NONE, MePOS.TEXT_STYLE_NONE, MePOS.TEXT_POSITION_CENTER);
        receipt.addLine(rcpt);

        rcpt = new MePOSReceiptTextLine();
        rcpt.setText("            price override/discount here", MePOS.TEXT_STYLE_NONE, MePOS.TEXT_STYLE_NONE, MePOS.TEXT_POSITION_CENTER);
        receipt.addLine(rcpt);

        rcpt = new MePOSReceiptTextLine();
        rcpt.setText("-Sirlon variants here  +Variant price here", MePOS.TEXT_STYLE_NONE, MePOS.TEXT_STYLE_NONE, MePOS.TEXT_POSITION_CENTER);
        receipt.addLine(rcpt);


        rcpt = new MePOSReceiptTextLine();
        rcpt.setText("-Sirlon edited ingredients/stock here", MePOS.TEXT_STYLE_NONE, MePOS.TEXT_STYLE_NONE, MePOS.TEXT_POSITION_CENTER);
        receipt.addLine(rcpt);

        rcpt = new MePOSReceiptTextLine();
        rcpt.setText("-Sirlon notes here", MePOS.TEXT_STYLE_NONE, MePOS.TEXT_STYLE_NONE, MePOS.TEXT_POSITION_CENTER);
        receipt.addLine(rcpt);

        rcpt = new MePOSReceiptTextLine();
        rcpt.setText("1 Sorbet                      5.00", MePOS.TEXT_STYLE_NONE, MePOS.TEXT_STYLE_NONE, MePOS.TEXT_POSITION_CENTER);
        receipt.addLine(rcpt);
        receipt.addLine(new MePOSReceiptFeedLine(1));

        rcpt = new MePOSReceiptTextLine();
        rcpt.setText("Service charge % set percentage", MePOS.TEXT_STYLE_NONE, MePOS.TEXT_STYLE_NONE, MePOS.TEXT_POSITION_LEFT);
        receipt.addLine(rcpt);
        receipt.addLine(new MePOSReceiptSingleCharLine('-'));

        rcpt = new MePOSReceiptTextLine();
        rcpt.setText("Total items in basquet        4", MePOS.TEXT_STYLE_NONE, MePOS.TEXT_STYLE_NONE, MePOS.TEXT_POSITION_CENTER);
        receipt.addLine(rcpt);
        receipt.addLine(new MePOSReceiptFeedLine(1));

        rcpt = new MePOSReceiptTextLine();
        rcpt.setText("Total top category 1      Sum of all drinks", MePOS.TEXT_STYLE_NONE, MePOS.TEXT_STYLE_NONE, MePOS.TEXT_POSITION_LEFT);
        receipt.addLine(rcpt);

        rcpt = new MePOSReceiptTextLine();
        rcpt.setText("Total top category 1      Sum of all food", MePOS.TEXT_STYLE_NONE, MePOS.TEXT_STYLE_NONE, MePOS.TEXT_POSITION_LEFT);
        receipt.addLine(rcpt);


        rcpt = new MePOSReceiptTextLine();
        rcpt.setText("Total                        20.65", MePOS.TEXT_STYLE_NONE, MePOS.TEXT_STYLE_NONE, MePOS.TEXT_POSITION_LEFT);
        receipt.addLine(rcpt);
        receipt.addLine(new MePOSReceiptSingleCharLine('-'));
        receipt.addLine(new MePOSReceiptFeedLine(1));

        receipt.addLine(new MePOSReceiptBarcodeLine(MePOS.BARCODE_TYPE_CODE39, "R0926031274"));
        receipt.addLine(new MePOSReceiptTextLine("R0926031274", MePOS.TEXT_STYLE_NONE, MePOS.TEXT_SIZE_NORMAL, MePOS.TEXT_POSITION_CENTER));
        receipt.addLine(new MePOSReceiptFeedLine(1));
        receipt.addLine(new MePOSReceiptSingleCharLine('-'));

        return receipt;
    }

    private Bitmap getBitmapFromAsset(Context context, String filePath) {
        AssetManager assetManager = context.getAssets();
        InputStream istr;
        Bitmap bitmap = null;
        try {
            istr = assetManager.open(filePath);
            bitmap = BitmapFactory.decodeStream(istr);
        } catch (IOException e) {
            Log.e("No logo image", e.getLocalizedMessage());
        }
        return bitmap;
    }

}
