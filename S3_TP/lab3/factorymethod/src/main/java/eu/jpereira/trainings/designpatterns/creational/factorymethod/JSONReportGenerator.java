package eu.jpereira.trainings.designpatterns.creational.factorymethod;



public class JSONReportGenerator extends ReportGenerator{
    @Override
    public void createReport (ReportData data) {
        Report report = new JSONReport();
        report.generateReport(data);
        setReport(report);
    }
}
