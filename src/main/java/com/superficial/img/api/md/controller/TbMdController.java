package com.superficial.img.api.md.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.superficial.img.api.md.pojo.TbMd;
import com.superficial.img.api.md.service.TbMdCategoryService;
import com.superficial.img.api.md.service.TbMdService;
import com.superficial.img.api.md.vo.DeleteVo;
import com.superficial.img.api.md.vo.MdSearchVo;
import com.superficial.img.api.md.vo.TbMdVo;
import com.superficial.img.common.tool.CommonUtil;
import com.superficial.img.common.tool.JwtHelper;
import com.superficial.img.common.vo.LayUIPage;
import com.superficial.img.common.vo.ResultVO;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
@Slf4j
@RestController
public class TbMdController {
    @Autowired
    private TbMdCategoryService categoryService;
    @Autowired
    private TbMdService mdService;

    /**
     * 增加或者更新
     */
    @RequestMapping("/api/tbMd/addOrUpdate")
    public ResultVO add(@Validated TbMd md){
        try {
            if( CommonUtil.isEmpty(md.getMdId() )){
                md.setMdId(IdWorker.getId());
                md.setUserId(JwtHelper.getUserId());
                md.setCreateBy(JwtHelper.getLoginName());
                md.setUpdateBy(JwtHelper.getLoginName());
                mdService.insert(md);
            }else {
                md.setUpdateBy(JwtHelper.getLoginName());
               mdService.updateById(md);
            }
            return ResultVO.newSuccess("插入/更新成功",md);
        }catch (Exception e){
            log.error("新增文档分类出现异常",e);
            return ResultVO.newError(e.getMessage());
        }
    }

    /**
     * 删除或者批量删除
     */
    @RequestMapping("/api/tbMd/delete")
    public ResultVO deleteCategory(DeleteVo<Long> deleteVo){
        try {
            List<Long> list = deleteVo.getList();
            Long mdId = deleteVo.getId();
            if( !CommonUtil.isEmpty(list) ){
                boolean deleteFlag = mdService.delete(new EntityWrapper<TbMd>().in("md_id", list));
                return ResultVO.newSuccess("删除成功",deleteFlag);
            }
            if( !CommonUtil.isEmpty(mdId) ){
                boolean deleteFlag = mdService.delete(new EntityWrapper<TbMd>().eq("md_id", mdId));
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
    @RequestMapping("/api/tbMd/query")
    public ResultVO queryMd(Long mdId ){
        try {
            TbMdVo mdVo = mdService.selectMdVoById(mdId);
            return ResultVO.newSuccess("获取成功",mdVo);
        }catch (Exception e){
            log.error("获取文档分类出现异常",e);
            return ResultVO.newError(e.getMessage());
        }
    }
    /**
     *  分页查询
     */
    @RequestMapping("/api/tbMd/queryList")
    public Map querys(MdSearchVo mdSearchVo){
        LayUIPage layUIPage = new LayUIPage();
        try {
            Map<String,Object> map = mdService.selectMdVoList(mdSearchVo);
            return map;
        }catch (Exception e){
            log.error("获取文档分类出现异常",e);
            return layUIPage.setCode(-1).setMsg("获取文档分类出现异常"+e.getMessage()).toMap();
        }
    }

}
