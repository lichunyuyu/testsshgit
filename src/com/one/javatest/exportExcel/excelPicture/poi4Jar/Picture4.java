package com.one.javatest.exportExcel.excelPicture.poi4Jar;


/**
 * Created by vtstar on 2018/10/25.
 */
public class Picture4  {
//
//    private static final POILogger logger = POILogFactory.getLogger(XSSFPicture.class);
////    private static CTPicture prototype;
////    private CTPicture ctPicture;
//
//
//    public void resize(double scaleX, double scaleY,XSSFPicture pict){
//        XSSFClientAnchor anchor = getClientAnchor(pict);
//        XSSFClientAnchor pref = getPreferredSize(scaleX, scaleY,pict);
//        if(anchor != null && pref != null) {
//            int row2 = anchor.getRow1() + (pref.getRow2() - pref.getRow1());
//            int col2 = anchor.getCol1() + (pref.getCol2() - pref.getCol1());
//            anchor.setCol2(col2);
//            anchor.setDx2(pref.getDx2());
//            anchor.setRow2(row2);
//            anchor.setDy2(pref.getDy2());
//        } else {
//            logger.log(5, new Object[]{"picture is not anchored via client anchor - ignoring resize call"});
//        }
//    };
//
//    public XSSFClientAnchor getClientAnchor(XSSFPicture pict) {
//        XSSFAnchor a = pict.getAnchor();
//        return a instanceof XSSFClientAnchor?(XSSFClientAnchor)a:null;
//    }
//    public XSSFClientAnchor getPreferredSize(double scaleX, double scaleY,XSSFPicture pict) {
//        Dimension dim = ImageUtils.setPreferredSize(pict, scaleX, scaleY);
//        CTPositiveSize2D size2d = pict.ctPicture.getSpPr().getXfrm().getExt();
//        size2d.setCx((long)((int)dim.getWidth()));
//        size2d.setCy((long)((int)dim.getHeight()));
//        return this.getClientAnchor(pict);
//    }

}
