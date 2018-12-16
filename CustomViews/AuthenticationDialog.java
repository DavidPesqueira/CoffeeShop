package com.example.norskhaxor.coffeeshop.CustomViews;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.norskhaxor.coffeeshop.Interface.AuthenticationListener;
import com.example.norskhaxor.coffeeshop.R;
import com.example.norskhaxor.coffeeshop.ViewHolder.Constants;

public class AuthenticationDialog extends Dialog {
    private AuthenticationListener listener;

    private final String url = Constants.BASE_URL
            +"oauth/authorize/?client_id="
            +Constants.INSTAGRAM_CLIENT_ID
            +"&redirect_uri="
            +Constants.REDIRECT_URI
            +"&response_type=token"
            +"&display=touch&scope=public_content";

    public AuthenticationDialog(@NonNull Context context,
                                AuthenticationListener listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.auth_dialog);
        initializeWebView();
    }

    private void initializeWebView() {
        WebView webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient(){

            String access_token;
            boolean authComplete;

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //check
                if(url.contains("&access_token=")&& !authComplete){
                    Uri uri = Uri.parse(url);
                    access_token = uri.getEncodedFragment();
                    //Retrieve token after "="
                    access_token = access_token.substring(access_token.lastIndexOf("=")+1);
                    Log.e("access_token", access_token);
                    authComplete =true;
                    listener.onCodeReceive(access_token);
                    dismiss();
                }else if (url.contains("?error")){
                    Log.e("access_token","Error fetching access token");
                    dismiss();
                }
            }

        });
    }
}
