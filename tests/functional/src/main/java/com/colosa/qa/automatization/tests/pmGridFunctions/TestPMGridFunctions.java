package com.colosa.qa.automatization.tests.pmGridFunctions;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;

public class TestPMGridFunctions extends com.colosa.qa.automatization.tests.common.Test{

	public static String[] campo1 = {"fasfas", "wrwe", "jhgfgdh", "bhsdftrt", "terter", "sdghgfhfsfdgdfsgdsfgdsg", "erterwrwrsddg", "erwterttertwtdgdfg", "ndgfgtrbhfhgfrtyryr", "dsafsdfgdfcx", "badgrewtertet", "mhgjytutyukhjk", "ljkjkjgkhgkj", "ñgjkhkjgry", "bsfgsdfreteert", "vasdfsferw", "zxfdsafadfgfd", "qewrfdsgrett", "gfhghfgdh", "rewrwetregdfgssdg"};
	public static String[] salary = {"1,457.45", "789.48", "7,897,979,789,756,418,979.12", "46,546,546,316,548,797,654,648,979.69", "7,965,461,231.1235", "46,546,346.236", "123,214.1635649", "11,231,231,223,131.15264", "13,245,687,966,456.1356749", "46,579,863,232,135.464687", "134,567,987.1236", "123,416,547,987.45689", "98,762,136,654.312363", "45,646,579,879.134564", "789,546,431,646.12364654", "64,897,312,364,664.26598", "14,657,932,165,489.764", "64,649,876,464,646.32135665", "9,879,865,432,164,979,879.1316546", "79,864,632,131,013,547,978,976,431,316,498,761,313,130,314,649,879,864,123,165,489,796,465,412,313,356,497,876,413,131.13564987"};
	public static String[] porcent = {"31231231.321312", "31234343534.543534534", "42342354345.4324254", "645613213.1634654", "1321654973.131654", "5647936132165.134657", "734231646.134564", "3456498763.13165", "1313467653.3241364", "3213465313.213465", "97643133.13465", "3464987.13456", "45632123654.1324", "79634679.13456798", "36597643.32416546", "765431654987.79743", "65463136497,789643", "63156197,31236546", "4676643216.465498", "765431654.79764321"};
	public static String[] desc = {"prueba1", "prueba2", "prueba3", "prueba4", "prueba5", "prueba6", "prueba7", "prueba8", "prueba9", "prueba10", "prueba11", "prueba12", "prueba13", "prueba14", "prueba15", "prueba16", "prueba17", "prueba18", "prueba19", "prueba20"};
	public static String[] drop = {"Valor4", "Valor5", "Valor2", "Valor3", "Valor3", "Valor1", "Valor4", "Valor3", "Valor1", "Valor3", "Valor5", "Valor1", "Valor2", "Valor1", "Valor4", "Valor2", "Valor1", "Valor5", "Valor3", "Valor3", "Valor2"};


