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
package io.github.microsphere.spring.config.zookeeper.annotation;

import io.github.microsphere.spring.config.context.annotation.PropertySourceExtension;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;
import org.springframework.core.env.PropertySource;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Enable Zookeeper Configuration
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@PropertySourceExtension
@Import(EnableZookeeperConfigPropertySourceLoader.class)
public @interface EnableZookeeperConfig {

    /**
     * The name of Zookeeper {@link PropertySource}
     */
    @AliasFor(annotation = PropertySourceExtension.class, attribute = "name")
    String name() default "";

    /**
     * It indicates the property source is auto-refreshed when the configuration is
     * changed.
     *
     * @return default value is <code>true</code>
     */
    @AliasFor(annotation = PropertySourceExtension.class, attribute = "autoRefreshed")
    boolean autoRefreshed() default true;

    /**
     * Indicates current {@link PropertySource} is first order or not If specified ,
     * {@link #before()} and {@link #after()} will be ignored, or last order.
     *
     * @return default value is <code>false</code>
     */
    @AliasFor(annotation = PropertySourceExtension.class, attribute = "first")
    boolean first() default false;

    /**
     * The relative order before specified {@link PropertySource}
     * <p>
     * If not specified , current {@link PropertySource} will be added last.
     * <p>
     * If {@link #first()} specified , current attribute will be ignored.
     *
     * @return the name of {@link PropertySource}, default value is the empty string
     */
    @AliasFor(annotation = PropertySourceExtension.class, attribute = "before")
    String before() default "";

    /**
     * The relative order after specified {@link PropertySource}
     * <p>
     * If not specified , current {@link PropertySource} will be added last.
     * <p>
     * If {@link #first()} specified , current attribute will be ignored.
     *
     * @return the name of {@link PropertySource}, default value is the empty string
     */
    @AliasFor(annotation = PropertySourceExtension.class, attribute = "after")
    String after() default "";

    /**
     * The string presenting connection to Zookeeper
     *
     * @return non-null
     */
    String connectString() default "127.0.0.1:2181";

    /**
     * The root path of all configurations
     *
     * @return non-null
     */
    String rootPath() default "/configs";
}