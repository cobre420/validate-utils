package org.gv.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class ValidateResultTools {

    private final static Logger logger = LoggerFactory.getLogger(ValidateResultTools.class);

//    public static String ID = "ID";
//    public static String ID_PARENT = "ID_PARENT";
//    public static String POPIS = "POPIS";
//    public static String DETAIL = "DETAIL";
//    public static String STAV = "STAV";

//    public static String[] colNames = new String[] {ID, ID_PARENT, POPIS, DETAIL, STAV};

//    public static SimpleDataSet toDS(ValidateResult validateResult) {
//        SimpleDataSet result = createDS();
//
//        for (ValidateResultItem item : validateResult.getItems()) {
//            int id = result.getRowCount();
//            itemToDsRow(
//                    result,
//                    item,
//                    id,
//                    null
//            );
//            appendChilds(result, item, id);
//        }
//
//        return result;
//    }

//    private static void appendChilds(SimpleDataSet ds, ValidateResultItem item, int id) {
//        for (ValidateResultItem childItem : item.childs()) {
//            int rowID = ds.getRowCount();
//            itemToDsRow(ds, childItem, rowID, id);
//            if (childItem.childCount() > 0) {
//                appendChilds(ds, childItem, rowID);
//            }
//        }
//    }

//    private static void itemToDsRow(SimpleDataSet ds, ValidateResultItem item, int id, Integer idParent) {
//        ds.addRow(
//                colNames,
//                new Object[] {
//                        id,
//                        idParent,
//                        item.getCaption(),
//                        item.getDescription(),
//                        item.getItemType().name()
//                }
//        );
//    }
//
//    private static SimpleDataSet createDS() {
//        List<SimpleDataSet.Column> cols = new ArrayList<SimpleDataSet.Column>();
//
//
//        cols.add(SimpleDataSet.newColumn(ID, Types.INTEGER));
//        cols.add(SimpleDataSet.newColumn(ID_PARENT, Types.INTEGER));
//        cols.add(SimpleDataSet.newColumn(POPIS, Types.VARCHAR, 1000, 0));
//        cols.add(SimpleDataSet.newColumn(DETAIL, Types.LONGVARCHAR));
//        cols.add(SimpleDataSet.newColumn(STAV, Types.VARCHAR, 20, 0));
//
//        return new SimpleDataSet(cols);
//    }

//    public static String toXmlString(final ValidateResult vr) {
//        final ByteArrayOutputStream os = new ByteArrayOutputStream();
//        XMLTools.marshal(vr, os, ValidateResult.class);
//
//        try {
//            return new String(os.toByteArray(), "UTF-8");
//        } catch (Exception e) {
//            throw new SerializationException("Error serializing to XML string: "+e.getMessage(), e);
//        }
//    }

//    public static ValidateResult fromXmlString(final String source) {
//        ValidateResult result = null;
//
//        if (!StringUtils.isBlank(source)) {
//            try {
//                result = XMLTools.unmarshal(new ByteArrayInputStream(source.getBytes("UTF-8")), ValidateResult.class);
//            } catch (Exception e) {
//                throw new SerializationException("Error deserializing from XML string: "+e.getMessage(), e);
//            }
//        }
//
//        return result;
//    }
}
