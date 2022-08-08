/**
 * @author IvanK
 * @email ivan.katumba@cbsinteractive.com
 * @create date 2022-06-03 15:55:50
 * @modify date 2022-06-03 15:55:50
 * @desc [description]
 */
package com.cbs.test.dataproviders;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import com.cbs.utils.excel.ExcelUtils;

import io.qameta.allure.Step;

public class DataProvision {

    @Step("Reading all the CreditCard Data")
    @DataProvider(name = "cardData")
    String[][] getData() throws IOException {

        String Path = "src/test/resources/files/apiData/Cards.xlsx";
        int rowNum = ExcelUtils.getRowCount(Path, "PAYAPI");
        int colCount = ExcelUtils.getCellCount(Path, "PAYAPI", 1);
        String cardData[][] = new String[rowNum][colCount];

        for (int i = 1; i <= rowNum; i++) {
            for (int j = 0; j < colCount; j++) {

                cardData[i - 1][j] = ExcelUtils.getCellData(Path, "PAYAPI", i, j);
            }

        }
        return cardData;

    }

}
