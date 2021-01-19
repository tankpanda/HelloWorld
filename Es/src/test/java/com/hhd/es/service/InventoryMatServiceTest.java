package com.hhd.es.service;

import com.hhd.es.EsApplication;
import com.hhd.es.bean.InventoryMat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TODO
 *
 * @author hengda.hu
 * @date 2020/11/30 10:42
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class InventoryMatServiceTest {
    @Resource
    private InventoryMatService inventoryMatService;

    @Test
    public void save2EsTest() throws Exception {
        inventoryMatService.save2Es();
    }

    @Test
    public void getTest() throws Exception {
        InventoryMat inventoryMat = inventoryMatService.get(247L);
        System.out.println(inventoryMat);
    }

    @Test
    public void delIndexTest() throws Exception {
        inventoryMatService.delIndex();
    }

    @Test
    public void saveWithRepositoryTest() throws Exception {
        inventoryMatService.saveWithRepository();
    }

    @Test
    public void getWithRepositoryTest() throws Exception {
        InventoryMat inventoryMat = inventoryMatService.getWithRepository(247L);
        System.out.println(inventoryMat);
    }

    @Test
    public void findByMatName() throws Exception {
//        List<InventoryMat> inventoryMats = inventoryMatService.findByMatName("封闭", PageRequest.of(1, 10, Sort.by(Sort.Direction.ASC, "id")));
        List<InventoryMat> inventoryMats = inventoryMatService.findByMatName("Clarity", PageRequest.of(1, 10, Sort.by(Sort.Direction.ASC, "id")));
        System.out.println(inventoryMats);
    }



}