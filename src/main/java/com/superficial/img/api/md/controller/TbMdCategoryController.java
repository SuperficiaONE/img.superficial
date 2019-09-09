package com.superficial.img.api.md.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.superficial.img.api.md.pojo.TbMdCategory;
import com.superficial.img.api.md.service.TbMdCategoryService;
import com.superficial.img.api.md.service.TbMdService;
import com.superficial.img.api.md.vo.DeleteVo;
import com.superficial.img.api.md.vo.MdCategorySearchVo;
import com.superficial.img.common.tool.CommonUtil;
import com.superficial.img.common.tool.JwtHelper;
import com.superficial.img.common.vo.LayUIPage;
import com.superficial.img.common.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class TbMdCategoryController {
    @Autowired
    private TbMdCategoryService categoryService;

    /**
     * 增加或者更新
     */
    @RequestMapping("/api/tbMdCategory/addOrUpdate")
    public ResultVO add(@Validated TbMdCategory category){
        try {
            if(CommonUtil.isEmpty(category.getMdCategoryName())){
                return ResultVO.newFail("分类名称不能为空");
            }
            Integer count =categoryService.selectCount(new EntityWrapper<TbMdCategory>()
                    .eq("md_category_name", category.getMdCategoryName() ));
            if(count>0){
                return ResultVO.newFail("该分类:"+category.getMdCategoryName()+"已存在");
            }
            if(CommonUtil.isEmpty(category.getMdCategoryId())){
                category.setMdCategoryId(IdWorker.getId());
                category.setUpdateBy(JwtHelper.getLoginName());
                category.setCreateBy(JwtHelper.getLoginName());
                categoryService.insert(category);
                return ResultVO.newSuccess("插入成功",category);
            }else {
                category.setUpdateBy(JwtHelper.getLoginName());
                categoryService.updateById(category);
                return ResultVO.newSuccess("更新成功",category);

            }
        }catch (Exception e){
            log.error("新增文档分类出现异常",e);
            return ResultVO.newError(e.getMessage());
        }
    }

    /**
     * 删除或者批量删除
     */
    @RequestMapping("/api/tbMdCategory/delete")
    public ResultVO deleteCategory(DeleteVo<String> deleteVo){
        try {
            List<String> list = deleteVo.getList();
            String mdCategoryId = deleteVo.getId();
            if(!CommonUtil.isEmpty(list)){
                Boolean deleteFlag= categoryService.delete(new EntityWrapper<TbMdCategory>().in("md_category_id",list));
                return ResultVO.newSuccess("删除成功",deleteFlag);
            }
            if(!CommonUtil.isEmpty(mdCategoryId)){
                Boolean deleteFlag= categoryService.delete(new EntityWrapper<TbMdCategory>().eq("md_category_id",mdCategoryId));
                return ResultVO.newSuccess("删除成功",deleteFlag);
            }
            return ResultVO.newFail("参数不能为空");

        }catch (Exception e){
            log.error("删除文档分类出现异常",e);

            return ResultVO.newError(e.getMessage());
        }
    }

    /**
     * 查询单个
     */
    @RequestMapping("/api/tbMdCategory/query")
    public ResultVO queryCategory(Long mdCategoryId ){
        try {
            TbMdCategory category = categoryService.selectOne(
                    new EntityWrapper<TbMdCategory>()
                            .eq("md_category_id", mdCategoryId));
            return ResultVO.newSuccess("获取成功",category);
        }catch (Exception e){
            log.error("获取文档分类出现异常",e);
            return ResultVO.newError(e.getMessage());
        }
    }
    /**
     *  分页查询
     */
    @RequestMapping("/api/tbMdCategory/queryList")
    public Map queryCategory(MdCategorySearchVo categorySearchVo){
        LayUIPage layUIPage = new LayUIPage();
        try {
            Map map = categoryService.selectLayUIpage(categorySearchVo);
            return map;
        }catch (Exception e){
            log.error("获取文档分类出现异常",e);
            return layUIPage.setCode(-1).setMsg("获取文档分类出现异常"+e.getMessage()).toMap();
        }
    }


}
