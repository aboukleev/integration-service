package ru.cinimex.sbrf.integrationservice;

import org.apache.camel.builder.RouteBuilder;

import java.util.Map;

public interface RouteBuilderFactory {

    RouteBuilder getRouteBuidler(Map<String, String> params);
}
