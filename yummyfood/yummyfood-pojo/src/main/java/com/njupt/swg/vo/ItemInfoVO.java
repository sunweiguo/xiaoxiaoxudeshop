package com.njupt.swg.vo;

import com.njupt.swg.pojo.Items;
import com.njupt.swg.pojo.ItemsImg;
import com.njupt.swg.pojo.ItemsParam;
import com.njupt.swg.pojo.ItemsSpec;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author swg.
 * @Date 2020/3/25 21:23
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Getter
@Setter
public class ItemInfoVO {
    private Items item;
    private List<ItemsImg> itemImgList;
    private List<ItemsSpec> itemSpecList;
    private ItemsParam itemParams;
}
