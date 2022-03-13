package com.whz.Tools;

import com.aliyun.alink.linkkit.api.ILinkKit;
import com.aliyun.alink.linksdk.cmp.connect.channel.MqttSubscribeRequest;

public class Sub extends Thread{
    private String SubTopic;
    private ILinkKit iLinkKit;
    public Sub(String SubTopic,ILinkKit iLinkKit){
        this.iLinkKit=iLinkKit;
        this.SubTopic=SubTopic;
    }
    @Override
    public void run(){
        if (SubTopic==null)return;
        boolean isSub=false;
        MqttSubscribeRequest subscribeRequest = new MqttSubscribeRequest();
        subscribeRequest.topic = SubTopic;
        subscribeRequest.isSubscribe = true;
        subscribeRequest.qos = 0;
        SubListener subListener=new SubListener();
        while (!isSub){
            iLinkKit.subscribe(subscribeRequest,subListener);
            isSub=subListener.getIsSub();
        }
    }
}
