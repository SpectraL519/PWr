package eu.jpereira.trainings.designpatterns.creational.abstractfactory.xml;

import eu.jpereira.trainings.designpatterns.creational.abstractfactory.AbstractReport;
import eu.jpereira.trainings.designpatterns.creational.abstractfactory.ReportBody;
import eu.jpereira.trainings.designpatterns.creational.abstractfactory.ReportFooter;
import eu.jpereira.trainings.designpatterns.creational.abstractfactory.ReportHeader;



public class XMLReport implements AbstractReport {
    private ReportBody reportBody;
    private ReportFooter reportFooter;
    private ReportHeader reportHeader;
    private String content;

    public XMLReport () {
        this.reportBody = new XMLReportBody();
        this.reportFooter = new XMLReportFooter();
        this.reportHeader = new XMLReportHeader();
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
