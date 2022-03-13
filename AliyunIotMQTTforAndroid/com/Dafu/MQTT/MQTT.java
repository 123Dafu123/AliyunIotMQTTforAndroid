package com.Dafu.MQTT;

import android.content.Context;
import com.aliyun.alink.dm.api.DeviceInfo;
import com.aliyun.alink.linkkit.api.*;
import com.aliyun.alink.linksdk.cmp.connect.channel.MqttPublishRequest;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.base.AResponse;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.tmp.device.payload.ValueWrapper;
import com.aliyun.alink.linksdk.tools.AError;
import com.whz.tools.*;

import java.util.HashMap;
import java.util.Map;

public class MQTT {
    ILinkKit iLinkKit;
    public void init(){
        this.iLinkKit=LinkKit.getInstance();
    }

    public void connect(String productKey, String deviceName, String deviceSecret,Context context){
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.productKey = productKey;
        deviceInfo.deviceName = deviceName;
        deviceInfo.deviceSecret = deviceSecret;
        Map<String, ValueWrapper> propertyValues = new HashMap<>();
        IoTMqttClientConfig clientConfig = new IoTMqttClientConfig(productKey, deviceName, deviceSecret);
        LinkKitInitParams params = new LinkKitInitParams();
        params.deviceInfo = deviceInfo;
        params.propertyValues = propertyValues;
        params.mqttClientConfig = clientConfig;

        iLinkKit.init(context,params, new ILinkKitConnectListener() {
            @Override
            public void onError(AError error) {
            }
            @Override
            public void onInitDone(Object data) {
            }
        });
    }

    public void pub(String PubTopic, String PubMethod, String RelyTopic, String device, int data){
        MqttPublishRequest request = new MqttPublishRequest();
        request.isRPC = false;
        request.qos = 0;
        request.topic = PubTopic;
        request.replyTopic =RelyTopic;
        request.payloadObj ="{\"id\":\"456\", \"version\":\"1.0\",\"method\":\""+PubMethod+"\",\"params\":{\""+device+"\""+":"+data+"} }";
        iLinkKit.publish(request, new IConnectSendListener() {
            @Override
            public void onResponse(ARequest aRequest, AResponse aResponse) {
            }
            @Override
            public void onFailure(ARequest aRequest, AError aError) {
            }
        });
    }

    public void sub(String SubTopic){
        if (SubTopic==null)return;
        Sub sub=new Sub(SubTopic,iLinkKit);
        sub.start();
    }

    public void unSub(String UnSubTopic){
        if(UnSubTopic==null)return;
        UnSub unSub=new UnSub(UnSubTopic,iLinkKit);
        unSub.start();
    }

    public void setListener(Listener listener){
        iLinkKit.registerOnPushListener(listener);
    }
}

