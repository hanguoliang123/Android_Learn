package com.ecarx.alipay;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class AliPayService extends Service {
    public AliPayService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new Mybinder();
    }

    @Override
    public void onCreate() {
        System.out.println("支付宝服务被创建了");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        System.out.println("支付宝服务被销毁了");
        super.onDestroy();
    }

    private class Mybinder extends IService.Stub {

        @Override
        public int callSafePay(String username, String passwd, float money, long timestamp) throws RemoteException {
            return safePay(username,passwd,money,timestamp);
        }
    }

    /**
     * 安全支付的服务方法
     * @param username
     *          用户名
     * @param passwd
     *          密码
     * @param money
     *          钱
     * @param timestamp
     *          时间戳
     * @return
     */
    public int safePay(String username,String passwd,float money,long timestamp){
        System.out.println("加密的username");
        System.out.println("加密的passwd");
        System.out.println("加密的money");
        System.out.println("提交数据到支付宝的服务器");
        if(money>5000){
            return 505;//支付超过了当日限额
        }

        if("zhangsan".equals(username)&&"123".equals(passwd)){
            return 200; //成功支付
        }else{
            return 300; //用户名和密码不正确
        }
    }

}
