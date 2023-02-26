package eu.jpereira.trainings.designpatterns.creational.builder.json;

import eu.jpereira.trainings.designpatterns.creational.builder.ReportBuilder;
import eu.jpereira.trainings.designpatterns.creational.builder.model.Report;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SoldItem;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SaleEntry;
import eu.jpereira.trainings.designpatterns.creational.builder.model.ReportBody;

import java.util.Iterator;



public class JSONReportBuilder implements ReportBuilder {

    private JSONReportBody reportBody;
    private Report report;

    public JSONReportBuilder (SaleEntry saleEntry) {
        this.reportBody = new JSONReportBody();
        this.report = new Report();

        //Add customer info
        this.reportBody.addContent("sale:{customer:{");
        this.reportBody.addContent("name:\"");
        this.reportBody.addContent(saleEntry.getCustomer().getName());
        this.reportBody.addContent("\",phone:\"");
        this.reportBody.addContent(saleEntry.getCustomer().getPhone());
        this.reportBody.addContent("\"}");
        //add array of items
        this.reportBody.addContent(",items:[");
        Iterator<SoldItem> it = saleEntry.getSoldItems().iterator();
        while (it.hasNext()) {
            SoldItem item = it.next();
            this.reportBody.addContent("{name:\"");
            this.reportBody.addContent(item.getName());
            this.reportBody.addContent("\",quantity:");
            this.reportBody.addContent(String.valueOf(item.getQuantity()));
            this.reportBody.addContent(",price:");
            this.reportBody.addContent(String.valueOf(item.getUnitPrice()));
            this.reportBody.addContent("}");
            if (it.hasNext()) {
                this.reportBody.addContent(",");
            }

        }
        this.reportBody.addContent("]}");
        this.report = new Report();
        this.report.setReportBody(this.reportBody);
    }

    public Report getReport() {
        return this.report;
    }
}
