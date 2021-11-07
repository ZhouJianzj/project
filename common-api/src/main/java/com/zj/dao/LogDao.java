package com.zj.dao;

import com.zj.entity.Log;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface LogDao {

    @Insert("insert into log(username, operType, moduleName, result, operTimer, operContent) values(#{username},#{operType},#{moduleName},#{result},#{operTimer},#{operContent})")
    void logInsert(Log log);
}
