package com.whz.Tools;

import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSubscribeListener;
import com.aliyun.alink.linksdk.tools.AError;

public class SubListener implements IConnectSubscribeListener {
    private boolean isSub=false;
    @Override
    public void onSuccess() {
        isSub=true;
    }

    @Override
    public void onFailure(AError aError) {
        isSub=false;
    }

    public boolean getIsSub(){
        return isSub;
    }
}
