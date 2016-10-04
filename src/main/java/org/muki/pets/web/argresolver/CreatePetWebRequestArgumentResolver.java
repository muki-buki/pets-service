package org.muki.pets.web.argresolver;

import org.muki.pets.web.CreatePetWebRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

public class CreatePetWebRequestArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private HttpMessageConverter<Object> jsonConverter;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return CreatePetWebRequest.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        final HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpInputMessage inputMessage = new ServletServerHttpRequest(request) {
            @Override
            public InputStream getBody() throws IOException {
                return request.getInputStream();
            }
        };
        if (!jsonConverter.canRead(CreatePetWebRequest.class,
                inputMessage.getHeaders().getContentType())) {
            throw new HttpMediaTypeNotSupportedException("Media type is not supported");
        }
        if (request.getContentLength() <= 0) {
            return null;
        }
        CreatePetWebRequest resolvedRequest =
                (CreatePetWebRequest) jsonConverter.read(CreatePetWebRequest.class, inputMessage);


        return resolvedRequest;
    }

}