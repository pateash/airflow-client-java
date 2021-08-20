package com.apache.airflow.client.common;

import com.apache.airflow.client.ApiClient;
import com.apache.airflow.client.Configuration;

public abstract class BasicApiClientAuthenticator extends ApiClientAuthenticator {

    @Override
    public  ApiClient getApiClient(){
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/api/v1/");
        defaultClient.setUsername("admin");
        defaultClient.setPassword("admin");
        return defaultClient;
    }

}
