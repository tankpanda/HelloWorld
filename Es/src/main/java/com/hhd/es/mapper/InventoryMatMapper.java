package com.hhd.es.mapper;

import com.hhd.es.bean.InventoryMat;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author hengda.hu
 * @date 2020/11/24 15:26
 */
@Repository
public interface InventoryMatMapper {
    List<InventoryMat> listAll();
}
