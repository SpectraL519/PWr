package eu.jpereira.trainings.designpatterns.creational.builder.html;

import eu.jpereira.trainings.designpatterns.creational.builder.ReportBuilder;
import eu.jpereira.trainings.designpatterns.creational.builder.model.Report;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SoldItem;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SaleEntry;
import eu.jpereira.trainings.designpatterns.creational.builder.model.ReportBody;

import java.util.Iterator;



public class HTMLReportBuilder implements ReportBuilder {

    private HTMLReportBody reportBody;
    private Report report;

    public HTMLReportBuilder (SaleEntry saleEntry) {
        this.reportBody = new HTMLReportBody();
		this.report = new Report();

		this.reportBody.putContent("<span class=\"customerName\">");
		this.reportBody.putContent(saleEntry.getCustomer().getName());
		this.reportBody.putContent("</span><span class=\"customerPhone\">");
		this.reportBody.putContent(saleEntry.getCustomer().getPhone());
		this.reportBody.putContent("</span>");
		this.reportBody.putContent("<items>");
			
		Iterator<SoldItem> it = saleEntry.getSoldItems().iterator();
		while ( it.hasNext() ) {
			SoldItem soldEntry= it.next();
			this.reportBody.putContent("<item><name>");
			this.reportBody.putContent(soldEntry.getName());
			this.reportBody.putContent("</name><quantity>");
			this.reportBody.putContent(soldEntry.getQuantity());
			this.reportBody.putContent("</quantity><price>");
			this.reportBody.putContent(soldEntry.getUnitPrice());
			this.reportBody.putContent("</price></item>");
		}
		this.reportBody.putContent("</items>");
		this.report.setReportBody(this.reportBody);
    }

    public Report getReport() {
        return this.report;
    }
}
