package com.zj.dao;

import com.zj.entity.Pipe;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhoujian
 */
@Repository
public interface AssetDao {

    boolean pipeInsert(Pipe pipe);

    boolean pipeDelete(int id);

    boolean pipeModify(Pipe pipe);

    List<Pipe> pipeSelect(String key);
}
