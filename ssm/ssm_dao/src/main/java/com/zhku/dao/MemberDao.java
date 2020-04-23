package com.zhku.dao;

import com.zhku.pojo.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberDao {
    /**
     * 根据id查询会员信息
     * @param id
     * @return
     */
    @Select("select * from member where id = #{id}")
    Member findById(int id);
}
