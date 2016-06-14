package github.kcs.board.util;

import javax.servlet.http.HttpServletRequest;

public class WebUtil {

    public static int Int ( HttpServletRequest req, String param, int defaultNum) {
        try {
            return Integer.parseInt(req.getParameter(param));
        } catch ( Exception e) {
            return defaultNum;
        }
    }
}
