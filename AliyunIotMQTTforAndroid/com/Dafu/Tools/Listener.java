package com.whz.Tools;

import com.aliyun.alink.linksdk.cmp.core.base.AMessage;
import com.aliyun.alink.linksdk.cmp.core.base.ConnectState;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener;

public class Listener implements IConnectNotifyListener{
    private String data;

    @Override
    public void onNotify(String connectId, String topic, AMessage aMessage) {
        data = new String((byte[]) aMessage.data);
    }

    @Override
    public boolean shouldHandle(String connectId, String topic) {
        return true;
    }

    @Override
    public void onConnectStateChange(String connectId, ConnectState connectState) {
    }

    public String getData(){
        return data;
    }

}
