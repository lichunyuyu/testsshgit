package com.one.controller;

import com.one.dao.HibernateTwo.IHibernateService;
import com.one.entity.User;
import com.one.entity.Vote;
import com.one.service.IUserManager;
import com.one.service.IVoteManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.util.jar.Pack200.Packer.ERROR;
import static org.apache.taglibs.standard.resources.Resources.getMessage;

/**
 * Created by vtstar on 2017/12/29.
 */
@Controller
@RequestMapping("/vote")
public class VoteController extends BaseController{

    @Autowired
    @Qualifier("dbService")
    private IHibernateService hibernateService;

    @Autowired
    private IVoteManager voteManager;

    @Autowired
    private IUserManager userManager;

    @RequestMapping("/jfreeChart5")
    public ModelAndView getJfreeChart5(HttpServletRequest request){
        return new ModelAndView("jfreeChart5");
    }
    @RequestMapping("/addVote")
    public ModelAndView getaddVote(){
        return new ModelAndView("vote/addVote");
    }
    @RequestMapping("/saveVoter")
    public String saveVote(Vote vote){
        vote.setVoteCount(0);
        voteManager.addVote(vote);

        return "redirect:/vote/getAllVote";
    }

    @RequestMapping("editVote")
    public String editVote(String id,HttpServletRequest request){
        Vote vote = voteManager.getVote(id);
        request.setAttribute("vote",vote);
        return "vote/editVote";
    }
    @RequestMapping("updateVote")
    public String updateVote(Vote vote,HttpServletRequest request){

        if(voteManager.updateVote(vote)){
            vote = voteManager.getVote(vote.getId());
            request.setAttribute("vote", vote);
            //return "/editUser";
            return "redirect:/vote/getAllVote";
        }else{
            return "/error";
        }
    }

    @RequestMapping("/getAllVote")
    public String getAllVote(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        List<Vote> votelist = voteManager.getAllVote();
    System.out.println("votelist="+votelist.get(0).getVoteName());
       request.setAttribute("voteList", votelist);
       //12-30 为了 js 判断
        request.setAttribute("message",null); //    否则还是会弹出
        request.getRequestDispatcher("/WEB-INF/jsp/voteManager.jsp").forward(request,response); // 加上这个 js  才会收到
        //request.getSession().setAttribute("voteList", votelist);
        return "/voteManager";
    }
    //进入投票系统验证  一人 一天只能投一次票
    @RequestMapping("/doFilter")
    public void doFilter(HttpServletRequest request,HttpServletResponse response
    ) throws ParseException, ServletException, IOException {
        System.out.println("ddofilter");
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String path = uri.substring(contextPath.length());
        System.out.println("uri="+uri+",contextPath="+contextPath+",path="+path); //uri=/vote/doFilter,contextPath=,path=/vote/doFilter
        if(path.equals("/getVote")){
           //...
        }

        User user = (User)request.getSession().getAttribute("user");
        if(user==null){
            request.setAttribute("message","对不起，你还没有登录，请先登录!");
            //request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response); // 加上这个 js  才会收到
            //return "redirect:/user/logintest";
            System.out.println("ddddddddofilter");
            response.sendRedirect("/user/logintest");

            //return "2"; //返回登录页面
        }
        user = userManager.getUser(user.getId());
        String recordDate = user.getRecordDate();
        if(user.getRecordDate()!=null){
            //格式化当前时间
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(currentTime);//转换成字符串

            Date one = formatter.parse(dateString);
            Date two = formatter.parse(recordDate);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long c = time1 - time2;
            if(c<24*60*60){
                request.setAttribute("message", "对不起，您已经投过票了，明天再来吧！");
                //request.getRequestDispatcher("/WEB-INF/jsp/voteManager.jsp").forward(request,response); // 加上这个 js  才会收到
               // return "redirect:/vote/getAllVote";
               // response.flushBuffer(); // 直接找不到
                response.sendRedirect("/vote/getAllVote");
               // response.flushBuffer();//没什么影响，还是收不到
                //--用ajax 返回  0 无 1 有
                //return "1";  // 已经投过票
               // new ModelAndView().addObject("message","对不起，您已经投过票了，明天再来吧！");

            }else{
                //return "0"; //可以投票
                //return "redirect:/vote/getVote";
                response.sendRedirect("/vote/getVote");
            }
        }else{
            response.sendRedirect("/vote/getVote");
            //return "redirect:/vote/getVote";
        }


    }

    protected void sendErrorMessage(Model model,
                                    String messageKey, Object... parameter) {
        model.addAttribute(ERROR, getMessage(messageKey,
                parameter));
    }

    @RequestMapping("/getVote")
    public String  getVote(HttpServletRequest request){
        List<Vote> votelist = voteManager.getAllVote();
        request.setAttribute("voteList", votelist);
        System.out.println("votelist="+votelist.size());
        return "/getVote";
    }

    @RequestMapping("/updateVoteCount")
    public String getJfreeChart5(Vote vote,HttpServletRequest request){
        System.out.println("updateVoteCount="+vote.getId()+","+vote.getVoteCount()+","+vote.getVoteName());
        if(vote.getVoteName()==null){
            System.out.println("vote.getVoteName()==null,,");
           //Vote vote2 = voteManager.getVote(vote.getId());
           //vote.setVoteName(vote2.getVoteName());
            vote = voteManager.getVote(vote.getId());
        }
        //格式化当前时间
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);//转换成字符串
        vote.setRecordDate(dateString);
        //更新 投票者即用户的时间
       User user = (User)request.getSession().getAttribute("user");
       user.setRecordDate(dateString);
        if(userManager.updateUser(user)){
            if(voteManager.updateVoteCount(vote)){
                vote = voteManager.getVote(vote.getId());
                request.setAttribute("vote", vote);
                //return "/editUser";
                return "redirect:/vote/getVote";
            }else{
                return "/error";
            }
        }else{
            return "/error";
        }
    }

}
