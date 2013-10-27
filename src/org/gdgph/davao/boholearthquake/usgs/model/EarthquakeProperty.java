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

package org.gdgph.davao.boholearthquake.usgs.model;

public class EarthquakeProperty {

  private float mag;
  private String place;
  private long time;
  private long updated;
  private int tz;
  private String url;
  private String detail;
  private int felt;
  private float cdi;
  private float mmi;
  private String alert;
  private int tsunami;
  private int sig;
  private String net;
  private String code;
  private String ids;
  private String sources;
  private String types;
  private float dmin;
  private float rms;
  private float gap;
  private String magType;
  private String type;
  private String title;

  public float getMag() {
    return mag;
  }

  public void setMag(float mag) {
    this.mag = mag;
  }

  public String getPlace() {
    return place;
  }

  public void setPlace(String place) {
    this.place = place;
  }

  public long getTime() {
    return time;
  }

  public void setTime(long time) {
    this.time = time;
  }

  public long getUpdated() {
    return updated;
  }

  public void setUpdated(long updated) {
    this.updated = updated;
  }

  public int getTz() {
    return tz;
  }

  public void setTz(int tz) {
    this.tz = tz;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  public int getFelt() {
    return felt;
  }

  public void setFelt(int felt) {
    this.felt = felt;
  }

  public float getCdi() {
    return cdi;
  }

  public void setCdi(float cdi) {
    this.cdi = cdi;
  }

  public float getMmi() {
    return mmi;
  }

  public void setMmi(float mmi) {
    this.mmi = mmi;
  }

  public String getAlert() {
    return alert;
  }

  public void setAlert(String alert) {
    this.alert = alert;
  }

  public int getTsunami() {
    return tsunami;
  }

  public void setTsunami(int tsunami) {
    this.tsunami = tsunami;
  }

  public int getSig() {
    return sig;
  }

  public void setSig(int sig) {
    this.sig = sig;
  }

  public String getNet() {
    return net;
  }

  public void setNet(String net) {
    this.net = net;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getIds() {
    return ids;
  }

  public void setIds(String ids) {
    this.ids = ids;
  }

  public String getSources() {
    return sources;
  }

  public void setSources(String sources) {
    this.sources = sources;
  }

  public String getTypes() {
    return types;
  }

  public void setTypes(String types) {
    this.types = types;
  }

  public float getDmin() {
    return dmin;
  }

  public void setDmin(float dmin) {
    this.dmin = dmin;
  }

  public float getRms() {
    return rms;
  }

  public void setRms(float rms) {
    this.rms = rms;
  }

  public float getGap() {
    return gap;
  }

  public void setGap(float gap) {
    this.gap = gap;
  }

  public String getMagType() {
    return magType;
  }

  public void setMagType(String magType) {
    this.magType = magType;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
