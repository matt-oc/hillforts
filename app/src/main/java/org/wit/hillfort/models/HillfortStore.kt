package org.wit.hillfort.models

interface HillfortStore {
  fun findAll(): List<HillfortModel>
  fun create(hillfortModel: HillfortModel)
  fun update(hillfort: HillfortModel)
  fun delete(hillfort: HillfortModel)
  fun findById(id:Long) : HillfortModel?
}