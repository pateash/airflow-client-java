/*
 * Airflow API (Stable)
 * # Overview  To facilitate management, Apache Airflow supports a range of REST API endpoints across its objects. This section provides an overview of the API design, methods, and supported use cases.  Most of the endpoints accept `JSON` as input and return `JSON` responses. This means that you must usually add the following headers to your request: ``` Content-type: application/json Accept: application/json ```  ## Resources  The term `resource` refers to a single type of object in the Airflow metadata. An API is broken up by its endpoint's corresponding resource. The name of a resource is typically plural and expressed in camelCase. Example: `dagRuns`.  Resource names are used as part of endpoint URLs, as well as in API parameters and responses.  ## CRUD Operations  The platform supports **C**reate, **R**ead, **U**pdate, and **D**elete operations on most resources. You can review the standards for these operations and their standard parameters below.  Some endpoints have special behavior as exceptions.  ### Create  To create a resource, you typically submit an HTTP `POST` request with the resource's required metadata in the request body. The response returns a `201 Created` response code upon success with the resource's metadata, including its internal `id`, in the response body.  ### Read  The HTTP `GET` request can be used to read a resource or to list a number of resources.  A resource's `id` can be submitted in the request parameters to read a specific resource. The response usually returns a `200 OK` response code upon success, with the resource's metadata in the response body.  If a `GET` request does not include a specific resource `id`, it is treated as a list request. The response usually returns a `200 OK` response code upon success, with an object containing a list of resources' metadata in the response body.  When reading resources, some common query parameters are usually available. e.g.: ``` v1/connections?limit=25&offset=25 ```  |Query Parameter|Type|Description| |---------------|----|-----------| |limit|integer|Maximum number of objects to fetch. Usually 25 by default| |offset|integer|Offset after which to start returning objects. For use with limit query parameter.|  ### Update  Updating a resource requires the resource `id`, and is typically done using an HTTP `PATCH` request, with the fields to modify in the request body. The response usually returns a `200 OK` response code upon success, with information about the modified resource in the response body.  ### Delete  Deleting a resource requires the resource `id` and is typically executing via an HTTP `DELETE` request. The response usually returns a `204 No Content` response code upon success.  ## Conventions  - Resource names are plural and expressed in camelCase. - Names are consistent between URL parameter name and field name.  - Field names are in snake_case. ```json {     \"name\": \"string\",     \"slots\": 0,     \"occupied_slots\": 0,     \"used_slots\": 0,     \"queued_slots\": 0,     \"open_slots\": 0 } ```  ### Update Mask  Update mask is available as a query parameter in patch endpoints. It is used to notify the API which fields you want to update. Using `update_mask` makes it easier to update objects by helping the server know which fields to update in an object instead of updating all fields. The update request ignores any fields that aren't specified in the field mask, leaving them with their current values.  Example: ```   resource = request.get('/resource/my-id').json()   resource['my_field'] = 'new-value'   request.patch('/resource/my-id?update_mask=my_field', data=json.dumps(resource)) ```  ## Versioning and Endpoint Lifecycle  - API versioning is not synchronized to specific releases of the Apache Airflow. - APIs are designed to be backward compatible. - Any changes to the API will first go through a deprecation phase.  # Summary of Changes  | Airflow version | Description | |-|-| | v2.0 | Initial release | | v2.0.2    | Added /plugins endpoint | | v2.1 | New providers endpoint |  # Trying the API  You can use a third party client, such as [curl](https://curl.haxx.se/), [HTTPie](https://httpie.org/), [Postman](https://www.postman.com/) or [the Insomnia rest client](https://insomnia.rest/) to test the Apache Airflow API.  Note that you will need to pass credentials data.  For e.g., here is how to pause a DAG with [curl](https://curl.haxx.se/), when basic authorization is used: ```bash curl -X PATCH 'https://example.com/api/v1/dags/{dag_id}?update_mask=is_paused' \\ -H 'Content-Type: application/json' \\ --user \"username:password\" \\ -d '{     \"is_paused\": true }' ```  Using a graphical tool such as [Postman](https://www.postman.com/) or [Insomnia](https://insomnia.rest/), it is possible to import the API specifications directly:  1. Download the API specification by clicking the **Download** button at top of this document 2. Import the JSON specification in the graphical tool of your choice.   - In *Postman*, you can click the **import** button at the top   - With *Insomnia*, you can just drag-and-drop the file on the UI  Note that with *Postman*, you can also generate code snippets by selecting a request and clicking on the **Code** button.  ## Enabling CORS  [Cross-origin resource sharing (CORS)](https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS) is a browser security feature that restricts HTTP requests that are initiated from scripts running in the browser.  For details on enabling/configuring CORS, see [Enabling CORS](https://airflow.apache.org/docs/apache-airflow/stable/security/api.html).  # Authentication  To be able to meet the requirements of many organizations, Airflow supports many authentication methods, and it is even possible to add your own method.  If you want to check which auth backend is currently set, you can use `airflow config get-value api auth_backend` command as in the example below. ```bash $ airflow config get-value api auth_backend airflow.api.auth.backend.basic_auth ``` The default is to deny all requests.  For details on configuring the authentication, see [API Authorization](https://airflow.apache.org/docs/apache-airflow/stable/security/api.html).  # Errors  We follow the error response format proposed in [RFC 7807](https://tools.ietf.org/html/rfc7807) also known as Problem Details for HTTP APIs. As with our normal API responses, your client must be prepared to gracefully handle additional members of the response.  ## Unauthenticated  This indicates that the request has not been applied because it lacks valid authentication credentials for the target resource. Please check that you have valid credentials.  ## PermissionDenied  This response means that the server understood the request but refuses to authorize it because it lacks sufficient rights to the resource. It happens when you do not have the necessary permission to execute the action you performed. You need to get the appropriate permissions in other to resolve this error.  ## BadRequest  This response means that the server cannot or will not process the request due to something that is perceived to be a client error (e.g., malformed request syntax, invalid request message framing, or deceptive request routing). To resolve this, please ensure that your syntax is correct.  ## NotFound  This client error response indicates that the server cannot find the requested resource.  ## MethodNotAllowed  Indicates that the request method is known by the server but is not supported by the target resource.  ## NotAcceptable  The target resource does not have a current representation that would be acceptable to the user agent, according to the proactive negotiation header fields received in the request, and the server is unwilling to supply a default representation.  ## AlreadyExists  The request could not be completed due to a conflict with the current state of the target resource, e.g. the resource it tries to create already exists.  ## Unknown  This means that the server encountered an unexpected condition that prevented it from fulfilling the request. 
 *
 * The version of the OpenAPI document: 1.0.0
 * Contact: dev@airflow.apache.org
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package com.apache.airflow.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.apache.airflow.client.model.DagState;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.time.OffsetDateTime;
import org.openapitools.jackson.nullable.JsonNullable;

/**
 * DAGRun
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class DAGRun {
  public static final String SERIALIZED_NAME_DAG_RUN_ID = "dag_run_id";
  @SerializedName(SERIALIZED_NAME_DAG_RUN_ID)
  private String dagRunId;

  public static final String SERIALIZED_NAME_DAG_ID = "dag_id";
  @SerializedName(SERIALIZED_NAME_DAG_ID)
  private String dagId;

  public static final String SERIALIZED_NAME_LOGICAL_DATE = "logical_date";
  @SerializedName(SERIALIZED_NAME_LOGICAL_DATE)
  private OffsetDateTime logicalDate;

  public static final String SERIALIZED_NAME_EXECUTION_DATE = "execution_date";
  @SerializedName(SERIALIZED_NAME_EXECUTION_DATE)
  private OffsetDateTime executionDate;

  public static final String SERIALIZED_NAME_START_DATE = "start_date";
  @SerializedName(SERIALIZED_NAME_START_DATE)
  private OffsetDateTime startDate;

  public static final String SERIALIZED_NAME_END_DATE = "end_date";
  @SerializedName(SERIALIZED_NAME_END_DATE)
  private OffsetDateTime endDate;

  public static final String SERIALIZED_NAME_STATE = "state";
  @SerializedName(SERIALIZED_NAME_STATE)
  private DagState state;

  public static final String SERIALIZED_NAME_EXTERNAL_TRIGGER = "external_trigger";
  @SerializedName(SERIALIZED_NAME_EXTERNAL_TRIGGER)
  private Boolean externalTrigger = true;

  public static final String SERIALIZED_NAME_CONF = "conf";
  @SerializedName(SERIALIZED_NAME_CONF)
  private Object conf;


  public DAGRun dagRunId(String dagRunId) {
    
    this.dagRunId = dagRunId;
    return this;
  }

   /**
   * Run ID.  The value of this field can be set only when creating the object. If you try to modify the field of an existing object, the request fails with an BAD_REQUEST error.  If not provided, a value will be generated based on execution_date.  If the specified dag_run_id is in use, the creation request fails with an ALREADY_EXISTS error.  This together with DAG_ID are a unique key. 
   * @return dagRunId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Run ID.  The value of this field can be set only when creating the object. If you try to modify the field of an existing object, the request fails with an BAD_REQUEST error.  If not provided, a value will be generated based on execution_date.  If the specified dag_run_id is in use, the creation request fails with an ALREADY_EXISTS error.  This together with DAG_ID are a unique key. ")

  public String getDagRunId() {
    return dagRunId;
  }


  public void setDagRunId(String dagRunId) {
    this.dagRunId = dagRunId;
  }


   /**
   * Get dagId
   * @return dagId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getDagId() {
    return dagId;
  }




  public DAGRun logicalDate(OffsetDateTime logicalDate) {
    
    this.logicalDate = logicalDate;
    return this;
  }

   /**
   * The logical date (previously called execution date). This is the time or interval covered by this DAG run, according to the DAG definition.  The value of this field can be set only when creating the object. If you try to modify the field of an existing object, the request fails with an BAD_REQUEST error.  This together with DAG_ID are a unique key.  *New in version 2.2.0* 
   * @return logicalDate
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The logical date (previously called execution date). This is the time or interval covered by this DAG run, according to the DAG definition.  The value of this field can be set only when creating the object. If you try to modify the field of an existing object, the request fails with an BAD_REQUEST error.  This together with DAG_ID are a unique key.  *New in version 2.2.0* ")

  public OffsetDateTime getLogicalDate() {
    return logicalDate;
  }


  public void setLogicalDate(OffsetDateTime logicalDate) {
    this.logicalDate = logicalDate;
  }


  public DAGRun executionDate(OffsetDateTime executionDate) {
    
    this.executionDate = executionDate;
    return this;
  }

   /**
   * The execution date. This is the same as logical_date, kept for backwards compatibility. If both this field and logical_date are provided but with different values, the request will fail with an BAD_REQUEST error.  *Changed in version 2.2.0*&amp;#58; Field becomes nullable.  *Deprecated since version 2.2.0*&amp;#58; Use &#39;logical_date&#39; instead. 
   * @return executionDate
   * @deprecated
  **/
  @Deprecated
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The execution date. This is the same as logical_date, kept for backwards compatibility. If both this field and logical_date are provided but with different values, the request will fail with an BAD_REQUEST error.  *Changed in version 2.2.0*&#58; Field becomes nullable.  *Deprecated since version 2.2.0*&#58; Use 'logical_date' instead. ")

  public OffsetDateTime getExecutionDate() {
    return executionDate;
  }


  public void setExecutionDate(OffsetDateTime executionDate) {
    this.executionDate = executionDate;
  }


   /**
   * The start time. The time when DAG run was actually created.  *Changed in version 2.1.3*&amp;#58; Field becomes nullable. 
   * @return startDate
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The start time. The time when DAG run was actually created.  *Changed in version 2.1.3*&#58; Field becomes nullable. ")

  public OffsetDateTime getStartDate() {
    return startDate;
  }




   /**
   * Get endDate
   * @return endDate
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public OffsetDateTime getEndDate() {
    return endDate;
  }




  public DAGRun state(DagState state) {
    
    this.state = state;
    return this;
  }

   /**
   * Get state
   * @return state
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public DagState getState() {
    return state;
  }


  public void setState(DagState state) {
    this.state = state;
  }


   /**
   * Get externalTrigger
   * @return externalTrigger
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Boolean getExternalTrigger() {
    return externalTrigger;
  }




  public DAGRun conf(Object conf) {
    
    this.conf = conf;
    return this;
  }

   /**
   * JSON object describing additional configuration parameters.  The value of this field can be set only when creating the object. If you try to modify the field of an existing object, the request fails with an BAD_REQUEST error. 
   * @return conf
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "JSON object describing additional configuration parameters.  The value of this field can be set only when creating the object. If you try to modify the field of an existing object, the request fails with an BAD_REQUEST error. ")

  public Object getConf() {
    return conf;
  }


  public void setConf(Object conf) {
    this.conf = conf;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DAGRun daGRun = (DAGRun) o;
    return Objects.equals(this.dagRunId, daGRun.dagRunId) &&
        Objects.equals(this.dagId, daGRun.dagId) &&
        Objects.equals(this.logicalDate, daGRun.logicalDate) &&
        Objects.equals(this.executionDate, daGRun.executionDate) &&
        Objects.equals(this.startDate, daGRun.startDate) &&
        Objects.equals(this.endDate, daGRun.endDate) &&
        Objects.equals(this.state, daGRun.state) &&
        Objects.equals(this.externalTrigger, daGRun.externalTrigger) &&
        Objects.equals(this.conf, daGRun.conf);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(dagRunId, dagId, logicalDate, executionDate, startDate, endDate, state, externalTrigger, conf);
  }

  private static <T> int hashCodeNullable(JsonNullable<T> a) {
    if (a == null) {
      return 1;
    }
    return a.isPresent() ? Arrays.deepHashCode(new Object[]{a.get()}) : 31;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DAGRun {\n");
    sb.append("    dagRunId: ").append(toIndentedString(dagRunId)).append("\n");
    sb.append("    dagId: ").append(toIndentedString(dagId)).append("\n");
    sb.append("    logicalDate: ").append(toIndentedString(logicalDate)).append("\n");
    sb.append("    executionDate: ").append(toIndentedString(executionDate)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    externalTrigger: ").append(toIndentedString(externalTrigger)).append("\n");
    sb.append("    conf: ").append(toIndentedString(conf)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

