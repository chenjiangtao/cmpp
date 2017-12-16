package so.dian.cmpp.annotation;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * 用于统一的接口入参解析和处理，如有特殊解析逻辑，最好自行扩展 HandlerMethodArgumentResolver 实现，不要放在此处
 *
 * @author will
 * @since 2016-08-13
 */
@Service
public class MeidaRequestDataCustomArgumentResolver implements HandlerMethodArgumentResolver {

    final static Logger logger = LoggerFactory.getLogger("biz");

    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(MeidaRequestData.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        String dataAsJson = webRequest.getParameter("data");
            try {
                HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
                logger.error(String.format("uri=%s, data=%s", request.getRequestURI(), dataAsJson));
            }catch (Exception e){
                logger.error(String.format("data=%s", dataAsJson));
            }
        return gson.fromJson(dataAsJson, parameter.getParameterType());
    }
}
