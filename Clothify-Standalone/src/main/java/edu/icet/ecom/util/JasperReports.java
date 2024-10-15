package edu.icet.ecom.util;

import edu.icet.ecom.dto.OrderDetails;
import edu.icet.ecom.dto.Orders;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import java.util.HashMap;
import java.util.List;

public class JasperReports {
    private static JasperReports instance;
    private JasperReports(){}
    public static JasperReports getInstance(){
        return instance==null?instance=new JasperReports():instance;
    }

    public void createInvoice(Orders orders){
        String jasperFilePath = "D:\\University\\A ICET\\Level 3\\Standalone apps\\Clothing Shop\\Application\\Clothify-Standalone\\src\\main\\resources\\jasperReports\\compiledTemplates\\Blank.jasper";
        try {
            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(jasperFilePath);

            List<OrderDetails> dataList = orders.getOrderDetails();
            JRBeanCollectionDataSource orderDataSource = new JRBeanCollectionDataSource(dataList);
            System.out.println(orders.getOrderDetails().get(0));

            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("adminName",orders.getAdminName());
//            parameters.put("netTotal",orders.getNetTotal());
            parameters.put("DATASET",orderDataSource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,orderDataSource);

            JasperExportManager.exportReportToPdfFile(jasperPrint,"D:\\University\\A ICET\\Level 3\\Standalone apps\\Clothing Shop\\Application\\Clothify-Standalone\\src\\main\\resources\\jasperReports\\outputReports\\Sample.pdf");
            System.out.println("Successful");
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }
}
