package it.syncroweb.android.bio;

import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.CallbackManager;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

public class ShareFacebook extends FragmentActivity {

    CallbackManager callbackManager;
    ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);

        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse("market://details?id=it.syncroweb.android.bio"))
                    .setContentTitle("Result Compatibility")
                    .setContentDescription("SCRIVI QUI DESCRIZIONE")
                    .setImageUrl(Uri.parse(""))
                    .build();

            shareDialog.show(linkContent);
        }
    }
}
