package com.hantsylabs.restexample.springmvc.config;

import java.util.Arrays;

import org.hdiv.config.annotation.ExclusionRegistry;
import org.hdiv.config.annotation.RuleRegistry;
import org.hdiv.config.annotation.ValidationConfigurer;
import org.hdiv.ee.config.SessionType;
import org.hdiv.ee.config.SingleCacheConfig;
import org.hdiv.ee.config.annotation.ExternalStateStorageConfigurer;
import org.hdiv.ee.session.cache.CacheType;
import org.springframework.context.annotation.Configuration;

import com.hdivsecurity.web.hateoas.config.EnableHdiv4ServicesSecurityConfiguration;
import com.hdivsecurity.web.hateoas.config.HdivServicesSecurityConfigurerAdapter;
import com.hdivsecurity.web.hateoas.config.ServicesConfig.IdProtectionType;
import com.hdivsecurity.web.hateoas.config.ServicesSecurityConfigBuilder;

@Configuration
@EnableHdiv4ServicesSecurityConfiguration
public class DelegateConfig extends HdivServicesSecurityConfigurerAdapter {

	@Override
	public void configure(final ServicesSecurityConfigBuilder builder) {
		builder.confidentiality(false).sessionExpired().homePage("/");
		builder.showErrorPageOnEditableValidation(true);
		builder.reuseExistingPageInAjaxRequest(true).debugMode(true);

		builder.idProtection(IdProtectionType.PLAINTEXT_HID);

		builder.hypermediaSupport(false);
		builder.sessionType(SessionType.COOKIE);
	}

	@Override
	public void addExclusions(final ExclusionRegistry registry) {
		registry.addUrlExclusions("/", "/.*", "/js/.*", "/i18n/.*", "/scripts/.*", "/bootstrap/.*", "/images/.*", "/fonts/.*",
				"/angular-ui-router/.*");
		// registry.addUrlExclusions("/.*");
		registry.addParamExclusions("rel").forUrls("/.*");
	}

	@Override
	public void addRules(final RuleRegistry registry) {
		registry.addRule("safeText").acceptedPattern("^[a-zA-Z0-9 :@.\\-_+#]*$").rejectedPattern("(\\s|\\S)*(--)(\\s|\\S)*]");
		registry.addRule("numbers").acceptedPattern("^[1-9]\\d*$");
	}

	@Override
	public void configureEditableValidation(final ValidationConfigurer validationConfigurer) {
	}

	@Override
	public void configureExternalStateStorage(final ExternalStateStorageConfigurer externalStateStorageConfigurer) {
		SingleCacheConfig config = new SingleCacheConfig(CacheType.SHARED);
		externalStateStorageConfigurer.cacheConfig(Arrays.asList(config));
	}

}
