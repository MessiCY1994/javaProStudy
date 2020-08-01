package com.messi.springFramework.beans.factory;

import com.messi.springFramework.beans.config.BeanDefination;
import com.messi.springFramework.beans.config.ClassPathResource;
import com.messi.springFramework.beans.config.Resoure;
import com.messi.springFramework.beans.config.XmlBeanDefinationReader;
import com.sun.prism.shader.AlphaOne_Color_AlphaTest_Loader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DefaultListableBeanFactory
 * @Description: TODO
 * @Author messi
 * @Date 2020/8/1
 * @Version V1.0
 **/
public class DefaultListableBeanFactory extends AbstractBeanFactory {
    /**
     * 读取xml之后获得的beandefination信息
     */
    private Map<String, BeanDefination> beanDefinations = new HashMap<>();

    public Map<String, BeanDefination> getBeanDefinations() {
        return beanDefinations;
    }

    public void addBeanDefinations(String beanName,BeanDefination beanDefination) {
       this.beanDefinations.put(beanName,beanDefination);
    }

    /**
     * 传说中的spring容器
     */
    private Map<String,Object> singletonObjects = new HashMap<>();

    private List<Resoure> resoures = new ArrayList<>();

    public DefaultListableBeanFactory(String location){
        //注册资源类
        registeResources();
        //读取配置文件的bean信息
        XmlBeanDefinationReader xmlBeanDefinationReader = new XmlBeanDefinationReader();
        //因为不清楚lication字符串到底代表的是类路径下的xml，还是d盘下面的xml，还是url
        Resoure resoure = getResource(location);
        xmlBeanDefinationReader.loadBeanDefinations(this,resoure);

    }

    private void registeResources() {
        resoures.add(new ClassPathResource());
    }

    private Resoure getResource(String location) {
        for (Resoure resoure:resoures) {
            if(resoure.isCanRead(location)){
                return resoure;
            }
        }
        return null;
    }

    @Override
    public Object getBean(String beanName) {
        return singletonObjects.get(beanName);
    }
}
