/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.seatunnel.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"org.apache.seatunnel.app", "org.apache.seatunnel.scheduler"})
@EnableTransactionManagement
@EnableConfigurationProperties
@EnableScheduling
@EnableAsync(proxyTargetClass = true)
@MapperScan({"org.apache.seatunnel.app.dal"})
public class SeatunnelApplication {
    public static void main(String[] args) {
        System.setProperty("SEATUNNEL_HOME", "/Users/fanjia/Code/seatunnel-fork/seatunnel-dist/target/apache-seatunnel-incubating-2.3.1-SNAPSHOT");
        SpringApplication.run(SeatunnelApplication.class, args);
    }
}
