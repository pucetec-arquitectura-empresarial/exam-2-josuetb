
package com.pucetec.exam2.controllers

import com.pucetec.exam2.services.ParkingService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/parking")
class ParkingController(private val parkingService: ParkingService) {

    @GetMapping("/availability")
    fun getAvailability(): Map<String, Any> = parkingService.getAvailability()

    @PostMapping("/entry")
    fun registerEntry(): Any {
        return parkingService.assignParkingSpot()
    }

    @ExceptionHandler(com.pucetec.exam2.exceptions.NoAvailableSpotException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun handleNoAvailableSpotException(ex: com.pucetec.exam2.exceptions.NoAvailableSpotException): Map<String, String> =
        mapOf("error" to ex.message.orEmpty())
}
}




