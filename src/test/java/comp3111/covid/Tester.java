package comp3111.covid;
import static org.junit.Assert.*;
import org.junit.Test;
public class Tester {
	private CommonData data1 = new CommonData();
	private CommonData data2 = new CommonData("2022/4/12","Hong Kong");
	private String dataset = "COVID_Dataset_v1.0.csv";
	
	@Test
	public void checkdate() {
		assertEquals(data1.getdate(),"");
		assertEquals(data2.getdate(),"2022/4/12");
	}
	
	@Test
	public void checkcountry() {
		assertEquals(data1.getcountry(),"");
		assertEquals(data2.getcountry(),"Hong Kong");
	}
	
	@Test
	public void checkfileparser() {
		assertSame(CommonData.getFileParser(dataset).getClass(),DataAnalysis.getFileParser(dataset).getClass());
	}
	
	@Test
	public void checksetcountry() {
		data1.setcountry(dataset, "HKG");
		assertEquals(data1.getcountry() ,"Hong Kong");
	}
}
