<%@ page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@ page import="it.infn.ct.scigaia.Job"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>

<portlet:defineObjects />

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
Job job = (Job)row.getObject();

%>


<portlet:actionURL var="editURL" name="editJob">

	
	<portlet:param name="jobId" value="<%= Integer.toString(job.getId()) %>"/>
</portlet:actionURL>

<portlet:actionURL var="deleteURL" name="deleteJob">
	<portlet:param name="jobId" value="<%= Integer.toString(job.getId()) %>"/>
</portlet:actionURL>

<liferay-ui:icon-menu>
	<liferay-ui:icon image="edit" message="Edit" url="<%= editURL.toString() %>"></liferay-ui:icon>
	<liferay-ui:icon-delete url="<%= deleteURL.toString() %>"></liferay-ui:icon-delete>
</liferay-ui:icon-menu>


