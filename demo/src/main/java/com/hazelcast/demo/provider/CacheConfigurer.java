package com.hazelcast.demo.provider;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.config.Config;
import com.hazelcast.config.XmlConfigBuilder;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.LifecycleEvent;
import com.hazelcast.core.LifecycleListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.util.Objects;

/**
 * Cache configurer for configuring and
 * managing a single cache client.
 */
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CacheConfigurer {

    private static final Logger LOGGER = LogManager.getLogger(CacheConfigurer.class);

//    @Value("${hazelcast.cluster.members}")
    private String clusterMembers = "localhost";
//    @Value("${hazelcast.enabled}")
    private boolean isHazelcastEnabled = true;
//    @Value("${hazelcast.cluster.group.user}")
    private String clusterGroupUser = "dev";
//    @Value("${hazelcast.cluster.group.password}")
    private String clusterGroupPassword = "dev-pass";
//    @Value("${hazelcast.connectionAttemptLimit}")
    private int connectionAttemptLimit = 10;
//    @Value("${hazelcast.connectionAttemptPeriod}")
    private int connectionAttemptPeriod = 3000;
//    @Value("${hazelcast.connectionTimeout}")
    private int connectionTimeout = 5000;
    private static final String clientUUID = "clientUUID";
    private static String currentAppName;
    private static boolean isCacheEnabled = false;
    private static ClientConfig clientConfig;

    private static HazelcastInstance hazelcastInstance;

    @Autowired
    private Environment environment;

    @PostConstruct
    private void initHazelcastInstance() {
        isCacheEnabled = true;
        Config config = new XmlConfigBuilder().build();
        config.getGroupConfig().setName("dev");
        config.getGroupConfig().setPassword("dev-pass");
        config.getManagementCenterConfig().setEnabled(true);
        config.getManagementCenterConfig().setUrl("http://localhost:8200/mancenter");
        hazelcastInstance = Hazelcast.newHazelcastInstance(config);
//        if (isHazelcastEnabled) {
//            isCacheEnabled = true;
//            String appName = "demo";
//            setCurrentAppName(appName);
//            setClientConfig(fetchHZClientConfig());
//            configureHazelcast();
//        }
    }

//    private static void configureHazelcast() {
//        while (Objects.isNull(hazelcastInstance) || !hazelcastInstance.getLifecycleService().isRunning()) {
//            hazelcastInstance = HazelcastClient.newHazelcastClient(clientConfig);
//            hazelcastInstance.getMap(clientUUID).put(hazelcastInstance.getLocalEndpoint().getUuid(), currentAppName);
//            hazelcastInstance.getLifecycleService().addLifecycleListener(new HazelcastEventListener());
//        }
//    }

    private ClientConfig fetchHZClientConfig() {
        Config config = new XmlConfigBuilder().build();
        config.getGroupConfig().setName("dev");
        config.getGroupConfig().setPassword("dev-pass");
//        config.getManagementCenterConfig().setEnabled(true);
//        config.getManagementCenterConfig().setUrl("http://localhost:8200/mancenter");
//        Hazelcast.newHazelcastInstance(config);
//        ClientConfig clientConfig = new ClientConfig();
//        clientConfig.getGroupConfig()
//                .setName(clusterGroupUser)
//                .setPassword(clusterGroupPassword);
//        ClientNetworkConfig networkConfig = clientConfig.getNetworkConfig();
//        String[] hosts = clusterMembers.split(",");
//        for (String host : hosts) {
//            networkConfig.addAddress(host);
//        }
//        networkConfig.setConnectionAttemptLimit(connectionAttemptLimit)
//                .setConnectionAttemptPeriod(connectionAttemptPeriod)
//                .setConnectionTimeout(connectionTimeout);
        LOGGER.info("<=================== Hazelcast Client config =================>");
        LOGGER.info("Members         => " + clusterMembers);
        LOGGER.info("User           => " + clusterGroupUser);
        LOGGER.info("Password       => " + clusterGroupPassword);
        LOGGER.info("AppName       => " + currentAppName);
        LOGGER.info("<=============================================================>");
        return clientConfig;
    }

    /**
     * Gets hazelcast instance.
     *
     * @return the hazelcast instance
     */
    public static HazelcastInstance getHazelcastInstance() {
        return hazelcastInstance;
    }

    /**
     * Returns if hazelcast is running boolean.
     *
     * @return the boolean
     */
    public static boolean isHazelcastRunning() {
        return Objects.nonNull(hazelcastInstance) && hazelcastInstance.getLifecycleService().isRunning();
    }

    private static String getClientUUID() {
        return clientUUID;
    }

    private static ClientConfig getClientConfig() {
        return clientConfig;
    }

    private static void setClientConfig(ClientConfig clientConfig) {
        CacheConfigurer.clientConfig = clientConfig;
    }

    public static boolean isCacheEnabled() {
        return isCacheEnabled;
    }

    private static String getCurrentAppName() {
        return currentAppName;
    }

    private static void setCurrentAppName(String currentAppName) {
        CacheConfigurer.currentAppName = currentAppName;
    }

//    private static class HazelcastEventListener implements LifecycleListener {
//
//        @Override
//        public void stateChanged(LifecycleEvent event) {
//            new Thread(() -> {
//                while (Objects.isNull(hazelcastInstance) || !hazelcastInstance.getLifecycleService().isRunning()) {
//                    synchronized (CacheConfigurer.class) {
//                        if (Objects.isNull(hazelcastInstance) || !hazelcastInstance.getLifecycleService().isRunning()) {
//                            try {
//                                hazelcastInstance = HazelcastClient.newHazelcastClient(getClientConfig());
//                                hazelcastInstance.getLifecycleService().addLifecycleListener(new HazelcastEventListener());
//                            } catch (Exception e) {
//                                System.out.println("#### ERROR WHILE INITIALIZING HAZELCAST INSTANCE ##### " + e.getMessage());
////                                LOGGER.error("#### ERROR WHILE INITIALIZING HAZELCAST INSTANCE ##### " + e.getMessage());
//                            } finally {
//                                if (Objects.nonNull(hazelcastInstance) && hazelcastInstance.getLifecycleService().isRunning()) {
//                                    hazelcastInstance.getMap(getClientUUID()).put(hazelcastInstance.getLocalEndpoint().getUuid(), getCurrentAppName());
//                                }
//                            }
//                        }
//                    }
//                }
//            }).start();
//        }
//    }
}