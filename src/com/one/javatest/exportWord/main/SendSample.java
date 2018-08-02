package com.one.javatest.exportWord.main;

/**
 * Created by vtstar on 2018/2/23.
 */

public class SendSample  {

    private String serialNo;


    private String sampleNo;  // 不用

    private String sampleName;

    private String sendDate;

    private String reqinspectionDate;

    private String sendPerson;

    private String sendPurpose;

    private String sendDepart;

    private String testItems;

    private String custFlag;  // 0 大客户     1 战略性客户  2 中等客户    3 小客户   4 竞争对手

    private String freeFlag; // 0 否  1 是

    private String reason; //  （是  --理由 ）


    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getReqinspectionDate() {
        return reqinspectionDate;
    }

    public void setReqinspectionDate(String reqinspectionDate) {
        this.reqinspectionDate = reqinspectionDate;
    }

    public String getSendPerson() {
        return sendPerson;
    }

    public void setSendPerson(String sendPerson) {
        this.sendPerson = sendPerson;
    }

    public String getSendPurpose() {
        return sendPurpose;
    }

    public void setSendPurpose(String sendPurpose) {
        this.sendPurpose = sendPurpose;
    }

    public String getSendDepart() {
        return sendDepart;
    }

    public void setSendDepart(String sendDepart) {
        this.sendDepart = sendDepart;
    }

    public String getTestItems() {
        return testItems;
    }

    public void setTestItems(String testItems) {
        this.testItems = testItems;
    }

    public String getCustFlag() {
        return custFlag;
    }

    public void setCustFlag(String custFlag) {
        this.custFlag = custFlag;
    }

    public String getFreeFlag() {
        return freeFlag;
    }

    public void setFreeFlag(String freeFlag) {
        this.freeFlag = freeFlag;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
