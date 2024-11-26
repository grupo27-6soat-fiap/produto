package br.com.fiap.lanchonete.produtoservicefase4;



import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.FEATURES_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;


@Suite
@IncludeEngines("cucumber")
@SelectPackages({"br.com.fiap.lanchonete.produtoservicefase4", "classpath:features"})
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "br.com.fiap.lanchonete.produtoservicefase4" )
@ConfigurationParameter( key = FEATURES_PROPERTY_NAME, value = "classpath:features")
public class CucumberTest {
}


