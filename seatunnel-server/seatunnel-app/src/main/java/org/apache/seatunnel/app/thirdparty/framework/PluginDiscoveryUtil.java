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

package org.apache.seatunnel.app.thirdparty.framework;

import org.apache.seatunnel.app.domain.response.connector.ConnectorInfo;
import org.apache.seatunnel.common.constants.PluginType;
import org.apache.seatunnel.plugin.discovery.AbstractPluginDiscovery;
import org.apache.seatunnel.plugin.discovery.PluginIdentifier;
import org.apache.seatunnel.plugin.discovery.seatunnel.SeaTunnelSinkPluginDiscovery;
import org.apache.seatunnel.plugin.discovery.seatunnel.SeaTunnelSourcePluginDiscovery;
import org.apache.seatunnel.plugin.discovery.seatunnel.SeaTunnelTransformPluginDiscovery;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PluginDiscoveryUtil {

    public static List<ConnectorInfo> getAllConnectorsFromPluginMapping(PluginType pluginType) {
        Map<PluginIdentifier, String> plugins = AbstractPluginDiscovery.getAllSupportedPlugins("seatunnel", pluginType);
        List<ConnectorInfo> connectorInfos = new ArrayList<>();
        plugins.forEach((plugin, artifactId) -> connectorInfos.add(new ConnectorInfo(plugin, artifactId)));
        return connectorInfos;
    }

    public static List<ConnectorInfo> getDownloadedConnectors(PluginType pluginType) throws IOException {
        List<PluginIdentifier> connectors = new ArrayList<>();
        if (pluginType.equals(PluginType.SOURCE)) {
            connectors = new SeaTunnelSourcePluginDiscovery().getAllPlugin(PluginType.SOURCE);
        }
        if (pluginType.equals(PluginType.SINK)) {
            connectors = new SeaTunnelSinkPluginDiscovery().getAllPlugin(PluginType.SINK);
        }

        List<ConnectorInfo> connectorInfos = new ArrayList<>();
        connectors.forEach(plugin -> connectorInfos.add(new ConnectorInfo(plugin, null)));
        return connectorInfos;
    }

    public static List<ConnectorInfo> getTransforms() throws IOException {
        List<PluginIdentifier> transforms = new SeaTunnelTransformPluginDiscovery().getAllPlugin(PluginType.TRANSFORM);
        return transforms.stream().map(t -> new ConnectorInfo(t, null)).collect(Collectors.toList());
    }

}
