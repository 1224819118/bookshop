package com.caohao.bookshop.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caohao.bookshop.entity.Address;
import com.caohao.bookshop.mapper.AddressMapper;
import org.springframework.stereotype.Service;

@Service("addressService")
public class AddressService extends ServiceImpl<AddressMapper, Address> {
}
