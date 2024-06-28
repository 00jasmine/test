package com.team.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.team.entity.Leave;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LeaveMapper extends BaseMapper<Leave> {

    @Select("select * from hrm.att_leave where is_deleted = 0 and dept_id = #{id}")
    List<Leave> findByDeptId(@Param("id") Integer id);

}
