package com.dj.sometest.entity;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * @Author: Chris
 * @Date: 2020/8/13 11:08
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		GenericBeanDefinition bd = (GenericBeanDefinition) beanFactory.getBeanDefinition("userService");
		//设置自动装配方式为构造器模式 则会推断构造方法会选用c的构造方法
		bd.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
		System.out.println("自定义beanfactory后置处理器");
	}
}
