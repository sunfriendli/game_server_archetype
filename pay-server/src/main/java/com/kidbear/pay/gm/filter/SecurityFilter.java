package com.kidbear.pay.gm.filter;

import com.kidbear.pay.gm.admin.Admin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 何金成
 * @ClassName: SecurityFilter
 * @Description: 过滤器
 * @date 2015年4月18日 上午10:21:07
 */
public class SecurityFilter implements Filter {

    private static final Logger logger = LoggerFactory
            .getLogger(SecurityFilter.class);
    private static List<String> extensive = new ArrayList<String>();
    private String exclusions = null;
    public static boolean isOpen = false;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        extensive.add("login");
        extensive.add("notify");
        extensive.add("route");
        extensive.add("core");
        extensive.add("pay");
        this.exclusions = filterConfig.getInitParameter("exclusions");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        if (!isOpen) {
            chain.doFilter(request, response);
            return;
        }
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(true);
        // 从session里取的用户信息
        Admin admin = (Admin) session.getAttribute("admin");
        String uri = ((HttpServletRequest) request).getRequestURI();
        // 判断如果没有取到用户信息,就跳转到登陆页面
        if (admin != null || checkExclusive(uri)) {
            chain.doFilter(request, response);
        } else {
            // 跳转到登陆页面
            res.sendRedirect(req.getContextPath() + "/admin/login");
        }
    }

    /**
     * @param uri
     * @return boolean
     * @throws
     * @Title: checkExclusive
     * @Description: 检查是否包含过滤的字段
     */
    private boolean checkExclusive(String uri) {
        uri = uri.replaceAll("pay", "");
        for (String exclusion : this.exclusions.split(",")) {
            if (uri.indexOf(exclusion) != -1) {
                return true;
            }
        }
        for (String tmp : extensive) {
            if (uri.contains(tmp)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void destroy() {
    }

}
