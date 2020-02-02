package serviceimpl;

import pojo.ModuleConfigData;
import mapper.ModuleConfigDataMapper;
import service.IModuleConfigDataService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wxc
 * @since 2019-06-15
 */
@Service
public class ModuleConfigDataServiceImpl extends ServiceImpl<ModuleConfigDataMapper, ModuleConfigData> implements IModuleConfigDataService {

}
