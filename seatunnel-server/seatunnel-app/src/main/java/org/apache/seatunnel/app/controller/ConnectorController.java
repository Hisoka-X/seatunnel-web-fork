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

package org.apache.seatunnel.app.controller;

import org.apache.seatunnel.app.common.Result;
import org.apache.seatunnel.app.domain.request.connector.ConnectorStatus;
import org.apache.seatunnel.app.domain.response.connector.ConnectorInfo;
import org.apache.seatunnel.app.service.IConnectorService;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.util.List;

@RequestMapping("/api/v1/connector")
@RestController
public class ConnectorController {

    @Resource
    private IConnectorService connectorService;

    @GetMapping("/sources")
    @ApiOperation(value = "list all source connector", httpMethod = "GET")
    public Result<List<ConnectorInfo>> listAllSource(@RequestParam(defaultValue = "ALL") ConnectorStatus status) {
        return Result.success(connectorService.listSources(status));
    }

    @GetMapping("/transforms")
    @ApiOperation(value = "list all transforms", httpMethod = "GET")
    public Result<List<ConnectorInfo>> listAllTransform() {
        return Result.success(connectorService.listTransforms());
    }

    @GetMapping("/sinks")
    @ApiOperation(value = "list all sink connector", httpMethod = "GET")
    public Result<List<ConnectorInfo>> listAllSink(@RequestParam(defaultValue = "ALL") ConnectorStatus status) {
        return Result.success(connectorService.listSinks(status));
    }

}
