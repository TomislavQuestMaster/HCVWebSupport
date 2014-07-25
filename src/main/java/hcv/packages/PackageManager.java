package hcv.packages;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import hcv.packages.model.PackageItem;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.itextpdf.text.pdf.PdfWriter;

/**
 * @author tdubravcevic
 */
public class PackageManager implements IPackageManager {

	@Override
	public List<PackageItem> getAllPackages() {

		return null;
	}

	@Override
	public List<PackageItem> getAllPackagesInGroup(PackageItem group) {

		return null;
	}

	@Override
	public void pack(PackageItem packageItem) {

	}

	@Override
	public void generateSheet(PackageItem packageItem) throws IOException, DocumentException {
		createDocument();
	}

	private PdfPTable table;

	private void createDocument () throws DocumentException, IOException {
		Document document = new Document ();

		PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\tdubravcevic\\Downloads\\pdfTest.pdf"));
		document.open();

		table = new PdfPTable(3);
		table.setTotalWidth(565f);
		table.setLockedWidth(true);
		float[] widths = new float[] { 20f, 345f, 200f};
		table.setWidths(widths);

		table.addCell("No");
		table.addCell("Dauer/ Drill /Ziel");
		table.addCell("Description/ Keypoints");


		addTrainingRow ("prvi", "drugi");
		addTrainingRow ("drugi", "treci");
		addTrainingRow ("treci", "prvi");

		document.add(table);
		document.close();
	}

	private void addTrainingRow (String description, String keypoints) throws BadElementException, IOException {
		PdfPTable column1 = new PdfPTable (1);
		column1.addCell("");
		column1.addCell("");
		PdfPCell column1Wrapper = new PdfPCell (column1);
		column1Wrapper.setPadding(0f);
		column1Wrapper.setMinimumHeight(230f);
		table.addCell(column1Wrapper);


		PdfPTable column2 = new PdfPTable (1);
		column2.addCell("Ziel:..............................................................");

		Image jpg = Image.getInstance("C:\\Users\\tdubravcevic\\Downloads\\ci-mail.png");
		jpg.scaleAbsoluteWidth (340f);
		jpg.scaleAbsoluteHeight (210.0f);

		PdfPCell imageCell = new PdfPCell (jpg);
		imageCell.setPadding(2f);
		column2.addCell (imageCell);


		PdfPCell column2Wrapper = new PdfPCell (column2);
		column2Wrapper.setPadding(0f);
		table.addCell(column2Wrapper);


		PdfPTable column3 = new PdfPTable (1);
		PdfPCell descriptionCell = new PdfPCell (new Phrase(description));
		descriptionCell.setMinimumHeight(140.0f);
		column3.addCell(descriptionCell);
		column3.addCell(keypoints);
		PdfPCell column3Wrapper = new PdfPCell (column3);
		column3Wrapper.setPadding(0f);
		table.addCell(column3Wrapper);
	}

	@Override
	public PackageItem save(PackageItem element) {

		return null;
	}

	@Override
	public void remove(PackageItem element) {

	}

	@Override
	public PackageItem getOne(int id) {

		return null;
	}

	@Override
	public List<PackageItem> getUnsynced() {

		return null;
	}

	@Override
	public List<PackageItem> getDeleted() {

		return null;
	}
}
