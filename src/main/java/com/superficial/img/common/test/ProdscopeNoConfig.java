package com.superficial.img.common.test;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdscopeNoConfig extends BaseRowModel {
    @ExcelProperty("data_id")
    private String dataId;
    @ExcelProperty("prodscopeno_id")
    private String prodscopenoId;
    @ExcelProperty("prodscopeno_config_id")
    private String prodscopenoConfigId;
    @ExcelProperty("cust_apply_id")
    private String custApplyId;
    @ExcelProperty("branch_id")
   private String branchId;
}
