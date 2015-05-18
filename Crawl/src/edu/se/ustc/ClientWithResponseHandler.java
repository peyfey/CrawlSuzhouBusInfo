/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

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

import edu.se.ustc.item.BusRouteInfo;
import edu.se.ustc.util.dbUtil;

import java.sql.*;
/**
 * This example demonstrates the use of the {@link ResponseHandler} to simplify
 * the process of processing the HTTP response and releasing associated
 * resources.
 */
public class ClientWithResponseHandler implements Runnable {
    /**
     * the main handler function of Crawls
     * @throws Exception
     */
    private String fileName;    //进程生成的文件名
    private String URL;             //进程URL
    private static boolean startFlag; //所有进程启动标志
    
    public ClientWithResponseHandler(String fileName, String URL){
        this.fileName=fileName;
        this.URL=URL;
    }
    
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

			BusRouteInfo binfo=new BusRouteInfo();
			List<BusRouteInfo> bsrList = binfo.getBusRouteInfoList(str);
			List<BusRouteInfo> oldList = bsrList;
			
			binfo.printBusRouteInfo(bsrList);
			
			
			//select the db
			//dbUtil db = new dbUtil();
			//result=db.executeQuery("SELECT * FROM station_stats");
//			if(result!=null)
//			    System.out.println(result.getString(1));
		} finally {
			httpclient.close();
		}
	}

//	public static void main(String[] agrs){
//		
//		//ClientWithResponseHandler cwrh  = new ClientWithResponseHandler();
//		//
//		//String address = "http://www.szjt.gov.cn/BusQuery/APTSLine.aspx?cid=175ecd8d-c39d-4116-83ff-109b946d7cb4&LineGuid=9acf55b9-8406-40ef-8056-6de249174ee0&LineInfo=19(%E6%96%B0%E7%81%AB%E8%BD%A6%E7%AB%99%E5%8C%97%E4%B8%B4%E6%97%B6%E5%B9%BF%E5%9C%BA)";
//		//快线2号
//		String address ="http://www.szjt.gov.cn/BusQuery/APTSLine.aspx?cid=175ecd8d-c39d-4116-83ff-109b946d7cb4&LineGuid=e0e5561a-32ea-432d-ac98-38eed8c4e448&LineInfo=%E5%BF%AB%E7%BA%BF2%E5%8F%B7(%E7%8B%AC%E5%A2%85%E6%B9%96%E9%AB%98%E6%95%99%E5%8C%BA%E9%A6%96%E6%9C%AB%E7%AB%99=%3E%E7%81%AB%E8%BD%A6%E7%AB%99)";
//		try {
//			cwrh.run(address);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}

	//设置进程开始标志
	public static void setStartFlag(boolean flag){
	    startFlag=flag;
	}
	//获得进程开始标志
	public static boolean getStartFlag(){
	    return startFlag;
	}
	
    @Override
    public void run() {
        // TODO Auto-generated method stub
        //
        //String address = "http://www.szjt.gov.cn/BusQuery/APTSLine.aspx?cid=175ecd8d-c39d-4116-83ff-109b946d7cb4&LineGuid=9acf55b9-8406-40ef-8056-6de249174ee0&LineInfo=19(%E6%96%B0%E7%81%AB%E8%BD%A6%E7%AB%99%E5%8C%97%E4%B8%B4%E6%97%B6%E5%B9%BF%E5%9C%BA)";
        //快线2号
        //String address ="http://www.szjt.gov.cn/BusQuery/APTSLine.aspx?cid=175ecd8d-c39d-4116-83ff-109b946d7cb4&LineGuid=e0e5561a-32ea-432d-ac98-38eed8c4e448&LineInfo=%E5%BF%AB%E7%BA%BF2%E5%8F%B7(%E7%8B%AC%E5%A2%85%E6%B9%96%E9%AB%98%E6%95%99%E5%8C%BA%E9%A6%96%E6%9C%AB%E7%AB%99=%3E%E7%81%AB%E8%BD%A6%E7%AB%99)";
        //ClientWithResponseHandler cwrh  = new ClientWithResponseHandler("K2",address);
        while(getStartFlag()){
            try {
                run(URL);
                Thread.currentThread().sleep(5000);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
