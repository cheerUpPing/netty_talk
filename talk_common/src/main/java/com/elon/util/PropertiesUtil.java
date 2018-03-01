package com.elon.util;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

/**
 * Created by elon on 2016/6/8.
 * 获取 *.properties 文件的相关信息
 */
public class PropertiesUtil {

    static PropertiesUtil pu;// 创建对象pu
    private static Hashtable register = new Hashtable();

    private PropertiesUtil() {
        super();
    }

    /**
     * 取得PropertiesUtil的一个实例
     */
    public static PropertiesUtil getInstance() {
        if (pu == null) {
            synchronized (PropertiesUtil.class) {
                if (pu == null)
                    pu = new PropertiesUtil();
            }
        }
        return pu;
    }


    /**
     * 读取配置文件
     */
    @SuppressWarnings("unchecked")
    public Properties getProperties(String fileName) {
        InputStream is = null;
        Properties p = null;
        try {
            p = (Properties) register.get(fileName);
            if (p == null) {
                try {
                    is = new FileInputStream(fileName);
                } catch (Exception e) {
                    if (fileName.startsWith("/"))
                        is = PropertiesUtil.class.getResourceAsStream(fileName);
                    else
                        is = PropertiesUtil.class.getResourceAsStream("/" + fileName);
                }
                if (is == null) {
                    throw new RuntimeException("未找到名称为" + fileName + "的资源！");
                }
                p = new Properties();
                p.load(is);
                register.put(fileName, p);
                is.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null)
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return p;
    }

    public String getPropertyValue(String fileName, String strKey) {
        Properties p = getProperties(fileName);
        try {
            return p.getProperty(strKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
