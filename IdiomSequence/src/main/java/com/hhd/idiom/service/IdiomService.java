package com.hhd.idiom.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.hhd.idiom.bean.BaiduIdiom;
import com.hhd.idiom.bean.BaiduIdiomResult;
import com.hhd.idiom.bean.BaiduIdiomResultResult;
import com.hhd.idiom.bean.Idiom;
import com.hhd.idiom.mapper.IdiomMapper;
import com.hhd.idiom.utils.HttpUtils;
import com.hhd.idiom.utils.PinyinUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by huhengda on 2021/3/1.
 */
@Service
public class IdiomService {
    @Resource
    private IdiomMapper idiomMapper;

    public Integer insert(Idiom idiom) {
        return idiomMapper.insert(idiom);
    }

    public Integer update(Idiom idiom) {
        return idiomMapper.update(idiom);
    }

    public List<Idiom> getList(Idiom idiom) {
        return idiomMapper.getList(idiom);
    }

    public void updateToneMark() {
        for (;;) {
            List<Idiom> idioms = idiomMapper.getListWithToneMarkNull();
            if (idioms.isEmpty()) {
                break;
            }
            idioms.forEach(idiom -> {
                idiom.setPinyinToneMark(PinyinUtils.getAllPinyin(idiom.getIdiom()));
                idiomMapper.update(idiom);
            });
        }
    }

    public void updateFirstWord() {
        for (;;) {
            List<Idiom> idioms = idiomMapper.getListWithFistWordNull();
            if (idioms.isEmpty()) {
                break;
            }
            idioms.forEach(idiom -> {
                idiom.setPinyinFirstWord(PinyinUtils.getAllPinyin(idiom.getIdiom().substring(0, 1)));
                idiomMapper.update(idiom);
            });
        }
    }

    public Boolean insertWithRobot() throws Exception {
        for (int i = 1490; i < 1491; i++) {
            System.out.println(i);
            String url = "https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php";
            String param = "resource_id=28204&from_mid=1&&format=json&ie=utf-8&oe=utf-8&query=%E6%88%90%E8%AF%AD&sort_key=&sort_type=1&stat0=&stat1=&stat2=&stat3=&pn=" + 30 * i + "&rn=30&cb=jQuery110208889001633023266_1614593381105&_=1614593381110";
            TimeUnit.SECONDS.sleep(5L);
//            final String s = "https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?resource_id=28204&from_mid=1&&format=json&ie=utf-8&oe=utf-8&query=%E6%88%90%E8%AF%AD&sort_key=&sort_type=1&stat0=&stat1=&stat2=&stat3=&pn=0&rn=30&cb=jQuery110208889001633023266_1614593381105&_=1614593381110";
//            System.out.println(s.equals(url + "?" + param));
            String result = HttpUtils.sendGet(url + "?" + param);
            System.out.println(result);
            result = result.substring(result.indexOf("{"), result.length() - 1);
            BaiduIdiom baiduIdiom = new Gson().fromJson(result, BaiduIdiom.class);
            for (BaiduIdiomResultResult idiomResult : baiduIdiom.getData().get(0).getResult()) {
                Idiom idiom = new Idiom();
                idiom.setIdiom(idiomResult.getEname());
                idiom.setPinyin(PinyinUtils.getAllPinyin(idiom.getIdiom()));
                idiomMapper.insert(idiom);
            }
        }
        return Boolean.TRUE;
    }

    public static void main(String[] args) throws Exception {
        new IdiomService().insertWithRobot();
    }

}
