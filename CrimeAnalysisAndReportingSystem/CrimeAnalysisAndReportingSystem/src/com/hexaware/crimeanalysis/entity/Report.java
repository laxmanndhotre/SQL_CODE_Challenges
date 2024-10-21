package com.hexaware.crimeanalysis.entity;

public class Report {
	   private int reportID;
	    private int incidentID;
	    private String reportingOfficer;
	    private String reportDate; 
	    private String reportDetails;
	    private String status;
	    private int victimID;
	    private int suspectID;
	    public Report() {}
		public Report(int reportID, int incidentID, String reportingOfficer, String reportDate,
				String reportDetails, String status,int victimID,int suspectID) {
			super();
			this.reportID = reportID;
			this.incidentID = incidentID;
			this.reportingOfficer = reportingOfficer;
			this.reportDate = reportDate;
			this.reportDetails = reportDetails;
			this.status = status;
			this.victimID = victimID;
			this.suspectID = suspectID;
		}
		public int getReportID() {
			return reportID;
		}
		public void setReportID(int reportID) {
			this.reportID = reportID;
		}
		public int getIncidentID() {
			return incidentID;
		}
		public void setIncidentID(int incidentID) {
			this.incidentID = incidentID;
		}
		public String getReportingOfficer() {
			return reportingOfficer;
		}
		public void setReportingOfficer(String reportingOfficer) {
			this.reportingOfficer = reportingOfficer;
		}
		public String getReportDate() {
			return reportDate;
		}
		public void setReportDate(String reportDate) {
			this.reportDate = reportDate;
		}
		public String getReportDetails() {
			return reportDetails;
		}
		public void setReportDetails(String reportDetails) {
			this.reportDetails = reportDetails;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public int getVictimID() {
			return victimID;
		}
		public void setVictimID(int victimID) {
			this.victimID = victimID;
		}
		public int getSuspectID() {
			return suspectID;
		}
		public void setSuspectID(int suspectID) {
			this.suspectID = suspectID;
		}
	    

}
