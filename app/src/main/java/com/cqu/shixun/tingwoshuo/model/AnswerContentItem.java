package com.cqu.shixun.tingwoshuo.model;

import android.widget.Button;

/**
 * Created by legendpeng on 2018/7/6.
 */

public class AnswerContentItem {

    int id;
    private String StrAskAvatar;
    private String StrAskPerson;
    private String StrListenAvatar;
    private String StrAskContent;
    private Button BtnListenContent;
    private String StrDate;
    private int IntQuesitionPrice;
    private int IntListenContentNum;
    private Button BtnReAnswer;

    public  AnswerContentItem(int id) {
        this.id = id;
    }
    public  int getId(){return id;}

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

    public int getIntQuesitionPrice() {
        return IntQuesitionPrice;
    }

    public void setIntQuesitionPrice(int intQuesitionPrice) {
        IntQuesitionPrice = intQuesitionPrice;
    }

    public Button getBtnReAnswer() {
        return BtnReAnswer;
    }

    public void setBtnReAnswer(Button btnReAnswer) {
        BtnReAnswer = btnReAnswer;
    }

    public int getIntListenContentNum() {
        return IntListenContentNum;
    }

    public void setIntListenContentNum(int intListenContentNum) {
        IntListenContentNum = intListenContentNum;
    }
}
