/**
 * Copyright 2011 Joao Miguel Pereira
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package eu.jpereira.trainings.designpatterns.creational.factorymethod;

import static org.junit.Assert.*;

import org.junit.Test;

import eu.jpereira.trainings.designpatterns.creational.factorymethod.Report;
import eu.jpereira.trainings.designpatterns.creational.factorymethod.ReportData;
import eu.jpereira.trainings.designpatterns.creational.factorymethod.JSONReportGenerator;
import eu.jpereira.trainings.designpatterns.creational.factorymethod.XMLReportGenerator;
import eu.jpereira.trainings.designpatterns.creational.factorymethod.HTMLReportGenerator;
import eu.jpereira.trainings.designpatterns.creational.factorymethod.PDFReportGenerator;



public class ReportGeneratorTest extends AbstractReportingTest{
	
	@Test
	public void testCreateJSONReport() {
		ReportData reportData = createDummyReportData();
		JSONReportGenerator generator = new JSONReportGenerator();
		generator.createReport(reportData);
		Report generatedReport = generator.getReport();
		assertEquals("JSON Report. Name: "+reportData.getName(), generatedReport.getReportContent());
	}
	
	
	@Test
	public void testCreateXMLReport() {
		ReportData reportData = createDummyReportData();
		XMLReportGenerator generator = new XMLReportGenerator();
		generator.createReport(reportData);
		Report generatedReport = generator.getReport();
		assertEquals("XML Report. Name: "+reportData.getName(), generatedReport.getReportContent());
	}
	
	@Test
	public void testCreateHTMLReport() {
		ReportData reportData = createDummyReportData();
		HTMLReportGenerator generator = new HTMLReportGenerator();
		generator.createReport(reportData);
		Report generatedReport = generator.getReport();
		assertEquals("HTML Report. Name: "+reportData.getName(), generatedReport.getReportContent());
	}
	
	@Test
	public void testCreatePDFReport() {
		ReportData reportData = createDummyReportData();
		PDFReportGenerator generator = new PDFReportGenerator();
		generator.createReport(reportData);
		Report generatedReport = generator.getReport();
		assertEquals("PDF Report. Name: "+reportData.getName(), generatedReport.getReportContent());
	}
	

	
	
}
