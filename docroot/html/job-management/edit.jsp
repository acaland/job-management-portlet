<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
<%@ page import="com.test.Job"%>
<%@ page import="java.util.Calendar"%> 

<portlet:defineObjects />

<%
	Job jobToEdit = (Job)request.getAttribute("jobToEdit");
	
%>

<portlet:actionURL var="updateJobURL" name="updateJob">
	<portlet:param name="jobToUpdate" value="<%= Integer.toString(jobToEdit.getId()) %>"/>
</portlet:actionURL>

<h3>Edit Job <%=  jobToEdit.getTitle() %></h3>

<portlet:renderURL var="listJobURL">
	<portlet:param name="mvcPath" value="/html/guestbook/view.jsp"/>
</portlet:renderURL>


<aui:form method="post" action="<%= updateJobURL %>">

	

	<aui:fieldset label="Job Details">
		<aui:input name="title" 
			label="Title" 
			type="text" size="100" 
			placeholder="Job Title"
			value="<%= jobToEdit.getTitle() %>">
			<aui:validator name="required"/>
		</aui:input>
		
		<aui:input name="description" 
			label="Description" 
			type="textarea" 
			cols="100" rows="4" maxlength="4000"
			placeholder="Give a brief description"
			value="<%= jobToEdit.getDescription() %>">
		</aui:input>
		
		<aui:select label="Type" name="type">
			<aui:option label="Parametric" value="parametric" 
				selected="<%= jobToEdit.getType().equals(\"parametric\") ? true : false %>"/>
			<aui:option label="Regular" value="regular" 
				selected="<%= jobToEdit.getType().equals(\"regular\") ? true : false %>"/>
			<aui:option label="Workflow" value="workflow" 
				selected="<%= jobToEdit.getType().equals(\"workflow\") ? true : false %>"/>
		</aui:select>
		<aui:field-wrapper label="Scheduled at">
			<liferay-ui:input-date 
				dayValue="<%= jobToEdit.getScheduledAt().get(Calendar.DATE) %>"
				dayParam="scheduledDay"
				monthValue="<%= jobToEdit.getScheduledAt().get(Calendar.MONTH) %>"
				monthParam="scheduledMonth"
				yearRangeEnd="2020" 
				yearRangeStart="1900" 
				yearValue="<%= jobToEdit.getScheduledAt().get(Calendar.YEAR) %>"
				yearParam="scheduledYear">
			
			</liferay-ui:input-date>
			
		</aui:field-wrapper>
		<aui:input name="executable" label="Executable" size="50" value="<%= jobToEdit.getExecutable() %>">
			<aui:validator name="required"/>
		</aui:input>
		<aui:button type="submit" value="Update"></aui:button>
		<aui:button type="cancel" value="Cancel" onClick="<%= listJobURL.toString()  %>"></aui:button>
	</aui:fieldset>
</aui:form>

<aui:a href="<%= listJobURL %>">Go Back</aui:a>