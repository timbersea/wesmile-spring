package com.qian.wesmile;

import com.qian.wesmile.annotation.Api;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Set;

public class SmileScanner extends ClassPathBeanDefinitionScanner {
    public SmileScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    public int scan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitionHolders = doScan(basePackages);
        processBeanDefinitions(beanDefinitionHolders);
        return beanDefinitionHolders.size();
    }

    private void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitions) {
        for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitions) {
            GenericBeanDefinition beanDefinition = (GenericBeanDefinition) beanDefinitionHolder.getBeanDefinition();
            String beanClassName = beanDefinition.getBeanClassName();
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(beanClassName);
            beanDefinition.setBeanClass(SmileFactoryBean.class);
        }
    }

    public void includeApiAnnotation() {
        addIncludeFilter(new AnnotationTypeFilter(Api.class));
    }

    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return true;
    }
}
