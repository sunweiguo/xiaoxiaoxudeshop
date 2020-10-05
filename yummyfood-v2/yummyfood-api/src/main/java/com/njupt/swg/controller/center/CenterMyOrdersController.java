package com.njupt.swg.controller.center;

import com.njupt.swg.pojo.Orders;
import com.njupt.swg.service.center.IMyOrdersService;
import com.njupt.swg.utils.CommonJsonResult;
import com.njupt.swg.utils.PagedGridResult;
import com.njupt.swg.vo.OrderStatusCountsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author swg.
 * @Date 2020/9/19 16:37
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Api(value = "用户中心订单相关接口",tags = "用户中心订单相关接口")
@RestController
@RequestMapping("myorders")
public class CenterMyOrdersController {
    @Autowired
    private IMyOrdersService myOrdersService;

    @ApiOperation(value = "获得订单状态数概况", notes = "获得订单状态数概况", httpMethod = "POST")
    @PostMapping("/statusCounts")
    public CommonJsonResult statusCounts(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId){
        //TODO userid检查
        if(StringUtils.isBlank(userId)){
            return CommonJsonResult.errorMsg(null);
        }

        //查询首页的订单的四种状态的数量
        OrderStatusCountsVO result = myOrdersService.getOrderStatusCounts(userId);

        return CommonJsonResult.ok(result);
    }

    @ApiOperation(value = "查询订单动向", notes = "查询订单动向", httpMethod = "POST")
    @PostMapping("/trend")
    public CommonJsonResult trend(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId,
            @ApiParam(name = "page",value = "当前页",required = true)
            @RequestParam(name = "page",defaultValue = "0") Integer page,
            @ApiParam(name = "pageSize",value = "每页显示的数量",required = true)
            @RequestParam(name = "pageSize",defaultValue = "10") Integer pageSize) {

        if (StringUtils.isBlank(userId)) {
            return CommonJsonResult.errorMsg(null);
        }

        PagedGridResult grid = myOrdersService.getOrdersTrend(userId, page, pageSize);

        return CommonJsonResult.ok(grid);
    }

    @ApiOperation(value = "用户中心我的订单",notes = "用户中心我的订单",httpMethod = "POST")
    @PostMapping("query")
    public CommonJsonResult query(
            @ApiParam(name = "userId",value = "用户ID",required = true)
            @RequestParam String userId,
            @ApiParam(name = "orderStatus",value = "订单状态",required = false)
            @RequestParam Integer orderStatus,
            @ApiParam(name = "page",value = "当前页",required = true)
            @RequestParam(name = "page",defaultValue = "0") Integer page,
            @ApiParam(name = "pageSize",value = "每页显示的数量",required = true)
            @RequestParam(name = "pageSize",defaultValue = "10") Integer pageSize){
        return CommonJsonResult.ok(myOrdersService.queryMyOrders(userId,orderStatus,page,pageSize));
    }


    @ApiOperation(value="用户确认收货", notes="用户确认收货", httpMethod = "POST")
    @PostMapping("/confirmReceive")
    public CommonJsonResult confirmReceive(
            @ApiParam(name = "orderId", value = "订单id", required = true)
            @RequestParam String orderId,
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId) throws Exception {

        CommonJsonResult checkResult = checkUserOrder(userId, orderId);
        if (checkResult.getStatus() != HttpStatus.OK.value()) {
            return checkResult;
        }

        boolean res = myOrdersService.updateReceiveOrderStatus(orderId);
        if (!res) {
            return CommonJsonResult.errorMsg("订单确认收货失败！");
        }

        return CommonJsonResult.ok();
    }

    @ApiOperation(value="用户删除订单", notes="用户删除订单", httpMethod = "POST")
    @PostMapping("/delete")
    public CommonJsonResult delete(
            @ApiParam(name = "orderId", value = "订单id", required = true)
            @RequestParam String orderId,
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId) throws Exception {

        CommonJsonResult checkResult = checkUserOrder(userId, orderId);
        if (checkResult.getStatus() != HttpStatus.OK.value()) {
            return checkResult;
        }

        boolean res = myOrdersService.deleteOrder(userId, orderId);
        if (!res) {
            return CommonJsonResult.errorMsg("订单删除失败！");
        }

        return CommonJsonResult.ok();
    }

    private CommonJsonResult checkUserOrder(String userId, String orderId) {
        Orders order = myOrdersService.queryMyOrder(userId, orderId);
        if (order == null) {
            return CommonJsonResult.errorMsg("订单不存在！");
        }
        return CommonJsonResult.ok();
    }

}
