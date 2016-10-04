package org.muki.pets.web.argresolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.muki.pets.web.CreatePetWebRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;

import java.lang.reflect.Method;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CreatePetWebRequestArgumentResolverTestConfig.class)
public class CreatePetWebRequestArgumentResolverTest {

    private static MethodParameter parameter;
    @Autowired
    private HandlerMethodArgumentResolver argumentResolver;
    @Autowired
    private ObjectMapper jacksonMapper;

    @BeforeClass
    public static void beforeClass() throws Exception {
        Method handlerMethod1 = CreatePetWebRequestArgumentResolverTest.class.getDeclaredMethod("handlerMethod1",
                CreatePetWebRequest.class);
        parameter = new MethodParameter(handlerMethod1, 0);
    }

    @SuppressWarnings("unused")
    private void handlerMethod1(CreatePetWebRequest request) {
    }

    @SuppressWarnings("unused")
    private void handlerMethod2(Object request) {
    }

    @Test
    public void supportsParameter_yes() throws Exception {
        Assert.assertTrue(argumentResolver.supportsParameter(parameter));
    }

    @Test
    public void supportsParameter_no() throws Exception {
        Method handlerMethod2 = this.getClass().getDeclaredMethod("handlerMethod2", Object.class);
        MethodParameter parameter = new MethodParameter(handlerMethod2, 0);
        Assert.assertFalse(argumentResolver.supportsParameter(parameter));
    }

    @Test(expected = HttpMediaTypeNotSupportedException.class)
    public void resolveArgument_unsupportedMediaType() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setContentType(MediaType.TEXT_PLAIN_VALUE);
        argumentResolver.resolveArgument(parameter, null, new ServletWebRequest(request), null);
    }

    @Test
    public void resolveArgument_noContent() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setContentType(MediaType.APPLICATION_JSON_VALUE);
        Assert.assertNull(argumentResolver.resolveArgument(parameter, null, new ServletWebRequest(request), null));
    }
}