package so.dian.cmpp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import so.dian.cmpp.commons.MeidaRequestDataCustomArgumentResolver;

import java.util.List;

@Configuration
public class DefineAdapter extends WebMvcConfigurerAdapter {
    @Autowired
    MeidaRequestDataCustomArgumentResolver meidaRequestDataCustomArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(meidaRequestDataCustomArgumentResolver);
    }
}
