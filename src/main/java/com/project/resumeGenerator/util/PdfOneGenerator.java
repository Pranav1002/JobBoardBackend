package com.project.resumeGenerator.util;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.project.resumeGenerator.model.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.project.resumeGenerator.model.Header;
import com.project.resumeGenerator.model.Resume1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Map;
import java.util.Map.Entry;

@Component
public class PdfOneGenerator {

	/* Fonts for lines */
	private static final Font nameStyle = new Font(Font.FontFamily.HELVETICA, 12f, Font.BOLD);
	private static final Font emptyLineStyle = new Font(Font.FontFamily.HELVETICA, 4f, Font.BOLD);
	private static final Font headingStyle = new Font(Font.FontFamily.HELVETICA, 11f, Font.BOLD);
	private static final Font contactStyle = new Font(Font.FontFamily.HELVETICA, 11f, Font.ITALIC);
	private static final Font contentStyle = new Font(Font.FontFamily.HELVETICA, 10f);

	@Autowired
	private Cloudinary cloudinary;

	public String createDocument(@Valid Resume1 resume) throws IOException {
		Document document = new Document();
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			String filename = resume.getHeader().getName(); // Construct filename
			PdfWriter writer = PdfWriter.getInstance(document, byteArrayOutputStream);

			document.open();
			if (resume != null) {
				if (resume.getHeader() != null) {
					addMetaData(document, resume.getHeader().getName());
				}
				addLayoutConfig(document);
				addLines(writer);
				if (resume.getHeader() != null) {
					addHeader(document, resume.getHeader());
				}
				if (resume.getEducation() != null) {
					addEducation(document, resume.getEducation());
				}
				if (resume.getExperience() != null) {
					addExperience(document, resume.getExperience());
				}
				if (resume.getSkills() != null) {
					addSkills(document, resume.getSkills());
				}
				if (resume.getProjects() != null) {
					addProjects(document, resume.getProjects());
				}
			}
			document.close();
			writer.close();

			// Upload the generated resume to Cloudinary
			Map uploadResult = uploadToCloudinary(byteArrayOutputStream.toByteArray(), filename);

			// Optionally, you can return the Cloudinary URL of the uploaded file
			String cloudinaryUrl = (String) uploadResult.get("secure_url");
			return cloudinaryUrl != null ? cloudinaryUrl : "Upload failed";
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed";
		}
	}

	private Map uploadToCloudinary(byte[] resumeBytes, String filename) {
		try {
			Map uploadResult = this.cloudinary.uploader().upload(resumeBytes, ObjectUtils.asMap(
					"resource_type", "auto", // Automatically detect the resource type
					"public_id", filename, // Public ID is the filename without extension
					"folder", "resumes" // Optional: folder in Cloudinary where the file will be stored
			));
			return uploadResult;
		} catch (IOException e) {
			throw new RuntimeException("Failed to upload to Cloudinary", e);
		}
	}


	public byte[] getDocument(String file_name) {
		try {
			String cloudinaryUrl = "resumes/" + file_name; // Assuming resumes are stored in the 'resumes' folder in Cloudinary
			URL url = new URL(cloudinaryUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			// Set Cloudinary credentials if needed
			connection.setRequestProperty("Authorization", "Basic " + Base64.getEncoder().encodeToString("api_key:api_secret".getBytes()));

			InputStream inputStream = connection.getInputStream();
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			int nRead;
			byte[] data = new byte[1024];
			while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, nRead);
			}
			buffer.flush();

			return buffer.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}


	public static void addMetaData(Document document, String author) {
		document.addTitle("My Resume PDF");
		document.addSubject("Using iText");
		document.addKeywords("Java, PDF, iText");
		document.addAuthor(author);
		document.addCreator("BuildByResume.com");
	}

	public static void addLayoutConfig(Document document) {
		/* Margins */
		document.setPageSize(PageSize.A4);
		document.setMargins(50f, 44f, 69f, 69f);
	}

	public static void addEmptyLines(Document document) throws DocumentException {
		Paragraph emptyLine = new Paragraph("", emptyLineStyle);
		document.add(emptyLine);
	}

	public static void addHeader(Document document, Header header) {
		try {
			Paragraph title = new Paragraph(header.getName() + "\n", nameStyle);
			title.setAlignment(Element.ALIGN_CENTER);
			document.add(title);

			addEmptyLines(document);

			PdfPTable table = new PdfPTable(new float[] { 20f, 40f, 40f });
			table.setPaddingTop(30f);
			table.getDefaultCell().setBorder(0);
			table.setWidthPercentage(100f);

			PdfPCell addressCell = new PdfPCell(new Phrase(header.getAddress(), contentStyle));
			addressCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			addressCell.setBorder(Rectangle.NO_BORDER);
			table.addCell(addressCell);

			PdfPCell personalWebsite = new PdfPCell(new Phrase(header.getWebsite(), contentStyle));
			personalWebsite.setHorizontalAlignment(Element.ALIGN_CENTER);
			personalWebsite.setBorder(Rectangle.NO_BORDER);
			table.addCell(personalWebsite);

			PdfPCell emailCell = new PdfPCell(new Phrase(header.getEmailAddress(), contentStyle));
			emailCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			emailCell.setBorder(Rectangle.NO_BORDER);
			table.addCell(emailCell);

			PdfPCell phoneCell = new PdfPCell(new Phrase(header.getPhoneNumber(), contentStyle));
			phoneCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			phoneCell.setBorder(Rectangle.NO_BORDER);
			table.addCell(phoneCell);

			PdfPCell githubCell = new PdfPCell(new Phrase(header.getGithub(), contentStyle));
			githubCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			githubCell.setBorder(Rectangle.NO_BORDER);
			table.addCell(githubCell);

			PdfPCell linkedInCell = new PdfPCell(new Phrase(header.getLinkedin(), contentStyle));
			linkedInCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			linkedInCell.setBorder(Rectangle.NO_BORDER);
			table.addCell(linkedInCell);

			document.add(table);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void addLines(PdfWriter writer) {

		/*
		 * PdfContentByte canvas = writer.getDirectContent();
		 * canvas.setColorStroke(BaseColor.BLACK ); canvas.moveTo(35, 735);
		 * canvas.lineTo(560, 735); canvas.closePathStroke();
		 *
		 * canvas.setColorStroke(BaseColor.BLACK ); canvas.moveTo(35, 610);
		 * canvas.lineTo(560, 610); canvas.closePathStroke();
		 */
	}

	public static void addEducation(Document document, ArrayList<Education> education) throws DocumentException {
		PdfPTable tablehead = new PdfPTable(new float[] { 100f });
		tablehead.getDefaultCell().setBorder(0);
		tablehead.setWidthPercentage(100f);

		PdfPCell headCell = new PdfPCell();
		Paragraph title = new Paragraph("Education", nameStyle);
		headCell.addElement(title);
		headCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		headCell.setBorder(Rectangle.BOTTOM);
		headCell.setBorderWidth(1f);
		tablehead.addCell(headCell);

		document.add(tablehead);
		addEmptyLines(document);

		PdfPTable table = new PdfPTable(new float[] { 70f, 30f });
		table.getDefaultCell().setBorder(0);
		table.setWidthPercentage(100f);

		for (Education edu : education) {
			PdfPCell collegeCell = new PdfPCell();
			Paragraph colName = new Paragraph(edu.getDegree() + " | " + edu.getName(), headingStyle);
			Paragraph colPlace = new Paragraph(edu.getLocation(), contactStyle);
			Paragraph colCourse = new Paragraph(edu.getMajors(), contactStyle);
			collegeCell.addElement(colName);
			collegeCell.addElement(colPlace);
			collegeCell.addElement(colCourse);
			collegeCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			collegeCell.setBorder(Rectangle.NO_BORDER);
			table.addCell(collegeCell);

			PdfPCell collegeTimeCell = new PdfPCell();
			Paragraph colTime = new Paragraph(edu.getPeriod(), headingStyle);
			colTime.setAlignment(Element.ALIGN_RIGHT);
			Paragraph colGPA = new Paragraph("GPA: " + edu.getGpa(), contactStyle);
			colGPA.setAlignment(Element.ALIGN_RIGHT);
			collegeTimeCell.addElement(colTime);
			collegeTimeCell.addElement(colGPA);
			collegeTimeCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			collegeTimeCell.setBorder(Rectangle.NO_BORDER);
			table.addCell(collegeTimeCell);
		}
		document.add(table);
		addEmptyLines(document);

	}

	public static void addExperience(Document document, ArrayList<Experience> experiences) throws DocumentException {
		PdfPTable tablehead = new PdfPTable(new float[] { 100f });
		tablehead.getDefaultCell().setBorder(0);
		tablehead.setWidthPercentage(100f);

		PdfPCell headCell = new PdfPCell();
		Paragraph title = new Paragraph("Experiences", nameStyle);
		headCell.addElement(title);
		headCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		headCell.setBorder(Rectangle.BOTTOM);
		headCell.setBorderWidth(1f);
		tablehead.addCell(headCell);

		document.add(tablehead);
		addEmptyLines(document);
		for (Experience exp : experiences) {
			/* header */
			PdfPTable table = new PdfPTable(new float[] { 70f, 30f });
			table.getDefaultCell().setBorder(0);
			table.setWidthPercentage(100f);

			PdfPCell jobCell = new PdfPCell();
			Paragraph jobName = new Paragraph(exp.getCompany() + " " + exp.getLocation(), headingStyle);
			Paragraph jobRole = new Paragraph(exp.getJobrole(), contactStyle);
			jobCell.addElement(jobName);
			jobCell.addElement(jobRole);
			jobCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			jobCell.setBorder(Rectangle.NO_BORDER);
			table.addCell(jobCell);

			PdfPCell jobTimeCell = new PdfPCell();
			Paragraph jobTime = new Paragraph(exp.getPeriod(), headingStyle);
			jobTime.setAlignment(Element.ALIGN_RIGHT);
			jobTimeCell.addElement(jobTime);
			jobTimeCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			jobTimeCell.setBorder(Rectangle.NO_BORDER);
			table.addCell(jobTimeCell);

			document.add(table);

			/* responsibilities */
			PdfPTable tableresp = new PdfPTable(new float[] { 100f });
			tableresp.getDefaultCell().setBorder(0);
			tableresp.setWidthPercentage(100f);

			PdfPCell resCell = new PdfPCell();
			List list = new List(List.UNORDERED);
			for (String res : exp.getResponsibilites()) {
				ListItem item = new ListItem(res, contentStyle);
				item.setAlignment(Element.ALIGN_JUSTIFIED);
				list.add(item);
			}
			resCell.addElement(list);
			// resCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			resCell.setBorder(Rectangle.NO_BORDER);
			tableresp.addCell(resCell);
			document.add(tableresp);

		}
	}

	public static void addSkills(Document document, Map<String, String> skill) throws DocumentException {
		PdfPTable tablehead = new PdfPTable(new float[] { 100f });
		tablehead.getDefaultCell().setBorder(0);
		tablehead.setWidthPercentage(100f);

		PdfPCell headCell = new PdfPCell();
		Paragraph title = new Paragraph("Skills", nameStyle);
		headCell.addElement(title);
		headCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		headCell.setBorder(Rectangle.BOTTOM);
		headCell.setBorderWidth(1f);
		tablehead.addCell(headCell);

		document.add(tablehead);
		addEmptyLines(document);

		PdfPTable table = new PdfPTable(new float[] { 40f, 60f });
		table.getDefaultCell().setBorder(0);
		table.setWidthPercentage(100f);

		for (Entry<String, String> me : skill.entrySet()) {
			PdfPCell category = new PdfPCell();
			Paragraph categoryName = new Paragraph(me.getKey(), contentStyle);
			category.addElement(categoryName);
			category.setHorizontalAlignment(Element.ALIGN_LEFT);
			category.setBorder(Rectangle.NO_BORDER);
			table.addCell(category);

			PdfPCell categoryValue = new PdfPCell();
			Paragraph categoryVal = new Paragraph(me.getValue(), contentStyle);
			categoryValue.addElement(categoryVal);
			categoryValue.setHorizontalAlignment(Element.ALIGN_LEFT);
			categoryValue.setBorder(Rectangle.NO_BORDER);
			table.addCell(categoryValue);
		}
		document.add(table);

	}

	public static void addProjects(Document document, ArrayList<Project> projectList) throws DocumentException {
		PdfPTable tablehead = new PdfPTable(new float[] { 100f });
		tablehead.getDefaultCell().setBorder(0);
		tablehead.setWidthPercentage(100f);

		PdfPCell headCell = new PdfPCell();
		Paragraph title = new Paragraph("Projects", nameStyle);
		headCell.addElement(title);
		headCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		headCell.setBorder(Rectangle.BOTTOM);
		headCell.setBorderWidth(1f);
		tablehead.addCell(headCell);

		document.add(tablehead);
		addEmptyLines(document);
		for (Project pro : projectList) {
			/* header */
			PdfPTable table = new PdfPTable(new float[] { 100f });
			table.getDefaultCell().setBorder(0);
			table.setWidthPercentage(100f);

			PdfPCell projectNameCell = new PdfPCell();
			Paragraph proName = new Paragraph(pro.getName(), headingStyle);
			projectNameCell.addElement(proName);
			projectNameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			projectNameCell.setBorder(Rectangle.NO_BORDER);
			table.addCell(projectNameCell);

			PdfPCell descCell = new PdfPCell();
			Paragraph proDesc = new Paragraph(pro.getDescription(), contentStyle);
			Paragraph proTech = new Paragraph("Technologies: " + pro.getTechnology(), contentStyle);
			descCell.addElement(proDesc);
			descCell.addElement(proTech);
			descCell.setBorder(Rectangle.NO_BORDER);
			table.addCell(descCell);

			document.add(table);
		}
	}
}