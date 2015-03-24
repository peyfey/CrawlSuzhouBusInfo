package edu.se.ustc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import edu.se.ustc.item.BusStationInfo;

public class ClientWithResponseHandler {
	
	
	/**
	 * get each station information with the format of String
	 * @param str
	 * @return
	 */

	List<String> getEachStationInfo(String str) {

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
	 * get BusStationInfo from String information
	 * @param bsiString
	 * @return
	 */
	BusStationInfo stringToBusStationInfo(String bsiString) {

		if (bsiString.indexOf("<td>") < 0 || bsiString.indexOf("</td>") < 0) {
			return null;
		}

		BusStationInfo bsi = new BusStationInfo();

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
	 * get BusStationInfo List from String List
	 * @param stringList
	 * @return
	 */
	List<BusStationInfo> getBusStationInfoList(List<String> stringList) {
		List<BusStationInfo> bsiList = new ArrayList<BusStationInfo>();
		int size = 0;

		while (size < stringList.size()) {

			String bsiString = stringList.get(size);

			if (stringToBusStationInfo(bsiString) != null)
				bsiList.add(stringToBusStationInfo(bsiString));

			size++;
		}
		return bsiList;

	}
	
	/**
	 * output BusStation List
	 * @param bsiList
	 */

	void printBusStationInfo(List<BusStationInfo> bsiList) {
		for (int i = 0; i < bsiList.size(); i++) {
			System.out.println(bsiList.get(i));
		}
	}

	/**
	 * the main handler function of Crawls
	 * @throws Exception
	 */
	public void run(String address) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpget = new HttpGet(address);

			System.out.println("Executing request " + httpget.getRequestLine());

			// Create a custom response handler
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

				public String handleResponse(final HttpResponse response)
						throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity)
								: null;
					} else {
						throw new ClientProtocolException(
								"Unexpected response status: " + status);
					}
				}

			};
			String responseBody = httpclient.execute(httpget, responseHandler);
			System.out.println("----------------------------------------");

			// System.out.println(responseBody);

			String str = responseBody.substring(responseBody.indexOf("table"),
					responseBody.lastIndexOf("table"));

			System.out.println(str);

			List<String> stringList = getEachStationInfo(str);

			List<BusStationInfo> bsiList = getBusStationInfoList(stringList);

			printBusStationInfo(bsiList);

		} finally {
			httpclient.close();
		}
	}

	public static void main(String[] agrs) {

		ClientWithResponseHandler cwrh = new ClientWithResponseHandler();
		String address = "http://www.szjt.gov.cn/BusQuery/APTSLine.aspx?cid=175ecd8d-c39d-4116-83ff-109b946d7cb4&LineGuid=9acf55b9-8406-40ef-8056-6de249174ee0&LineInfo=19(%E6%96%B0%E7%81%AB%E8%BD%A6%E7%AB%99%E5%8C%97%E4%B8%B4%E6%97%B6%E5%B9%BF%E5%9C%BA)";
		try {
			cwrh.run(address);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
