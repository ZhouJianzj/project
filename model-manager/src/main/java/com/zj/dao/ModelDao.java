package com.zj.dao;

import com.zj.entity.PipeModel;
import org.springframework.stereotype.Repository;

/**
 * @author zhoujian
 */
@Repository
public interface ModelDao {


    Boolean pipeModelInsert(PipeModel pipeModel);

    Boolean test(String test);

    PipeModel findPipeModelDao(String id);

    boolean updatePipeModel(PipeModel pipeModel);
}
