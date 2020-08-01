package com.messi.springFramework.beans.config;

import java.io.InputStream;

/**
 * @ClassName ClassPathResourece
 * @Description: TODO
 * @Author messi
 * @Date 2020/8/1
 * @Version V1.0
 **/
public class ClassPathResource implements Resoure{

    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean isCanRead(String localtion) {
        if(localtion==null||"".equals(localtion)){
            return false;
        }
        if(localtion.startsWith("calsspath:")){
            this.location = localtion;
            return true;
        }
       return false;
    }

    @Override
    public InputStream getInputStream() {
        if (location==null||"".equals(location)){
            return null;
        }
        return this.getClass().getClassLoader().getResourceAsStream(location);
    }
}
