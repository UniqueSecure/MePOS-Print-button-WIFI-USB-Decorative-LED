package persistence;

import android.content.Context;

import com.uniquesecure.meposconnect.MePOS;
import com.uniquesecure.meposconnect.MePOSConnectionType;

public class MePOSSingleton {
    private static MePOS mepos = null;

    public static MePOS getInstance() {
        return mepos;
    }

    public static void createInstance(Context context, MePOSConnectionType type) {
        mepos = new MePOS(context, type);
    }

}
