package com.qian.wesmile;

import com.qian.wesmile.request.AbstractHttpRequester;
import com.qian.wesmile.request.OkHttpRequester;
import com.qian.wesmile.spi.AccessTokenManager;
import com.qian.wesmile.spi.MemoryAccessTokenManager;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.StringUtils;

import static org.springframework.util.Assert.notNull;

public class SmileConfigurer implements BeanDefinitionRegistryPostProcessor, InitializingBean, ApplicationContextAware, BeanNameAware {
    private static final String DEFAULT_DOMAIN = "https://api.weixin.qq.com";

    public String appid;

    public String appSecret;
    public String domain = DEFAULT_DOMAIN;
    private String scanPackage;
    private String beanName;

    private AbstractHttpRequester httpRequester = new OkHttpRequester();
    private AccessTokenManager accessTokenManager = new MemoryAccessTokenManager();
    private WeSmile weSmile;

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void afterPropertiesSet() {
        notNull(this.appid, "Property 'appid' is required");
        notNull(this.appSecret, "Property 'appSecret' is required");
        notNull(this.domain, "Property 'domain' is required");
        notNull(this.httpRequester, "Property 'httpRequester' is required");
        notNull(this.accessTokenManager, "Property 'accessTokenManager' is required");
        notNull(this.scanPackage, "Property 'scanPackage' is required");
        this.weSmile = new WeSmile();
        weSmile.setAppid(appid);
        weSmile.setAppSecret(appSecret);
        weSmile.setDomain(domain);
        weSmile.setAccessTokenManager(accessTokenManager);
        weSmile.setHttpRequester(httpRequester);
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        SmileScanner smileScanner = new SmileScanner(registry);
        smileScanner.resetFilters(false);
        smileScanner.includeApiAnnotation();
        smileScanner.scan(
                StringUtils.tokenizeToStringArray(this.scanPackage, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS));
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getScanPackage() {
        return scanPackage;
    }

    public void setScanPackage(String scanPackage) {
        this.scanPackage = scanPackage;
    }


    public void setAbstractHttpRequester(AbstractHttpRequester abstractHttpRequester) {
        this.httpRequester = abstractHttpRequester;
    }


    public void setAccessTokenManager(AccessTokenManager accessTokenManager) {
        this.accessTokenManager = accessTokenManager;
    }

    public WeSmile getWeSmile() {
        return weSmile;
    }
}
