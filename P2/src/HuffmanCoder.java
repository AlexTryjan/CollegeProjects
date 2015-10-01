


public class HuffmanCoder {

	String inputFile;
	String outputFile;
	BinaryWriter output;
	
	public HuffmanCoder(String inputFile, String outputFile) {
		this.inputFile = inputFile;
		this.outputFile = outputFile;
	}
	
	public void compress() {
		HuffmanCode HuffTree = new HuffmanCode(inputFile);
		try {
			output = new BinaryWriter(outputFile);
		} catch(Exception FileNotFoundException) {throw new IllegalArgumentException("PATH UNAVAILABLE");}
		try{
		for(byte b : HuffTree.listORIGINAL.order) {
		output.writeBinaryArray(HuffTree.code(b));
		}
		output.close();
		} catch(Exception IOException) {throw new IllegalArgumentException("PATH CLOSER UNAVAILABLE");}
	}
}
