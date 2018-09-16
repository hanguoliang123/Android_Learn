/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: H:\\Android_Studio_Project\\AliPay\\app\\src\\main\\aidl\\com\\ecarx\\alipay\\IService.aidl
 */
package debug.com.ecarx.alipay;
// Declare any non-default types here with import statements
-interface IService {
-    int callSafePay(String username, String passwd, float money, long timestamp);
-}

public interface IService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.ecarx.alipay.IService
{
private static final String DESCRIPTOR = "com.ecarx.alipay.IService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.ecarx.alipay.IService interface,
 * generating a proxy if needed.
 */
public static com.ecarx.alipay.IService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.ecarx.alipay.IService))) {
return ((com.ecarx.alipay.IService)iin);
}
return new Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_callSafePay:
{
data.enforceInterface(DESCRIPTOR);
String _arg0;
_arg0 = data.readString();
String _arg1;
_arg1 = data.readString();
float _arg2;
_arg2 = data.readFloat();
long _arg3;
_arg3 = data.readLong();
int _result = this.callSafePay(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.ecarx.alipay.IService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public int callSafePay(String username, String passwd, float money, long timestamp) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(username);
_data.writeString(passwd);
_data.writeFloat(money);
_data.writeLong(timestamp);
mRemote.transact(Stub.TRANSACTION_callSafePay, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_callSafePay = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public int callSafePay(String username, String passwd, float money, long timestamp) throws android.os.RemoteException;
}
