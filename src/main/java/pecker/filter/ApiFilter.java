package pecker.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Map;

@Slf4j
@WebFilter(filterName = "apiFilter",
        urlPatterns = "/*"
//        ,
//        initParams = {
//                @WebInitParam(name = "passURI", value = ".*login.*")
//        }
)
public class ApiFilter implements Filter {

    private String[] passURI;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        passURI = filterConfig.getInitParameter("passURI").split(",");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        log.info("================================address apiFilter=====================================");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //打印参数
        log.info("send {} request to {}", request.getMethod(), request.getRequestURL());
        log.info("content-type:{}", request.getHeader("content-type"));
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String value = headerNames.nextElement();
            log.info("header -- {} : {}", value, request.getHeader(value));
        }

        Map<String, String[]> parameterMap = request.getParameterMap();
        if (parameterMap != null) {
            for (Map.Entry parameter : parameterMap.entrySet()) {
                log.info("param -- {} : {}", parameter.getKey(), parameter.getValue());
            }
        }

        String reqURI = request.getRequestURI();
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        filterChain.doFilter(servletRequest, servletResponse);
//        boolean pass = isPass(reqURI);
//        if (pass) {
//            filterChain.doFilter(servletRequest, servletResponse);
//            return;
//        }
//        String token = request.getHeader("token");
//
//        boolean check = JwtToken.checkToken(token);
//
//        if (check) {
//            filterChain.doFilter(servletRequest, servletResponse);
//            return;
//        }
//        try {
//            response.setCharacterEncoding("utf-8");
//            response.getWriter().print("{\"code\":\"103\",\"message\":\"请重新登录\"}");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            response.getWriter().close();
//        }
    }

//    private boolean isPass(String reqURI) {
//        boolean pass = false;
//        for (String str : passURI) {
//            if (reqURI.matches(str)) {
//                log.info("---pass---");
//                pass = true;
//                break;
//            }
//        }
//        return pass;
//    }

    @Override
    public void destroy() {

    }
}
