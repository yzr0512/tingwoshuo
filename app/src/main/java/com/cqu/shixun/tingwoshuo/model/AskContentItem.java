package com.cqu.shixun.tingwoshuo.model;

import android.widget.Button;

/**
 * Created by legendpeng on 2018/7/6.
 */

public class AskContentItem {
    int  id;
    private String StrAskAvatar;
    private String StrAskPerson;
    private String StrAskContent;
    private String StrQuesitionState;
    private String StrDate;
    private int IntQuesitionPrice;
    public AskContentItem(int id){
        this.id=id;
    }

    public int getId() {
        return id;
    }
    public String getStrAskAvatar() {
        return StrAskAvatar;
    }

    public void setStrAskAvatar(String strAskAvatar) {
        StrAskAvatar = strAskAvatar;
    }

    public String getStrAskPerson() {
        return StrAskPerson;
    }

    public void setStrAskPerson(String strAskPerson) {
        StrAskPerson = strAskPerson;
    }

    public String getStrAskContent() {
        return StrAskContent;
    }

    public void setStrAskContent(String strAskContent) {
        StrAskContent = strAskContent;
    }

    public String getStrQuesitionState() {
        return StrQuesitionState;
    }

    public void setStrQuesitionState(String strQuesitionState) {
        StrQuesitionState = strQuesitionState;
    }

    public String getStrDate() {
        return StrDate;
    }

    public void setStrDate(String strDate) {
        StrDate = strDate;
    }

    public int getIntQuesitionPrice() {
        return IntQuesitionPrice;
    }

    public void setIntQuesitionPrice(int intQuesitionPrice) {
        IntQuesitionPrice = intQuesitionPrice;
    }
}
