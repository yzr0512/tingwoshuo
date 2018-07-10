package com.cqu.shixun.tingwoshuo.model;

import android.widget.Button;

/**
 * Created by legendpeng on 2018/7/5.
 */

public class ContentItem {
    private String StrAvatar;
    private String StrName;
    private String StrType;
    private String StrQuesition;
    private Button BtnAnswer;
    private int IntListenNum;
    private int IntListenPrice;
    private String filePath;
    private int quesitionId;

    public String getStrAvatar() {
        return StrAvatar;
    }

    public void setStrAvatar(String strAvatar) {
        StrAvatar = strAvatar;
    }

    public String getStrName() {
        return StrName;
    }

    public void setStrName(String strName) {
        StrName = strName;
    }

    public String getStrType() {
        return StrType;
    }

    public void setStrType(String strType) {
        StrType = strType;
    }

    public String getStrQuesition() {
        return StrQuesition;
    }

    public void setStrQuesition(String strQuesition) {
        StrQuesition = strQuesition;
    }

    public Button getBtnAnswer() {
        return BtnAnswer;
    }

    public void setBtnAnswer(Button btnAnswer) {
        BtnAnswer = btnAnswer;
    }

    public int getIntListenNum() {
        return IntListenNum;
    }

    public void setIntListenNum(int intListenNum) {
        IntListenNum = intListenNum;
    }

    public int getIntListenPrice() {
        return IntListenPrice;
    }

    public void setIntListenPrice(int intListenPrice) {
        IntListenPrice = intListenPrice;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getQuesitionId() {
        return quesitionId;
    }

    public void setQuesitionId(int quesitionId) {
        this.quesitionId = quesitionId;
    }
}
