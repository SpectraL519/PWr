package eu.jpereira.trainings.designpatterns.creational.abstractfactory.json;

import eu.jpereira.trainings.designpatterns.creational.abstractfactory.AbstractReport;
import eu.jpereira.trainings.designpatterns.creational.abstractfactory.ReportBody;
import eu.jpereira.trainings.designpatterns.creational.abstractfactory.ReportFooter;
import eu.jpereira.trainings.designpatterns.creational.abstractfactory.ReportHeader;



public class JSONReport implements AbstractReport {
    private ReportBody reportBody;
    private ReportHeader reportHeader;
    private ReportFooter reportFooter;
    private String content;

    public JSONReport (){
        this.reportBody = new JSONReportBody();
        this.reportHeader = new JSONReportHeader();
        this.reportFooter = new JSONReportFooter();
        this.content = null;
    }

    @Override
    public void setBody (ReportBody body) {
        this.reportBody = body;
    }

    @Override
    public void setFooter (ReportFooter footer) {
        this.reportFooter = footer;
    }

    @Override
    public void setHeader (ReportHeader header) {
        this.reportHeader = header;
    }

    @Override
    public void setContent (String content){
        this.content = content;
    }

    @Override
    public ReportBody getBody () {
        return this.reportBody;
    }

    @Override
    public ReportFooter getFooter () {
        return this.reportFooter;
    }

    @Override
    public ReportHeader getHeader () {
        return this.reportHeader;
    }

    @Override
    public String getContent () {
        return this.content;
    }
}
