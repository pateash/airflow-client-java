# PermissionApi

All URIs are relative to *http://localhost/api/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getPermissions**](PermissionApi.md#getPermissions) | **GET** /permissions | List permissions


<a name="getPermissions"></a>
# **getPermissions**
> ActionCollection getPermissions(limit, offset)

List permissions

Get a list of permissions.  *New in version 2.1.0* 

### Example
```java
// Import classes:
import com.apache.airflow.client.ApiClient;
import com.apache.airflow.client.ApiException;
import com.apache.airflow.client.Configuration;
import com.apache.airflow.client.auth.*;
import com.apache.airflow.client.models.*;
import com.apache.airflow.client.api.PermissionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/api/v1");
    
    // Configure HTTP basic authorization: Basic
    HttpBasicAuth Basic = (HttpBasicAuth) defaultClient.getAuthentication("Basic");
    Basic.setUsername("YOUR USERNAME");
    Basic.setPassword("YOUR PASSWORD");


    PermissionApi apiInstance = new PermissionApi(defaultClient);
    Integer limit = 100; // Integer | The numbers of items to return.
    Integer offset = 56; // Integer | The number of items to skip before starting to collect the result set.
    try {
      ActionCollection result = apiInstance.getPermissions(limit, offset);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PermissionApi#getPermissions");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **limit** | **Integer**| The numbers of items to return. | [optional] [default to 100]
 **offset** | **Integer**| The number of items to skip before starting to collect the result set. | [optional]

### Return type

[**ActionCollection**](ActionCollection.md)

### Authorization

[Basic](../README.md#Basic), [Kerberos](../README.md#Kerberos)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Success. |  -  |
**401** | Request not authenticated due to missing, invalid, authentication info. |  -  |
**403** | Client does not have sufficient permission. |  -  |

