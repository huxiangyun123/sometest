package com.dj.sometest.util;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class LngAndLatUtil {


  public static void main(String[] args) {
      Map<String, Double> map = LngAndLatUtil.getLngAndLat("上海");
  }

  public static Map<String, Double> getLngAndLat(String address) {
    Map<String, Double> map = new HashMap<String, Double>();
    //XtZSvo84FVQ2tumRRC0PtIGVNURSX4dQ
    //String url = "http://api.map.baidu.com/geocoder/v2/?address="+address+"&output=json&ak=vmoG32fVwWTqu8X3T7xdkLZAH09tFtDX";
    String url = "http://api.map.baidu.com/geocoding/v3/?address=" + address + "&output=json&ak=XtZSvo84FVQ2tumRRC0PtIGVNURSX4dQ";
    String json = loadJSON(url);
    JSONObject obj = JSONObject.fromObject(json);
    if (obj.get("status").toString().equals("0")) {
      double lng = obj.getJSONObject("result").getJSONObject("location").getDouble("lng");
      double lat = obj.getJSONObject("result").getJSONObject("location").getDouble("lat");
      map.put("lng", lng);
      map.put("lat", lat);
      System.out.println("经度：" + lng + "---纬度：" + lat);
    } else {
      log.info("未找到相匹配的经纬度！");
    }
    return map;
  }

  public static String loadJSON(String url) {
    StringBuilder json = new StringBuilder();
    try {
      URL oracle = new URL(url);
      URLConnection yc = oracle.openConnection();
      BufferedReader in = new BufferedReader(new InputStreamReader(
          yc.getInputStream()));
      String inputLine = null;
      while ((inputLine = in.readLine()) != null) {
        json.append(inputLine);
      }
      in.close();
    } catch (MalformedURLException e) {
    } catch (IOException e) {
    }
    return json.toString();
  }

}