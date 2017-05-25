package com.bluedungeon.javayelper;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bluedungeon.javayelper.SingleShotLocationProvider.GPSCoordinates;
import com.bumptech.glide.Glide;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.yelp.fusion.client.connection.YelpFusionApi;
import com.yelp.fusion.client.connection.YelpFusionApiFactory;
import com.yelp.fusion.client.models.Business;
import com.yelp.fusion.client.models.SearchResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

public class YelpSwipe extends AppCompatActivity implements OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {

    public static MyAppAdapter myAppAdapter;
    public static ViewHolder viewHolder;
    private ArrayList<Data> array;
    private SwipeFlingAdapterView flingContainer;
    private int i;
    public float lat, longi;

    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // over ride multi thread rule for networking
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yelp_swipe);

        flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);


        array = new ArrayList<>();
        array.add(new Data("https://www.androidtutorialpoint.com/wp-content/uploads/2016/11/Katrina-Kaif.jpg", "27"));
        array.add(new Data("https://www.androidtutorialpoint.com/wp-content/uploads/2016/11/Emma-Watson.jpg", "27"));
        array.add(new Data("https://www.androidtutorialpoint.com/wp-content/uploads/2016/11/Scarlett-Johansson.jpg", "22"));
        array.add(new Data("https://www.androidtutorialpoint.com/wp-content/uploads/2016/11/Priyanka-Chopra.jpg", "19"));
        array.add(new Data("https://www.androidtutorialpoint.com/wp-content/uploads/2016/11/Deepika-Padukone.jpg", "90"));
        array.add(new Data("https://www.androidtutorialpoint.com/wp-content/uploads/2016/11/Anjelina-Jolie.jpg", "26"));
        array.add(new Data("https://www.androidtutorialpoint.com/wp-content/uploads/2016/11/Aishwarya-Rai.jpg", "90"));

        myAppAdapter = new MyAppAdapter(array, YelpSwipe.this);
        flingContainer.setAdapter(myAppAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {

//        ///// YELP


        try {


            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            mGoogleApiClient.connect();

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                // Check Permissions Now
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        1);
                Log.d("INSIDE","INSIDE PERMSISSON");
                return;
            }

=======
            SingleShotLocationProvider.requestSingleUpdate(this,
                    new SingleShotLocationProvider.LocationCallback() {
                        @Override public void onNewLocationAvailable(GPSCoordinates location) {
                            Log.d("Location", "my latitude is " + location.latitude);
                            Log.d("Location", "my longitude is " + location.longitude);
                            lat = location.latitude;
                            longi = location.longitude;
                    return;
                        }
                    });


            Log.d("Location", "LATITUDE " + lat);
            Log.d("Location", "LONGITUDE " + longi);

            YelpFusionApiFactory apiFactory = new YelpFusionApiFactory();
            YelpFusionApi yelpFusionApi = apiFactory.createAPI("xyvegEYbrGqW0Oz88TepFg", "SpHHdrGjFrHTDpUcP2Ypv24GDrFemmBuWEuSXAezA7lSjnowMNJglyuWRnGgApWY");
            Map<String, String> params = new HashMap<>();

// general params
            params.put("term", "food");
//            params.put("location","New York");
            params.put("latitude", "40.715168");
            params.put("longitude", "-73.78001");
//            params.put("latitude", Float.toString(lat));
//            params.put("longitude", Float.toString(longi));


                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.background).setAlpha(1);
                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });


            Call<SearchResponse> call = yelpFusionApi.getBusinessSearch(params);
