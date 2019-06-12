package serviceimpl;

import pojo.ModuleConfig;
import mapper.ModuleConfigMapper;
import service.IModuleConfigService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wxc
 * @since 2019-06-04
 */
@Service
public class ModuleConfigServiceImpl extends ServiceImpl<ModuleConfigMapper, ModuleConfig> implements IModuleConfigService {

}
