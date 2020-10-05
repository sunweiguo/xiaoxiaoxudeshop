package com.njupt.swg.controller.center;

import com.njupt.swg.bo.OrderItemsCommentBO;
import com.njupt.swg.enums.YesOrNo;
import com.njupt.swg.pojo.OrderItems;
import com.njupt.swg.pojo.Orders;
import com.njupt.swg.service.center.IMyCommentsService;
import com.njupt.swg.service.center.IMyOrdersService;
import com.njupt.swg.utils.CommonJsonResult;
import com.njupt.swg.utils.PagedGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author swg.
 * @Date 2020/10/5 18:51
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Api(value = "用户中心评价模块", tags = {"用户中心评价模块相关接口"})
@RestController
@RequestMapping("mycomments")
public class MyCommentsController {

    @Autowired
    private IMyCommentsService myCommentsService;
    @Autowired
    private IMyOrdersService myOrdersService;

    @ApiOperation(value = "查询我的评价", notes = "查询我的评价", httpMethod = "POST")
    @PostMapping("/query")
    public CommonJsonResult query(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId,
            @ApiParam(name = "page",value = "当前页",required = true)
            @RequestParam(name = "page",defaultValue = "0") Integer page,
            @ApiParam(name = "pageSize",value = "每页显示的数量",required = true)
            @RequestParam(name = "pageSize",defaultValue = "10") Integer pageSize) {

        if (StringUtils.isBlank(userId)) {
            return CommonJsonResult.errorMsg(null);
        }

        PagedGridResult grid = myCommentsService.queryMyComments(userId,
                page,
                pageSize);

        return CommonJsonResult.ok(grid);
    }


    @ApiOperation(value = "查询订单列表", notes = "查询订单列表", httpMethod = "POST")
    @PostMapping("/pending")
    public CommonJsonResult pending(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId,
            @ApiParam(name = "orderId", value = "订单id", required = true)
            @RequestParam String orderId) {

        // 判断用户和订单是否关联
        CommonJsonResult checkResult = checkUserOrder(userId, orderId);
        if (checkResult.getStatus() != HttpStatus.OK.value()) {
            return checkResult;
        }
        // 判断该笔订单是否已经评价过，评价过了就不再继续
        Orders myOrder = (Orders)checkResult.getData();
        if (myOrder.getIsComment().equals(YesOrNo.YES.type)) {
            return CommonJsonResult.errorMsg("该笔订单已经评价");
        }

        List<OrderItems> list = myCommentsService.queryPendingComment(orderId);

        return CommonJsonResult.ok(list);
    }

    private CommonJsonResult checkUserOrder(String userId, String orderId) {
        Orders order = myOrdersService.queryMyOrder(userId, orderId);
        if (order == null) {
            return CommonJsonResult.errorMsg("订单不存在！");
        }
        return CommonJsonResult.ok(order);
    }


    @ApiOperation(value = "保存评论列表", notes = "保存评论列表", httpMethod = "POST")
    @PostMapping("/saveList")
    public CommonJsonResult saveList(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId,
            @ApiParam(name = "orderId", value = "订单id", required = true)
            @RequestParam String orderId,
            @RequestBody List<OrderItemsCommentBO> commentList) {

        System.out.println(commentList);

        // 判断用户和订单是否关联
        CommonJsonResult checkResult = checkUserOrder(userId, orderId);
        if (checkResult.getStatus() != HttpStatus.OK.value()) {
            return checkResult;
        }
        // 判断评论内容list不能为空
        if (commentList == null || commentList.isEmpty() || commentList.size() == 0) {
            return CommonJsonResult.errorMsg("评论内容不能为空！");
        }

        myCommentsService.saveComments(orderId, userId, commentList);
        return CommonJsonResult.ok();
    }
}
