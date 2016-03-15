
public class DBDemoMain {

	public static void main(String[] args) throws Exception {
		MySQLAccess dao = new MySQLAccess();
		dao.connectToDB();
		//dao.writeSales();
	    dao.readSales();
	    dao.close();
	}

}
