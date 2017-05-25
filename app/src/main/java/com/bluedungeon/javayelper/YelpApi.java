package com.bluedungeon.javayelper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.yelp.fusion.client.connection.*;
import com.yelp.fusion.client.models.SearchResponse;
import retrofit2.*;

/**
 * Created by isuru on 5/20/17.
 */

public class YelpApi {

    private String appId="xyvegEYbrGqW0Oz88TepFg",
            appSecret="SpHHdrGjFrHTDpUcP2Ypv24GDrFemmBuWEuSXAezA7lSjnowMNJglyuWRnGgApWY";

    public Map<String, String> params;
    public YelpFusionApiFactory apiFactory;
    public YelpFusionApi yelpFusionApi;
    public Call<SearchResponse> call;
    public Response<SearchResponse> response;

    YelpApi(){
        this.params = new HashMap<>();
        this.apiFactory = new YelpFusionApiFactory();

    }

    {
        try {
             yelpFusionApi = this.apiFactory.createAPI(getAppId(), getAppSecret());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getAppId(){
        return this.appId;
    }
    public String getAppSecret(){
        return this.appSecret;
    }

    public void search(String term, String longitude, String latitude){
        params.put("term", term);
        params.put("latitude", latitude);
        params.put("longitude", longitude);
        this.call = this.yelpFusionApi.getBusinessSearch(this.params);
    }


}
