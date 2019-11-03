package org.wit.hillfort.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.wit.hillfort.R

import kotlinx.android.synthetic.main.activity_hillfort_maps.*
import kotlinx.android.synthetic.main.content_hillfort_maps.*
import org.wit.hillfort.helpers.readImageFromPath
import org.wit.hillfort.main.MainApp

class HillfortMapsActivity : AppCompatActivity(), GoogleMap.OnMarkerClickListener {

  lateinit var map: GoogleMap
  lateinit var app: MainApp

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    app = application as MainApp
    setContentView(R.layout.activity_hillfort_maps)
    setSupportActionBar(toolbarMaps)
    mapView.onCreate(savedInstanceState)
    mapView.getMapAsync {
      map = it
      configureMap()
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    mapView.onDestroy()
  }

  override fun onLowMemory() {
    super.onLowMemory()
    mapView.onLowMemory()
  }

  override fun onPause() {
    super.onPause()
    mapView.onPause()
  }

  override fun onResume() {
    super.onResume()
    mapView.onResume()
  }

  override fun onSaveInstanceState(outState: Bundle?) {
    super.onSaveInstanceState(outState)
    mapView.onSaveInstanceState(outState)
  }

  override fun onMarkerClick(marker: Marker): Boolean {
    val tag = marker.tag as Long
    val placemark = app.hillforts.findById(tag)
    currentTitle.text = placemark!!.title
    currentDescription.text = placemark!!.description
    imageView.setImageBitmap(readImageFromPath(this@HillfortMapsActivity, placemark.image))
    return true
  }

  fun configureMap() {
    map.uiSettings.setZoomControlsEnabled(true)
    app.hillforts.findAll().forEach {
      val loc = LatLng(it.lat, it.lng)
      val options = MarkerOptions().title(it.title).position(loc)
      map.setOnMarkerClickListener(this)
      map.addMarker(options).tag = it.id
      map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, it.zoom))
    }
  }
}
