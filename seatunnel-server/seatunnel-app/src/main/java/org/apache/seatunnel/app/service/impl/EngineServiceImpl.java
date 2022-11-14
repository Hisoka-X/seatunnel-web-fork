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

package org.apache.seatunnel.app.service.impl;

import org.apache.seatunnel.app.domain.response.engine.Engine;
import org.apache.seatunnel.app.service.IEngineService;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EngineServiceImpl implements IEngineService {

    private final ThreadLocal<List<Engine>> engines = ThreadLocal.withInitial(() -> Lists.newArrayList(
        new Engine("Spark", "2.4.0"),
        new Engine("Flink", "1.13.6"),
        new Engine("SeaTunnel", "2.3,1")
    ));

    @Override
    public List<Engine> listSupportEngines() {
        return engines.get();
    }
}
