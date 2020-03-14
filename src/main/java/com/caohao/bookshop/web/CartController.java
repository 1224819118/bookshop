package com.caohao.bookshop.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.caohao.bookshop.entity.Cart;
import com.caohao.bookshop.entity.CartVo;
import com.caohao.bookshop.entity.User;
import com.caohao.bookshop.entity.UserCartVo;
import com.caohao.bookshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;
    /**
     * 加入购物车
     */
    @RequestMapping("/add")
    public String add(Cart cart, HttpSession session){
        User user = (User) session.getAttribute("user");
        cart.setUserId(user.getId());
        //判断cart表中是否已经存在这个字段
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",cart.getUserId());
        queryWrapper.eq("book_id",cart.getBookId());
        Cart cart1 = (Cart) cartService.getOne(queryWrapper);
        if (cart1==null){
            cartService.save(cart);
        }else {
            cart1.setCount(cart1.getCount()+cart.getCount());
            cartService.updateById(cart1);
        }
        return "success";
    }

    /**
     *查询当前用户的购物车列表
     */
    @RequestMapping("/list")
    public String list(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        List<CartVo> cartByUser = cartService.findCartByUser(user.getId());

        //将用户的购物车信息存放到session中
        UserCartVo userCartVo = cartService.wapperCart(cartByUser);
        session.setAttribute("userCartInfo",userCartVo);

        model.addAttribute("cartList",cartByUser);
        return "cart";
    }

    /**
     * 更新购物车信息
     */
    @ResponseBody
    @RequestMapping("/update")
    public String update(HttpSession session,Cart cart){
        cartService.updateById(cart);
        User user = (User) session.getAttribute("user");
        List<CartVo> cartByUser = cartService.findCartByUser(user.getId());

        //将用户的购物车信息存放到session中
        UserCartVo userCartVo = cartService.wapperCart(cartByUser);
        session.setAttribute("userCartInfo",userCartVo);

        double price = cartService.getCartTiemsTotal(cartByUser);
        return String.valueOf(price);
    }
    /**
     * 删除购物车内的商品
     */
    @ResponseBody
    @RequestMapping("/delete")
    public String delete(String ids){
        return cartService.batchDelete(ids);
    }
}
