package com.hhd.es.repository;

import com.hhd.es.bean.InventoryMat;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TODO
 *
 * @author hengda.hu
 * @date 2020/11/30 13:45
 */
@Repository
public interface InventoryMatRepository extends ElasticsearchRepository<InventoryMat, Long> {

    List<InventoryMat> findByMatName(String matName, Pageable pageable);

}
