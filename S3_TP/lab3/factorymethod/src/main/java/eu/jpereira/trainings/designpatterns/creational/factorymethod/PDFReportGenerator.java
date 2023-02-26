package eu.jpereira.trainings.designpatterns.creational.factorymethod;



public class PDFReportGenerator extends ReportGenerator{

    @Override
    public void createReport (ReportData data) {
        Report report = new PDFReport();
        report.generateReport(data);
        setReport(report);
    }
}
