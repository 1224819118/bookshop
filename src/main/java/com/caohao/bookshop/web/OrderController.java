package com.caohao.bookshop.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.caohao.bookshop.entity.*;
import com.caohao.bookshop.service.AddressService;
import com.caohao.bookshop.service.CartService;
import com.caohao.bookshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private CartService cartService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private OrderService orderService;
    /**
     * 确认订单
     */
    @RequestMapping("/confirm")
    public String confirm(String ids, Model model, HttpSession session){
        //查询记录
        List<CartVo> cartListByIds = cartService.findCartListByIds(ids);
        //获取地址
        User user = (User) session.getAttribute("user");
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",user.getId());
        List<Address> list = addressService.list(queryWrapper);


        //将购买的商品信息添加到session中
        session.setAttribute("cartVos",cartListByIds);

        model.addAttribute("addressList",list);
        model.addAttribute("list",cartListByIds);
        return "confirm_order";
    }

    /**
     * 提交订单
     */
    @RequestMapping("/commitOrder")
    public String commitOrder(Integer addrId,HttpSession session){
        List<CartVo> cartVos = (List<CartVo>) session.getAttribute("cartVos");
        String flag = orderService.buy(cartVos,addrId,session);
        if(flag.equals("success")){
            //跳转至订单列表页
            return "redirect:/order/list";
        }else {
            return "redirect:/book/index";
        }
    }

    /**
     * 显示用户订单列表
     */
    @RequestMapping("/list")
    public String list(){
        return "order_list";
    }

    /**
     *
     * @param session
     * @param model
     * @return 获取用户订单信息
     */
    @RequestMapping("/getOrderListData")
    public String getOrderListData(HttpSession session, OrderQueryVo orderQueryVo, Model model){
        User user = (User) session.getAttribute("user");
        List<Order> orders = orderService.findUserOrder(user.getId(),orderQueryVo);
        model.addAttribute("orders",orders);
        model.addAttribute("pre",orderQueryVo.getPage() -1);
        model.addAttribute("next",orderQueryVo.getPage() + 1);
        model.addAttribute("cur",orderQueryVo.getPage());
        model.addAttribute("pages",orderService.findUserOrderPages(user.getId(),orderQueryVo));
        model.addAttribute("pageSize",orderQueryVo.getPageSize());
        return "orderData";
    }

}
