package com.team.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team.dto.Response;
import com.team.dto.ResponseDTO;
import com.team.entity.Staff;
import com.team.enums.BusinessStatusEnum;
import com.team.mapper.StaffMapper;
import com.team.util.JWTUtil;
import com.team.util.MD5Util;
import com.team.vo.StaffDeptVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginService extends ServiceImpl<StaffMapper, Staff> {

    @Resource
    private StaffMapper staffMapper;

    public ResponseDTO login(Staff staff) {
        String password = MD5Util.MD55(staff.getPassword());
        StaffDeptVO staffDeptVO = this.staffMapper.findStaffInfo(staff.getCode(), password);
        if (staffDeptVO != null) {
            // 验证用户状态
            if (staffDeptVO.getStatus() == 1) {
                String token = JWTUtil.generateToken(staffDeptVO.getId(),password);
                return Response.success(staffDeptVO, token); // 返回员工信息和token
            }
            return Response.error(BusinessStatusEnum.STAFF_STATUS_ERROR);
        }
        return Response.error("用户名或密码错误!");
    }
}
