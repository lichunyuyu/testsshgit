package com.one.javatest.test.HttpsRestfulPostman;

import org.apache.http.conn.ssl.SSLSocketFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

/**
 * Created by vtstar on 2018/6/1.
 *
 * 1. 在准备证书阶段选择的是使用证书认证
 */
public class HTTPSCertifiedClient extends HTTPSClient {

    public HTTPSCertifiedClient(){

    }
    @Override
    public void prepareCertificate() throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException, KeyManagementException {
        // 获得密匙库
        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType()); //  throws KeyStoreException
        FileInputStream inputStream = new FileInputStream(
                new File("C:/Users/zhda6001/Downloads/software/xxx.keystore")
        );  // FileNotFoundException

        // FileInputStream instream = new FileInputStream(new File("C:/Users/zhda6001/Downloads/xxx.keystore"));
        //密匙库的密码
        trustStore.load(inputStream,"password".toCharArray());// 密码   IOException, CertificateException, NoSuchAlgorithmException
        //注册密匙库
        this.socketFactory = new SSLSocketFactory(trustStore); // UnrecoverableKeyException, KeyManagementException
        //不校验域名
        socketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);


    }
}
