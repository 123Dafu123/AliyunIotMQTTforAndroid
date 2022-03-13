package com.whz.Tools;

import com.aliyun.alink.linksdk.cmp.core.listener.IConnectUnscribeListener;
import com.aliyun.alink.linksdk.tools.AError;

public class UnSubListener implements IConnectUnscribeListener {
    private boolean IsUnSub=false;
    @Override
    public void onSuccess() {
        IsUnSub=true;
    }

    @Override
    public void onFailure(AError aError) {
        IsUnSub=false;
    }

    public boolean getIsUnSub(){
        return IsUnSub;
    }
}
