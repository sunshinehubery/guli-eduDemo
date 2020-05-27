package com.sunshine.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sunshine.baseService.exception.GuliException;
import com.sunshine.common.utils.ResultCode;
import com.sunshine.eduService.entity.EduSubject;
import com.sunshine.eduService.mapper.EduSubjectMapper;
import com.sunshine.eduService.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author sunshine
 * @since 2020-05-26
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public List<String> importSubject(MultipartFile file) {
        try {
            //用户存储错误信息
            List<String> msg = new ArrayList<>();
            //获取文件输入流
            InputStream in = file.getInputStream();
            //创建workBook
            Workbook workbook = new HSSFWorkbook(in);
            //获取sheet
            Sheet sheet = workbook.getSheetAt(0);
            //获取表格中每行数据
            int rowNum = sheet.getLastRowNum();
            //遍历
            for(int i = 1;i <= rowNum;i++){
                Row row = sheet.getRow(i);
                //判断该行是否有数据
                if(row == null){
                    String errorMsg = i + "行表格数据为空，请输入数据";
                    msg.add(errorMsg);
                    continue;
                }
                //不为空，获取列数据
                Cell cellOne = row.getCell(0);
                //判断列数据是否为空
                if(cellOne == null){
                    String errorMsg = "第" + i + "行数据为空";
                    msg.add(errorMsg);
                    continue;
                }
                //b不为空，判断数据库是否存在，不存在存入数据库
                String cellOneValue = cellOne.getStringCellValue();
                EduSubject eduSubjectOneExit = this.existOneSubject(cellOneValue);
                //存储一级分类id(为该分类下二级分类准备)
                String id_parent = null;
                if(eduSubjectOneExit == null){
                    //存储添加
                    EduSubject eduSubject = new EduSubject();
                    eduSubject.setTitle(cellOneValue);
                    eduSubject.setParentId("0");
                    eduSubject.setSort(0);
                    baseMapper.insert(eduSubject);
                    id_parent = eduSubject.getId();
                }else{
                    id_parent = eduSubjectOneExit.getId();
                }
                //获取第二列的数据
                Cell cellTwo = row.getCell(1);
                //判断列数据是否为空
                if(cellTwo == null){
                    String errorMsg = "第" + i + "行数据为空";
                    msg.add(errorMsg);
                    continue;
                }
                String cellTwoValue = cellTwo.getStringCellValue();
                EduSubject eduSubjectTwoExit = this.existTwoSubject(cellTwoValue, id_parent);
                if(eduSubjectTwoExit == null) {
                    EduSubject twoSubject = new EduSubject();
                    twoSubject.setTitle(cellTwoValue);
                    twoSubject.setParentId(id_parent);
                    twoSubject.setSort(0);
                    baseMapper.insert(twoSubject);
                }
            }
            return msg;
        } catch (IOException e) {
            e.printStackTrace();
            throw new GuliException(ResultCode.ERROR_INPUT_FILE);
        }
    }

    //判断是否存在该一级分类的数据
    private EduSubject existOneSubject(String cellOneValue){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", cellOneValue);
        wrapper.eq("parent_id",0);
        return baseMapper.selectOne(wrapper);
    }

    //判断是否存在该一级分类的数据
    private EduSubject existTwoSubject(String cellTwoValue,String parentId){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", cellTwoValue);
        wrapper.eq("parent_id",parentId);
        return baseMapper.selectOne(wrapper);
    }
}
