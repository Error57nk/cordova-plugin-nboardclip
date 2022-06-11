package cordova.pluging.nboardclip;

import static android.content.ClipDescription.MIMETYPE_TEXT_PLAIN;
import static android.content.Context.CLIPBOARD_SERVICE;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.Log;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class NBoardClip extends CordovaPlugin {
    ClipboardManager clipboard;
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("getClipBoardData")) {
            Context context = this.cordova.getActivity().getApplicationContext();
            int pos;
            try{
                String message = args.getString(0);
                pos =  Integer.valueOf(message);

            }catch(Exception e){
                pos = 0;
            }
            this.getClipBoardData(pos,context ,callbackContext);
            return true;
        }
        return false;
    }

    private void getClipBoardData(int position, Context context ,CallbackContext callbackContext) {
            try{
                clipboard = (ClipboardManager) cordova.getActivity().getSystemService(context.CLIPBOARD_SERVICE);
                if (!(clipboard.hasPrimaryClip())) {
                    callbackContext.error("Data Not Present ClipBoard Copy First");
                } else if (!(clipboard.getPrimaryClipDescription().hasMimeType(MIMETYPE_TEXT_PLAIN))) {
                    // since the clipboard has data but it is not plain text
                    callbackContext.error("item is not Text");
                } else {
                    //since the clipboard contains plain text.
                    ClipData.Item item = clipboard.getPrimaryClip().getItemAt(position);
                    String data = item.getText().toString();
                    // Gets the clipboard as text.
                    if(!data.equals("")){
                        callbackContext.success(data);
                    }else{
                        callbackContext.error("Data Not Present Copy first");
                    }
                }
            }catch (Exception e){
                callbackContext.error("Expected" + e);
            }
    }
}
