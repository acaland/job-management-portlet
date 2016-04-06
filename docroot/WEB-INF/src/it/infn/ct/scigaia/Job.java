package it.infn.ct.scigaia;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.CalendarUtil;

public class Job {
	
	private String title;
	private String description;
	private String type;
	private String executable;
	private Calendar scheduledAt;
	private int id;
	

	public Job(int id, String title, String description, String type, String executable, int year, int month, int day) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.type = type;
		this.executable = executable;
		this.scheduledAt = CalendarFactoryUtil.getCalendar();
		scheduledAt.set(Calendar.MONTH, month);
		scheduledAt.set(Calendar.YEAR, year);
		scheduledAt.set(Calendar.DATE, day);

	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getExecutable() {
		return executable;
	}
	public void setExecutable(String executable) {
		this.executable = executable;
	}
	public Calendar getScheduledAt() {
		return scheduledAt;
	}
	public void setScheduledAt(Calendar scheduledAt) {
		this.scheduledAt = scheduledAt;
	}
	
	public String getScheduledAtAsString() {
		// Convert Calendar to Date
		Date scheduledDate = scheduledAt.getTime();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		return format1.format(scheduledDate);
	}
	
}
