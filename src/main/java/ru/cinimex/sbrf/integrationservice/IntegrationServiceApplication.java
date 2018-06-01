package ru.cinimex.sbrf.integrationservice;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@Configuration
public class IntegrationServiceApplication {




	public static void main(String[] args) {
		SpringApplication.run(IntegrationServiceApplication.class, args);

	}

	@Bean
	CamelContextConfiguration run(List<RouteBuilderFactory> routeBuilders) throws Exception {

		return new CamelContextConfiguration() {
			@Override
			public void beforeApplicationStart(CamelContext context) {
				try {
					routeBuilders.forEach((b) -> System.out.println(b));

					//маршруты по образцу первого шаблона IntegrationTemplate1
					for (int i = 0; i < 6; i++) {
						Map<String, String> params = new HashMap();
						params.put("from", "E:/temp/example/template1/in" + i);
						params.put("out", "E:/temp/example/template1/out" + i);
						RouteBuilder rb1 = routeBuilders.get(0).getRouteBuidler(params);
						context.addRoutes(rb1);
					}

					//маршруты по образцу второго шаблона IntegrationTemplate2
					for (int i = 0; i < 6; i++) {
						Map<String, String> params = new HashMap();
						params.put("from", "E:/temp/example/template2/in" + i);
						params.put("out", "E:/temp/example/template2/out" + i);
						RouteBuilder rb2 = routeBuilders.get(1).getRouteBuidler(params);
						context.addRoutes(rb2);
					}
				} catch(Exception ex) {
					throw new RuntimeException(ex);
				}

			}

			@Override
			public void afterApplicationStart(CamelContext camelContext) {
			}
		};

	}
}
