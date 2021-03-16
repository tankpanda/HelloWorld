package com.hhd.idiom.service;

import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.hhd.idiom.bean.Idiom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by huhengda on 2021/3/16.
 */
@Service
@Slf4j
public class IdiomExcelService {

    private Map<String, List<Idiom>> idiomMap;
    private Long lastQuery;
    private static final Long CACHE_TIME = 10L;
    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    private void init() {
        ClassPathResource resource = new ClassPathResource("/excel/idiom.xls");
        try (ExcelReader reader = ExcelUtil.getReader(resource.getStream())) {
            List<Idiom> idioms = reader.readAll(Idiom.class);
            idiomMap = idioms.stream().collect(Collectors.groupingBy(Idiom::getPinyinFirstWord));
        }
    }

    private void clear() {
        if (System.currentTimeMillis() - lastQuery >= CACHE_TIME) {
            idiomMap = null;
        }
    }

    public List<Idiom> getList(Idiom idiom) {
        if (idiomMap == null) {
            init();
        }
        lastQuery = System.currentTimeMillis();
        executor.schedule(() -> clear(), CACHE_TIME, TimeUnit.SECONDS);
        return idiomMap.get(idiom.getPinyinFirstWord()).stream().limit(50).collect(Collectors.toList());
    }
}
