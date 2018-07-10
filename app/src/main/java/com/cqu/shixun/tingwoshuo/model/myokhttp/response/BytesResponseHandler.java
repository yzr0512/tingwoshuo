package com.cqu.shixun.tingwoshuo.model.myokhttp.response;

import com.cqu.shixun.tingwoshuo.model.myokhttp.MyOkHttp;
import com.cqu.shixun.tingwoshuo.model.myokhttp.util.LogUtils;

import java.io.IOException;

import okhttp3.Response;
import okhttp3.ResponseBody;

public abstract class BytesResponseHandler implements IResponseHandler {
    @Override
    public void onSuccess(final Response response) {
        ResponseBody responseBody = response.body();
        byte[] responseBodyBytes;

        try {
            responseBodyBytes = responseBody.bytes();
        } catch (IOException e) {
            e.printStackTrace();
            LogUtils.e("onResponse fail read response body");
            MyOkHttp.mHandler.post(new Runnable() {
                @Override
                public void run() {
                    onFailure(response.code(), "fail read response body");
                }
            });
            return;
        } finally {
            responseBody.close();
        }

        final byte[] finalResponseBodyBytes = responseBodyBytes;
        MyOkHttp.mHandler.post(new Runnable() {
            @Override
            public void run() {
                onSuccess(response.code(), finalResponseBodyBytes);
            }
        });
    }

    public abstract void onSuccess(int statusCode, byte[] response);


    @Override
    public void onProgress(long currentBytes, long totalBytes) {

    }

}
