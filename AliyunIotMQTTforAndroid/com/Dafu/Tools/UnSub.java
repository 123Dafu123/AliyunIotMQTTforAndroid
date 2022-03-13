package com.whz.Tools;

import com.aliyun.alink.linkkit.api.ILinkKit;
import com.aliyun.alink.linksdk.cmp.connect.channel.MqttSubscribeRequest;

public class UnSub extends Thread {
    private String UnSubTopic;
    private ILinkKit iLinkKit;
    public UnSub(String UnSubTopic,ILinkKit iLinkKit){
        this.iLinkKit=iLinkKit;
        this.UnSubTopic=UnSubTopic;
    }

    @Override
    public void run() {
        if (UnSubTopic == null) return;
        boolean isUnSub=false;
        MqttSubscribeRequest UnSubscribeRequest = new MqttSubscribeRequest();
        UnSubscribeRequest.topic = UnSubTopic;
        UnSubscribeRequest.isSubscribe = false;
        UnSubscribeRequest.qos = 0; // 支持0或者1

        UnSubListener unSubListener = new UnSubListener();
        while (!isUnSub) {
            iLinkKit.unsubscribe(UnSubscribeRequest, unSubListener);
            isUnSub=unSubListener.getIsUnSub();
        }
    }
}
