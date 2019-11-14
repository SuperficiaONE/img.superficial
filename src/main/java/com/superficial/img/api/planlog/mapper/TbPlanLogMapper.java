package com.superficial.img.api.planlog.mapper;


        import com.baomidou.mybatisplus.mapper.BaseMapper;
        import com.superficial.img.api.planlog.pojo.TbPlanLog;
        import org.apache.ibatis.annotations.Mapper;
        import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wxc
 * @since 2019-05-06
 */

@Mapper
@Repository
public interface TbPlanLogMapper extends BaseMapper<TbPlanLog> {

}
