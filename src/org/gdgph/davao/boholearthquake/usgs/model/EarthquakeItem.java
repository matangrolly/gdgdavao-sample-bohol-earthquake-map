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

import com.google.gson.annotations.SerializedName;

public class EarthquakeItem {
  private String type;
  private String id;
  @SerializedName("properties")
  private EarthquakeProperty property;
  private EarthquakeGeometry geometry;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public EarthquakeProperty getProperty() {
    return property;
  }

  public void setProperty(EarthquakeProperty property) {
    this.property = property;
  }

  public EarthquakeGeometry getGeometry() {
    return geometry;
  }

  public void setGeometry(EarthquakeGeometry geometry) {
    this.geometry = geometry;
  }

}
