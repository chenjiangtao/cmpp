package so.dian.cmpp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Preload Controller
 *
 * @author will
 * @since 2016-08-24
 */

@RestController
public class PreloadController {

    private static volatile boolean running = true;

    @RequestMapping("/preload")
    public String preload(HttpServletResponse response) throws IOException {
        if (running) {
            return "OK";
        } else {
            response.sendError(404);
            return null;
        }
    }

    @RequestMapping("/running")
    public void running(@RequestParam Integer running) throws IOException {
        this.running = running == 1 ? true : false;
    }
}
