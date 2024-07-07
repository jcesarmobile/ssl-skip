package com.jcesarmobile.sslskip;

import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;

import com.getcapacitor.BridgeWebViewClient;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

@CapacitorPlugin(name = "SslSkip")
public class SslSkipPlugin extends Plugin {
    private boolean allowUntrusted = false;

    TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }

        @Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
            // Not implemented
        }

        @Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
            // Not implemented
        }
    }};

	/**
	 *
	 *
	 */
    @PluginMethod
    public void isAllowUntrusted(PluginCall call) {
        JSObject result = new JSObject();
        result.put("allowUntrusted", allowUntrusted);
        call.resolve(result);
    }

	/**
	 *
	 *
	 */
    @PluginMethod
    public void setAllowUntrusted(PluginCall call) {
        final Boolean sAllowUntrusted = call.getBoolean("allowUntrusted");
		this.allowUntrusted = sAllowUntrusted;
	}

	/**
	 *
	 *
	 */
    @Override
    public void load() {
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() { @Override public boolean verify(String hostname, SSLSession session) { return true; } });
            HttpsURLConnection.setFollowRedirects(true);
        }
		catch (KeyManagementException e) {
            e.printStackTrace();
        }
		catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
		
        this.bridge.setWebViewClient(new BridgeWebViewClient(this.bridge) {
            @Override
            public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {
              if (isAllowUntrusted) {
                handler.proceed();
              }
              else {
                super.onReceivedSslError(view, handler, error);
              }
            }
        });
    }
}
