package com.hrm.service;

import com.hrm.mapper.SalaryMapper;
import com.hrm.entity.Salary;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 员工工资表 服务类
 * </p>
 *
 * @author qiujie
 * @since 2024-06-28
 */
@Service
public class  SalaryService extends ServiceImpl<SalaryMapper,Salary >{


public ResponseDTO add(Salary salary){
        if(save(salary)){
        return Response.success();
        }
        return Response.error();
        }

public ResponseDTO deleteById(Integer id){
        if(removeById(id)){
        return Response.success();
        }
        return Response.error();
        }

public ResponseDTO deleteBatch(List<Integer> ids){
        if(removeBatchByIds(ids)){
        return Response.success();
        }
        return Response.error();
        }


public ResponseDTO edit(Salary salary){
        if(updateById(salary)){
        return Response.success();
        }
        return Response.error();
        }


public ResponseDTO findById(Integer id){
    Salary salary =getById(id);
        if(salary !=null){
        return Response.success(salary);
        }
        return Response.error();
        }


public ResponseDTO list(Integer current,Integer size,String name){
        IPage<Salary> config=new Page<>(current,size);
        QueryWrapper<Salary> wrapper=null;
        if(name!=""&&name!=null){
        wrapper=new QueryWrapper<>();
        wrapper.like("name",name);
        }
        IPage<Salary> page=page(config,wrapper);
        // 将响应数据填充到map中
        Map map=new HashMap();
        map.put("pages",page.getPages());
        map.put("total",page.getTotal());
        map.put("list",page.getRecords());
        return Response.success(map);
        }

/**
* 数据导出
*
* @param response
* @return
*/
public ResponseDTO export(HttpServletResponse response)throws IOException{
        List<Salary> list=list();
        // 通过工具类创建对象，可以指定磁盘路径
        ExcelWriter writer=ExcelUtil.getWriter(true);
        // 一次性将list内的数据写入到excel，使用默认样式,强制输出标题
        writer.write(list,true);
        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String filename=URLEncoder.encode("数据","UTF-8");
        response.setHeader("Content-Disposition","attachment;filename="+filename+".xlsx");
        ServletOutputStream outputStream=response.getOutputStream();
        writer.flush(outputStream,true);
        outputStream.close();
        writer.close();
        return Response.success();
        }

/**
 * 数据导入
 *
 * @param file
 * @return
 */
public ResponseDTO imp(MultipartFile file)throws IOException{
        InputStream inputStream=file.getInputStream();
        ExcelReader reader=ExcelUtil.getReader(inputStream);
        List<Salary> list=reader.readAll(Salary.class);
        // IService接口中的方法.批量插入数据
        if(saveBatch(list)){
        return Response.success();
        }
        return Response.error();
        }


        }




