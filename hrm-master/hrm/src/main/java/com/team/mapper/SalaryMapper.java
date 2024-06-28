package com.team.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.team.entity.Salary;
import com.team.vo.StaffSalaryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface SalaryMapper extends BaseMapper<Salary> {


    @Select("select ss.id staff_id,ss.dept_id,ss.code,ss.name,ss.phone,ss.address,sd.name dept_name,si.per_social_pay social_pay,si.per_house_pay house_pay " +
            "from hrm.sys_staff ss inner join hrm.sys_dept sd on ss.dept_id = sd.id left join hrm.soc_insurance si on ss.id = si.staff_id where ss.is_deleted = 0")
    List<StaffSalaryVO> findStaffSalaryVO();


    @Select("select ss.id staff_id,ss.dept_id,ss.code,ss.name,ss.phone,ss.address,sd.name dept_name,si.per_social_pay social_pay,si.per_house_pay house_pay " +
            "from hrm.sys_staff ss inner join hrm.sys_dept sd on ss.dept_id = sd.id left join hrm.soc_insurance si on ss.id = si.staff_id where ss.is_deleted = 0 and ss.name like concat('%',#{name},'%')")
        IPage<StaffSalaryVO> listStaffSalaryVO(IPage<StaffSalaryVO> config, @Param("name") String name);

    @Select("select ss.id staff_id,ss.dept_id,ss.code,ss.name,ss.phone,ss.address,sd.name dept_name,si.per_social_pay social_pay,si.per_house_pay house_pay " +
            "from hrm.sys_staff ss inner join hrm.sys_dept sd on ss.dept_id = sd.id left join hrm.soc_insurance si on ss.id = si.staff_id where ss.is_deleted = 0 and ss.dept_id = #{deptId} and ss.name like concat('%',#{name},'%')")
    IPage<StaffSalaryVO> listStaffDeptSalaryVO(IPage<StaffSalaryVO> config, @Param("name") String name, @Param("deptId")Integer deptId);

}
