package org.apache.airflow.client.tests.common;

import org.apache.airflow.client.ApiClient;
import org.apache.airflow.client.Configuration;
import org.apache.airflow.client.auth.HttpBasicAuth;
import org.junit.BeforeClass;

public class BasicAuthentication {

    @BeforeClass
    public static void setUp() throws Exception {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/api/v1");
        HttpBasicAuth Basic = (HttpBasicAuth) defaultClient.getAuthentication("Basic");
        Basic.setUsername("admin");
        Basic.setPassword("admin");
    }

}
