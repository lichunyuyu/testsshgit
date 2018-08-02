package com.one.controller;

import com.one.dao.HibernateTwo.IHibernateService;
import com.one.entity.User;
import com.one.service.IUserManager;
import com.one.swing.jframe.LoginJFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;


/**
 * Created by vtstar on 2017/12/7.
 */

@Controller
@RequestMapping("/user")
// 继承 extends BaseController   不知道作甚么（不继承也可以）
public class UserController  extends BaseController{

    /**
     * hibernateService 后加的，测试 双数据库，测试testfirst数据库  （不创建 entity层）
     * */
    @Autowired
    @Qualifier("dbService")
    private IHibernateService hibernateService;

   // @Resource(name="userManager")// 获取spring配置文件中bean的id为userManager的，并注入

    /**
     * @Service("userService")注解是告诉Spring，
     * 当Spring要创建UserServiceImpl的的实例时，bean的名字必须叫做"userService"，
     * 这样当Action需要使用UserServiceImpl的的实例时,就可以由Spring创建好的"userService"，然后注入给Action。
     * 例 ：userManager .
     * @Qualifier("userManager")    当Usermanager  实例的名字不是
     * */
   @Autowired
    private IUserManager userManager;

   //12-28 //实例四：将类似实例三生成的图片嵌入到JSP页面中去。  jfreeChart.jsp
   @RequestMapping("/jfreeChart")
   public ModelAndView getJfreeChart(HttpServletRequest request){
       System.out.println("UserController=/jfreeChartn");
//        log.debug("控制器查找到请求的路径！");
       //String viewName = userManager.getUserName(request);
       return new ModelAndView("jfreeChart");
   }

   //也可以 。
    @RequestMapping("/logintest")
    public ModelAndView getLogin(HttpServletRequest request){
        System.out.println("UserController=/login");
//        log.debug("控制器查找到请求的路径！");
        //String viewName = userManager.getUserName(request);
        return new ModelAndView("login");
    }

    //swing 桌面应用 窗口程序
    @RequestMapping("/loginframe")
    public void loginframe(HttpServletRequest request){
        System.out.println("UserController.loginframe");
        LoginJFrame loginJFrame = new LoginJFrame();
        loginJFrame.setVisible(true);

        //return "/main";
    }


    //  注意 ： 视图 解析器的 后缀 .jsp
   @RequestMapping("/login")
   public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
       //12-29添加用户session
       //1.得到用户名和密码
       String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        //2.将信息封装
       User user = new User();
       user.setUserName(userName);
       user.setPassword(password);
       //3.调service方法 登录方法
        User u = userManager.getLogin(user);
        if(u == null){
            request.setAttribute("message","登陆失败，用户名或密码错误！");
            //登陆失败，请求转发到登录页面显示错误信息message
            try {
                request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);
                return "/login";
            } catch (ServletException e) {
                System.out.println("登录异常");
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            // 登录成功 --- 存到session
            request.getSession().setAttribute("user",u);
            //重定向 到 下个页面
//            response.sendRedirect(request.getContextPath()+"/main.jsp");
            //return "redirect:/user/main";
            return "redirect:/vote/getAllVote";
       }
       return "";
   }

    @RequestMapping("/main")
    public String loginmain(HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println("UserController.main");
        List<User> userlist = userManager.getAllUser();
        request.setAttribute("userList", userlist);
        //return "/login.jsp";
        return "/main";
    }


    @RequestMapping("/toAddUser")
    public String toAddUser(){
        System.out.println("UserController.toAddUser");
        return "/addUser";
    }

    @RequestMapping("/addUser")// 请求url地址映射，类似Struts的action-mapping
    public String addUser(User user){
        System.out.println("UserController.addUser");
        userManager.addUser(user);

        return "redirect:/user/getAllUser";
    }

    @RequestMapping("/getAllUser")
    public String getAllUser(HttpServletRequest request){
        System.out.println("UserController.userManager");
        List<User> userlist = userManager.getAllUser();
        System.out.println("UserController.userManager.userlist="+userlist);
        request.setAttribute("userList", userlist);

        //测试 testfirst 数据库
        String sql = "select * from userone";
        List<Map> list = hibernateService.queryForList(sql);
        request.setAttribute("mapList", list);
        return "/userManager";
    }

    @RequestMapping("/delUser")
    public void delUser(String id,HttpServletResponse response){
        String result = "{\"result\":\"error\"}";
        if(userManager.delUser(id)){
            result = "{\"result\":\"success\"}";
        }
        PrintWriter out = null;
        response.setContentType("application/json");

        try {
            out = response.getWriter();
            out.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/getUser")
    public String getUser(String id,HttpServletRequest request){
        User user = userManager.getUser(id);
        System.out.println("/getUser"+user.getId());
        request.setAttribute("user", user);
        return "/editUser";
    }

    @RequestMapping("/updateUser")
    public String updateUser(User user,HttpServletRequest request){

        if(userManager.updateUser(user)){
            user = userManager.getUser(user.getId());
            request.setAttribute("user", user);
            //return "/editUser";
            return "redirect:/user/getAllUser";
        }else{
            return "/error";
        }

    }
}
