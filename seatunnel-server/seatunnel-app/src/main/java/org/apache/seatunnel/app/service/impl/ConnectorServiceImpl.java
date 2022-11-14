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

import org.apache.seatunnel.app.bean.connector.ConnectorCache;
import org.apache.seatunnel.app.domain.request.connector.ConnectorStatus;
import org.apache.seatunnel.app.domain.response.connector.ConnectorInfo;
import org.apache.seatunnel.app.service.IConnectorService;
import org.apache.seatunnel.common.constants.PluginType;
import org.apache.seatunnel.server.common.SeatunnelErrorEnum;
import org.apache.seatunnel.server.common.SeatunnelException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ConnectorServiceImpl implements IConnectorService {

    private final ConnectorCache connectorCache;

    @Autowired
    public ConnectorServiceImpl(ConnectorCache connectorCache) {
        this.connectorCache = connectorCache;
    }

    @Override
    public List<ConnectorInfo> listSources(ConnectorStatus status) {
        if (status == ConnectorStatus.ALL) {
            return connectorCache.getAllConnectors(PluginType.SOURCE);
        } else if (status == ConnectorStatus.DOWNLOADED) {
            return connectorCache.getDownLoadConnector(PluginType.SOURCE);
        } else if (status == ConnectorStatus.NOT_DOWNLOAD) {
            return connectorCache.getNotDownLoadConnector(PluginType.SOURCE);
        }
        throw new SeatunnelException(SeatunnelErrorEnum.NO_SUCH_RESOURCE);
    }

    @Override
    public List<ConnectorInfo> listTransforms() {
        return connectorCache.getTransform();
    }

    @Override
    public List<ConnectorInfo> listSinks(ConnectorStatus status) {
        if (status == ConnectorStatus.ALL) {
            return connectorCache.getAllConnectors(PluginType.SINK);
        } else if (status == ConnectorStatus.DOWNLOADED) {
            return connectorCache.getDownLoadConnector(PluginType.SINK);
        } else if (status == ConnectorStatus.NOT_DOWNLOAD) {
            return connectorCache.getNotDownLoadConnector(PluginType.SINK);
        }
        throw new SeatunnelException(SeatunnelErrorEnum.NO_SUCH_RESOURCE);
    }

    @Override
    public void sync() throws IOException {
        connectorCache.refresh();
    }
}
