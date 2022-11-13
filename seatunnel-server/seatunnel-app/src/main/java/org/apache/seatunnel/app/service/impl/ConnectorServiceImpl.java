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

import org.apache.seatunnel.app.domain.request.connector.ConnectorStatus;
import org.apache.seatunnel.app.domain.response.connector.ConnectorInfo;
import org.apache.seatunnel.app.service.IConnectorService;
import org.apache.seatunnel.plugin.discovery.PluginIdentifier;
import org.apache.seatunnel.plugin.discovery.seatunnel.SeaTunnelSourcePluginDiscovery;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ConnectorServiceImpl implements IConnectorService {
    @Override
    public List<ConnectorInfo> listSources(ConnectorStatus status) {
        if (status == ConnectorStatus.ALL) {
            // TODO
        } else if (status == ConnectorStatus.DOWNLOADED) {
            // TODO
        } else if (status == ConnectorStatus.NOT_DOWNLOAD) {
            // TODO
        }
        return null;
    }

    @Override
    public List<ConnectorInfo> listTransforms() {
        Map<PluginIdentifier, String> result = new HashMap<>();

        return null;
    }

    @Override
    public List<ConnectorInfo> listSinks(ConnectorStatus status) {
        if (status == ConnectorStatus.ALL) {
            // TODO
        } else if (status == ConnectorStatus.DOWNLOADED) {
            // TODO
        } else if (status == ConnectorStatus.NOT_DOWNLOAD) {
            // TODO
        }
        return null;
    }

    private List<ConnectorInfo> getAllConnectorsFromPluginMapping() {

        SeaTunnelSourcePluginDiscovery sourcePluginDiscovery = new SeaTunnelSourcePluginDiscovery();
        Map<PluginIdentifier, String> result = new HashMap<>();

        return null;
    }

    private List<ConnectorInfo> getDownloadedConnectors() {
        return null;
    }

    private List<ConnectorInfo> getNotDownloadedConnectors() {
        return null;
    }

}
