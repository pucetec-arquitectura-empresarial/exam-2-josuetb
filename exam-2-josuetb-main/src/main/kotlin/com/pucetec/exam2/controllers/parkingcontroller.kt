
package com.pucetec.exam2.controllers

import com.pucetec.exam2.services.ParkingService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/parking")
class ParkingController(private val parkingService: ParkingService) {

    @GetMapping("/availability")
    fun getAvailability(): Map<String, Any> = parkingService.getAvailability()

    @PostMapping("/entry")
    fun registerEntry(): Any {
        val spot = parkingService.assignParkingSpot()
        return if (spot != null) {
            mapOf("message" to "Vehículo ingresado", "spot" to spot)
        } else {
            mapOf("error" to "No hay plazas disponibles")
        }
    }
}
}




