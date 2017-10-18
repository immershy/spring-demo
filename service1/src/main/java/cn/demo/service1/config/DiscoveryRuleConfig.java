package cn.demo.service1.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by zhengwei on 2017/10/17.
 */
@Configuration
public class DiscoveryRuleConfig {

    @Bean
    @ConditionalOnMissingBean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public MetadataAwareRule metadataAwareRule() {
        return new MetadataAwareRule();
    }


}
