package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import service.HelloService;

/**
 *
 * @author Heisaman
 */
public class HelloController implements Controller {

    protected final Log logger = LogFactory.getLog(getClass());

    private HelloService helloService;

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Returning hello view");
        return new ModelAndView("hello");
    }

    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }
}