//            Response<SearchResponse> response = call.execute();

            SearchResponse searchResponse = call.execute().body();

            int totalNumberOfResult = searchResponse.getTotal();  // 3

            ArrayList<Business> businesses = searchResponse.getBusinesses();
            String businessName = businesses.get(0).getName();  // "JapaCurry Truck"
            String url = businesses.get(0).getImageUrl();
            Double rating = businesses.get(0).getRating();  // 4.0
            Log.v("THIS", Integer.toString(totalNumberOfResult));
            array = new ArrayList<>();
           for (Business business: businesses){
               array.add(new Data(business.getImageUrl(), business.getName()));
           }


            myAppAdapter = new MyAppAdapter(array, YelpSwipe.this);
            flingContainer.setAdapter(myAppAdapter);
            flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
                @Override
                public void removeFirstObjectInAdapter() {

                }

                @Override
                public void onLeftCardExit(Object dataObject) {
                    array.remove(0);
                    myAppAdapter.notifyDataSetChanged();
                    //Do something on the left!
                    //You also have access to the original object.
                    //If you want to use it just cast it (String) dataObject
                }

                @Override
                public void onRightCardExit(Object dataObject) {


                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.background).setAlpha(1);

                    array.remove(0);
                    myAppAdapter.notifyDataSetChanged();
                }

                @Override
                public void onAdapterAboutToEmpty(int itemsInAdapter) {
                    // Ask for more data here
                    array.add(new Data("https://www.androidtutorialpoint.com/wp-content/uploads/2016/11/Katrina-Kaif.jpg", "Hi I am Katrina Kaif. Wanna chat with me ?. \n" +
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
                    array.add(new Data("https://www.androidtutorialpoint.com/wp-content/uploads/2016/11/Katrina-Kaif.jpg", "Hi I am Katrina Kaif. Wanna chat with me ?. \n" +
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));


                }

                @Override
                public void onScroll(float scrollProgressPercent) {

                    View view = flingContainer.getSelectedView();
                    view.findViewById(R.id.background).setAlpha(0);
                    view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                    view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
                }
            });


            // Optionally add an OnItemClickListener
            flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
                @Override
                public void onItemClicked(int itemPosition, Object dataObject) {

                    View view = flingContainer.getSelectedView();
                    view.findViewById(R.id.background).setAlpha(0);

                    myAppAdapter.notifyDataSetChanged();
                }
            });


        }catch (IOException e) {
            e.printStackTrace();
        }



    }

    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions,
                                           int[] grantResults) {
        Log.v("Inside request", "inside request");
        if (requestCode == 1) {
            if(grantResults.length == 1
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // We can now safely use the API we requested access to
                Location myLocation =
                        LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            } else {
                // Permission was denied or request was cancelled
            }
        }
    }

    public static JSONObject getJSONObjectFromURL(String urlString) throws IOException, JSONException {

        HttpURLConnection urlConnection = null;

        URL url = new URL(urlString);

        urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setRequestMethod("GET");
        urlConnection.setReadTimeout(10000 /* milliseconds */);
        urlConnection.setConnectTimeout(15000 /* milliseconds */);

        urlConnection.setDoOutput(true);

        urlConnection.connect();

        BufferedReader br=new BufferedReader(new InputStreamReader(url.openStream()));

        char[] buffer = new char[1024];

        String jsonString = new String();

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line+"\n");
        }
        br.close();

        jsonString = sb.toString();

        System.out.println("JSON: " + jsonString);

        return new JSONObject(jsonString);
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }


    public static class ViewHolder {
        public static FrameLayout background;
        public TextView bookText;
        public ImageView cardImage;


    }

    public class MyAppAdapter extends BaseAdapter {


        public List<Data> parkingList;
        public Context context;

        private MyAppAdapter(List<Data> apps, Context context) {
            this.parkingList = apps;
            this.context = context;
        }

        @Override
        public int getCount() {
            return parkingList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View rowView = convertView;


            if (rowView == null) {

                LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.item, parent, false);
                // configure view holder
                viewHolder = new ViewHolder();
                viewHolder.bookText = (TextView) rowView.findViewById(R.id.bookText);
                viewHolder.background = (FrameLayout) rowView.findViewById(R.id.background);
                viewHolder.cardImage = (ImageView) rowView.findViewById(R.id.cardImage);
                rowView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.DataText.setText(parkingList.get(position).getDescription() + "");

            Glide.with(YelpSwipe.this).load(parkingList.get(position).getImagePath()).into(viewHolder.cardImage);

            return rowView;
        }
    }
}