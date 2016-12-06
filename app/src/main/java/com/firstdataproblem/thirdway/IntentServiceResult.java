package com.firstdataproblem.thirdway;

/**
 * Created by Avdhesh AKhani on 12/5/2016.
 */

public class IntentServiceResult {

    int resultCode;
    String resultValue;

    public IntentServiceResult(int resultCode, String resultValue) {
        this.resultCode = resultCode;
        resultValue = resultValue;
    }

    public int getResultCode() {
        return resultCode;
    }

    public String getResultValue() {
        return resultValue;
    }

}
