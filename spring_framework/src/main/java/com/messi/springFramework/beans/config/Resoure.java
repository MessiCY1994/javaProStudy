package com.messi.springFramework.beans.config;

import java.io.InputStream;

public interface Resoure {
    boolean isCanRead(String localtion);
    InputStream getInputStream();
}
