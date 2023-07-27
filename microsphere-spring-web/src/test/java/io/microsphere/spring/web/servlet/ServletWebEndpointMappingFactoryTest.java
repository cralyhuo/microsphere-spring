/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.microsphere.spring.web.servlet;

import io.microsphere.spring.web.metadata.WebEndpointMapping;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.mock.web.MockServletContext;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServlet;
import java.util.Optional;

import static io.microsphere.util.ArrayUtils.of;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@link ServletWebEndpointMappingFactory} Test
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see ServletWebEndpointMappingFactory
 * @since 1.0.0
 */
public class ServletWebEndpointMappingFactoryTest {

    private String servletName;

    private String url;

    private TestServletContext servletContext;

    private MockServletConfig servletConfig;

    private TestServlet testServlet;

    private ServletWebEndpointMappingFactory factory;

    @BeforeEach
    public void init() throws ServletException {
        servletName = "test-servlet";
        url = "/test";
        servletContext = new TestServletContext();

        servletConfig = new MockServletConfig(servletName);
        this.testServlet = new TestServlet();
        this.testServlet.init(servletConfig);
        this.factory = new ServletWebEndpointMappingFactory(servletContext);

        ServletRegistration.Dynamic dynamic = this.servletContext.addServlet(servletName, this.testServlet);
        dynamic.addMapping(url);
    }

    @Test
    public void testCreate() {
        Optional<WebEndpointMapping<?>> webEndpointMapping = factory.create(this.testServlet);
        webEndpointMapping.ifPresent(mapping -> {
            assertEquals(this.servletName, mapping.getSource());
            assertArrayEquals(of(this.url), mapping.getPatterns());
        });
    }

    static class TestServlet extends HttpServlet {

    }
}