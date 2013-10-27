/* Sample Earthquake Map App - Bohol Earthquake
 * Copyright (C) 2013  Rolly Rulete
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.gdgph.davao.boholearthquake;

import org.gdgph.davao.boholearthquake.R;
import org.gdgph.davao.boholearthquake.usgs.model.EarthquakeCollection;
import org.gdgph.davao.boholearthquake.usgs.model.EarthquakeItem;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;

public class MainActivity extends FragmentActivity {

  private GoogleMap mMap;

  private static final LatLng MANILA_POSITION = new LatLng(14.583333, 120.966667);
  private static final LatLng CEBU_POSITION = new LatLng(10.316667, 123.75);
  private static final LatLng DAVAO_POSITION = new LatLng(7.064444, 125.607778);
  private static final int DEFAULT_ZOOM = 10;

  private static final double EARTHQUAKE_CENTER_LAT = CEBU_POSITION.latitude; // 9.86;
  private static final double EARTHQUAKE_CENTER_LNG = CEBU_POSITION.longitude; // 127.4;
  private static final double EARTHQUAKE_MAX_RADIUS = 200;
  private static final String EARTHQUAKE_START_DATE = "2013-10-14";

  /* 
   * References:
   *  http://earthquake.usgs.gov/earthquakes/feed/v1.0/geojson.php
   *  http://comcat.cr.usgs.gov/fdsnws/event/1/
   */
  private static final String EARTHQUAKE_USGS_URL = "http://comcat.cr.usgs.gov/fdsnws/event/1/query?format=geojson&starttime="
      + EARTHQUAKE_START_DATE
      + "&latitude="
      + EARTHQUAKE_CENTER_LAT
      + "&longitude="
      + EARTHQUAKE_CENTER_LNG + "&&maxradiuskm=" + EARTHQUAKE_MAX_RADIUS;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    
    // request window feature - progress indicator in actionbar
    requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

    setContentView(R.layout.activity_main);

    initMap();

    new DownloadDataTask().execute();
  }

  private void initMap() {
    if (mMap == null) {
      SupportMapFragment mapFrag = (SupportMapFragment) getSupportFragmentManager()
          .findFragmentById(R.id.map);
      mMap = mapFrag.getMap();

      // enable 'My Location' button in map
      mMap.setMyLocationEnabled(true);

      // set custom info window for markers
      mMap.setInfoWindowAdapter(new CustomWindowAdapter());

      // set default map position and zoom
      CameraUpdate update = CameraUpdateFactory.newLatLngZoom(CEBU_POSITION, DEFAULT_ZOOM);
      mMap.moveCamera(update);
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    switch (item.getItemId()) {
    case R.id.menuMapNormal:
      mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
      break;
    case R.id.menuMapSatellite:
      mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
      break;
    case R.id.menuMapTerrain:
      mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
      break;
    }

    return super.onOptionsItemSelected(item);
  }

  private class DownloadDataTask extends AsyncTask<Void, Void, EarthquakeCollection> {

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      
      // show the progress indicator in actionbar
      setProgressBarIndeterminateVisibility(true);
    }

    @Override
    protected EarthquakeCollection doInBackground(Void... params) {
      EarthquakeCollection earthquakes = null;

      String json = HttpRequest.get(EARTHQUAKE_USGS_URL).body();
      Gson gson = new Gson();
      earthquakes = gson.fromJson(json, EarthquakeCollection.class);

      return earthquakes;
    }

    @Override
    protected void onPostExecute(EarthquakeCollection earthquakes) {
      
      // hide the progress indicator in actionbar
      setProgressBarIndeterminateVisibility(false);

      for (EarthquakeItem earthquake : earthquakes.getItems()) {
        float magnitude = earthquake.getProperty().getMag();
        float markerHue;

        if (magnitude >= 9.0) {
          markerHue = BitmapDescriptorFactory.HUE_RED;
        } else if (magnitude >= 7.0) {
          markerHue = BitmapDescriptorFactory.HUE_YELLOW;
        } else if (magnitude >= 5.0) {
          markerHue = BitmapDescriptorFactory.HUE_GREEN;
        } else {
          markerHue = BitmapDescriptorFactory.HUE_AZURE;
        }

        LatLng position = new LatLng(earthquake.getGeometry().getCoordinates().get(1), earthquake
            .getGeometry().getCoordinates().get(0));
        String snippet = "Time; "
            + DateUtils.formatDateTime(MainActivity.this, earthquake.getProperty().getTime(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_TIME
                    | DateUtils.FORMAT_SHOW_YEAR) + "\nLocation: " + position.latitude + ", "
            + position.longitude + "\nMagnitude: " + magnitude + "M";

        MarkerOptions options = new MarkerOptions().position(position)
            .title(earthquake.getProperty().getTitle())
            .icon(BitmapDescriptorFactory.defaultMarker(markerHue)).snippet(snippet);

        mMap.addMarker(options);
      }

      LatLng southwest = new LatLng(earthquakes.getBbox().get(1), earthquakes.getBbox().get(0));
      LatLng northeast = new LatLng(earthquakes.getBbox().get(4), earthquakes.getBbox().get(3));
      LatLngBounds bounds = new LatLngBounds(southwest, northeast);
      CameraUpdate update = CameraUpdateFactory.newLatLngBounds(bounds, 100);

      // animate map position to boundary box
      mMap.animateCamera(update);

      // demo circle - radius of earthquake scope data
      CircleOptions circle = new CircleOptions().center(mMap.getCameraPosition().target)
          .radius(EARTHQUAKE_MAX_RADIUS * 1000).fillColor(0x330000FF).strokeColor(Color.BLUE);
      mMap.addCircle(circle);

      // demo polyline - connecting Manila, Cebu and Davao points
      PolylineOptions polyline = new PolylineOptions().add(MANILA_POSITION).add(CEBU_POSITION)
          .add(DAVAO_POSITION).color(Color.RED).width(2);
      mMap.addPolyline(polyline);

      LatLng northwest = new LatLng(earthquakes.getBbox().get(1), earthquakes.getBbox().get(3));
      LatLng southeast = new LatLng(earthquakes.getBbox().get(4), earthquakes.getBbox().get(0));
      
      // demo polygon - draw the rectable boundary box
      PolygonOptions polygon = new PolygonOptions().add(northwest).add(northeast).add(southeast)
          .add(southwest).strokeColor(Color.YELLOW).strokeWidth(5);
      mMap.addPolygon(polygon);

    }

  }

  private class CustomWindowAdapter implements InfoWindowAdapter {

    private final View mInfoWindow;

    public CustomWindowAdapter() {
      mInfoWindow = getLayoutInflater().inflate(R.layout.custom_info_window, null);
    }

    @Override
    public View getInfoContents(Marker marker) {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public View getInfoWindow(Marker marker) {
      TextView tvTitle = (TextView) mInfoWindow.findViewById(R.id.tvTitle);
      tvTitle.setText(marker.getTitle());

      TextView tvSnippet = (TextView) mInfoWindow.findViewById(R.id.tvSnippet);
      tvSnippet.setText(marker.getSnippet());

      return mInfoWindow;
    }

  }
}
