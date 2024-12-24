
package com.corelabs.menu.vo;

import com.corelabs.common.vo.CommonVO;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("menuVO")
public class MenuVO extends CommonVO {
    private String menuCd;
    private String menuNm;
    private String parentCd;
    private int depth;
    private String url;
    private String useYn;
    private String createUserId;
    private String createDt;
    private String updateUserId;
    private String updateDt;

}
