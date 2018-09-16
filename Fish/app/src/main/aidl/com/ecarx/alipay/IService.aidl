// IService.aidl
package com.ecarx.alipay;

// Declare any non-default types here with import statements

interface IService {
    int callSafePay(String username,String passwd,float money,long timestamp);
}
