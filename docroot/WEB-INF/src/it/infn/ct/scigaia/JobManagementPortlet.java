package it.infn.ct.scigaia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletSession;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class JobManagementPortlet 
 */
public class JobManagementPortlet extends MVCPortlet {
	Log log = LogFactoryUtil.getLog(JobManagementPortlet.class);
	int id = 0;
	
	
	
	
	@Override
	protected void addSuccessMessage(ActionRequest actionRequest,
			ActionResponse actionResponse) {
		
		Job jobToEdit = (Job) actionRequest.getAttribute("jobToEdit");
		
		if (jobToEdit == null) {
			//log.warn("in addSuccessMessage: " + jobToEdit.getId() );
			super.addSuccessMessage(actionRequest, actionResponse);
		}

	}

	public void addJob(ActionRequest actionRequest,
			ActionResponse actionResponse) throws IOException, PortletException {
		
		String title = ParamUtil.getString(actionRequest, "title");
		String description = ParamUtil.getString(actionRequest, "description");
		String type = ParamUtil.getString(actionRequest, "type");
		String executable = ParamUtil.getString(actionRequest, "executable");
		String scheduledYear = ParamUtil.getString(actionRequest, "scheduledYear");
		String scheduledDay = ParamUtil.getString(actionRequest, "scheduledDay");
		String scheduledMonth = ParamUtil.getString(actionRequest, "scheduledMonth");
		log.warn("title : " + title);
		log.warn("description : " + description);
		log.warn("type : " + type);
		log.warn("executable : " + executable);
		log.warn("scheduledYear : " + scheduledYear);
		log.warn("scheduledDay : " + scheduledDay);
		log.warn("scheduledMonth : " + scheduledMonth);
		
		
		id++;
		
		Job newJob = new Job(id, title, description, type, executable, 
				Integer.parseInt(scheduledYear), Integer.parseInt(scheduledMonth),
				Integer.parseInt(scheduledDay));
		
		
		PortletSession session = actionRequest.getPortletSession();
		
		ArrayList<Job> joblist = (ArrayList<Job>) session.getAttribute("job-list");
		log.warn("job-list");
		log.warn(joblist);
		
		if (joblist == null || joblist.isEmpty()) {
			joblist = new ArrayList<Job>();
			
		}
		joblist.add(newJob);

		session.setAttribute("job-list", joblist);
		
		//ArrayList<Job> joblist = session.getAttribute("job-list");
		//actionRequest.setAttribute("joblist", joblist);
		
	}
	
	public void deleteJob(ActionRequest actionRequest,
			ActionResponse actionResponse) throws IOException, PortletException {
		
			log.warn("in deleteJob Action");
			String id = ParamUtil.getString(actionRequest, "jobId");
			log.warn("id: " + id);
			
			PortletSession session = actionRequest.getPortletSession();
			ArrayList<Job> joblist = (ArrayList<Job>) session.getAttribute("job-list");
			for (Job job: joblist) {
				if (job.getId() == Integer.parseInt(id)) {
					joblist.remove(job);
					break;
				}
			}
			session.setAttribute("job-list", joblist);
			//actionRequest.setAttribute("joblist", joblist);
	}
	
	public void editJob(ActionRequest actionRequest,
			ActionResponse actionResponse) throws IOException, PortletException {
		
		
		log.warn("in editJob Action");
		String id = ParamUtil.getString(actionRequest, "jobId");
		PortletSession session = actionRequest.getPortletSession();
		ArrayList<Job> joblist = (ArrayList<Job>) session.getAttribute("job-list");
		for (Job job: joblist) {
			if (job.getId() == Integer.parseInt(id)) {
				actionRequest.setAttribute("jobToEdit", job);
				break;
			}
		}		
		
		actionResponse.setRenderParameter("mvcPath", "/html/job-management/edit.jsp");
		//SessionMessages.clear(actionRequest);

	}
	
	public void updateJob(ActionRequest actionRequest, 
			ActionResponse actionResponse) throws IOException, PortletException {
		
		log.warn("update job");
		String id = ParamUtil.getString(actionRequest, "jobToUpdate");
		log.warn("jobToUpdate: " + id);
		String title = ParamUtil.getString(actionRequest, "title");
		String description = ParamUtil.getString(actionRequest, "description");
		String type = ParamUtil.getString(actionRequest, "type");
		String executable = ParamUtil.getString(actionRequest, "executable");
		String scheduledYear = ParamUtil.getString(actionRequest, "scheduledYear");
		String scheduledDay = ParamUtil.getString(actionRequest, "scheduledDay");
		String scheduledMonth = ParamUtil.getString(actionRequest, "scheduledMonth");
		
		log.warn("title : " + title);
		log.warn("description : " + description);
		log.warn("type : " + type);
		log.warn("executable : " + executable);
		log.warn("scheduledYear : " + scheduledYear);
		log.warn("scheduledDay : " + scheduledDay);
		log.warn("scheduledMonth : " + scheduledMonth);
		
		
		PortletSession session = actionRequest.getPortletSession();
		ArrayList<Job> joblist = (ArrayList<Job>) session.getAttribute("job-list");
		for (Job job: joblist) {
			if (job.getId() == Integer.parseInt(id)) {
				job.setTitle(title);
				job.setType(type);
				job.setDescription(description);
				job.setExecutable(executable);
				Calendar scheduledAt = CalendarFactoryUtil.getCalendar();
				scheduledAt.set(Calendar.MONTH, Integer.parseInt(scheduledMonth));
				scheduledAt.set(Calendar.YEAR, Integer.parseInt(scheduledYear));
				scheduledAt.set(Calendar.DATE, Integer.parseInt(scheduledDay));
				job.setScheduledAt(scheduledAt);
				session.setAttribute("job-list", joblist);
				//actionRequest.setAttribute("jobToEdit", job);
				break;
			}
		}
		actionResponse.setRenderParameter("mvcPath", "/html/job-management/view.jsp");
	}
}
