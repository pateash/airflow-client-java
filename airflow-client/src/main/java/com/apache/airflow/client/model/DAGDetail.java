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
import com.apache.airflow.client.model.DAG;
import com.apache.airflow.client.model.DAGDetailAllOf;
import com.apache.airflow.client.model.ScheduleInterval;
import com.apache.airflow.client.model.Tag;
import com.apache.airflow.client.model.TimeDelta;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;

/**
 * DAG details.  For details see: [airflow.models.DAG](https://airflow.apache.org/docs/apache-airflow/stable/_api/airflow/models/index.html#airflow.models.DAG) 
 */
@ApiModel(description = "DAG details.  For details see: [airflow.models.DAG](https://airflow.apache.org/docs/apache-airflow/stable/_api/airflow/models/index.html#airflow.models.DAG) ")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class DAGDetail {
  public static final String SERIALIZED_NAME_DAG_ID = "dag_id";
  @SerializedName(SERIALIZED_NAME_DAG_ID)
  private String dagId;

  public static final String SERIALIZED_NAME_ROOT_DAG_ID = "root_dag_id";
  @SerializedName(SERIALIZED_NAME_ROOT_DAG_ID)
  private String rootDagId;

  public static final String SERIALIZED_NAME_IS_PAUSED = "is_paused";
  @SerializedName(SERIALIZED_NAME_IS_PAUSED)
  private Boolean isPaused;

  public static final String SERIALIZED_NAME_IS_ACTIVE = "is_active";
  @SerializedName(SERIALIZED_NAME_IS_ACTIVE)
  private Boolean isActive;

  public static final String SERIALIZED_NAME_IS_SUBDAG = "is_subdag";
  @SerializedName(SERIALIZED_NAME_IS_SUBDAG)
  private Boolean isSubdag;

  public static final String SERIALIZED_NAME_FILELOC = "fileloc";
  @SerializedName(SERIALIZED_NAME_FILELOC)
  private String fileloc;

  public static final String SERIALIZED_NAME_FILE_TOKEN = "file_token";
  @SerializedName(SERIALIZED_NAME_FILE_TOKEN)
  private String fileToken;

  public static final String SERIALIZED_NAME_OWNERS = "owners";
  @SerializedName(SERIALIZED_NAME_OWNERS)
  private List<String> owners = null;

  public static final String SERIALIZED_NAME_DESCRIPTION = "description";
  @SerializedName(SERIALIZED_NAME_DESCRIPTION)
  private String description;

  public static final String SERIALIZED_NAME_SCHEDULE_INTERVAL = "schedule_interval";
  @SerializedName(SERIALIZED_NAME_SCHEDULE_INTERVAL)
  private ScheduleInterval scheduleInterval;

  public static final String SERIALIZED_NAME_TAGS = "tags";
  @SerializedName(SERIALIZED_NAME_TAGS)
  private List<Tag> tags = null;

  public static final String SERIALIZED_NAME_TIMEZONE = "timezone";
  @SerializedName(SERIALIZED_NAME_TIMEZONE)
  private String timezone;

  public static final String SERIALIZED_NAME_CATCHUP = "catchup";
  @SerializedName(SERIALIZED_NAME_CATCHUP)
  private Boolean catchup;

  public static final String SERIALIZED_NAME_ORIENTATION = "orientation";
  @SerializedName(SERIALIZED_NAME_ORIENTATION)
  private String orientation;

  public static final String SERIALIZED_NAME_CONCURRENCY = "concurrency";
  @SerializedName(SERIALIZED_NAME_CONCURRENCY)
  private BigDecimal concurrency;

  public static final String SERIALIZED_NAME_START_DATE = "start_date";
  @SerializedName(SERIALIZED_NAME_START_DATE)
  private OffsetDateTime startDate;

  public static final String SERIALIZED_NAME_DAG_RUN_TIMEOUT = "dag_run_timeout";
  @SerializedName(SERIALIZED_NAME_DAG_RUN_TIMEOUT)
  private TimeDelta dagRunTimeout;

  public static final String SERIALIZED_NAME_DOC_MD = "doc_md";
  @SerializedName(SERIALIZED_NAME_DOC_MD)
  private String docMd;

  public static final String SERIALIZED_NAME_DEFAULT_VIEW = "default_view";
  @SerializedName(SERIALIZED_NAME_DEFAULT_VIEW)
  private String defaultView;

  public static final String SERIALIZED_NAME_PARAMS = "params";
  @SerializedName(SERIALIZED_NAME_PARAMS)
  private Object params;


   /**
   * The ID of the DAG.
   * @return dagId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The ID of the DAG.")

  public String getDagId() {
    return dagId;
  }




   /**
   * If the DAG is SubDAG then it is the top level DAG identifier. Otherwise, null.
   * @return rootDagId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "If the DAG is SubDAG then it is the top level DAG identifier. Otherwise, null.")

  public String getRootDagId() {
    return rootDagId;
  }




  public DAGDetail isPaused(Boolean isPaused) {
    
    this.isPaused = isPaused;
    return this;
  }

   /**
   * Whether the DAG is paused.
   * @return isPaused
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Whether the DAG is paused.")

  public Boolean getIsPaused() {
    return isPaused;
  }


  public void setIsPaused(Boolean isPaused) {
    this.isPaused = isPaused;
  }


   /**
   * Whether the DAG is currently seen by the scheduler(s).  *New in version 2.1.1*  *Changed in version 2.2.0*&amp;#58; Field is read-only. 
   * @return isActive
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Whether the DAG is currently seen by the scheduler(s).  *New in version 2.1.1*  *Changed in version 2.2.0*&#58; Field is read-only. ")

  public Boolean getIsActive() {
    return isActive;
  }




   /**
   * Whether the DAG is SubDAG.
   * @return isSubdag
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Whether the DAG is SubDAG.")

  public Boolean getIsSubdag() {
    return isSubdag;
  }




   /**
   * The absolute path to the file.
   * @return fileloc
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The absolute path to the file.")

  public String getFileloc() {
    return fileloc;
  }




   /**
   * The key containing the encrypted path to the file. Encryption and decryption take place only on the server. This prevents the client from reading an non-DAG file. This also ensures API extensibility, because the format of encrypted data may change. 
   * @return fileToken
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The key containing the encrypted path to the file. Encryption and decryption take place only on the server. This prevents the client from reading an non-DAG file. This also ensures API extensibility, because the format of encrypted data may change. ")

  public String getFileToken() {
    return fileToken;
  }




   /**
   * Get owners
   * @return owners
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<String> getOwners() {
    return owners;
  }




   /**
   * User-provided DAG description, which can consist of several sentences or paragraphs that describe DAG contents. 
   * @return description
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "User-provided DAG description, which can consist of several sentences or paragraphs that describe DAG contents. ")

  public String getDescription() {
    return description;
  }




  public DAGDetail scheduleInterval(ScheduleInterval scheduleInterval) {
    
    this.scheduleInterval = scheduleInterval;
    return this;
  }

   /**
   * Get scheduleInterval
   * @return scheduleInterval
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public ScheduleInterval getScheduleInterval() {
    return scheduleInterval;
  }


  public void setScheduleInterval(ScheduleInterval scheduleInterval) {
    this.scheduleInterval = scheduleInterval;
  }


   /**
   * List of tags.
   * @return tags
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "List of tags.")

  public List<Tag> getTags() {
    return tags;
  }




  public DAGDetail timezone(String timezone) {
    
    this.timezone = timezone;
    return this;
  }

   /**
   * Get timezone
   * @return timezone
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getTimezone() {
    return timezone;
  }


  public void setTimezone(String timezone) {
    this.timezone = timezone;
  }


   /**
   * Get catchup
   * @return catchup
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Boolean getCatchup() {
    return catchup;
  }




   /**
   * Get orientation
   * @return orientation
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getOrientation() {
    return orientation;
  }




   /**
   * Get concurrency
   * @return concurrency
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public BigDecimal getConcurrency() {
    return concurrency;
  }




   /**
   * The DAG&#39;s start date.  *Changed in version 2.0.1*&amp;#58; Field becomes nullable. 
   * @return startDate
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The DAG's start date.  *Changed in version 2.0.1*&#58; Field becomes nullable. ")

  public OffsetDateTime getStartDate() {
    return startDate;
  }




  public DAGDetail dagRunTimeout(TimeDelta dagRunTimeout) {
    
    this.dagRunTimeout = dagRunTimeout;
    return this;
  }

   /**
   * Get dagRunTimeout
   * @return dagRunTimeout
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public TimeDelta getDagRunTimeout() {
    return dagRunTimeout;
  }


  public void setDagRunTimeout(TimeDelta dagRunTimeout) {
    this.dagRunTimeout = dagRunTimeout;
  }


   /**
   * Get docMd
   * @return docMd
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getDocMd() {
    return docMd;
  }




   /**
   * Get defaultView
   * @return defaultView
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getDefaultView() {
    return defaultView;
  }




   /**
   * User-specified DAG params.  *New in version 2.0.1* 
   * @return params
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "User-specified DAG params.  *New in version 2.0.1* ")

  public Object getParams() {
    return params;
  }




  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DAGDetail daGDetail = (DAGDetail) o;
    return Objects.equals(this.dagId, daGDetail.dagId) &&
        Objects.equals(this.rootDagId, daGDetail.rootDagId) &&
        Objects.equals(this.isPaused, daGDetail.isPaused) &&
        Objects.equals(this.isActive, daGDetail.isActive) &&
        Objects.equals(this.isSubdag, daGDetail.isSubdag) &&
        Objects.equals(this.fileloc, daGDetail.fileloc) &&
        Objects.equals(this.fileToken, daGDetail.fileToken) &&
        Objects.equals(this.owners, daGDetail.owners) &&
        Objects.equals(this.description, daGDetail.description) &&
        Objects.equals(this.scheduleInterval, daGDetail.scheduleInterval) &&
        Objects.equals(this.tags, daGDetail.tags) &&
        Objects.equals(this.timezone, daGDetail.timezone) &&
        Objects.equals(this.catchup, daGDetail.catchup) &&
        Objects.equals(this.orientation, daGDetail.orientation) &&
        Objects.equals(this.concurrency, daGDetail.concurrency) &&
        Objects.equals(this.startDate, daGDetail.startDate) &&
        Objects.equals(this.dagRunTimeout, daGDetail.dagRunTimeout) &&
        Objects.equals(this.docMd, daGDetail.docMd) &&
        Objects.equals(this.defaultView, daGDetail.defaultView) &&
        Objects.equals(this.params, daGDetail.params);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(dagId, rootDagId, isPaused, isActive, isSubdag, fileloc, fileToken, owners, description, scheduleInterval, tags, timezone, catchup, orientation, concurrency, startDate, dagRunTimeout, docMd, defaultView, params);
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
    sb.append("class DAGDetail {\n");
    sb.append("    dagId: ").append(toIndentedString(dagId)).append("\n");
    sb.append("    rootDagId: ").append(toIndentedString(rootDagId)).append("\n");
    sb.append("    isPaused: ").append(toIndentedString(isPaused)).append("\n");
    sb.append("    isActive: ").append(toIndentedString(isActive)).append("\n");
    sb.append("    isSubdag: ").append(toIndentedString(isSubdag)).append("\n");
    sb.append("    fileloc: ").append(toIndentedString(fileloc)).append("\n");
    sb.append("    fileToken: ").append(toIndentedString(fileToken)).append("\n");
    sb.append("    owners: ").append(toIndentedString(owners)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    scheduleInterval: ").append(toIndentedString(scheduleInterval)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
    sb.append("    timezone: ").append(toIndentedString(timezone)).append("\n");
    sb.append("    catchup: ").append(toIndentedString(catchup)).append("\n");
    sb.append("    orientation: ").append(toIndentedString(orientation)).append("\n");
    sb.append("    concurrency: ").append(toIndentedString(concurrency)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    dagRunTimeout: ").append(toIndentedString(dagRunTimeout)).append("\n");
    sb.append("    docMd: ").append(toIndentedString(docMd)).append("\n");
    sb.append("    defaultView: ").append(toIndentedString(defaultView)).append("\n");
    sb.append("    params: ").append(toIndentedString(params)).append("\n");
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

