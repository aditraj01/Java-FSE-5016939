public class ExcelDocumentFactory extends DocumentFactory{

	@Override
	public Document createDocument() {
		// TODO Auto-generated method stub
		return new ExcelDocument();
	}

}