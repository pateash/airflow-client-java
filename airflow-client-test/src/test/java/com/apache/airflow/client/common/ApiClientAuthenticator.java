package com.apache.airflow.client.common;

import com.apache.airflow.client.ApiClient;
import com.apache.airflow.client.api.ConnectionApi;
import org.junit.Before;

public abstract class ApiClientAuthenticator {

    private final ConnectionApi api = new ConnectionApi();

    public  abstract ApiClient getApiClient();


    @Before
    public void setUp() throws Exception {
        api.setApiClient(this.getApiClient());
    }

}
