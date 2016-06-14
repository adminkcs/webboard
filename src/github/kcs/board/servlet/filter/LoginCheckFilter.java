package github.kcs.board.servlet.filter;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginCheckFilter
 */
@WebFilter(dispatcherTypes = {DispatcherType.REQUEST }
                    , urlPatterns = { "/*" })
public class LoginCheckFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginCheckFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // TODO Auto-generated method stub
        // place your code here
        // 설계 미스!! 매번 다운캐스팅을 해줘야 합니다.
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        System.out.println("   ##### 로그인 필터A!!!!!!");
        String uri = parseUri ( req);
        
        // String [] checkUrls = new String[]{"/write", "/doWrite", "/edit", "/doEdit", "/delete"};
        if ( loginCheckingRequired( checkUrls, uri) ) {
            
            if ( !isLogined ( req ) ) {
                res.sendRedirect("login");
                return;
            }
        }
        // pass the request along the filter chain
        chain.doFilter(request, response);
        
        System.out.println("   ##### 로그인 필터B!!!!!!");
    }

    /**
     * 주어진 uri 가 로그인 확인을 해야하는 uri인지를 판단합니다.
     * @param checkUrls 로그인 확인이 필요한 uri들
     * @param uri 사용자가 요청한 uri 
     * @return
     */
    private boolean loginCheckingRequired(String[] checkUrls, String uri) {
        for(int i=0; i<checkUrls.length; i++){
            if(uri.equals(checkUrls[i])){
                return true;                
            }
        }
        return false;
    }

    private String parseUri(HttpServletRequest request) {
        String ctxpapth = request.getContextPath();
        String fullPath = request.getRequestURI();
        String uri = fullPath.substring(ctxpapth.length());
        return uri;
    }
    
    /**
     *  주어진 요청을 보낸 클라이언트가 현재 로그인했는지를 판단합니다.
     * @param request
     * @return 로그인 되었으면 true, 그렇지 않으면 false를 반환합니다.
     */
    private boolean isLogined(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if( session.getAttribute("loginUser") != null ){
            return true;
        } else {
            return false;
        }
    }

    private String [] checkUrls;

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        ServletContext context = fConfig.getServletContext();
        String urls = context.getInitParameter("login.url"); // "/url,/url,/ur" --> {"/url", "/url"... }
        checkUrls = urls.split(",");
        for (int i = 0; i < checkUrls.length; i++) {
            checkUrls[i] = checkUrls[i].trim();
        }
        System.out.println("  #### " + urls);
    }

}
