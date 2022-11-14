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

package org.apache.seatunnel.app.bean.connector;

import org.apache.seatunnel.app.domain.response.connector.ConnectorInfo;
import org.apache.seatunnel.app.thirdparty.framework.PluginDiscoveryUtil;
import org.apache.seatunnel.common.constants.PluginType;
import org.apache.seatunnel.plugin.discovery.PluginIdentifier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ConnectorCache {

    private final ConcurrentMap<PluginType, List<ConnectorInfo>> downloadConnectorCache = new ConcurrentHashMap<>();

    private final ConcurrentMap<PluginType, List<ConnectorInfo>> allConnectorCache = new ConcurrentHashMap<>();

    private List<ConnectorInfo> transformCache = new CopyOnWriteArrayList<>();

    public ConnectorCache() throws IOException {
        downloadConnectorCache.put(PluginType.SOURCE, PluginDiscoveryUtil.getDownloadedConnectors(PluginType.SOURCE));
        downloadConnectorCache.put(PluginType.SINK, PluginDiscoveryUtil.getDownloadedConnectors(PluginType.SINK));
        allConnectorCache.put(PluginType.SOURCE, PluginDiscoveryUtil.getAllConnectorsFromPluginMapping(PluginType.SOURCE));
        allConnectorCache.put(PluginType.SINK, PluginDiscoveryUtil.getAllConnectorsFromPluginMapping(PluginType.SINK));
        transformCache.addAll(PluginDiscoveryUtil.getTransforms());
    }

    public List<ConnectorInfo> getAllConnectors(PluginType pluginType) {
        return allConnectorCache.get(pluginType);
    }

    public List<ConnectorInfo> getTransform() {
        return transformCache;
    }

    public List<ConnectorInfo> getDownLoadConnector(PluginType pluginType) {
        return downloadConnectorCache.get(pluginType);
    }

    public List<ConnectorInfo> getNotDownLoadConnector(PluginType pluginType) {
        Map<PluginIdentifier, ConnectorInfo> allConnectors = allConnectorCache.get(pluginType).stream().collect(Collectors.toMap(ConnectorInfo::getPluginIdentifier, Function.identity()));
        downloadConnectorCache.get(pluginType).forEach(d -> allConnectors.remove(d.getPluginIdentifier()));
        return new ArrayList<>(allConnectors.values());
    }

    public void refresh() throws IOException {
        downloadConnectorCache.put(PluginType.SOURCE, PluginDiscoveryUtil.getDownloadedConnectors(PluginType.SOURCE));
        downloadConnectorCache.put(PluginType.SINK, PluginDiscoveryUtil.getDownloadedConnectors(PluginType.SINK));
        allConnectorCache.put(PluginType.SOURCE, PluginDiscoveryUtil.getAllConnectorsFromPluginMapping(PluginType.SOURCE));
        allConnectorCache.put(PluginType.SINK, PluginDiscoveryUtil.getAllConnectorsFromPluginMapping(PluginType.SINK));
        transformCache = new CopyOnWriteArrayList<>(PluginDiscoveryUtil.getTransforms());
    }

}
