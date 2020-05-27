//package com.wang.session;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
//import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
//import org.apache.shiro.web.util.WebUtils;
//
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import java.io.Serializable;
//
///**
// * @author wzq
// * @date 2020/5/26
// * @DESC
// */
//public class CustomSessionManager extends DefaultWebSessionManager {
//
//    /**
//     * 头信息中指定sessionId获取方式
//     *  包含信息 ：请求头
//     *              Authorization，sessionId
//     * */
//    @Override
//    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
//        String id = WebUtils.toHttp(request).getHeader("Authorization");
//        if (StringUtils.isEmpty(id)){
//            //  如果没有携带生成兴的sessionId
//            return super.getSessionId(request, response);
//        }else {
//            // 请求头信息，Bearer
////            id = id.replaceAll("Bearer ", Constants.EMPTY);
//            // 返回sessionId
//            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,"header");
//            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID,id);
//            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID,Boolean.TRUE);
//            return id;
//        }
//    }
//
//
//}
