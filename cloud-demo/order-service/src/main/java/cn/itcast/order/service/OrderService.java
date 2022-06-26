package cn.itcast.order.service;

import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import cn.itcast.order.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RestTemplate restTemplate;

    //    public Order queryOrderById(Long orderId) {
//        // 1.查询订单
//        Order order = orderMapper.findById(orderId);
//        //向UserService服务端发送请求，查询当前订单所属用户信息
//        //先创建url路径,根据userService服务名称从服务端拉取对应的地址信息
//        String url = "http://localhost:8081/user/" + order.getUserId();
//        //发送远程访问请求
//        //参数1：请求路径，参数2：返回结果类型
//        //String json = restTemplate.getForObject(url,User.class);
//        //restTemplate可以将结果进行解析
//        User user = restTemplate.getForObject(url, User.class);
//        //将查询结果封装到订单对象中
//        order.setUser(user);
//        // 4.返回
//        return order;
//    }
    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        //向UserService服务端发送请求，查询当前订单所属用户信息
        //先创建url路径,根据userService服务名称从服务端拉取对应的地址信息
        String url = "http://userservice/user/" + order.getUserId();
        //发送远程访问请求
        //参数1：请求路径，参数2：返回结果类型
        //String json = restTemplate.getForObject(url,User.class);
        //restTemplate可以将结果进行解析
        User user = restTemplate.getForObject(url, User.class);
        //将查询结果封装到订单对象中
        order.setUser(user);
        // 4.返回
        return order;
    }
}
