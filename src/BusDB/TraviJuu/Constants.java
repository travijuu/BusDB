package BusDB.TraviJuu;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Travego
 * Date: 14.01.2012
 * Time: 01:58
 * To change this template use File | Settings | File Templates.
 */
public  class Constants {

     HashMap<Integer, String> busTypeMap = new HashMap<Integer, String>();
     HashMap<Integer, Integer> busPictureMap = new HashMap<Integer, Integer>();
    ArrayList<String> busTypeList = new ArrayList<String>();

    public Constants() {
        busTypeList.add("Mercedes - Travego 15");
        busTypeMap.put(100, "Mercedes - Travego 15");
        busPictureMap.put(100, R.drawable.travego15);
        busTypeList.add("Mercedes - Travego 17");
        busTypeMap.put(101, "Mercedes - Travego 17");
        busPictureMap.put(101, R.drawable.travego17);
        busTypeList.add("Setra - S 415");
        busTypeMap.put(102, " Setra - S 415");
        busPictureMap.put(102, R.drawable.setra415);
        busTypeList.add("Setra - S 416");
        busTypeMap.put(103, "Setra - S 416");
        busPictureMap.put(103, R.drawable.setra416);
        busTypeList.add("Setra - S 417");
        busTypeMap.put(104, "Setra - S 417");
        busPictureMap.put(104, R.drawable.setra417);
        busTypeList.add("Setra - S 431");
        busTypeMap.put(105, "Setra - S 431");
        busPictureMap.put(105, R.drawable.setra431);
        busTypeList.add("Neoplan - Starliner II");
        busTypeMap.put(106, "Neoplan - Starliner II");
        busPictureMap.put(106, R.drawable.starliner);
        busTypeList.add("Neoplan - Cityliner");
        busTypeMap.put(107, "Neoplan - Cityliner");
        busPictureMap.put(107, R.drawable.cityliner);
        busTypeList.add("Neoplan - Tourliner SHD");
        busTypeMap.put(108, "Neoplan - Tourliner SHD");
        busPictureMap.put(108, R.drawable.tourlinershd);
        busTypeList.add("Neoplan - Tourliner SHDL");
        busTypeMap.put(109, "Neoplan - Tourliner SHDL");
        busPictureMap.put(109, R.drawable.tourlinershdl);
        busTypeList.add("Mercedes - Tourismo 15");
        busTypeMap.put(110, "Mercedes - Tourismo 15");
        busPictureMap.put(110, R.drawable.tourismo15);
        busTypeList.add("Mercedes - Tourismo 17");
        busTypeMap.put(111, "Mercedes - Tourismo 17");
        busPictureMap.put(111, R.drawable.tourismo17);
        busTypeList.add("Man - Fortuna");
        busTypeMap.put(112, "Man - Fortuna");
        busPictureMap.put(112, R.drawable.fortuna);
        busTypeList.add("Man - Fortuna Mega");
        busTypeMap.put(113, "Man - FortunaMega");
        busPictureMap.put(113, R.drawable.fortunamega);
        busTypeList.add("Mercedes - O 403");
        busTypeMap.put(114, "Mercedes - O 403");
        busPictureMap.put(114, R.drawable.o403);
    }
}
