package com.bookloom.shared.controllers.bind;

import com.bookloom.shared.controllers.bind.annotation.RequestFilter;
import org.springframework.core.MethodParameter;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class RequestFilterArgumentResolver implements HandlerMethodArgumentResolver  {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestFilter.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        var annotation = parameter.getParameterAnnotation(RequestFilter.class);
        var filter = webRequest.getParameter("filter");
        if(filter == null) {
            return null;
        }

        try {
            return new BasicQuery(filter);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Filter cannot be parsed.", ex);
        }
    }
}
