package eu.jpereira.trainings.designpatterns.creational.abstractfactory;



public interface AbstractReport {

    public void setBody (ReportBody body);
    public void setFooter (ReportFooter footer);
    public void setHeader (ReportHeader header);
    public void setContent (String content);
    public ReportBody getBody();
    public ReportFooter getFooter();
    public ReportHeader getHeader();
    public String getContent();
}