	public static String[] nombre = {"prueba1", "prueba2", "prueba3", "prueba4", "prueba5", "prueba6", "prueba7", "prueba8", "prueba9", "prueba10", "prueba11", "prueba12", "prueba13", "prueba14", "prueba15", "prueba16", "prueba17", "prueba18", "prueba19", "prueba20"};
	public static String[] salario = {"1,457.45", "789.48", "7,897,979,789,756,418,979.12", "46,546,546,316,548,797,654,648,979.69", "7,965,461,231.1235", "46,546,346.236", "123,214.1635649", "11,231,231,223,131.15264", "13,245,687,966,456.1356749", "46,579,863,232,135.464687", "134,567,987.1236", "123,416,547,987.45689", "98,762,136,654.312363", "45,646,579,879.134564", "789,546,431,646.12364654", "64,897,312,364,664.26598", "14,657,932,165,489.764", "64,649,876,464,646.32135665", "9,879,865,432,164,979,879.1316546", "79,864,632,131,013,547,978,976,431,316,498,761,313,130,314,649,879,864,123,165,489,796,465,412,313,356,497,876,413,131.13564987"};
	public static String[] descuento = {"546.465789", "131.324", "45,646,312,316,446.4646", "765,321,645,789.13564", "4,564,879.13564", "6,579,863.13654", "45,456.2659", "4,563,216,546,487.1365649", "321,564,498,796.13464", "45,646,316,546.13464987", "78,964,631.131654", "65,431,646,546.764316", "11,564,778,964.1316546", "25,643,216,546.46543", "545,646,546,546.1316464", "12,331,231,642,316.1231231", "14,432,132,465,321.13564654", "4,654,657,983,621.24324", "8,686,856,658,566,856.254353534", "465,465,123,165,445,646,465,454,664,646,546,532,421,423,423,424,324,231,432,432,142,142,424,234,142,332,412,432,424.13416574987"};
	public static String[] salario2 = {"1457.45", "789.48", "789797978975.12", "465465463165.69", "7965461231.35", "46546346.36", "123214.49", "112312312231.15", "132456879664.13", "465798632321.46", "134567987.36", "123416547987.45", "98762136654.63", "45646579879.64", "789546431646.12", "648973123646.26", "146579321654.76", "646498764646.32", "987986543216.13", "798646321310.13"};
	public static String[] descuento2 = {"546.89", "131.24", "456463123164.46", "765321645789.13", "4564879.64", "6579863.54", "45456.59", "456321654648.13", "321564498796.13", "45646316546.87", "78964631.54", "65431646546.16", "11564778964.46", "25643216546.43", "545646546546.13", "123312316423.12", "144321324653.13", "465465798362.24", "868685665856.25", "465465123165.13"};
	public static double total;

    public TestPMGridFunctions(String browserName) throws IOException {
        super(browserName);
    }


    @Test
	public void runProcess()throws FileNotFoundException, IOException, Exception{
		
		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		pages.Home().gotoNewCase().startCase("Test Grid Functions (Task 1)");
		pages.DynaformExecution().intoDynaform();
		for(int count=0;count<19;count++)
		{
			pages.DynaformExecution().gridAddNewRow("grid1");
			pages.DynaformExecution().gridAddNewRow("grid2");
		}

		int numRow = 0;
		int numRow2 = 0;
		int numRow3 = 0;
		DecimalFormat fmt = new DecimalFormat("0.00"); 
		for(int count2=0;count2<20;count2++)
		{
			numRow = count2+1;
			pages.DynaformExecution().setGridFieldValue("grid1", numRow, "Campo1", campo1[count2]);
			pages.DynaformExecution().setGridFieldValue("grid1", numRow, "Salario", salario[count2]);
			pages.DynaformExecution().setGridFieldValue("grid1", numRow, "Porcentaje", porcent[count2]);
			pages.DynaformExecution().setGridFieldValue("grid1", numRow, "Descripcion", desc[count2]);
			pages.DynaformExecution().setGridFieldValue("grid1", numRow, "Dropdown", drop[count2]);
		}

		for(int count3=0;count3<20;count3++)
		{
			numRow2 = count3+1;
			pages.DynaformExecution().setGridFieldValue("grid2", numRow2, "Nombre", nombre[count3]);
			pages.DynaformExecution().setGridFieldValue("grid2", numRow2, "salary", salario[count3]);
			pages.DynaformExecution().setGridFieldValue("grid2", numRow2, "discount", descuento[count3]);
		}

		pages.DynaformExecution().setFieldValue("Enviar", "");

		for(int count4=0;count4<20;count4++)
		{
			numRow3 = count4+1;
			total = Double.parseDouble(salario2[count4])-Double.parseDouble(descuento2[count4]);
			Assert.assertEquals(fmt.format(Double.parseDouble(pages.DynaformExecution().getGridFieldValue("grid2", numRow3, "total"))), fmt.format(total));
		}
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }

}