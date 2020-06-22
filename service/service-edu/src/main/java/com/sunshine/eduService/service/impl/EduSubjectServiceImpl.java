package com.sunshine.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sunshine.baseService.exception.GuliException;
import com.sunshine.common.utils.ResultCode;
import com.sunshine.eduService.entity.EduSubject;
import com.sunshine.eduService.entity.SubjectNestedVo;
import com.sunshine.eduService.entity.SubjectVo;
import com.sunshine.eduService.mapper.EduSubjectMapper;
import com.sunshine.eduService.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
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

    /**
     * 通过excel批量导入课程
     * @param file
     * @return
     */
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
                    String errorMsg = i+1 + "行表格数据为空，请输入数据";
                    msg.add(errorMsg);
                    continue;
                }
                //不为空，获取列数据
                Cell cellOne = row.getCell(0);
                //判断列数据是否为空
                if(cellOne == null){
                    String errorMsg = "第" + i+1 + "行数据为空";
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
                    String errorMsg = "第" + i+1 + "行数据为空";
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

    /**
     * 嵌套课程数据列表（一级分类和该课程下的二级分类）
     * @return
     */
    @Override
    public List<SubjectNestedVo> nestedList() {
        List<SubjectNestedVo> voList = new ArrayList<>();
        //获取一级分类的数据
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",0);
        wrapper.orderByAsc("sort","id");
        List<EduSubject> subjectList = baseMapper.selectList(wrapper);

        //获取二级分类的数据(直接获取所有的二级数据，减轻数据库服务器的压力)
        QueryWrapper<EduSubject> wrapper2 = new QueryWrapper<>();
        wrapper2.ne("parent_id",0);
        wrapper2.orderByAsc("sort","id");
        List<EduSubject> subjectList2 = baseMapper.selectList(wrapper2);

        //填充一级的vo数据
        for(EduSubject subject : subjectList){
            //创建一级类别vo对象
            SubjectNestedVo subjectNestedVo = new SubjectNestedVo();
            BeanUtils.copyProperties(subject, subjectNestedVo);
            List<SubjectVo> vos = new ArrayList<>();
            for (EduSubject subject2 : subjectList2){
                if(subject.getId().equals(subject2.getParentId())){
                    //创建二级类别vo对象
                    SubjectVo subjectVo = new SubjectVo();
                    BeanUtils.copyProperties(subject2, subjectVo);
                    vos.add(subjectVo);
                }
            }
            subjectNestedVo.setSubjectVoList(vos);
            voList.add(subjectNestedVo);
        }
        return voList;
    }

    /**
     * 保存课程一级分类
     * @param eduSubject
     * @return
     */
    @Override
    public void saveLevelOne(EduSubject eduSubject) {
        EduSubject existOneSubject = this.existOneSubject(eduSubject.getTitle());
        if(existOneSubject == null){
            super.save(eduSubject);
        }else {
            throw new GuliException(ResultCode.EXIST_ALREADY_DATA);
        }
    }

    @Override
    public void saveLevelTwo(EduSubject eduSubject) {
        EduSubject existTwoSubject = this.existTwoSubject(eduSubject.getTitle(), eduSubject.getParentId());
        if(existTwoSubject == null){
            super.save(eduSubject);
        }else {
            throw new GuliException(ResultCode.EXIST_ALREADY_DATA);
        }
    }

    //判断是否存在该一级分类的数据
    private EduSubject existOneSubject(String cellOneValue){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", cellOneValue);
        wrapper.eq("parent_id",0);
        return baseMapper.selectOne(wrapper);
    }

    //判断是否存在该二级分类的数据
    private EduSubject existTwoSubject(String cellTwoValue,String parentId){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", cellTwoValue);
        wrapper.eq("parent_id",parentId);
        return baseMapper.selectOne(wrapper);
    }
}
