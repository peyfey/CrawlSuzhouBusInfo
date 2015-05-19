package edu.se.ustc.item;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BusRouteInfo {

    Integer id;
    String stationName;// the name of bus station
    String stationSeries = "-1";// the series of a station
    String busLicence = "-1";// a bus's licence
    String enterStationTime = "-1";// enter the station's time
    String attribute;// current time

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getAttribute() {
        return attribute;
    }
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
    
	public String getEnterStationTime() {
		return enterStationTime;
	}
	public void setEnterStationTime(String enterStationTime) {
		this.enterStationTime = enterStationTime;
	}

	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getStationSeries() {
		return stationSeries;
	}
	public void setStationSeries(String stationSeries) {
		this.stationSeries = stationSeries;
	}
	
	public String getBusLicence() {
		return busLicence;
	}
	public void setBusLicence(String busLicence) {
		this.busLicence = busLicence;
	}

	@Override
	//the string which will be printed each line
	public String toString() {
		return "BusRouteInfo [stationName=" + stationName
				+ ", stationSeries=" + stationSeries + ", busLicence="
				+ busLicence + ", enterStationTime=" + enterStationTime + "]";
	}
	
    /**
     * get each station information with the format of String
     * @param str
     * @return
     */
	private List<String> getEachStationInfo(String str) {
        List<String> stringList = new ArrayList<String>();

        while (str != null && str.indexOf("<tr>") >= 0
                && str.indexOf("</tr>") >= 0) {
            // System.out.println(str.indexOf("<tr>"));
            String content = str.substring(str.indexOf("<tr>"),
                    str.indexOf("</tr>") + 5);
            // System.out.println(content);
            stringList.add(content);
            str = str.substring(str.indexOf("</tr>") + 5);
        }
        return stringList;
    }
	
    /**
     * get BusRouteInfo from String information
     * @param bsiString
     * @return
     */
   public  BusRouteInfo stringToBusRouteInfo(String bsiString) {
        if (bsiString.indexOf("<td>") < 0 || bsiString.indexOf("</td>") < 0) {
            return null;
        }
        BusRouteInfo bsi = new BusRouteInfo();
        String str = bsiString;
        String content = str.substring(str.indexOf("<td>"),
                str.indexOf("</td>"));
        if (content.indexOf("StandName") >= 0) {
            bsi.setStationName(content.substring(
                    content.indexOf("StandName") + 10, content.indexOf("\">")));
        }

        str = str.substring(str.indexOf("</td>") + 5);
        content = str.substring(str.indexOf("<td>") + 4, str.indexOf("</td>"));
        if (content != "") {
            bsi.setStationSeries(content);
        }

        str = str.substring(str.indexOf("</td>") + 5);
        content = str.substring(str.indexOf("<td>") + 4, str.indexOf("</td>"));
        if (content != "") {
            bsi.setBusLicence(content);
        }

        str = str.substring(str.indexOf("</td>") + 5);
        content = str.substring(str.indexOf("<td>") + 4, str.indexOf("</td>"));

        if (content != "") {

            bsi.setEnterStationTime(content);
        }
        return bsi;
    }
   
   /**
    * get BusRouteInfo List from String List
    * @param stringList
    * @return
    */
    public List<BusRouteInfo> getBusRouteInfoList(String str) {
        List<String> stringList =getEachStationInfo(str);
        List<BusRouteInfo> bsiList = new ArrayList<BusRouteInfo>();
        
        int size = 0;

        while (size < stringList.size()) {
            String bsiString = stringList.get(size);
            if (stringToBusRouteInfo(bsiString) != null)
                bsiList.add(stringToBusRouteInfo(bsiString));
            size++;
        } 
        return bsiList;
    }
    
    /**
     * output BusStation List
     * @param bsiList
     */
   public  void printBusRouteInfo(List<BusRouteInfo> bsiList) {
        for (int i = 0; i < bsiList.size(); i++) {
            System.out.println(bsiList.get(i));
        }
   }
        
   //the string which will be printed into text
   public String toTxt() {
       //get current time
       Date d = new Date();
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd "); 
       String dateNowStr = sdf.format(d);
       
       return   stationSeries + ","
               + busLicence + "," + dateNowStr + enterStationTime;
   }
   
        /**
         * try to save not empty BusStation List
         * @param bsiList
         * @throws IOException 
         */
   public boolean saveToTxt(String fileName, List<BusRouteInfo> oldList,List<BusRouteInfo> bsiList) throws IOException{
       
       File file = new File(fileName + ".txt");
       FileWriter fw = new FileWriter(file, true);
       
       if(!file.exists()){
           //创建文件
           try{
                    boolean b = file.createNewFile();
           }catch(Exception e){
                    e.printStackTrace();
           }
       }
       //只刷新当前为空
       for (int i = 0; i < bsiList.size()-1; i++) {
           //当前进站list发生更新,且更新内容不为空
           if(!oldList.get(i).enterStationTime.equals(bsiList.get(i).enterStationTime)){
               //非第一行更新
               if(bsiList.get(i).enterStationTime!=null&&bsiList.get(i+1).enterStationTime!=null){
                   System.out.println(fileName +" "+  bsiList.get(i+1).toTxt());
                   fw.append(bsiList.get(i+1).toTxt()+"\r\n");
                   fw.flush();
                   fw.close();
                   return true;
                   
               }
               //第一行更新
               else if(bsiList.get(i).enterStationTime==null&&bsiList.get(i).enterStationTime!=null){
                   System.out.println(fileName +" "+  bsiList.get(i).toTxt());
                   fw.append(bsiList.get(i).toTxt()+"\r\n");
                   fw.flush();
                   fw.close();
                   return true;
                  
               }
           }
       } 
       fw.flush();
       fw.close();
       return false;
   }
        
   
   
}
//>