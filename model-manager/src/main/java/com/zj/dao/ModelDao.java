package com.zj.dao;

import org.springframework.stereotype.Repository;

/**
 * @author zhoujian
 */
@Repository
public interface ModelDao {

    Boolean pipeModelFilesInsert(String pipeIntroduce,String pipePic,String pipeManual );
}
