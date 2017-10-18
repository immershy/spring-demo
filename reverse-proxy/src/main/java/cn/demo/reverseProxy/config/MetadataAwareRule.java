package cn.demo.reverseProxy.config;

import com.netflix.loadbalancer.AbstractServerPredicate;
import com.netflix.loadbalancer.AvailabilityPredicate;
import com.netflix.loadbalancer.CompositePredicate;
import com.netflix.loadbalancer.PredicateBasedRule;
import org.springframework.util.Assert;

/**
 * Created by zhengwei on 2017/10/17.
 */
public class MetadataAwareRule extends PredicateBasedRule {

    private final CompositePredicate predicate;

    public MetadataAwareRule() {
        this(new MetadataAwarePredicate());
    }

    public MetadataAwareRule(MetadataAwarePredicate metadataAwarePredicate) {
        Assert.notNull(metadataAwarePredicate, "Parameter 'metadataAwarePredicate' can't be null");
        this.predicate = createCompositePredicate(metadataAwarePredicate, new AvailabilityPredicate(this, null));
    }

    private CompositePredicate createCompositePredicate(MetadataAwarePredicate metadataAwarePredicate,
        AvailabilityPredicate availabilityPredicate) {
        return CompositePredicate.withPredicates(metadataAwarePredicate, availabilityPredicate)
            .build();
    }

    @Override
    public AbstractServerPredicate getPredicate() {
        return predicate;
    }
}
