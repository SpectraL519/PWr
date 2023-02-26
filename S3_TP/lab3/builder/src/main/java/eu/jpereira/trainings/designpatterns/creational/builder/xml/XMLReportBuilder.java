package eu.jpereira.trainings.designpatterns.creational.builder.xml;

import eu.jpereira.trainings.designpatterns.creational.builder.ReportBuilder;
import eu.jpereira.trainings.designpatterns.creational.builder.model.Report;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SaleEntry;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SoldItem;

import java.util.Iterator;



public class XMLReportBuilder implements ReportBuilder {

    private XMLReportBody reportBody;
    private Report report;

    public XMLReportBuilder (SaleEntry saleEntry) {
        this.reportBody = new XMLReportBody();
        this.report = new Report();

        this.reportBody.putContent("<sale><customer><name>");
        this.reportBody.putContent(saleEntry.getCustomer().getName());
        this.reportBody.putContent("</name><phone>");
        this.reportBody.putContent(saleEntry.getCustomer().getPhone());
        this.reportBody.putContent("</phone></customer>");

        this.reportBody.putContent("<items>");

        for (SoldItem soldEntry : saleEntry.getSoldItems()) {
            this.reportBody.putContent("<item><name>");
            this.reportBody.putContent(soldEntry.getName());
            this.reportBody.putContent("</name><quantity>");
            this.reportBody.putContent(soldEntry.getQuantity());
            this.reportBody.putContent("</quantity><price>");
            this.reportBody.putContent(soldEntry.getUnitPrice());
            this.reportBody.putContent("</price></item>");
        }
        this.reportBody.putContent("</items></sale>");
        this.report = new Report();
        this.report.setReportBody(this.reportBody);
    }

    public Report getReport() {
        return this.report;
    }
}
