package cn.demo.reverseProxy.config;

import com.netflix.loadbalancer.AbstractServerPredicate;
import com.netflix.loadbalancer.PredicateKey;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;
import com.sun.istack.internal.Nullable;
import java.util.Map;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * Created by zhengwei on 2017/10/17.
 */
public class MetadataAwarePredicate extends AbstractServerPredicate {

    @Override
    public boolean apply(@Nullable PredicateKey input) {
        return input != null
            && input.getServer() instanceof DiscoveryEnabledServer
            && apply((DiscoveryEnabledServer) input.getServer());
    }

    private boolean apply(DiscoveryEnabledServer server) {
        String province = (String) RequestContextHolder.getRequestAttributes().getAttribute("province", 0);
        final Map<String, String> metadata = server.getInstanceInfo().getMetadata();
        return metadata.get("province").equals(province);
    }
}
