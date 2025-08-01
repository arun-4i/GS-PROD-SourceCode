package com.mobile.integration.grandstores;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//public class GrandstoresApplication extends SpringBootServletInitializer {
public class GrandstoresApplication{
	public static void main(String[] args) {
		SpringApplication.run(GrandstoresApplication.class, args);
	}

	/*public static void main(String[] args) {
        new SpringApplicationBuilder(GrandstoresApplication.class)
        .sources(GrandstoresApplication.class)
        .properties(getProperties())
        .run(args);
        }
	@Override
        protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
            return application.sources(GrandstoresApplication.class).properties(getProperties());
        }
        static Properties getProperties() { 
            Properties props = new Properties();     
            //props.put("spring.config.location","file:///C:/HRDevelopment/das/application.properties");
			props.put("spring.config.location","file:///E:/Moshina_DIR/Projects/GrandStores_New/propertyFiles/application.properties");
            //dev
            //props.put("spring.config.location","file:///GSback/R122_TEST/TOMCAT/apache-tomcat-9.0.50/webapps/application.properties");
            //prod
            //props.put("spring.config.location","file:///apache-tomcat-9.0.33/adminfiles/property/hrmsserviceproperty/application.properties");
            return props;
            }*/
}
