package com.smile.food.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.smile.food.annotation.RoleAnnotation;
import com.smile.food.annotation.UserLoginToken;
import com.smile.food.config.FoodConfig;
import com.smile.food.config.UserConfig;
import com.smile.food.entity.FoodInfoEntity;
import com.smile.food.model.User;
import com.smile.food.service.UserService;
import com.smile.food.utils.ResultUtils;
import org.attoparser.util.TextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.TextUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户控制器
 */
@Controller
@RequestMapping("/user")
public class UserController  {

    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/add")
    public Object addUser(User user){
        int result =userService.addUser(user);
        if (result==1){
            return ResultUtils.success("注册成功");
        }else {
            return ResultUtils.error(99,"注册失败");
        }
    }

    @ResponseBody
    @GetMapping("/all")
    public Object findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "1") int pageSize){

        PageInfo<User> allUser = userService.findAllUser(pageNum, pageSize);
        List<User> list = allUser.getList();


        return ResultUtils.success(list);

    }


    @ResponseBody
    @GetMapping("/page/{c}/{num}")
    public Object findListByPage(@PathVariable("c") Integer c, @PathVariable("num") Integer num){
        List<User> list = userService.findListByPage(c, num);
        return ResultUtils.success(list);
    }

    @ResponseBody
    @PostMapping(value = "/login", produces = "application/json;charset=UTF-8")
    public Object login(@RequestBody JSONObject jsonObject){
        System.out.println("入参: " +jsonObject.toJSONString());
        String phone = jsonObject.getString("phone");
        String pwd = jsonObject.getString("password");
        Object o = userService.login(phone, pwd);
        if (o instanceof String){
            ResultUtils.error(99, o.toString());
        }else {
            return ResultUtils.success(o);
        }
        return jsonObject.toJSONString();
    }

    @UserLoginToken
    @ResponseBody
    @GetMapping("/info")
    @Cacheable(value = "users")
    public Object getUserInfo(@RequestHeader(value="token") String token){

        User user=userService.findUserByToken(token);
        user.setToken(token);
        return ResultUtils.success(user);
    }

    @RoleAnnotation(leave = UserConfig.LEAVE_ADMIN)
    @UserLoginToken
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(){
        return "admin";
    }


    @RoleAnnotation(leave = UserConfig.LEAVE_NORMAL)
    @UserLoginToken
    @RequestMapping(value = "/admins", method = RequestMethod.GET)
    @ResponseBody
    public Object admins(){
        return ResultUtils.success("权限通过");
    }




 /*   @PostMapping("/upload")
    @ResponseBody
    public Object upload(@RequestParam("file") MultipartFile file ){
        if (file.isEmpty()) {
            return ResultUtils.error(99,"文件不存在");
        }
        String fileName = file.getOriginalFilename()+".jpg";
        String filePath = "/Users/yaojiulong/file/imgs/";
        File dest = new File(filePath + fileName);

        try {
            file.transferTo(dest);
            return ResultUtils.success("上传成功");
        }catch (Exception e){
            return ResultUtils.error(99,"上传失败");
        }

    }
*/

    /**
     * 多个图片和多个参数上传
     * @param entity
     * @return
     */
    @PostMapping("/upload")
    @ResponseBody
    public Object upload(FoodInfoEntity entity){
        System.out.println("name "+entity.getName());
        System.out.println("des "+entity.toString());
        MultipartFile[] files = entity.getFile_step();
        List<String> imgs=new ArrayList<>();
        for (int i = 0; i <files.length ; i++) {
            System.out.println("file Name "+files[i].getOriginalFilename());
            String fileName = files[i].getOriginalFilename();
            String filePath = FoodConfig.IMAGE_PATH;
            File dest = new File(filePath + fileName);
            try {
                files[i].transferTo(dest);
                imgs.add(FoodConfig.IMAGE_BASE_URL+fileName);
            }catch (Exception e){
                return ResultUtils.error(99,"上传失败");
            }
        }

        return ResultUtils.success(imgs);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String upload() {
        System.out.println("上传");
        return "upload";
    }


}
