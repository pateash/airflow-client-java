<!--
 Licensed to the Apache Software Foundation (ASF) under one
 or more contributor license agreements.  See the NOTICE file
 distributed with this work for additional information
 regarding copyright ownership.  The ASF licenses this file
 to you under the Apache License, Version 2.0 (the
 "License"); you may not use this file except in compliance
 with the License.  You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing,
 software distributed under the License is distributed on an
 "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 KIND, either express or implied.  See the License for the
 specific language governing permissions and limitations
 under the License.
 -->

# Apache Airflow Java Client
 
> **_NOTE:_**  The Apache Airflow Client is still under active development and some methods
> or APIs might be broken. Please raise an issue in github if you encounter any such issues.



## Requirements.
JDK >=1.8

## Installation & Usage
### maven install

You can include this dependency in **pom.xml** directly using pip:

```sh
    <dependency>
            <groupId>org.apache.airflow</groupId>
            <artifactId>airflow-client</artifactId>
            <version>$VERSION</version>
    </dependency>````
```

## Getting Started

Please follow the [installation procedure](#installation--usage) and then run the following:

```java
// Import classes:
import org.apache.airflow.client.ApiClient;
import org.apache.airflow.client.ApiException;
import org.apache.airflow.client.Configuration;
import org.apache.airflow.client.auth.*;
import org.apache.airflow.client.models.*;
import org.apache.airflow.client.api.ConfigApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost/api/v1");

        // Configure HTTP basic authorization: Basic
        HttpBasicAuth Basic = (HttpBasicAuth) defaultClient.getAuthentication("Basic");
        Basic.setUsername("YOUR USERNAME");
        Basic.setPassword("YOUR PASSWORD");


        ConfigApi apiInstance = new ConnectionApi(defaultClient);
        try {
            Connection result = apiInstance.getConnection("connectionId");
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ConnectionApi#getConnection");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}


```

See [README](./airflow_client/README.md#documentation-for-api-endpoints) for full client API documentation.

## Release Process

The Python client is generated using Airflow's [openapi spec](https://github.com/apache/airflow/blob/master/clients/gen/python.sh).
To update the client for new APIs do the following steps:

```bash
# clone this repo
git clone git@github.com:apache/airflow-client-java.git

# clone Airflow repo (if not already)
git clone git@github.com:apache/airflow.git
```
Edit the file `airflow/airflow/api_connexion/openapi/v1.yaml`
Make sure it has the following `securitySchema`s listed under security `section`
```yaml
security: 
  - Basic: []
  - GoogleOpenId: []
  - Kerberos: []
```
If your deployment of Airflow uses any different authentication mechanism than the three listed above, you might need to make further changes to the `v1.yaml` and generate your own client, see [OpenAPI Schema specification](https://swagger.io/docs/specification/authentication/) for details.
(*These changes should not be commited to the upstream `v1.yaml` [as it will generate misleading openapi documentaion](https://github.com/apache/airflow/pull/17174)*)

```bash 
cd airflow

# bump up the version in python.sh & run the following command 
./clients/gen/java.sh airflow/api_connexion/openapi/v1.yaml ../airflow-client-java/airflow-client

# raise a PR in github for both the repos (airflow & airflow-client-java)
```
