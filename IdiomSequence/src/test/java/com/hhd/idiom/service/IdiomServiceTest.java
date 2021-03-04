package com.hhd.idiom.service;

import com.hhd.idiom.bean.Idiom;
import com.hhd.idiom.utils.PinyinUtils;
import com.sun.org.glassfish.external.statistics.annotations.Reset;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * Created by huhengda on 2021/3/2.
 */
@SpringBootTest
public class IdiomServiceTest {
    @Resource
    private IdiomService idiomService;

    @Test
    public void testInsert() {
        Idiom idiom = new Idiom();
        idiom.setIdiom("是哈哈哈");
        idiom.setPinyin(PinyinUtils.getAllPinyin(idiom.getIdiom()));
        idiomService.insert(idiom);
    }

    @Test
    public void testInsertWithRobot() throws Exception {
        idiomService.insertWithRobot();
    }


}