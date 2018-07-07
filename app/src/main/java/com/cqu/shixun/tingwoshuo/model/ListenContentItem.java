package com.cqu.shixun.tingwoshuo.model;

import android.widget.Button;

/**
 * Created by legendpeng on 2018/7/6.
 */

public class ListenContentItem {
    private String StrAskAvatar;
    private String StrListenAvatar;
    private String StrAskContent;
    private Button BtnListenContent;
    private String StrDate;
    private int IntListenContentNum;

    public String getStrAskAvatar() {
        return StrAskAvatar;
    }

    public void setStrAskAvatar(String strAskAvatar) {
        StrAskAvatar = strAskAvatar;
    }

    public String getStrListenAvatar() {
        return StrListenAvatar;
    }

    public void setStrListenAvatar(String strListenAvatar) {
        StrListenAvatar = strListenAvatar;
    }

    public String getStrAskContent() {
        return StrAskContent;
    }

    public void setStrAskContent(String strAskContent) {
        StrAskContent = strAskContent;
    }

    public Button getBtnListenContent() {
        return BtnListenContent;
    }

    public void setBtnListenContent(Button btnListenContent) {
        BtnListenContent = btnListenContent;
    }

    public String getStrDate() {
        return StrDate;
    }

    public void setStrDate(String strDate) {
        StrDate = strDate;
    }

    public int getIntListenContentNum() {
        return IntListenContentNum;
    }

    public void setIntListenContentNum(int intListenNum) {
        IntListenContentNum = intListenNum;
    }
}
