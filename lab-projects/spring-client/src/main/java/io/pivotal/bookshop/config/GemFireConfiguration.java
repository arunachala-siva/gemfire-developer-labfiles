package io.pivotal.bookshop.config;

import org.apache.geode.cache.GemFireCache;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.springframework.context.annotation.Bean;
import org.springframework.data.gemfire.client.ClientRegionFactoryBean;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication.Locator;
import org.springframework.data.gemfire.config.annotation.EnablePdx;

import io.pivotal.bookshop.domain.BookMaster;

// TODO-02: Add the appropriate annotation that will both declare this class is a JavaConfiguration class and will 
//          automatically configure the GemFire ClientCache. Also be sure to configure the Locator and optionally set 
//          the log level.
@ClientCacheApplication(logLevel="warn", locators= {
		@Locator(host="localhost", port=10334)		
})
// TODO-05: Add the appropriate annotation to enable PDX Serialization
@EnablePdx()
public class GemFireConfiguration {

    // TODO-03: Add the appropriate bean definition to configure the BookMaster Client region as a Proxy region type
	@Bean("BookMaster")
	public ClientRegionFactoryBean<Integer, BookMaster> bookMasterRegion (GemFireCache gemfireCache) {
		ClientRegionFactoryBean<Integer, BookMaster> books = new ClientRegionFactoryBean<>();
		books.setCache(gemfireCache);
		books.setClose(true);
		books.setShortcut(ClientRegionShortcut.PROXY);
		return books;
	}
	
}