package com.caohao.bookshop.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caohao.bookshop.entity.Cart;
import com.caohao.bookshop.entity.CartVo;
import com.caohao.bookshop.entity.User;
import com.caohao.bookshop.entity.UserCartVo;
import com.caohao.bookshop.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Service("cartService")
public class CartService extends ServiceImpl<CartMapper, Cart> {
    @Autowired
    CartMapper cartMapper;

    /**
     * 根据购物车id查询记录
     */
    public List<CartVo> findCartListByIds(String ids){
        return cartMapper.findCartListByIds(Arrays.asList(ids));
    }
    /**
     * 根据用户查询购物车记录
     * @param userId
     * @return
     */

    public List<CartVo> findCartByUser(Integer userId){
        return cartMapper.findCartListByUserId(userId);
    }
    /**
     * 统计当前购物车的总计
     */
    public double getCartTiemsTotal(List<CartVo> list){
        double sum = 0.0;
        for (CartVo c:list) {
            sum+=c.getCount()*c.getNewPrice();
        }
        return sum;
    }
    /**
     * 将购物车信息转化为usercartvo类型
     */
    public UserCartVo wapperCart(List<CartVo> list){
        UserCartVo userCartVo = new UserCartVo();
        userCartVo.setNum(list.size());
        userCartVo.setTotalPrice(getCartTiemsTotal(list));
        return userCartVo;
    }
    /**
     * 删除购物车
     */
    public String batchDelete(String ids){
        if (ids!=null){
            String[] id = ids.split(",");
            cartMapper.deleteBatchIds(Arrays.asList(id));
        }
        return "success";
    }
}
