<%@page import="javax.portlet.PortletSession"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>

<%@ page import="com.liferay.portal.kernel.util.DateUtil"%>  
<%@ page import="java.util.Calendar"%> 
<%@ page import="java.util.Date"%> 
<%@ page import="com.liferay.portal.kernel.util.CalendarFactoryUtil" %>
<%@ page import="com.liferay.portal.kernel.util.CalendarUtil" %>
<%@ page import="com.test.Job" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.liferay.portal.model.Contact" %>

<portlet:defineObjects />


<portlet:actionURL var="addJobURL" name="addJob"></portlet:actionURL>

<h3><liferay-ui:message key="new_job_title" /></h3>
<aui:form method="post" action="<%= addJobURL %>">

	<aui:fieldset label="Job Details">
		<aui:input name="title" 
			
			type="text" size="100" 
			>
			<aui:validator name="required"/>
		</aui:input>
		
		<aui:input name="description" 
			
			type="textarea" 
			cols="100" rows="4" maxlength="4000"
			placeholder="Give a brief description">
		</aui:input>
		

		<aui:select label="Type" name="type">
			<aui:option label="Parametric" value="parametric" />
			<aui:option label="Regular" value="regular" selected="true"/>
			<aui:option label="Workflow" value="workflow"/>
		</aui:select>
		<aui:field-wrapper label="Scheduled at">
			<liferay-ui:input-date 
				dayValue="4"
				dayParam="scheduledDay"
				monthValue="4"
				monthParam="scheduledMonth"
				yearRangeEnd="2020" 
				yearRangeStart="1900" 
				yearValue="2016"
				yearParam="scheduledYear">
			
			</liferay-ui:input-date>
			
		</aui:field-wrapper>
		<aui:input name="executable"  size="50">
			<aui:validator name="required"/>
		</aui:input>
		<aui:button type="submit" value="Add"></aui:button>
	</aui:fieldset>
</aui:form>

<%-- <jsp:useBean id="joblist" class="java.util.ArrayList" scope="request"/>  --%>

<br/><br/>

<% 
//PortletSession ciccio = renderRequest.getPortletSession();

ArrayList<Job> joblist = (ArrayList<Job>) portletSession.getAttribute("job-list"); 

if (joblist == null || joblist.isEmpty()) {
	joblist = new ArrayList<Job>();
}
	
%>


<liferay-ui:search-container>
	<liferay-ui:search-container-results
		results="<%= joblist %>"
		total="<%= joblist.size() %>"
	/>

	<liferay-ui:search-container-row
		className="com.test.Job"
		modelVar="job"
	>
	
		<liferay-ui:search-container-column-text property="title" />

		<liferay-ui:search-container-column-text property="type" />

		<liferay-ui:search-container-column-text property="description" />

		<liferay-ui:search-container-column-text property="executable" />

		<liferay-ui:search-container-column-text  name="Scheduled at" value="<%= job.getScheduledAtAsString() %>"/>
		<liferay-ui:search-container-column-jsp name="Actions" align="left" path="/html/guestbook/job_actions.jsp">
			
		</liferay-ui:search-container-column-jsp>

		
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

