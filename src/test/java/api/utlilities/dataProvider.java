package api.utlilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class dataProvider {

    private static final String EXCEL_PATH = "/Users/sajanrajak/Documents/MyFrameWork/RestAssuredframeWork/petStore_FrameWork/test-data/Userdata3.xlsx";

    @DataProvider(name = "data")
    public static Object[][] getData() throws IOException {
        ExcelUtils excel = new ExcelUtils(EXCEL_PATH);
        String sheet = "Sheet1";

        int rows = excel.getRowCount(sheet);
        int cols = excel.getCellCount(sheet, 0);

        Object[][] data = new Object[rows - 1][cols];

        for (int i = 1; i < rows; i++) { // skip header
            for (int j = 0; j < cols; j++) {
                data[i - 1][j] = excel.getCellData(sheet, i, j);
            }
        }
        excel.closeWorkbook();
        return data;
    }
    
    // New DataProvider that returns only usernames
    @DataProvider(name = "usernames")
    public static Object[][] getUsernames() throws IOException {
        ExcelUtils excel = new ExcelUtils(EXCEL_PATH);
        String sheet = "Sheet1";

        int rows = excel.getRowCount(sheet);

        // Assuming 'username' is in first column (index 0), adjust if needed
        Object[][] usernames = new Object[rows - 1][1]; // one column (username)

        for (int i = 1; i < rows; i++) { // skip header
            usernames[i - 1][0] = excel.getCellData(sheet, i, 0);
        }

        excel.closeWorkbook();
        return usernames;
    }
}

