

# ClearTaskInstance


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**dryRun** | **Boolean** | If set, don&#39;t actually run this operation. The response will contain a list of task instances planned to be cleaned, but not modified in any way.  |  [optional]
**taskIds** | **List&lt;String&gt;** | A list of task ids to clear.  *New in version 2.1.0*  |  [optional]
**startDate** | **String** | The minimum execution date to clear. |  [optional]
**endDate** | **String** | The maximum execution date to clear. |  [optional]
**onlyFailed** | **Boolean** | Only clear failed tasks. |  [optional]
**onlyRunning** | **Boolean** | Only clear running tasks. |  [optional]
**includeSubdags** | **Boolean** | Clear tasks in subdags and clear external tasks indicated by ExternalTaskMarker. |  [optional]
**includeParentdag** | **Boolean** | Clear tasks in the parent dag of the subdag. |  [optional]
**resetDagRuns** | **Boolean** | Set state of DAG runs to RUNNING. |  [optional]



