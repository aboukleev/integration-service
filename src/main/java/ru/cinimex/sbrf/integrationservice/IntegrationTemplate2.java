package ru.cinimex.sbrf.integrationservice;


import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class IntegrationTemplate2 implements RouteBuilderFactory {


    @Override
    public RouteBuilder getRouteBuidler(Map<String, String> params) {
        return new Builder(params);
    }

    private class Builder extends RouteBuilder {
        private Map<String, String> params;

        public Builder(Map<String,String> params) {
            this.params = params;
        }

        @Override
        public void configure() throws Exception {
            System.out.println("I am created from IntegrationTemplate2 with params " + params);

            from("file:/" + params.get("from"))
                    .process(exchange -> System.out.println(exchange))
                    .to("file:/"+ params.get("to"));
        }
    }
}
