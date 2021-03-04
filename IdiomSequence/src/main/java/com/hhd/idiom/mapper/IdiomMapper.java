package com.hhd.idiom.mapper;

import com.hhd.idiom.bean.Idiom;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by huhengda on 2021/3/1.
 */
@Repository
public interface IdiomMapper {
    Integer insert(@Param("idiom") Idiom idiom);

    Integer update(@Param("idiom") Idiom idiom);

    List<Idiom> getList(@Param("idiom") Idiom idiom);
}
