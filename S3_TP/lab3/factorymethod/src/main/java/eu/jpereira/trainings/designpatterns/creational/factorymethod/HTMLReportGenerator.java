package eu.jpereira.trainings.designpatterns.creational.factorymethod;



public class HTMLReportGenerator extends ReportGenerator{

    @Override
    public void createReport (ReportData data) {
        Report report = new HTMLReport();
        report.generateReport(data);
        setReport(report);
    }
}
