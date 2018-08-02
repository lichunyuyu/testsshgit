package com.one.javatest.test.HttpsRestfulPostman;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created by vtstar on 2018/6/1.
 * 现在发现这两个类都继承了同一个类HTTPSClient，并且HTTPSClient继承了DefaultHttpClient类，
 * 可以发现，这里使用了模板方法模式。
 */
public abstract class HTTPSClient extends DefaultHttpClient  {

    protected SSLSocketFactory socketFactory;

    /**
     * 初始化 HTTPClient
     * @return 返回当前实例
     *
     * */
    public HTTPSClient init() throws Exception {
        this.prepareCertificate();
        this.regist();

        return this;
    }
    /**
     * 准备证书验证
     * */
    public abstract void prepareCertificate() throws Exception;

    /**
     * 注册协议和端口，此方法也可以被子类重写
     * */
    protected void regist(){
        ClientConnectionManager ccm = this.getConnectionManager();
        SchemeRegistry sr = ccm.getSchemeRegistry();
        sr.register(new Scheme("https",443,  socketFactory));
    }


}
