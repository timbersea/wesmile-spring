package com.qian.wesmile;

import com.qian.wesmile.api.analytics.UserAnalysisData;
import com.qian.wesmile.model.param.UserAnalyze;
import com.qian.wesmile.model.result.Getusersummary;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringContext {
    private static final Logger log = LoggerFactory.getLogger(TestSpringContext.class);

    @Test
    public void testGetApi() {
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        UserAnalysisData api = ioc.getBean(UserAnalysisData.class);
        UserAnalyze userAnalyze = new UserAnalyze();
        userAnalyze.setBegin_date("2014-12-02");
        userAnalyze.setEnd_date("2014-12-07");
        Getusersummary getusersummary = api.getusersummary(userAnalyze);
        log.info("test1:{}", getusersummary);
    }

    @Test
    public void testGetUserDefineApi() {
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        Datacube datacube = ioc.getBean(Datacube.class);
        A a = new A();
        Getusersummary getusersummary = datacube.getusersummary(a);
        log.info("test1:{}", getusersummary);
    }
}
